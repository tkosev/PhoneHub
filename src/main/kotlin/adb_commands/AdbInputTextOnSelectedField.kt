package adb_commands

import adb_commands.base.BaseAdbCommand
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AdbInputTextOnSelectedField(private val text: String) : BaseAdbCommand<Unit>() {

    override val command: String
        get() = "adb shell input text $text"

    override suspend fun execute() {
        return withContext(Dispatchers.IO) {
            start()
        }
    }

    private fun start() {
        builder.command("cmd.exe", "/c", command)
        process = builder.start()
    }

    override fun handleOutput() {
        TODO("Not yet implemented")
    }
}