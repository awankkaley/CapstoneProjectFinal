package id.awankkaley.capstoneproject.util

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import id.awankkaley.core.util.Util


fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun ImageView.imageViewGlide(url: String?) {
    Glide.with(context)
        .load(Util.image_base_url + url)
        .into(this)
}



