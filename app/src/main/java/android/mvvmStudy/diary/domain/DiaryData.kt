package android.mvvmStudy.diary.domain

import java.util.*

data class DiaryData(
    val id: String,
    val title: String,
    val content: String,
    val createDate: Date
)