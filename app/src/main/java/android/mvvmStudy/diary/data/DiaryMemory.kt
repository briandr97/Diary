package android.mvvmStudy.diary.data

import android.mvvmStudy.diary.domain.DiaryData
import java.util.*

object DiaryMemory {

    private val diaries: MutableMap<String, DiaryData> = mutableMapOf(
        "first" to DiaryData("first", "기본 메모", "그냥 들어있는 메모", Date())
    )

    fun saveDiary(diaryData: DiaryData){
        diaries[diaryData.id]=diaryData
    }

    fun getDiary(id:String):DiaryData{
        return diaries[id]?: error("cannot find diary id of $id")
    }

    fun getAllDiaries():List<DiaryData>{
        return diaries.map { it.value }
    }
}