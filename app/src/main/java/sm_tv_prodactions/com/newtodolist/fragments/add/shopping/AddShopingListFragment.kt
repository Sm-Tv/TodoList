package sm_tv_prodactions.com.newtodolist.fragments.add.shopping

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*
import kotlinx.android.synthetic.main.fragment_add_shoping_list.*
import kotlinx.android.synthetic.main.fragment_add_shoping_list.view.*
import sm_tv_prodactions.com.newtodolist.R
import sm_tv_prodactions.com.newtodolist.models.Note
import sm_tv_prodactions.com.newtodolist.models.ShoppingList
import sm_tv_prodactions.com.newtodolist.viewmodels.NoteViewModels
import sm_tv_prodactions.com.newtodolist.viewmodels.ShoppingListViewModels

class AddShopingListFragment : Fragment() {

    private lateinit var mViewModel: ShoppingListViewModels
    private lateinit var edTitleShoppingList: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_add_shoping_list, container, false)

        edTitleShoppingList = view.findViewById(R.id.edTitleShopping)

        mViewModel = ViewModelProvider(this).get(ShoppingListViewModels::class.java)

        view.idAddShopping.setOnClickListener {
            insertDataToDatabase()
        }

        setHasOptionsMenu(true)
        return view
    }

    override fun onResume() {
        edTitleShoppingList.showKeyboard()
        super.onResume()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_menu, menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.save ->  {
                insertDataToDatabase()
            }
            android.R.id.home -> findNavController().navigate(R.id.action_addShopingListFragment_to_shopingListFragment)
        }


        return true
    }

    private fun insertDataToDatabase() {
        val title = edTitleShopping.text.toString()
        val done = false
        val timestamp = System.currentTimeMillis()
        if(chekInput(title)){
            val shoppingList = ShoppingList(0,title,timestamp,done)
            mViewModel.addShoppingList(shoppingList)
            //Toast.makeText(requireContext(),"выполнено добавление в бд", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addShopingListFragment_to_shopingListFragment)
        }else {
            Toast.makeText(requireContext(),resources.getString(R.string.warning_message_shop), Toast.LENGTH_SHORT).show()
        }
    }

    private fun chekInput(title: String): Boolean {
        return !(TextUtils.isEmpty(title))
    }

    fun View.showKeyboard() {
        this.requestFocus()
        val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
    }
}