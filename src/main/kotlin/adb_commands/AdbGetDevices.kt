package adb_commands

import adb_commands.base.BaseAdbCommand
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import models.Device
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder

class AdbGetDevices : BaseAdbCommand<List<Device>>() {

    override val command: String
        get() = "adb devices -l"

    override fun handleOutput(): List<Device> {
        val devices = mutableListOf<Device>()
        val bufferedReader = BufferedReader(InputStreamReader(process.inputStream))
        //val stdError = BufferedReader(InputStreamReader(process.errorStream))
        var line: String?
        val appendedLine = StringBuilder()
        val linesList = mutableListOf<String>()
        while (bufferedReader.readLine().also { line = it } != null) {
            line?.let { linesList.add(it) }
        }

        for ((i, v) in linesList.withIndex()) {
            if (i != 0) {
                println(v)
                val deviceListParameters = v.split("      ")
                val serialNumber = deviceListParameters[0]

                val deviceProductList = deviceListParameters[1].split(" ").toHashSet()
                for ((index, value) in linesList.withIndex()) {
                    if(index !=0){
                        val par = v.split(":")
                    }
                }

            }
        }
        val raw = appendedLine.toString().replace("      ", " ")
        val listRaw = raw.split(" ")

        return devices
    }
}