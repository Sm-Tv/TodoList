package sm_tv_prodactions.com.newtodolist.fragments.mainList.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MyViewModelFactory (private val application: Application, private var time: Int): ViewModelProvider.AndroidViewModelFactory(application) {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainNoteViewModel(application, time) as T
    }
}