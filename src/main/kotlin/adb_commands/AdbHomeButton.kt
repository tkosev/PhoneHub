package adb_commands

import adb_commands.base.BaseAdbCommand
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AdbHomeButton : BaseAdbCommand<Unit>() {

    override val command: String
        get() = "adb shell am start -W -c android.intent.category.HOME -a android.intent.action.MAIN"

    override suspend fun execute(): Unit {
        return withContext(Dispatchers.IO) {
            start()
        }
    }

    private fun start() {
        builder.command("cmd.exe", "/c", command)
        process = builder.start()
    }
}