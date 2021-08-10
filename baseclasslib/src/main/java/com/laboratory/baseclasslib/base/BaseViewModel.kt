package com.laboratory.baseclasslib.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.laboratory.baseclasslib.throwable.ResponseThrowable

/**
 * Author: 付鑫博
 * Version: 1.14.0
 * Date: 2021/8/10
 * Mender:
 * Modify:
 * Description:
 */
open class BaseViewModel : ViewModel() {

    var TAG: String? = javaClass.simpleName

    // 定义公共UI
    val mUiChange: UIChange by lazy { UIChange() }

    lateinit var responseThrowable: ResponseThrowable

    inner class UIChange {
        // 显示Loading
        val showLoadingDialog by lazy { MutableLiveData<String>() }

        // 取消Loading
        val dismissLoadingDialog by lazy { MutableLiveData<Boolean>() }

        // 显示Toast
        val showToastView by lazy { MutableLiveData<String>() }

        // 显示失败View
        val showErrorView by lazy { MutableLiveData<ResponseThrowable>() }

        // 显示空数据View
        val showEmptyView by lazy { MutableLiveData<String>() }
    }
}