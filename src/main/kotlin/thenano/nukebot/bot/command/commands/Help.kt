package thenano.nukebot.bot.command.commands

import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.exceptions.PermissionException
import thenano.nukebot.bot.command.CommandListener
import thenano.nukebot.bot.command.CommandManager
import java.awt.Color

@CommandListener.Info("!help","Post the list of commands",false)
class Help : CommandListener() {

    @Throws(RuntimeException::class)
    override fun onMessageReceived(event: MessageReceivedEvent) {
        if (onInit(event)) return

        if (messageRaw.startsWith("!help")) {
            try {
                val eb = EmbedBuilder()
                eb.setColor(Color(255,255,255))
                eb.setAuthor("Author : TheNano#0001")
                eb.setTitle("我好大 v0.1")
//                eb.addField("!ban","Ban All gay in this server",false)
//                eb.addField("!dc","Delete All gay channel in this server",false)
//                eb.addField("!dr","Delete All gay role in this server",false)
//                eb.addField("!mk","Kick All gay in this server",false)
//                eb.addField("!mt","TimeOut all gay in this server",false)
//                eb.addField("!sc <tag(true/false) <channelName> <contents>","Create lots of channel and tag all gay in this server",false)
//                eb.addField("!sr <roleName>","Create lots of role and tag all gay in this server",false)
                CommandManager.listeners.stream().forEach { i ->
                    try {
                        eb.addField(i.getAnnotation()?.name!!,i.getAnnotation()?.description!!,i.getAnnotation()?.inline!!)
                    } catch (e:Exception) {
                        e.stackTrace
                    }
                }
                channel.sendMessageEmbeds(eb.build()).queue()
            } catch (e:PermissionException) {
                e.printStackTrace()
            }
        }
    }
}