package adb_commands

import adb_commands.base.BaseAdbCommand
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AdbRebootDevice(private val isRecovery: Boolean) : BaseAdbCommand<Unit>() {

    override val command: String
        get() = if (isRecovery) "adb reboot recovery" else "adb reboot"

    override suspend fun execute() {
        return withContext(Dispatchers.IO) {
            start()
        }
    }

    private fun start() {
        builder.command("cmd.exe", "/c", command)
        process = builder.start()
    }
}