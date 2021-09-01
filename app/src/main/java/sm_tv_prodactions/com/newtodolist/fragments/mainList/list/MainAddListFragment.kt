package sm_tv_prodactions.com.newtodolist.fragments.mainList.list


import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_main_add_list.view.*
import sm_tv_prodactions.com.newtodolist.R
import sm_tv_prodactions.com.newtodolist.fragments.mainList.adapters.AdapterPersonalNote
import sm_tv_prodactions.com.newtodolist.fragments.mainList.viewmodel.MainNoteViewModel
import sm_tv_prodactions.com.newtodolist.fragments.mainList.viewmodel.MyViewModelFactory


class MainAddListFragment : Fragment() {

    private val args by navArgs<MainAddListFragmentArgs>()
    private lateinit var adapter: AdapterPersonalNote
    private lateinit var mViewModel: MainNoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main_add_list, container, false)

        (activity as AppCompatActivity).supportActionBar?.title = args.parentTitle

        //recycler view
        adapter = AdapterPersonalNote()
        val myRecyclerView = view.my_main_recyclerView
        myRecyclerView.adapter = adapter
        myRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        myRecyclerView.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))

        //view model note
        val time = args.parentUid
        val application = (activity as AppCompatActivity).application
        val viewModelFactory = MyViewModelFactory( application, time)
        mViewModel = ViewModelProvider(this, viewModelFactory).get(MainNoteViewModel::class.java)
        mViewModel.readPersonalNote.observe(viewLifecycleOwner, Observer {childModel ->
            adapter.setItems(childModel, mViewModel, args.parentTitle)
        })

        //слушатель к кнопки добавления списков
        view.my_main_floatingActionButton.setOnClickListener{
            addNoteInMainNote()
        }

        setHasOptionsMenu(true)

        return view
    }

    private fun addNoteInMainNote() {
        val bundle = Bundle()
        bundle.putInt("parent_uid", args.parentUid)
        bundle.putString("parent_title", args.parentTitle)
        findNavController().navigate(R.id.action_mainListFragment_to_addPersonalNote, bundle)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){

            android.R.id.home -> {
                findNavController().navigate(R.id.action_mainListFragment_to_firstMainFragment)
            }

            R.id.delete -> deleteAll(args.parentUid)
        }

        return true
    }

    private fun deleteAll(parent_uid: Int) {
        val dialog = AlertDialog.Builder(requireContext())
        dialog.setPositiveButton(resources.getString(R.string.yes)){_, _ ->
            mViewModel.deleteAllChildInParent(parent_uid)
        }
        dialog.setNegativeButton(resources.getString(R.string.no)){_, _ ->

        }
        dialog.setTitle(resources.getString(R.string.Title_dialog_note))
        dialog.setMessage(resources.getString(R.string.massage_dialog_note))
        dialog.create().show()
    }

}