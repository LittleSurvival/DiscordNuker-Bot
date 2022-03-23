package thenano.nukebot.bot.command

import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.entities.Member
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.entities.MessageChannel
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import kotlin.properties.Delegates

open class CommandListener : ListenerAdapter() {

    lateinit var name: String
    lateinit var description: String
    var inline by Delegates.notNull<Boolean>()

    init {
        CommandManager.listeners.add(this)
        try {
            name = getAnnotation()?.name!!
            description = getAnnotation()?.description!!
            inline = getAnnotation()?.inline!!
        } catch (_:NullPointerException) {}
    }

    companion object {
        lateinit var channel: MessageChannel
        lateinit var message: Message
        lateinit var guild: Guild
        lateinit var members: Array<Member>
        lateinit var messageRaw : String
        lateinit var self: Member

        fun onInit(event: MessageReceivedEvent):Boolean {
            channel = event.channel
            message = event.message
            messageRaw = event.message.contentRaw
            guild = event.guild
            members = event.guild.members.toTypedArray()
            self = event.guild.selfMember

            return (event.message.author == event.guild.selfMember || event.message.author.id != "464658705669947394")
        }
    }

    @kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
    annotation class Info(val name: String, val description: String, val inline: Boolean) {
    }

    fun getAnnotation(): Info? {
        if (javaClass.isAnnotationPresent(Info::class.java)) {
            return javaClass.getAnnotation(Info::class.java)
        }
        return null
    }

    @Throws(RuntimeException::class)
    open fun onEvent(event:MessageReceivedEvent) {
        if (onInit(event)) return
    }

    override fun onMessageReceived(event: MessageReceivedEvent) {
        CommandManager.listeners.stream().forEach { i ->
            onEvent(event)
        }
    }
}