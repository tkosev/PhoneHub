import AndroidKeys.KEYCODE_CALL

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

    executor.getDevices()
    executor.tapOnScreen(200f, 200f)
    executor.applyKey(KEYCODE_CALL)
}