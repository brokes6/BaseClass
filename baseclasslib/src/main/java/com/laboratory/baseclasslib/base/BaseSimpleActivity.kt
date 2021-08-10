package com.laboratory.baseclasslib.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewbinding.ViewBinding

/**
 * Author: 付鑫博
 * Version: 1.14.0
 * Date: 2021/8/10
 * Mender:
 * Modify:
 * Description:
 */
abstract class BaseSimpleActivity<DB : ViewBinding>(private val layoutId: Int) :
    AppCompatActivity() {

    lateinit var mViewBinding: DB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDataBind()
        init(savedInstanceState)
    }

    private fun initDataBind() {
        mViewBinding = DataBindingUtil.setContentView(this, layoutId)
        setContentView(mViewBinding.root)
    }

    private fun init(savedInstanceState: Bundle?) {
        initView(savedInstanceState)
        initData()
    }

    abstract fun initView(savedInstanceState: Bundle?)

    abstract fun initData()
}