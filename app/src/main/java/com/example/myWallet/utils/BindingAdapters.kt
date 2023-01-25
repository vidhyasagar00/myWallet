package com.example.myWallet.utils

import android.media.Image
import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.Spinner
import androidx.databinding.BindingAdapter
import com.example.myWallet.R
import com.example.myWallet.extras.MyList
import com.example.myWallet.models.Account
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

@BindingAdapter("selected")
fun listenClicks(spinner: Spinner, result: MutableStateFlow<Int>) {
    spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            if (parent != null) {
                result.update { position }
            }
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {}
    }
}

@BindingAdapter("setImageUri")
fun setImageWithUri(view: ImageView, id: Int) {
    if (id == -1) {
        view.setImageResource(R.drawable.wallet_1)
        return
    }
    view.setImageResource(MyList.category[id])
}

fun ImageView.setBudgetToImage(uri: String?) {

}