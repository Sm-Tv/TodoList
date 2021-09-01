package sm_tv_prodactions.com.newtodolist.fragments.mainList.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import sm_tv_prodactions.com.newtodolist.data.NoteDataBase
import sm_tv_prodactions.com.newtodolist.models.Note
import sm_tv_prodactions.com.newtodolist.models.foreignkey.ChildModel
import sm_tv_prodactions.com.newtodolist.models.foreignkey.ParentModel
import sm_tv_prodactions.com.newtodolist.repositories.NoteRepository

class MainNoteViewModel(application: Application, time: Int): AndroidViewModel(application) {

    val readAllMainNoteData: LiveData<List<ParentModel>>
    val readPersonalNote: LiveData<List<ChildModel>>
    private val repository: NoteRepository

    init {
        val noteDao = NoteDataBase.getDatabase(application).noteDao()
        repository = NoteRepository(noteDao)
        readAllMainNoteData = repository.readAllParentModel
        readPersonalNote = repository.getModel(time)
    }


    fun updateChildModel(childModel: ChildModel){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateChildModel(childModel)
        }
    }

    fun deleteChildModel(childModel: ChildModel){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteChildModel(childModel)
        }
    }

    fun deleteAllChildInParent(child_uid:Int){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllChildInParent(child_uid)
        }
    }


    //fun readPersonalNoter(time: Long): LiveData<List<StickyNotes>> {
   //     viewModelScope.launch(Dispatchers.IO) {
    //        repository.getPersonalNote(time)
    //    }
   // }

}