package android.mvvmStudy.diary.presentation

import android.content.Intent
import android.mvvmStudy.diary.databinding.ActivityDiaryListBinding
import android.mvvmStudy.diary.domain.DiaryData
import android.mvvmStudy.diary.presentation.EditDiaryActivity.Companion.KEY_DIARY_ID
import android.mvvmStudy.diary.presentation.util.ContextExt.shortToast
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class DiaryListActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDiaryListBinding
    private lateinit var diaryListAdapter: DiaryListAdapter
    private lateinit var editDiaryActivityLauncher : ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDiaryListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        editDiaryActivityLauncher=registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            it.resultCode //RESULT_OK이면 -1, RESULT_CANCLE이면 0
            this.shortToast(it.resultCode.toString())
        }
        initAdapter()
        clickNewDiary()
    }

    private fun initAdapter(){
        diaryListAdapter = DiaryListAdapter{onDiaryClick(it)}
        //({onDiaryClick(it)}) 이렇게 쓸거라고 생각하겠지만 컨벤션이 람다만 들어가면? 람다로 끝나면? 소괄호를 쓰지 않는 것
        //대신 (this::onDiaryClick) 혹은 (::onDiaryClick)으로 쓸 수도 있다.

        binding.rvDiaryList.adapter=diaryListAdapter
        diaryListAdapter.submitList( //submitList는 비동기로 호출됨 그래서 순서대로 된다고 생각하면 안 됨
            listOf(
                DiaryData("1", "코테 어쩌고 본 날", "오늘 코테를 봤다. 망했다. 다음엔 더 잘봐야지. 내가 꼭 삼성전자 들어간다.", Date()),
                DiaryData("2", "월요일", "공부 의욕이 샘솟는다. 오늘 하루종일 안드공부했다 재밌었다.", Date())
            )
        ){
            //비동기 처리가 끝나고 실행하고 싶은 코드가 있다면 이처럼 중괄호를 열고 작성하자!
        }
    }

    private fun onDiaryClick(diaryData: DiaryData){
        startEditDiary(diaryData)
    }

    private fun clickNewDiary(){
        binding.btnNewDiary.setOnClickListener{
            startEditDiary()
        }
    }

    private fun startEditDiary(diaryData : DiaryData? = null){
        val intent = Intent(this, EditDiaryActivity::class.java)
        intent.putExtra(KEY_DIARY_ID, diaryData?.id)
        editDiaryActivityLauncher.launch(intent)
    }
}