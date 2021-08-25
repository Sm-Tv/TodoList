package sm_tv_prodactions.com.newtodolist.fragments.mainList


import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_main_list.view.*
import sm_tv_prodactions.com.newtodolist.R


class MainListFragment : Fragment() {

    private val args by navArgs<MainListFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main_list, container, false)

        (activity as AppCompatActivity).supportActionBar?.title = args.mainNoteName

        //recycler view


        //слушатель к кнопки добавления списков
        view.my_main_floatingActionButton.setOnClickListener{
            addNoteInMainNote()
        }

        return view
    }

    private fun addNoteInMainNote() {

    }

}