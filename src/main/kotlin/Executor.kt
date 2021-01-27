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

    override fun mirrorScreen() {
        TODO("Not yet implemented")
    }

    override fun killAdbServer() {
        TODO("Not yet implemented")
    }

    override fun startServer() {
        TODO("Not yet implemented")
    }

    override fun getDevices(isProductModelNeeded: Boolean) {
        executeCommand("adb devices")
    }

    override fun tapOnScreen(x: Float, y: Float) {
        executeCommand("adb shell input tap $x $y")
    }

    override fun rebootDevice(isRecovery: Boolean) {
        TODO("Not yet implemented")
    }

    override fun rebootDeviceBootloader() {
        TODO("Not yet implemented")
    }

    override fun root() {
        TODO("Not yet implemented")
    }

    override fun connectToIP(ip: String) {
        TODO("Not yet implemented")
    }

    override fun homeButton() {
        TODO("Not yet implemented")
    }

    override fun inputTextOnSelectField(text: String) {
        TODO("Not yet implemented")
    }

    override fun screenShot() {
        TODO("Not yet implemented")
    }

    override fun applyKey(key: String) {
        TODO("Not yet implemented")
    }

    override fun getHeightAndWidth() {
        TODO("Not yet implemented")
    }
}
