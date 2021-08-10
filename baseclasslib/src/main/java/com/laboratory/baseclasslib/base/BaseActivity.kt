package com.laboratory.baseclasslib.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.laboratory.baseclasslib.topfunction.getVmClazz

/**
 * Author: 付鑫博
 * Version: 1.14.0
 * Date: 2021/8/10
 * Mender:
 * Modify:
 * Description:
 */
abstract class BaseActivity<VM : BaseViewModel, DB : ViewDataBinding>(private val layoutId: Int) :
    AppCompatActivity() {

    lateinit var mDataBinding: DB

    /**
     * 当前页面绑定的viewModel
     */
    lateinit var mViewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDataBind()
        init(savedInstanceState)
    }

    private fun initDataBind() {
        mDataBinding = DataBindingUtil.setContentView(this, layoutId)
        mDataBinding.lifecycleOwner = this
    }

    private fun init(savedInstanceState: Bundle?) {
        mViewModel = createViewModel()
        initView(savedInstanceState)
        initData()
    }

    abstract fun initView(savedInstanceState: Bundle?)

    abstract fun initData()

    /**
     * 创建ViewModel
     */
    private fun createViewModel(): VM {
        return ViewModelProvider(this).get(
            getVmClazz(
                this
            )
        )
    }

}