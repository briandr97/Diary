package android.mvvmStudy.diary.presentation.util

import android.content.Context
import android.widget.Toast

object ContextExt {
    fun Context.shortToast(message:String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
    fun Context.longToast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}