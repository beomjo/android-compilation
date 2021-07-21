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

package com.beomjo.compilation.bindings

import android.widget.EditText
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener

@BindingAdapter("hasFocus")
fun EditText.setFocusChanged(hasFocus: Boolean) {
    if (hasFocus) requestFocus()
    else clearFocus()
}

@InverseBindingAdapter(attribute = "hasFocus", event = "focusChanged")
fun EditText.getFocusChanged(): Boolean = hasFocus()

@BindingAdapter("focusChanged")
fun EditText.setFocusChangedListener(listener: InverseBindingListener) {
    setOnFocusChangeListener { v, _ ->
        listener.onChange()
    }
}