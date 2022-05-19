package android.mvvmStudy.diary.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EditDiaryViewModel : ViewModel() {
    var title = MutableLiveData<String>()
    var content = MutableLiveData<String>()
    var canSubmit = MutableLiveData(false)

    fun checkSubmitPossible(){
        canSubmit.value = !title.value.isNullOrBlank() and !content.value.isNullOrBlank()
    }
}