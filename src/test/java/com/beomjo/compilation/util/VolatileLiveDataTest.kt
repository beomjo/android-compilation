package com.beomjo.compilation.util

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class VolatileLiveDataTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule() // Force tests to be executed synchronously

    @Test
    fun `MutableLiveData Observe 해제후 다시 Observe 햇을떄, 기존에 방출햇던 데이터 재방출`() {
        //Given
        val result = mutableListOf<String>()
        val liveData = MutableLiveData<String>()
        val observer = Observer<String> { result.add(it) }
        liveData.observeForever(observer)

        //When
        liveData.setValue("1")
        liveData.removeObserver(observer)
        liveData.observeForever(observer)

        //Then
        Assert.assertEquals(listOf("1", "1"), result)
        liveData.removeObserver(observer)
    }

    @Test
    fun `VolatileLiveData Observe 해제후 다시 Observe 햇을떄, 기존에 방출했던 데이터 다시 방출하지않음`() {
        //Given
        val result = mutableListOf<String>()
        val liveData = VolatileLiveData<String>()
        val observer = Observer<String> { result.add(it) }
        liveData.observeForever(observer)

        //When
        liveData.setValue("1")
        liveData.removeObserver(observer)
        liveData.observeForever(observer)

        //Then
        Assert.assertEquals(listOf("1"), result)
        liveData.removeObserver(observer)
    }
}