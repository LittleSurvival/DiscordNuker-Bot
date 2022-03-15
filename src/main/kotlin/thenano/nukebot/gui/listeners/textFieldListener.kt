package thenano.nukebot.gui.listeners

import thenano.nukebot.gui.GuiInitiation
import thenano.nukebot.util.Console
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import java.awt.event.KeyListener

class textFieldListener : GuiInitiation(),KeyListener {

    override fun keyTyped(e: KeyEvent?) {

    }

    override fun keyPressed(e: KeyEvent?) {
        if (e?.keyCode == KeyEvent.VK_ENTER) {
            Console.addMessage(textField.text)
            textField.text = ""
        }
    }

    override fun keyReleased(e: KeyEvent?) {
    }

}