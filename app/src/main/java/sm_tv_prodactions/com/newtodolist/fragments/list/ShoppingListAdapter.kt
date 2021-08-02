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
import sm_tv_prodactions.com.newtodolist.fragments.list.shopping.ShopingListFragmentDirections
import sm_tv_prodactions.com.newtodolist.models.Note
import sm_tv_prodactions.com.newtodolist.models.ShoppingList
import sm_tv_prodactions.com.newtodolist.viewmodels.NoteViewModels
import sm_tv_prodactions.com.newtodolist.viewmodels.ShoppingListViewModels

class ShoppingListAdapter: RecyclerView.Adapter<ShoppingListAdapter.MyViewHolder>() {
    //private var sortedList = SortedList<Note>()
    private lateinit var  vModel: ShoppingListViewModels


    private var sortedList = SortedList(ShoppingList::class.java, object : SortedList.Callback<ShoppingList>() {
        override fun compare(o1: ShoppingList, o2: ShoppingList): Int {
            if (!o2.done && o1.done) {
                return 1
            }
            return if (o2.done && !o1.done) {
                -1
            } else (o2.timestamp - o1.timestamp).toInt()
        }

        override fun onChanged(position: Int, count: Int) {
            notifyItemRangeChanged(position, count)
        }

        override fun areContentsTheSame(oldItem: ShoppingList, newItem: ShoppingList): Boolean {
            return oldItem.equals(newItem)
        }

        override fun areItemsTheSame(item1: ShoppingList, item2: ShoppingList): Boolean {
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




    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        //private lateinit var  mViewModel: NoteViewModels
        private lateinit var note: ShoppingList
        //private var siletn: Boolean = true

        fun bind(noteer: ShoppingList, mViewModel: ShoppingListViewModels){
            note = noteer

            completeChecked()

            itemView.title_note.text = note.title
            itemView.completed_note.isChecked = note.done == true

            itemView.title_note.setOnClickListener {
                //val action = ListFragmentDirections.actionListFragmentToUpdateFeagment(note)
                val action = ShopingListFragmentDirections.actionShopingListFragmentToUpdateShopingItem(note)
                itemView.findNavController().navigate(action)
            }

            itemView.delete_note.setOnClickListener {
                mViewModel.deleteShoppingList(note)
            }

            itemView.completed_note.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { compoundButton, checked ->

                note.done = checked
                mViewModel.updateShoppingList(note)
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return ShoppingListAdapter.MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_new, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        sortedList.let { holder.bind(it.get(position), vModel) }
    }

    override fun getItemCount(): Int {
        return sortedList.size()
    }

    fun setItems(notes: List<ShoppingList>, viewModels: ShoppingListViewModels) {
        vModel = viewModels
        sortedList.replaceAll(notes)

    }

}