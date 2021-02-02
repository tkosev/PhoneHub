import adb_commands.base.BaseAdbCommand

interface CommandExecutor {
    suspend  fun<T> executeADBCommand(adbCommand: BaseAdbCommand<T>) : T
}