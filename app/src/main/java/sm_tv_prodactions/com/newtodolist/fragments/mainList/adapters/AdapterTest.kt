package sm_tv_prodactions.com.newtodolist.fragments.mainList.adapters

import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SortedList
import kotlinx.android.synthetic.main.item_new.view.*
import sm_tv_prodactions.com.newtodolist.R
import sm_tv_prodactions.com.newtodolist.fragments.mainList.viewmodel.MainNoteViewModel
import sm_tv_prodactions.com.newtodolist.models.foreignkey.ChildModel

class AdapterTest: RecyclerView.Adapter<AdapterTest.NoteViewHolder>() {

    //private lateinit var vModel: MainNoteViewModel
    //private lateinit var parentTitle: String

    private var sortedList = SortedList(ChildModel::class.java, object : SortedList.Callback<ChildModel>() {
        override fun compare(o1: ChildModel, o2: ChildModel): Int {
            if (!o2.child_done && o1.child_done) {
                return 1
            }
            return if (o2.child_done && !o1.child_done) {
                -1
            } else (o1.child_timestamp - o2.child_timestamp ).toInt()
        }

        override fun onChanged(position: Int, count: Int) {
            notifyItemRangeChanged(position, count)
        }

        override fun areContentsTheSame(oldItem: ChildModel, newItem: ChildModel): Boolean {
            return oldItem.equals(newItem)
        }

        override fun areItemsTheSame(item1: ChildModel, item2: ChildModel): Boolean {
            return item1.child_uid == item2.child_uid
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
        private lateinit var note: ChildModel



        fun bind(noteer: ChildModel ){
            note = noteer

            completeChecked()
            val titlefull =  note.child_title
            itemView.title_note.text = titlefull
            itemView.completed_note.isChecked = note.child_done == true

            itemView.title_note.setOnClickListener {
                val bundle = Bundle()
                //bundle.putString("parent_title", parentTitle)
                bundle.putString("child_title", note.child_title)
                bundle.putBoolean("child_done", note.child_done)
                bundle.putInt("child_uid", note.child_uid)
                bundle.putInt("uid", note.uid)
                itemView.findNavController().navigate(R.id.action_mainAddListFragment_to_updatePersonalOneNote, bundle)
            }

            itemView.delete_note.setOnClickListener {
               // mViewModel.deleteChildModel(note)
            }

            itemView.completed_note.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { compoundButton, checked ->

                note.child_done = checked
               // mViewModel.updateChildModel(note)
                completeChecked()

            })

        }

        private fun completeChecked(){
            if(note.child_done){
                itemView.title_note.paintFlags =  itemView.title_note.paintFlags or( Paint.STRIKE_THRU_TEXT_FLAG)
                itemView.completed_note.text = "Готово"
            }else{
                itemView.title_note.paintFlags =  itemView.title_note.paintFlags and( Paint.STRIKE_THRU_TEXT_FLAG.inv())
                itemView.completed_note.text = "Не готово"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return AdapterTest.NoteViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_new, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        sortedList.let { holder.bind(it.get(position)) }
    }

    override fun getItemCount(): Int {
        return sortedList.size()
    }

    fun setItems(notes: List<ChildModel>) {

        sortedList.replaceAll(notes)

    }
}