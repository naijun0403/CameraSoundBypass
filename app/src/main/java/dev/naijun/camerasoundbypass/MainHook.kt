package dev.naijun.camerasoundbypass

import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.IXposedHookZygoteInit
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.callbacks.XC_LoadPackage

class MainHook : IXposedHookLoadPackage {

    companion object {
        const val TAG = "CameraSoundBypass"
        const val SYSTEM_FRAMEWORK = "android"
    }

    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        if (lpparam.packageName != SYSTEM_FRAMEWORK) return

        XposedBridge.log("$TAG: Hooking System Framework")
        CameraMod().init(lpparam)
    }
}
