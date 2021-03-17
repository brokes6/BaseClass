package com.laboratory.baseclass.base

import android.app.Activity
import android.app.Application
import android.os.Bundle

class BaseApp : Application() {

    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, p1: Bundle?) {

                (activity as IActivity).initView()
                (activity as IActivity).initData()

//                if (activity.findViewById<RelativeLayout>(R.id.toolbar) != null) {
//                    activity.findViewById<RelativeLayout>(R.id.toolbar).let {
//                        it.setBackgroundColor(activity.titleColor)
//                        it.findViewById<ImageView>(R.id.icon_back).setOnClickListener {
//                            activity.finish()
//                        }
//                    }
//
//                }
            }

            override fun onActivityStarted(p0: Activity) {
            }

            override fun onActivityResumed(p0: Activity) {
            }

            override fun onActivityPaused(p0: Activity) {
            }

            override fun onActivityStopped(p0: Activity) {
            }

            override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {
            }

            override fun onActivityDestroyed(p0: Activity) {
            }

        })
    }
}