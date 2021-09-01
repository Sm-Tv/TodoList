package sm_tv_prodactions.com.newtodolist.fragments.mainList.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SortedList
import kotlinx.android.synthetic.main.item_first_main.view.*
import sm_tv_prodactions.com.newtodolist.R
import sm_tv_prodactions.com.newtodolist.fragments.mainList.viewmodel.FirstMainViewModel
import sm_tv_prodactions.com.newtodolist.fragments.mainList.viewmodel.MainNoteViewModel
import sm_tv_prodactions.com.newtodolist.models.foreignkey.ParentModel
import sm_tv_prodactions.com.newtodolist.viewmodels.NoteViewModels
import java.text.SimpleDateFormat
import java.util.*


class MyMainFirstAdapter: RecyclerView.Adapter<MyMainFirstAdapter.MainViewHolder>() {

    private lateinit var vModel: FirstMainViewModel

    private var sortedList = SortedList(ParentModel::class.java, object : SortedList.Callback<ParentModel>() {
        override fun compare(o1: ParentModel, o2: ParentModel): Int {
            return (o2.parent_timestamp - o1.parent_timestamp).toInt()

        }

        override fun onChanged(position: Int, count: Int) {
            notifyItemRangeChanged(position, count)
        }

        override fun areContentsTheSame(oldItem: ParentModel, newItem: ParentModel): Boolean {
            return oldItem.equals(newItem)
        }

        override fun areItemsTheSame(item1: ParentModel, item2: ParentModel): Boolean {
            return item1.parent_uid == item2.parent_uid
        }

        override fun onInserted(position: Int, count: Int) {
            notifyItemRangeInserted(position, count)
        }

        override fun onRemoved(position: Int, count: Int) {
            notifyItemRangeRemoved(position, count)
        }

        override fun onMoved(fromPosition: Int, toPosition: Int) {
            notifyItemMoved(fromPosition, toPosition)
        }
    })


    class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private lateinit var mainNote: ParentModel

        fun bind(mainNoter: ParentModel, mViewModel: FirstMainViewModel,) {
            mainNote = mainNoter
            itemView.TvMainTitle.text = mainNote.parent_title
            itemView.TvTimesTamp.text = mainNote.parent_timestamp.toString().asTime()

            itemView.main_layout_note.setOnClickListener{
                val bundle = Bundle()
                bundle.putInt("parent_uid", mainNote.parent_uid)
                bundle.putString("parent_title", mainNote.parent_title)
                itemView.findNavController().navigate(R.id.action_firstMainFragment_to_mainAddListFragment, bundle)
            }

            itemView.imParentDelete.setOnClickListener{
                mViewModel.deleteParentModel(mainNote)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MyMainFirstAdapter.MainViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_first_main, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyMainFirstAdapter.MainViewHolder, position: Int) {
        sortedList.let { holder.bind(it.get(position), vModel) }
    }


    override fun getItemCount(): Int {
        return sortedList.size()
    }

    fun setItems(notes: List<ParentModel>, myViewModel: FirstMainViewModel) {
        vModel = myViewModel
        sortedList.replaceAll(notes)
    }
}

private fun String.asTime(): String {
    val time = Date(this.toLong())
    var str = "yyyy-MM-dd HH:mm"
    val timeFormst = SimpleDateFormat(str, Locale.getDefault())
    return timeFormst.format(time)
}
