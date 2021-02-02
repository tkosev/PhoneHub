package adb_commands.base

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder

abstract class BaseAdbCommand<T> : ADBCommand<T> {

    protected lateinit var process: Process

    protected val builder: ProcessBuilder by lazy {
        ProcessBuilder().apply { redirectErrorStream(true) }
    }

    protected fun handleOutput(): String {
        val bufferedReader = BufferedReader(InputStreamReader(process.inputStream))
        //val stdError = BufferedReader(InputStreamReader(process.errorStream))
        var line: String?
        val appendedLine = StringBuilder()
        while (bufferedReader.readLine().also { line = it } != null) {
            appendedLine.append(line)
        }
        //TODO handle errors:
        //while (stdError.readLine().also { line = it } != null) {
        //}
        return appendedLine.toString()
    }

}