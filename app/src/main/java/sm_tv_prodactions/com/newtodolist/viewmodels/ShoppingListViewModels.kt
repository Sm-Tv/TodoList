package sm_tv_prodactions.com.newtodolist.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import sm_tv_prodactions.com.newtodolist.data.NoteDataBase
import sm_tv_prodactions.com.newtodolist.models.ShoppingList
import sm_tv_prodactions.com.newtodolist.repositories.ShoppingListRepository

class ShoppingListViewModels(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<ShoppingList>>

    //val readAllDataCompletedNote: LiveData<List<Note>>
    private val repository: ShoppingListRepository

    init {
        val shoppingDao = NoteDataBase.getDatabase(application).shoppingDao()
        repository = ShoppingListRepository(shoppingDao)
        readAllData = repository.readAllData
        //readAllDataCompletedNote = repository.readAllData
    }

    fun addShoppingList(shoppingList: ShoppingList){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addShoppingList(shoppingList)
        }
    }

    fun updateShoppingList(shoppingList: ShoppingList){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateShoppingList(shoppingList)
        }
    }

    fun deleteShoppingList(shoppingList: ShoppingList){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteShoppingList(shoppingList)
        }
    }

    fun deleteAllShoppingList(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllShoppingList()
        }
    }

}