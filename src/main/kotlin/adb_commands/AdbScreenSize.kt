package adb_commands

import adb_commands.base.BaseAdbCommand
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AdbScreenSize : BaseAdbCommand<String>() {

    override val command: String
        get() = "adb shell wm size"

    override suspend fun execute(): String {
        return withContext(Dispatchers.IO) {
            start()
            handleOutput()
        }
    }

    private fun start() {
        builder.command("cmd.exe", "/c", command)
        process = builder.start()

    }
}