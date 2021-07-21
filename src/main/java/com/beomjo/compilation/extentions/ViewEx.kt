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

import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.getSystemService


fun View.setMargin(
    left: Float? = null,
    top: Float? = null,
    right: Float? = null,
    bottom: Float? = null
) {
    layoutParams<ViewGroup.MarginLayoutParams> {
        left?.run { leftMargin = dpToPx(this) }
        top?.run { topMargin = dpToPx(this) }
        right?.run { rightMargin = dpToPx(this) }
        bottom?.run { bottomMargin = dpToPx(this) }
    }
}

inline fun <reified T : ViewGroup.LayoutParams> View.layoutParams(block: T.() -> Unit) {
    if (layoutParams is T) block(layoutParams as T)
}

fun View.dpToPx(dp: Float): Int = context.dpToPx(dp)

fun <T : View> T.setOnClickAnimation(function: (view: T) -> Unit): T {
    this.setOnClickListener {
        val toggleDuration = 80
        val animationMinScale = 0.525f

        try {
            this.apply {
                isClickable = false
                animate().run {
                    scaleXBy(1f)
                    scaleX(animationMinScale)
                    scaleYBy(1f)
                    scaleY(animationMinScale)
                    duration = toggleDuration.toLong()
                    setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: android.animation.Animator) {
                            super.onAnimationEnd(animation)
                            animate().run {
                                scaleX(1f)
                                scaleY(1f)
                                duration = toggleDuration.toLong()
                                setListener(object : AnimatorListenerAdapter() {
                                    override fun onAnimationEnd(
                                        animation: android.animation.Animator
                                    ) {
                                        super.onAnimationEnd(animation)
                                        isClickable = true
                                        function.invoke(this@apply)
                                    }
                                })
                            }
                        }
                    })
                }
            }
        } catch (e: Exception) {
            isClickable = true
            function.invoke(this)
        }
    }
    return this
}

fun Context.hideKeyboard(view: View) {
    this.getSystemService<InputMethodManager>()?.hideSoftInputFromWindow(view.windowToken, 0)
}

