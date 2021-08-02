package sm_tv_prodactions.com.newtodolist.fragments.list

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SortedList
import kotlinx.android.synthetic.main.item_new.view.*
import sm_tv_prodactions.com.newtodolist.R
import sm_tv_prodactions.com.newtodolist.fragments.list.note.ListFragmentDirections
import sm_tv_prodactions.com.newtodolist.models.Note
import sm_tv_prodactions.com.newtodolist.viewmodels.NoteViewModels
import kotlin.properties.Delegates


class Adapter: RecyclerView.Adapter<Adapter.NoteViewHolder>() {

    //private var sortedList = SortedList<Note>()
    private lateinit var  vModel: NoteViewModels


    private var sortedList = SortedList(Note::class.java, object : SortedList.Callback<Note>() {
            override fun compare(o1: Note, o2: Note): Int {
                if (!o2.done && o1.done) {
                    return 1
                }
                return if (o2.done && !o1.done) {
                    -1
                } else (o1.timestamp - o2.timestamp ).toInt()
            }

            override fun onChanged(position: Int, count: Int) {
                notifyItemRangeChanged(position, count)
            }

            override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem.equals(newItem)
            }

            override fun areItemsTheSame(item1: Note, item2: Note): Boolean {
                return item1.uid == item2.uid
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




    class NoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        //private lateinit var  mViewModel: NoteViewModels
        private lateinit var note: Note



        fun bind(noteer: Note, mViewModel: NoteViewModels){
            note = noteer

            completeChecked()
            val titlefull =  note.title
            itemView.title_note.text = titlefull
            itemView.completed_note.isChecked = note.done == true

            itemView.title_note.setOnClickListener {
                val action = ListFragmentDirections.actionListFragmentToUpdateFeagment(note)
                itemView.findNavController().navigate(action)
            }

            itemView.delete_note.setOnClickListener {
                mViewModel.deleteNote(note)
            }

            itemView.completed_note.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { compoundButton, checked ->

                note.done = checked
                mViewModel.updateNote(note)
                completeChecked()

            })

        }

        private fun completeChecked(){
            if(note.done){
                itemView.title_note.paintFlags =  itemView.title_note.paintFlags or( Paint.STRIKE_THRU_TEXT_FLAG)
                itemView.completed_note.text = "Готово"
            }else{
                itemView.title_note.paintFlags =  itemView.title_note.paintFlags and( Paint.STRIKE_THRU_TEXT_FLAG.inv())
                itemView.completed_note.text = "Не готово"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return Adapter.NoteViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_new, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        sortedList.let { holder.bind(it.get(position), vModel) }
    }

    override fun getItemCount(): Int {
        return sortedList.size()
    }

    fun setItems(notes: List<Note>, viewModels: NoteViewModels) {
        vModel = viewModels
        sortedList.replaceAll(notes)

    }

}