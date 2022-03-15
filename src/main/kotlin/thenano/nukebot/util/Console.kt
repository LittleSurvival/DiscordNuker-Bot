package thenano.nukebot.util

internal object Console {
    var console : MutableList<String>

    init {
        console = mutableListOf()
    }

    fun addMessage(inp:String) {
        console.add(inp + "\n")
    }

    fun deleteLine() {
        console.removeAt(console.size)
    }
}