package sm_tv_prodactions.com.newtodolist.fragments.list.shopping

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_shoping_list.view.*
import sm_tv_prodactions.com.newtodolist.R
import sm_tv_prodactions.com.newtodolist.fragments.list.ShoppingListAdapter
import sm_tv_prodactions.com.newtodolist.viewmodels.ShoppingListViewModels


class ShopingListFragment : Fragment() {

    private lateinit var adapter: ShoppingListAdapter
    private lateinit var mViewModel: ShoppingListViewModels

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_shoping_list, container, false)

        //Recycler
        adapter = ShoppingListAdapter()
        val myRecyclerView = view.shopping_recyclerView
        myRecyclerView.adapter = adapter
        myRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        myRecyclerView.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))

        //NoteNiewModel
        mViewModel = ViewModelProvider(this).get(ShoppingListViewModels::class.java)
        mViewModel.readAllData.observe(viewLifecycleOwner, Observer { shoppingList->
            adapter.setItems(shoppingList,mViewModel)
        })

        view.shopping_floatingActionButton.setOnClickListener{
            findNavController().navigate(R.id.action_shopingListFragment_to_addShopingListFragment)
        }

        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)

        setHasOptionsMenu(true)

        return view
    }



    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_menu, menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.delete -> deliteAll()

        }

        return true
    }

    private fun deliteAll() {
        val dialog = AlertDialog.Builder(requireContext())
        dialog.setPositiveButton(resources.getString(R.string.yes)){_, _ ->
            mViewModel.deleteAllShoppingList()
        }
        dialog.setNegativeButton(resources.getString(R.string.no)){_, _ ->

        }
        dialog.setTitle(resources.getString(R.string.Title_dialog_shop))
        dialog.setMessage(resources.getString(R.string.massage_dialog_shop))
        dialog.create().show()
    }


}