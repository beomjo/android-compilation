package com.beomjo.compilation.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

object LiveDataTestUtil {
    @JvmStatic
    fun <T> getOrAwaitValue(liveData: LiveData<T>): T {
        val data = arrayOfNulls<Any>(1)
        val latch = CountDownLatch(1)
        val observer = object : Observer<T> {
            override fun onChanged(o: T?) {
                data[0] = o
                latch.countDown()
                liveData.removeObserver(this)
            }
        }
        liveData.observeForever(observer)
        latch.await(2, TimeUnit.SECONDS)

        @Suppress("UNCHECKED_CAST")
        return data[0] as T
    }

    @JvmStatic
    fun <T> getEventContent(event: Event<*>): T {
        return event.getContentIfNotHandled() as T
    }
}

fun <E, T> LiveData<Event<E>>.getEventContent(): T {
    return this.getOrAwaitValue().getContent<T>()
}

fun <T> LiveData<T>.getOrAwaitValue(): T {
    val data = arrayOfNulls<Any>(1)
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(o: T?) {
            data[0] = o
            latch.countDown()
            this@getOrAwaitValue.removeObserver(this)
        }
    }
    this@getOrAwaitValue.observeForever(observer)
    latch.await(2, TimeUnit.SECONDS)

    @Suppress("UNCHECKED_CAST")
    return data[0] as T
}


fun <T> Event<*>.getContent(): T {
    @Suppress("UNCHECKED_CAST")
    return this.getContentIfNotHandled() as T
}