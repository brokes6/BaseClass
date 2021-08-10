package com.laboratory.baseclasslib.topfunction

import java.lang.reflect.ParameterizedType

/**
 * Author: 付鑫博
 * Version: 1.14.0
 * Date: 2021/8/10
 * Mender:
 * Modify:
 * Description:
 */

@Suppress("UNCHECKED_CAST")
fun <VM> getVmClazz(obj: Any): VM {
    return (obj.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as VM
}