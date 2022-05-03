package android.mvvmStudy.diary.presentation

import android.mvvmStudy.diary.databinding.ItemDiaryPreviewBinding
import android.mvvmStudy.diary.domain.DiaryData
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class DiaryListAdapter(private val onDiaryClick: ((DiaryData)->Unit)?=null) : ListAdapter<DiaryData, DiaryListAdapter.DiaryViewHolder>(DiaryListComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiaryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding=ItemDiaryPreviewBinding.inflate(layoutInflater, parent, false)
        return DiaryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DiaryViewHolder, position: Int) {
        holder.bind(getItem(position), onDiaryClick)
        //listAdapter는 getItem이라는 함수를 알아서 만들어줬기 때문에 우리가 굳이 리사이클러뷰때처럼 mutableListof 요런거 안만들어줘도 된다.
    }

    class DiaryViewHolder(private val binding:ItemDiaryPreviewBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(diaryData: DiaryData, onDiaryCick:((DiaryData)->Unit)? = null){
            binding.diary=diaryData
            binding.root.setOnClickListener{
                onDiaryCick?.invoke(diaryData) //invoke를 쓰는 이유는 지금 onDiaryClick이 nullable해서. 그게 아니라면 그냥 onDiaryClick(diaryData) 해도 상관없다
            }
        }
    }

    private class DiaryListComparator:DiffUtil.ItemCallback<DiaryData>(){

        override fun areItemsTheSame(oldItem: DiaryData, newItem: DiaryData): Boolean {
            return oldItem.id ==newItem.id
            //areItemsTheSame을 먼저 호출하고 true를 반환하면 아래 areContentsTheSame을 호출한다. (id가 같을 수도 있으니까..?)
            //즉 id로 걸러진다면 아래 areContentsTheSame은 호출되지 않는다.
        }

        override fun areContentsTheSame(oldItem: DiaryData, newItem: DiaryData): Boolean {
            return oldItem == newItem
            //위 areItemsTheSame이 false이면 호출한다.

        }

    }
}