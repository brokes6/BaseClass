package com.laboratory.baseclasslib.util

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Build
import android.text.TextUtils

/**
 * Author: 付鑫博
 * Date: 2021/7/26
 * Description: 剪切板读写工具
 */
object ClipBoardUtils {

    /**
     * 获取剪切板内容
     *
     * @param context 上下文
     * @return 获取到的剪切板第一条内容
     */
    fun getClipboardData(context: Context): String? {
        val manager: ClipboardManager? = context.getSystemService(Context.CLIPBOARD_SERVICE) as? ClipboardManager
        manager?.let {
            // 判断剪切板中的条目数量是否为0
            if (manager.hasPrimaryClip() && manager.primaryClip?.itemCount ?: 0 > 0) {
                // 只获取剪切板中的第一条
                if (!TextUtils.isEmpty(it.primaryClip?.getItemAt(0)?.text?.toString())) {
                    return it.primaryClip?.getItemAt(0)?.text?.toString()
                }
            }
        }
        return null
    }

    /**
     * 清除剪切板
     *
     * @param context 上下文
     */
    fun clearClipboard(context: Context) {
        val manager: ClipboardManager? = context.getSystemService(Context.CLIPBOARD_SERVICE) as? ClipboardManager
        manager?.let {
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    it.clearPrimaryClip()
                } else {
                    it.setPrimaryClip(ClipData.newPlainText(null, null))
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}