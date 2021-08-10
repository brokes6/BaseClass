package com.laboratory.baseclasslib.throwable

/**
 * Author: 付鑫博
 * Version: 1.14.0
 * Date: 2021/8/10
 * Mender:
 * Modify:
 * Description:
 */
data class ResponseThrowable(
    var errorCode: Int,
    var msg: String?
) : Throwable()