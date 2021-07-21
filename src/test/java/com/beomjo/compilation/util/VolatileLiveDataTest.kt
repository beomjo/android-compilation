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

package com.beomjo.compilation.util

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import io.mockk.MockKAnnotations
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class VolatileLiveDataTest {

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Rule
    @JvmField
    val instantTaskExecutorRule =
        InstantTaskExecutorRule() // Force tests to be executed synchronously

    @Test
    fun `MutableLiveData Observe 해제후 다시 Observe 햇을떄, 기존에 방출햇던 데이터 재방출`() {
        // Given
        val liveData = MutableLiveData<String>()
        val observer = mockk<Observer<String>> {
            every { onChanged("1") } just Runs
        }
        liveData.observeForever(observer)

        // When
        liveData.setValue("1")
        liveData.removeObserver(observer)
        liveData.observeForever(observer)

        // Then
        verify(exactly = 2) {
            observer.onChanged(eq("1"))
            observer.onChanged(eq("1"))
        }
    }

    @Test
    fun `VolatileLiveData Observe 해제후 다시 Observe 햇을떄, 기존에 방출했던 데이터 다시 방출하지않음`() {
        // Given
        val liveData = VolatileLiveData<String>()
        val observer = mockk<Observer<String>> {
            every { onChanged("1") } just Runs
        }
        liveData.observeForever(observer)

        // When
        liveData.setValue("1")
        liveData.removeObserver(observer)
        liveData.observeForever(observer)

        // Then
        verify(exactly = 1) { observer.onChanged(eq("1")) }
    }
}
