package com.laboratory.baseclass

import android.os.Bundle
import com.laboratory.baseclass.databinding.ActivityMainBinding
import com.laboratory.baseclasslib.base.BaseSimpleActivity
import com.laboratory.baseclasslib.extensions.toGONE

/**
 * Author: 付鑫博
 * Date: 2021/8/13
 * Description:
 */
class MainActivity : BaseSimpleActivity<ActivityMainBinding>() {

    override val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun initView(savedInstanceState: Bundle?) {

    }

    override fun initData() {

    }
}