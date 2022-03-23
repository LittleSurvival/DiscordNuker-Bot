package thenano.nukebot.bot.command.commands

import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import thenano.nukebot.bot.command.CommandListener
import thenano.nukebot.bot.command.CommandManager

@CommandListener.Info("!stop","Shutdown the bot",false)
class Stop : CommandListener() {

    @Throws(RuntimeException::class)
    override fun onEvent(event: MessageReceivedEvent) {
        if (messageRaw.startsWith("Stop")) {
            CommandManager.stop = true
        }
    }
}