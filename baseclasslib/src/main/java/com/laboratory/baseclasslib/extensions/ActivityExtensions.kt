package com.laboratory.baseclasslib.extensions

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.widget.FrameLayout

/**
 * Author: 付鑫博
 * Version: 1.14.0
 * Date: 2021/8/9
 * Mender:
 * Modify:
 * Description:
 */

val Activity.decorView: FrameLayout?
    get() = (takeIf {
        // 为了处理在api小于17的时候isDestroyed无法使用的情况
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            !isFinishing && !isDestroyed
        } else {
            !isFinishing
        }
    }?.window?.decorView) as? FrameLayout


inline fun <reified T : Activity> Activity.goActivity() {
    startActivity(Intent(this, T::class.java))
}

inline fun <reified T : Activity> Activity.startAndFinishActivity() {
    startActivity(Intent(this, T::class.java))
    finish()
}