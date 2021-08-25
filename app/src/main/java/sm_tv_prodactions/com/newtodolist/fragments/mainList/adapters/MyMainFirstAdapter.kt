package sm_tv_prodactions.com.newtodolist.fragments.mainList.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SortedList
import kotlinx.android.synthetic.main.item_first_main.view.*
import sm_tv_prodactions.com.newtodolist.R
import sm_tv_prodactions.com.newtodolist.models.MainNote
import sm_tv_prodactions.com.newtodolist.models.Note
import sm_tv_prodactions.com.newtodolist.viewmodels.NoteViewModels
import java.text.SimpleDateFormat
import java.util.*


class MyMainFirstAdapter: RecyclerView.Adapter<MyMainFirstAdapter.MainViewHolder>() {



    private var sortedList = SortedList(MainNote::class.java, object : SortedList.Callback<MainNote>() {
        override fun compare(o1: MainNote, o2: MainNote): Int {
            return (o2.main_timestamp - o1.main_timestamp).toInt()

        }

        override fun onChanged(position: Int, count: Int) {
            notifyItemRangeChanged(position, count)
        }

        override fun areContentsTheSame(oldItem: MainNote, newItem: MainNote): Boolean {
            return oldItem.equals(newItem)
        }

        override fun areItemsTheSame(item1: MainNote, item2: MainNote): Boolean {
            return item1.main_uid == item2.main_uid
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
        private lateinit var mainNote: MainNote

        fun bind(mainNoter: MainNote) {
            mainNote = mainNoter
            itemView.TvMainTitle.text = mainNote.main_title
            itemView.TvTimesTamp.text = mainNote.main_timestamp.toString().asTime()

            itemView.main_layout_note.setOnClickListener{

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MyMainFirstAdapter.MainViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_first_main, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyMainFirstAdapter.MainViewHolder, position: Int) {
        sortedList.let { holder.bind(it.get(position)) }
    }


    override fun getItemCount(): Int {
        return sortedList.size()
    }

    fun setItems(notes: List<MainNote>) {
        sortedList.replaceAll(notes)

    }
}

private fun String.asTime(): String {
    val time = Date(this.toLong())
    var str = "yyyy-MM-dd HH:mm"
    val timeFormst = SimpleDateFormat(str, Locale.getDefault())
    return timeFormst.format(time)
}
