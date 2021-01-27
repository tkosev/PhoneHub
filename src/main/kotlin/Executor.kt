import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder

class Executor : CommandExecutor, ICommand {
    private lateinit var process: Process

    lateinit var outPutListener: OutputListener

    private val builder: ProcessBuilder by lazy {
        ProcessBuilder().apply { redirectErrorStream(true) }
    }

    override fun executeCommand(command: String) {
        builder.command("cmd.exe", "/c", command)
        process = builder.start()
        val r = BufferedReader(InputStreamReader(process.inputStream))
        var line: String?
        val appendedLine = StringBuilder()
        while (true) {
            line = r.readLine()
            if (line == null) {
                break
            }
            appendedLine.append(line)
            outPutListener.handleOutputLine(line)
        }
        outPutListener.handleOut(appendedLine.toString())
    }

    override fun stopProcess() {
        if (this::process.isInitialized) {
            process.destroy()
        }
    }

    interface OutputListener {
        fun handleOutputLine(output: String)

        fun handleOut(output: String)
    }

    override fun killAdbServer() {
        TODO("Not yet implemented")
    }

    override fun startServer() {
        TODO("Not yet implemented")
    }

    override fun getDevices() {
        executeCommand("adb devices")
    }

    override fun tapOnScreen(x: Float, y: Float) {
        executeCommand("adb shell input tap $x $y")
    }
}
