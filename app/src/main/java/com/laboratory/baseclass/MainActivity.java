package com.laboratory.baseclass;

import android.os.Bundle;

import com.laboratory.baseclass.databinding.ActivityMainBinding;
import com.laboratory.baseclasslib.base.BaseSimpleActivity;

import org.jetbrains.annotations.Nullable;

public class MainActivity extends BaseSimpleActivity<ActivityMainBinding> {

    public MainActivity() {
        super(R.layout.activity_main);
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void initData() {

    }
}