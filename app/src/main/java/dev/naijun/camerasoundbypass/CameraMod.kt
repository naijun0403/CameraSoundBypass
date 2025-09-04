package dev.naijun.camerasoundbypass

import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage

class CameraMod {

    fun init(lpparam: XC_LoadPackage.LoadPackageParam) {
        val audioServiceClass = XposedHelpers.findClass(
            "com.android.server.audio.AudioService",
            lpparam.classLoader
        )

        XposedHelpers.findAndHookMethod(
            audioServiceClass,
            "readCameraSoundForced",
            object : XC_MethodHook() {
                override fun beforeHookedMethod(param: MethodHookParam) {
                    param.setResult(false)
                    XposedBridge.log("[CameraSoundBypass] readCameraSoundForced() hooked - returning false")
                }
            }
        )

        XposedBridge.log("[CameraSoundBypass] Successfully hooked AudioService.readCameraSoundForced()")
    }

}
