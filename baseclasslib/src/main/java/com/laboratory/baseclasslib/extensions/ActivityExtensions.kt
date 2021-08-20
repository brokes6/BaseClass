package com.laboratory.baseclasslib.extensions

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Build
import android.widget.FrameLayout
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

/**
 * Author: 付鑫博
 * Date: 2021/8/9
 * Description:
 */

/**
 * 直接获取DecorView
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


/**
 * 跳转页面
 *
 * @param T 传入Activity名称即可
 */
inline fun <reified T : Activity> Activity.goActivity() {
    startActivity(Intent(this, T::class.java))
}

/**
 * 跳转页面并关闭自己
 *
 * @param T 传入Activity名称即可
 */
inline fun <reified T : Activity> Activity.startAndFinishActivity() {
    startActivity(Intent(this, T::class.java))
    finish()
}

/**
 * 获取颜色
 *
 * @param colorId 颜色资源ID
 * @return
 */
fun Activity.color(@ColorRes colorId: Int): Int {
    return ContextCompat.getColor(this, colorId)
}

/**
 * 获取Drawable
 *
 * @param redId 资源ID
 * @return
 */
fun Activity.drawable(@DrawableRes redId: Int): Drawable? {
    return ContextCompat.getDrawable(this, redId)
}