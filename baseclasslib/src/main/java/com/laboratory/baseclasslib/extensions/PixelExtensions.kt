package com.laboratory.baseclasslib.extensions

import android.content.Context

/**
 * Author: 付鑫博
 * Version: 1.14.0
 * Date: 2021/8/9
 * Mender:
 * Modify:
 * Description:
 */
fun Int.dp(context: Context, pxVal: Float): Float {
    val scale: Float = context.resources.displayMetrics.density
    return pxVal / scale
}

fun Int.sp(context: Context, pxVal: Float): Float {
    return (pxVal / context.resources.displayMetrics.scaledDensity)
}

fun Int.dpToPx(context: Context, dpValue: Float): Float {
    val scale = context.resources.displayMetrics.density
    return ((dpValue * scale + 0.5f).toInt()).toFloat()
}