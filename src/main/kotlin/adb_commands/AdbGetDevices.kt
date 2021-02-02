package adb_commands

import adb_commands.base.BaseAdbCommand
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder

class GetDevicesCommand : BaseAdbCommand<String>() {

    override val command: String
    get() = "adb devices"

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