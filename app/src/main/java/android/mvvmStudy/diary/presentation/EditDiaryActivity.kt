package android.mvvmStudy.diary.presentation

import android.app.Activity
import android.mvvmStudy.diary.R
import android.mvvmStudy.diary.databinding.ActivityEditDiaryBinding
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class EditDiaryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditDiaryBinding
    private val editDiaryViewModel by viewModels<EditDiaryViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditDiaryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.viewModel = editDiaryViewModel
        clickSubmitDiary()
        checkButtonValid()
    }

    private fun clickSubmitDiary() {
        if (binding.btnSubmitDiary.isClickable) {
            binding.btnSubmitDiary.setOnClickListener {
                setResult(Activity.RESULT_OK)
                finish()
            }
        }
    }

    private fun getDiaryid(): String? {
        return intent.getStringExtra(KEY_DIARY_ID)
    }

    private fun checkButtonValid() {
        editDiaryViewModel.title.observe(this) {
            editDiaryViewModel.checkSubmitPossible()
        }

        editDiaryViewModel.content.observe(this) {
            editDiaryViewModel.checkSubmitPossible()
        }

        editDiaryViewModel.canSubmit.observe(this) {
            if (editDiaryViewModel.canSubmit.value!!) {
                binding.btnSubmitDiary.background =
                    ContextCompat.getDrawable(this, R.drawable.button_clicked)
                binding.btnSubmitDiary.isClickable = true
            } else {
                binding.btnSubmitDiary.background =
                    ContextCompat.getDrawable(this, R.drawable.rectangle_fill_gray_radius_10)
                binding.btnSubmitDiary.isClickable = false
            }
        }
    }

    companion object {
        const val KEY_DIARY_ID = "KEY_DIARY_ID"
    }
}