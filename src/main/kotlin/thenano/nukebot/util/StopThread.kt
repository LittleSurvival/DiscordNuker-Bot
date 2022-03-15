package thenano.nukebot.util

import net.dv8tion.jda.api.JDA
import thenano.nukebot.bot.command.CommandManager

internal class StopThread(jda: JDA) : Runnable {

    val j = jda
    override fun run() {
        while (true) {
            if (CommandManager.stop) {
                CommandManager.stop = false
            }
        }
    }
}