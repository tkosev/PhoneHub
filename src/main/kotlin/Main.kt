import adb_commands.AdbGetDevices
import kotlinx.coroutines.*

fun main() {
    runBlocking {
        val executor = Executor()
        val one = executor.executeADBCommand(AdbGetDevices())
        println("Executor in $one ")
    }
}

