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
        val bufferedReader = BufferedReader(InputStreamReader(process.inputStream))
        val stdError = BufferedReader(InputStreamReader(process.errorStream))
        var line: String?
        val appendedLine = StringBuilder()
        while (bufferedReader.readLine().also { line = it } != null) {
            appendedLine.append(line)
            line?.let { outPutListener.handleOutputLine(it) }
        }
        while (stdError.readLine().also { line = it } != null) {
            line?.let { outPutListener.onErrorOutput(it) }
        }
    }

    override fun stopProcess() {
        if (this::process.isInitialized) {
            process.destroy()
        }
    }

    interface OutputListener {
        fun handleOutputLine(output: String)
        fun handle(output: String)
        fun onErrorOutput(error: String)
    }

    override fun mirrorScreen() {
        TODO("Not yet implemented")
    }

    override fun killAdbServer() {
        executeCommand("adb kill-server")
    }

    override fun startServer() {
        executeCommand("adb start-server")
    }

    override fun getDevices(isProductModelNeeded: Boolean) {

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

    override fun applyKey(key: Int) {
        executeCommand("adb shell input keyevent $key")
    }

    override fun getHeightAndWidth() {
        executeCommand(" adb shell wm size")
    }
}
