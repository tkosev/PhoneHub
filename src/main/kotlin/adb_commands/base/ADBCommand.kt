package adb_commands.base

interface ADBCommand<T> {

    val command: String

    suspend fun execute(): T
}


