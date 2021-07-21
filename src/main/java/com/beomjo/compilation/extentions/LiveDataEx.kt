/*
 * Designed and developed by 2021 beomjo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.beomjo.compilation.extentions

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.beomjo.compilation.util.Event
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

fun <E, T> LiveData<Event<E>>.getEventContent(): T {
    return this.getOrAwaitValue().getContent<T>()
}

@VisibleForTesting
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
