package thenano.nukebot.gui

import thenano.nukebot.gui.listeners.consoleListener
import thenano.nukebot.gui.listeners.textFieldListener
import java.awt.Color
import java.awt.Dimension
import java.awt.Toolkit
import javax.swing.BorderFactory
import javax.swing.JFrame


internal class Gui : GuiInitiation() {

    companion object {
        @JvmStatic
        @Throws(Exception::class)
        fun main(args:Array<String>) {
            val gui = Gui()
        }
    }


    init {
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.isVisible = true
        frame.isResizable = false
        frame.title = "Discord Server Nuker - TheNano#0001"
        frame.setSize(420,420)
        val screenSize: Dimension = Toolkit.getDefaultToolkit().getScreenSize()
        frame.setBounds(screenSize.getWidth().toInt() - frame.width, 0, frame.width, frame.height)

        frame.add(panel)

        panel_left.setBounds(0,0,200,400)
        panel_right.setBounds(200,0,200,400)

        panel_right.isVisible = true
        panel_left.isVisible = true
        panel_left.background = Color.GRAY
        panel_right.background = Color.GRAY
        panel_right.border = BorderFactory.createBevelBorder(0)
        panel_right.layout = null
        panel_left.layout = null
        panel.background = Color.GRAY
        panel.isVisible = true
        panel.layout = null
        panel.add(panel_left)
        panel.add(panel_right)

        console.setBounds(10,-170,180,400)
        console.background = Color.DARK_GRAY
        console.text = "Console"
        console.toolTipText = "Command log here"
        val consoleThread = Thread(consoleListener)
        consoleThread.start()

        textField.setBounds(10,340,180,30)
        textField.toolTipText = "Enter Command Here"
        textField.addKeyListener(textFieldListener())

        panel_left.add(console)
        panel_left.add(textField)

        botToken.setBounds(10,40,180,20)
        botToken_l.setBounds(10,10,60,20)
        botToken_l.text = "BotToken"

        panel_right.add(botToken)
        panel_right.add(botToken_l)
    }
}