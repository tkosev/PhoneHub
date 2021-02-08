package adb_commands.base

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BaseAdbCommand<T> : ADBCommand<T> {

    protected lateinit var process: Process

    protected val builder: ProcessBuilder by lazy {
        ProcessBuilder().apply { redirectErrorStream(true) }
    }

    override suspend fun execute(): T {
        return withContext(Dispatchers.IO) {
            start()
            handleOutput()
        }
    }

    private fun start() {
        builder.command("cmd.exe", "/c", command)
        process = builder.start()
    }
    protected abstract fun handleOutput(): T

}