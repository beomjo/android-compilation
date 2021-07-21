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

import android.util.Log
import com.beomjo.compilation.env.Config
import com.google.gson.Gson

object LogUtil {
    private val TAG = LogUtil::class.java.canonicalName
    private var isVisibleLog = true

    @JvmStatic
    fun init(config: Config) {
        isVisibleLog = config.LOG_VISIBLE
    }

    @JvmStatic
    fun e(message: Any) {
        e(TAG!!, message)
    }

    @JvmStatic
    fun w(message: Any) {
        w(TAG!!, message)
    }

    @JvmStatic
    fun i(message: Any) {
        i(TAG!!, message)
    }

    @JvmStatic
    fun d(message: Any) {
        d(TAG!!, message)
    }

    @JvmStatic
    fun v(message: Any) {
        v(TAG!!, message)
    }

    @JvmStatic
    fun e(e: Throwable) {
        e(TAG!!, e)
    }

    @JvmStatic
    fun e(tag: String, message: Any?, e: Throwable) {
        if (isVisibleLog) Log.e(tag, convertToString(message), e)
    }

    @JvmStatic
    fun e(tag: String, message: Any?) {
        if (isVisibleLog) Log.e(tag, convertToString(message))
    }

    @JvmStatic
    fun w(tag: String, message: Any) {
        if (isVisibleLog) Log.w(tag, convertToString(message))
    }

    @JvmStatic
    fun i(tag: String, message: Any) {
        if (isVisibleLog) Log.i(tag, convertToString(message))
    }

    @JvmStatic
    fun d(tag: String, message: Any) {
        if (isVisibleLog) Log.d(tag, convertToString(message))
    }

    @JvmStatic
    fun v(tag: String, message: Any) {
        if (isVisibleLog) Log.v(tag, convertToString(message))
    }

    @JvmStatic
    fun e(tag: String, e: Throwable) {
        if (isVisibleLog) e(tag, e.message, e)
    }

    private fun convertToString(message: Any?): String {
        if (message == null) return "<null>"

        if (message is String) return message

        return Gson().toJson(message)
    }
}
