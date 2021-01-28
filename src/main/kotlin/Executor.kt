import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder

class Executor : CommandExecutor, ICommand {
    private lateinit var process: Process

    var outPutListener: OutputListener? = null

    private val builder: ProcessBuilder by lazy {
        ProcessBuilder().apply { redirectErrorStream(true) }
    }

    override fun executeCommand(command: String) {
        builder.command("cmd.exe", "/c", command)
        process = builder.start()
        handleOutput()
    }

    private fun handleOutput() {
        val bufferedReader = BufferedReader(InputStreamReader(process.inputStream))
        val stdError = BufferedReader(InputStreamReader(process.errorStream))
        var line: String?
        val appendedLine = StringBuilder()
        while (bufferedReader.readLine().also { line = it } != null) {
            appendedLine.append(line)
            line?.let { outPutListener?.handleOutputLine(it) }
        }
        while (stdError.readLine().also { line = it } != null) {
            line?.let { outPutListener?.onErrorOutput(it) }
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

    override fun mirrorScreen() = executeCommand("adb exec-out screenrecord --output-format=h264 --size 540x960 -")

    override fun killAdbServer() = executeCommand("adb kill-server")

    override fun startServer() = executeCommand("adb start-server")

    override fun getDevices(isProductModelNeeded: Boolean) = executeCommand("adb devices")

    override fun tapOnScreen(x: Float, y: Float) = executeCommand("adb shell input tap $x $y")

    override fun rebootDevice(isRecovery: Boolean) = when {
        isRecovery -> {
            executeCommand("adb reboot recovery")
        }
        else -> {
            executeCommand("adb reboot")
        }
    }

    override fun rebootDeviceBootloader() = executeCommand("reboot-bootloader")

    override fun root() = executeCommand("adb root")

    override fun connectToIP(ip: String) = executeCommand("adb connect $ip")

    override fun homeButton() = executeCommand("adb shell am start -W -c android.intent.category.HOME -a android.intent.action.MAIN")

    override fun inputTextOnSelectField(text: String) = executeCommand("adb shell input text $text")

    override fun screenShot(filePath: String) = executeCommand("adb shell screencap -p $filePath")

    override fun applyKey(key: Int) = executeCommand("adb shell input keyevent $key")

    override fun getHeightAndWidth() = executeCommand(" adb shell wm size")
}
