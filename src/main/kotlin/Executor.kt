import adb_commands.base.BaseAdbCommand

class Executor : CommandExecutor {

    override suspend fun <T> executeADBCommand(adbCommand: BaseAdbCommand<T>): T = adbCommand.execute()

//    override fun stopProcess() {
//        if (this::process.isInitialized) {
//            executeCommand("Taskkill /F /IM adb.exe")
//        }
//    }

}