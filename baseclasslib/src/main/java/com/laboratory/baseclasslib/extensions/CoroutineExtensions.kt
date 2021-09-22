package com.laboratory.baseclasslib.extensions

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import kotlinx.coroutines.*

/**
 * Author: 付鑫博
 * Version: 1.18.0
 * Date: 2021/9/22
 * Mender:
 * Modify:
 * Description:
 */

@ObsoleteCoroutinesApi
fun <T> LifecycleOwner.loadInThreadPool(loader: () -> T): Deferred<T> {
    val deferred = GlobalScope.async(newFixedThreadPoolContext(2, "num"), CoroutineStart.LAZY) {
        loader()
    }

    lifecycle.addObserver(CoroutineLifecycleListener(deferred))
    return deferred
}


fun <T> LifecycleOwner.load(loader: () -> T): Deferred<T> {
    val deferred = GlobalScope.async(Dispatchers.IO, CoroutineStart.LAZY) {
        loader()
    }

    lifecycle.addObserver(CoroutineLifecycleListener(deferred))
    return deferred
}

infix fun <T> Deferred<T>.then(block: (T) -> Unit): Job {
    return GlobalScope.launch(Dispatchers.Main){
        block(this@then.await())
    }
}

class CoroutineLifecycleListener(val deferred: Deferred<*>) : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun clean() {
        if (deferred.isCancelled){
            deferred.cancel()
        }
    }
}