package android.mvvmStudy.diary.presentation

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("app:createDate")

fun bindCreateDate(textView: TextView, date: Date?){
    val dateFormat = SimpleDateFormat("yyyy.MM.dd", Locale.KOREA)
    val createDateString = dateFormat.format(date?: return) //->2022.02.22 이런 형태로 나올 것
    textView.text=createDateString
}