import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(args: Array<String>) {
    val executor = Executor()
    executor.outPutListener = object : Executor.OutputListener {
        override fun handleOutputLine(output: String) {
            println("Tag: $output")
        }

        override fun handle(output: String) {
            println("Tag all : $output")
        }

        override fun onErrorOutput(error: String) {
            println("Tag error : $error")
        }
    }

    GlobalScope.launch {
        delay(7000)
        executor.stopProcess()
    }
    executor.mirrorScreen()

}