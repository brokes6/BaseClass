package com.laboratory.baseclasslib.extensions

import android.content.Context
import android.view.View
import android.view.ViewGroup

/**
 * Author: 付鑫博
 * Version: 1.14.0
 * Date: 2021/8/9
 * Mender:
 * Modify:
 * Description:
 */

/**
 * 设置控件Margins
 * @param l 左边距
 * @param t 上边距
 * @param r 右边距
 * @param b 下边距
 */
fun View.setMargins(l: Int, t: Int, r: Int, b: Int) {
    if (this.layoutParams is ViewGroup.MarginLayoutParams) {
        (this.layoutParams as ViewGroup.MarginLayoutParams).setMargins(l, t, r, b)
        this.requestLayout()
    }
}

inline fun Context.View(init: View.() -> Unit) =
    View(this).apply(init)

inline var View.layout_id: String
    get() {
        return ""
    }
    set(value) {
        id = value.toLayoutId()
    }
inline var View.layout_width: Int
    get() {
        return 0
    }
    set(value) {}

inline var View.layout_height: Int
    get() {
        return 0
    }
    set(value) {}

inline var View.background_color: Int
    get() {
        return 0
    }
    set(value) {
        setBackgroundColor(value)
    }

inline var View.layout_visibility: Int
    get() {
        return -1
    }
    set(value) {
        visibility = value
    }

fun String.toLayoutId(): Int {
    var id = java.lang.String(this).bytes.sum()
    if (id == 48) id = 0
    return id
}

fun <T : View> View.find(id: String): T? = findViewById(id.toLayoutId())