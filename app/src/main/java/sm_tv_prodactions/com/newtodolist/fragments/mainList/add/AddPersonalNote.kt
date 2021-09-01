package sm_tv_prodactions.com.newtodolist.fragments.mainList.add

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_add.view.*
import sm_tv_prodactions.com.newtodolist.R
import sm_tv_prodactions.com.newtodolist.fragments.mainList.viewmodel.FirstMainViewModel
import sm_tv_prodactions.com.newtodolist.models.foreignkey.ChildModel


class AddPersonalNote : Fragment() {

    private lateinit var mViewModel: FirstMainViewModel
    private lateinit var edTitle: EditText
    private val args by navArgs<AddPersonalNoteArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_add_personal_note, container, false)

        mViewModel = ViewModelProvider(this).get(FirstMainViewModel::class.java)

        //устанавливает каретку
        edTitle = view.findViewById(R.id.edTitle)

        view.idAdd.setOnClickListener {
            insertDataToDatabase()
        }

        setHasOptionsMenu(true)

        return view
    }

    override fun onResume() {
        edTitle.showKeyboard()
        super.onResume()
    }

    private fun insertDataToDatabase() {
        val parent_uid = args.parentUid
        val title = edTitle.text.toString()
        val done = false
        val timestamp = System.currentTimeMillis()
        if(chekInput(title)){
            val childModel = ChildModel(0, parent_uid, title, timestamp, done)
            mViewModel.addChildModel(childModel)
            //Toast.makeText(requireContext(),"выполнено добавление в бд",Toast.LENGTH_SHORT).show()
            val bundle = Bundle()
            bundle.putInt("parent_uid", args.parentUid )
            bundle.putString("parent_title", args.parentTitle)
            findNavController().navigate(R.id.action_addPersonalNote_to_mainAddListFragment, bundle)
        }else {
            Toast.makeText(requireContext(),resources.getString(R.string.warning_message), Toast.LENGTH_SHORT).show()
        }

    }

    private fun chekInput(title:String): Boolean{
        return !(TextUtils.isEmpty(title))
    }

    private fun View.showKeyboard() {
        //поставит фокус на поле где вводить текст
        this.requestFocus()
        //чтобы не прокручивал в право

        // эти две строчки вызывают клавиатуру
        val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.save ->  {
                insertDataToDatabase()
            }
            android.R.id.home -> {
                val bundle = Bundle()
                bundle.putInt("parent_uid", args.parentUid )
                bundle.putString("parent_title", args.parentTitle)
                findNavController().navigate(R.id.action_addPersonalNote_to_mainAddListFragment, bundle)}
        }


        return true
    }

}