package com.laboratory.baseclasslib.util

import android.graphics.Color
import android.text.TextUtils

/**
 * Author: xx
 * Version: 1.0
 * Date: 2020/10/27
 * Mender:
 * Modify:
 * Description: 颜色相关工具类
 */
object ColorUtils {

    /**
     * 解析颜色
     *
     * @param colorStr 颜色值字符串，需用这种格式："#FFF6BA29"、"#F6BA29"
     * @return
     */
    @JvmStatic
    fun parseColor(colorStr: String?): Int {
        if (!TextUtils.isEmpty(colorStr)) {
            try {
                return Color.parseColor(colorStr)
            } catch (e: IllegalArgumentException) {
                e.printStackTrace()
            }
        }
        return 0
    }
}