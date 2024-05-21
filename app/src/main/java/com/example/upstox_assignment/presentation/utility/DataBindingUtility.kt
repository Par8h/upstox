package com.example.upstox_assignment.presentation.utility

import android.view.View
import androidx.databinding.BindingAdapter

object DataBindingUtility {

    @JvmStatic
    @BindingAdapter("app:visibility")
    fun setVisibility(view: View, visibility: Boolean) {
        view.visibility = if (visibility) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter("app:visibilitySoft")
    fun setVisibilitySoft(view: View, visibility: Boolean) {
        view.visibility = if (visibility) View.VISIBLE else View.INVISIBLE
    }
}