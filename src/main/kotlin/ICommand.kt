
/*
== Files
adb push [source] [destination]    // Copy files from your computer to your phone.
adb pull [device file location] [local file location] // Copy files from your phone to your computer.

== App install
adb -e install path/to/app.apk

-d                        - directs command to the only connected USB device...
-e                        - directs command to the only running emulator...
-s <serial number>        ...
-p <product name or path> ...
The flag you decide to use has to come before the actual adb command:

adb devices | tail -n +2 | cut -sf 1 | xargs -IX adb -s X install -r com.myAppPackage // Install the given app on all connected devices.

== Uninstalling app from device
adb uninstall com.myAppPackage
adb uninstall <app .apk name>
adb uninstall -k <app .apk name> -> "Uninstall .apk withour deleting data"

adb shell pm uninstall com.example.MyApp
adb shell pm clear [package] // Deletes all data associated with a package.

adb devices | tail -n +2 | cut -sf 1 | xargs -IX adb -s X uninstall com.myAppPackage //Uninstall the given app from all connected devices

== Update app
adb install -r yourApp.apk  //  -r means re-install the app and keep its data on the device.
adb install –k <.apk file path on computer>
== Get device android version
adb shell getprop ro.build.version.release
 */

interface ICommand {
    fun mirrorScreen()
    fun killAdbServer()
    fun startServer()
    fun getDevices(isProductModelNeeded: Boolean = false)
    fun tapOnScreen(x: Float, y: Float)
    fun rebootDevice(isRecovery: Boolean = false)
    fun rebootDeviceBootloader()
    fun root()
    fun connectToIP(ip: String)
    fun homeButton()
    fun inputTextOnSelectField(text: String)
    fun screenShot(filePath:String)
    fun applyKey(key: Int)
    fun getHeightAndWidth()
}
/**
adb help // List all comands

== Adb Server
adb kill-server
adb start-server

== Adb Reboot
adb reboot
adb reboot recovery
adb reboot-bootloader
adb root //restarts adb with root permissions

== Shell
adb shell    // Open or run commands in a terminal on the host Android device.

== Devices
adb usb
adb devices   //show devices attached
adb devices -l //devices (product/model)
adb connect ip_address_of_device

== Home button
adb shell am start -W -c android.intent.category.HOME -a android.intent.action.MAIN





// Emulate device
adb shell wm size 2048x1536
adb shell wm density 288
// And reset to default
adb shell wm size reset
adb shell wm density reset

== Print text
adb shell input text 'Wow, it so cool feature'

== Screenshot
adb shell screencap -p /sdcard/screenshot.png

$ adb shell
shell@ $ screencap /sdcard/screen.png
shell@ $ exit
$ adb pull /sdcard/screen.png

---
adb shell screenrecord /sdcard/NotAbleToLogin.mp4

$ adb shell
shell@ $ screenrecord --verbose /sdcard/demo.mp4
(press Control + C to stop)
shell@ $ exit
$ adb pull /sdcard/demo.mp4

== Key event
adb shell input keyevent 3 // Home btn
adb shell input keyevent 4 // Back btn
adb shell input keyevent 5 // Call
adb shell input keyevent 6 // End call
adb shell input keyevent 26  // Turn Android device ON and OFF. It will toggle device to on/off status.
adb shell input keyevent 27 // Camera
adb shell input keyevent 64 // Open browser
adb shell input keyevent 66 // Enter
adb shell input keyevent 67 // Delete (backspace)

//TODO
adb shell input keyevent 207 // Contacts
adb shell input keyevent 220 / 221 // Brightness down/up
adb shell input keyevent 277 / 278 /279 // Cut/Copy/Paste


== Device onformation
adb get-statе (print device state)
adb get-serialno (get the serial number)
adb shell dumpsys iphonesybinfo (get the IMEI)
adb shell netstat (list TCP connectivity)
adb shell pwd (print current working directory)
adb shell dumpsys battery (battery status)
adb shell pm list features (list phone features)
adb shell service list (list all services)
adb shell dumpsys activity <package>/<activity> (activity info)
adb shell ps (print process status)
adb shell wm size (displays the current screen resolution)
dumpsys window windows | grep -E 'mCurrentFocus|mFocusedApp' (print current app's opened activity)



== Device Related Commands
adb reboot-recovery (reboot device into recovery mode)
adb reboot fastboot (reboot device into recovery mode)
adb shell screencap -p "/path/to/screenshot.png" (capture screenshot)
adb shell screenrecord "/path/to/record.mp4" (record device screen)
adb backup -apk -all -f backup.ab (backup settings and apps)
adb backup -apk -shared -all -f backup.ab (backup settings, apps and shared storage)
adb backup -apk -nosystem -all -f backup.ab (backup only non-system apps)
adb restore backup.ab (restore a previous backup)
adb shell am start|startservice|broadcast <INTENT>[<COMPONENT>]
-a <ACTION> e.g. android.intent.action.VIEW
-c <CATEGORY> e.g. android.intent.category.LAUNCHER (start activity intent)

adb shell am start -a android.intent.action.VIEW -d URL (open URL)
adb shell am start -t image/* -a android.intent.action.VIEW (opens gallery)

=
===============================================================

 **/