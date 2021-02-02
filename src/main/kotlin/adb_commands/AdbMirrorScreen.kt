package adb_commands

import adb_commands.base.BaseAdbCommand
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AdbMirrorScreen : BaseAdbCommand<String>() {

    override val command: String
        get() = "adb exec-out screenrecord --output-format=h264 --size 540x960 -"

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