package thenano.nukebot

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.requests.GatewayIntent
import net.dv8tion.jda.api.utils.ChunkingFilter
import net.dv8tion.jda.api.utils.MemberCachePolicy
import thenano.nukebot.bot.command.CommandListener
import thenano.nukebot.bot.command.CommandManager
import javax.security.auth.login.LoginException

internal class BotLoader {

    companion object {
        @JvmStatic
        @Throws(Exception::class)
        fun main(args : Array<String>) {

            try {
                val jda: JDA = JDABuilder
                    .createDefault("OTU1ODUwMDQyMTU2MTU0OTIw.YjnquA.QMLTPmONwwB3pAbdVZVSRc_51B8")
                    .setChunkingFilter(ChunkingFilter.ALL)
                    .setMemberCachePolicy(MemberCachePolicy.ALL)
                    .enableIntents(GatewayIntent.GUILD_MEMBERS)
                    .build()
                CommandManager.onInitListeners(jda)
//                val stopDetect = Thread(StopThread(jda))
//                stopDetect.start()

                jda.awaitReady()

                //CommandManager.onInitListeners(jda, listeners)
                println("Finished Building JDA!")
            } catch (e: LoginException) {
                e.printStackTrace()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

            val commandListener = CommandListener()
        }
    }
}