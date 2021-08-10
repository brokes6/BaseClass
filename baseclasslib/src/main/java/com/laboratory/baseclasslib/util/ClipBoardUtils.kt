package com.laboratory.baseclasslib.util

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Build
import android.text.TextUtils

/**
 * Author: 付鑫博
 * Version: 1.13.0
 * Date: 2021/7/26
 * Mender:
 * Modify:
 * Description: 剪切板读写工具
 */
object ClipBoardUtils {

    /**
     * 获取剪切板内容
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