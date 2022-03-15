package thenano.nukebot.gui.listeners

import thenano.nukebot.gui.GuiInitiation
import thenano.nukebot.util.Console

object consoleListener : GuiInitiation(),Runnable {

    override fun run() {
        while(true) {
//            var list:String = "";
//            Console.console.forEach {
//                s ->
//                list+=s
//            }
//            console.text = list

            console.text = "gay\n gaygay \naaa"
        }
    }
}