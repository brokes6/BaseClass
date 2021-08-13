package com.laboratory.baseclasslib.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.laboratory.baseclasslib.extensions.getVmClazz

/**
 * Author: 付鑫博
 * Version: 1.14.0
 * Date: 2021/8/10
 * Mender:
 * Modify:
 * Description:
 */
abstract class BaseActivity<VM : BaseViewModel,VB : ViewBinding> :
    AppCompatActivity() {

    /**
     * 当前页面绑定的viewModel
     */
    lateinit var mViewModel: VM

    protected abstract val binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        init(savedInstanceState)
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