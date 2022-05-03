package android.mvvmStudy.diary.presentation

import android.mvvmStudy.diary.databinding.ActivityEditDiaryBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class EditDiaryActivity : AppCompatActivity() {
    private lateinit var binding : ActivityEditDiaryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityEditDiaryBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}