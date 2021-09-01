package sm_tv_prodactions.com.newtodolist.fragments.mainList.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import sm_tv_prodactions.com.newtodolist.data.NoteDataBase
import sm_tv_prodactions.com.newtodolist.models.foreignkey.ChildModel
import sm_tv_prodactions.com.newtodolist.models.foreignkey.ParentModel

import sm_tv_prodactions.com.newtodolist.repositories.NoteRepository

class FirstMainViewModel(application: Application): AndroidViewModel(application)  {
    val readParentModel: LiveData<List<ParentModel>>
    val readChildModel: LiveData<List<ChildModel>>
    private val repository: NoteRepository

    init {
        val noteDao = NoteDataBase.getDatabase(application).noteDao()
        repository = NoteRepository(noteDao)
        readParentModel = repository.readAllParentModel
        readChildModel = repository.readAllChildModel

    }

    fun addParentModel(parentModel: ParentModel){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addParentModel(parentModel)
        }
    }

    fun addChildModel(childModel: ChildModel){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addChildModel(childModel)
        }
    }

    fun updateChildModel(childModel: ChildModel){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateChildModel(childModel)
        }
    }

    fun deleteParentModel(parentModel: ParentModel){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteParentModel(parentModel)
        }
    }

    fun deleteAllParentModel(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllParentModel()
        }
    }
}