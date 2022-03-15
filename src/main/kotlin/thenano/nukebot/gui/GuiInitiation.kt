package thenano.nukebot.gui

import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField

open class GuiInitiation {

    companion object {
        var frame = JFrame()

        var panel = JPanel()
        var panel_left = JPanel()
        var panel_right = JPanel()

        var console = JLabel()
        var textField = JTextField()

        var botToken = JTextField()
        var botToken_l = JLabel()


    }
}