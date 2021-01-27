
fun main(args: Array<String>) {
    val executor = Executor()
    executor.outPutListener = object : Executor.OutputListener {
        override fun handleOutputLine(output: String) {
            println("Tag: $output")
        }

        override fun handleOut(output: String) {
            println("Tag all : $output")
        }
    }

    executor.getDevices()
    executor.tapOnScreen(200f,200f)
}