import adb_commands.AdbScreenSize
import adb_commands.GetDevicesCommand
import kotlinx.coroutines.*

fun main() {
    runBlocking {
        val executor = Executor()
        val one = executor.executeADBCommand(GetDevicesCommand())
        val two = executor.executeADBCommand(AdbScreenSize())
        println("Executor in $one  $two ")
    }
}

