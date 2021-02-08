package adb_commands

import adb_commands.base.BaseAdbCommand
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AdbScreenShot(private val filePath: String): BaseAdbCommand<Unit>() {

    override val command: String
        get() = "adb shell screencap -p $filePath"

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