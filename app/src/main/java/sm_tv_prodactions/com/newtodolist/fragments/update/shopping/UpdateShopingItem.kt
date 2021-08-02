package sm_tv_prodactions.com.newtodolist.fragments.update.shopping

import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_update_feagment.*
import kotlinx.android.synthetic.main.fragment_update_feagment.view.*
import kotlinx.android.synthetic.main.fragment_update_shoping_item.*
import kotlinx.android.synthetic.main.fragment_update_shoping_item.view.*
import sm_tv_prodactions.com.newtodolist.R
import sm_tv_prodactions.com.newtodolist.fragments.update.note.UpdateFeagmentArgs
import sm_tv_prodactions.com.newtodolist.models.Note
import sm_tv_prodactions.com.newtodolist.models.ShoppingList
import sm_tv_prodactions.com.newtodolist.viewmodels.NoteViewModels
import sm_tv_prodactions.com.newtodolist.viewmodels.ShoppingListViewModels


class UpdateShopingItem : Fragment() {

    private val args by navArgs<UpdateShopingItemArgs>()
    private lateinit var myViewModel: ShoppingListViewModels

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_update_shoping_item, container, false)

        view.edTitle_update_shopping.setText(args.currentItem.title)
        myViewModel = ViewModelProvider(this).get(ShoppingListViewModels::class.java)

        view.idAdd_update_shopping.setOnClickListener {
            updateItem()
        }

        setHasOptionsMenu(true)

        return view
    }

    private fun updateItem() {
        val title = edTitle_update_shopping.text.toString()
        val timestamp = System.currentTimeMillis()
        if (chekInput(title)) {
            val updateShoppinglist = ShoppingList(args.currentItem.uid, title, timestamp, args.currentItem.done)
            myViewModel.updateShoppingList(updateShoppinglist)
            //Toast.makeText(requireContext(), "Обновили успешно", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateShopingItem_to_shopingListFragment)
        } else {
            Toast.makeText(requireContext(), resources.getString(R.string.warning_message_shop), Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.save -> {
                updateItem()
            }
            android.R.id.home -> findNavController().navigate(R.id.action_updateShopingItem_to_shopingListFragment)
        }

        return true
    }

    private fun chekInput(title: String): Boolean {
        return !(TextUtils.isEmpty(title))
    }


}