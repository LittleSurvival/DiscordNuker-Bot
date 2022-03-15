package thenano.nukebot.util

object Timer {

    private var time = -1L

    fun passedMs(ms: Int): Boolean {
        return passedNS(convertToNS(ms))
    }

    private fun passedNS(ns: Long): Boolean {
        return System.nanoTime() - time >= ns
    }

    fun reset(): Timer {
        time = System.nanoTime()
        return this
    }

    private fun convertToNS(time: Int): Long {
        return time * 1000000L
    }
}