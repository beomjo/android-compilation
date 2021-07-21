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

import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.beomjo.compilation.extentions.setOnClickAnimation
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun ImageView.loadUrl(url: String?) {
    url?.let {
        Glide.with(this.context)
            .load(it)
            .into(this)
    }
}

@BindingAdapter("imageUri")
fun ImageView.loadUri(uri: Uri?) {
    uri?.let {
        Glide.with(this.context)
            .load(it.toString())
            .into(this)
    }
}

@BindingAdapter("isSelected")
fun View.setSelection(isSelected: Boolean) {
    this.isSelected = isSelected
}

@BindingAdapter("isVisible")
fun View.setVisibleGone(isVisible: Boolean) {
    this.isVisible = isVisible
}

@BindingAdapter("isGone")
fun View.setGoneVisible(isGone: Boolean) {
    this.isGone = isGone
}


@BindingAdapter("onClickWithAnimation")
fun View.onClickWithAnimation(listener: View.OnClickListener) {
    this.setOnClickAnimation { listener.onClick(this) }
}


