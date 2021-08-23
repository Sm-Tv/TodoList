package sm_tv_prodactions.com.newtodolist.fragments.list.note

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
import kotlinx.android.synthetic.main.fragment_list.view.*
import sm_tv_prodactions.com.newtodolist.R
import sm_tv_prodactions.com.newtodolist.fragments.list.Adapter
import sm_tv_prodactions.com.newtodolist.viewmodels.NoteViewModels


class ListFragment : Fragment() {

    private lateinit var mViewModel: NoteViewModels
    //private lateinit var adapter: ListAdapter
    private lateinit var adapter: Adapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_list, container, false)

        //Recycler
        //adapter = ListAdapter()
        adapter = Adapter()
        val myRecyclerView = view.my_recyclerView
        myRecyclerView.adapter = adapter
        myRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        myRecyclerView.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))

        //NoteViewModel
        mViewModel = ViewModelProvider(this).get(NoteViewModels::class.java)
        mViewModel.readAllData.observe(viewLifecycleOwner, Observer { note->
            adapter.setItems(note,mViewModel)
        })

        view.my_floatingActionButton.setOnClickListener{
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
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
            mViewModel.deleteAllNotes()
        }
        dialog.setNegativeButton(resources.getString(R.string.no)){_, _ ->

        }
        dialog.setTitle(resources.getString(R.string.Title_dialog_note))
        dialog.setMessage(resources.getString(R.string.massage_dialog_note))
        dialog.create().show()
    }

}