package sm_tv_prodactions.com.newtodolist.repositories

import androidx.lifecycle.LiveData
import sm_tv_prodactions.com.newtodolist.data.ShoppingDao
import sm_tv_prodactions.com.newtodolist.models.ShoppingList

class ShoppingListRepository(private val shoppingDao: ShoppingDao) {
    val readAllData: LiveData<List<ShoppingList>> = shoppingDao.getAllLiveData()
    //val readAllDataCompleted: LiveData<List<Note>> = noteDao.getAllLiveDataCompleted()

    suspend fun addShoppingList(shoppingList: ShoppingList){
        shoppingDao.insert(shoppingList)
    }

    suspend fun updateShoppingList(shoppingList: ShoppingList){
        shoppingDao.update(shoppingList)
    }

    suspend fun deleteShoppingList(shoppingList: ShoppingList){
        shoppingDao.delete(shoppingList)
    }

    suspend fun deleteAllShoppingList(){
        shoppingDao.deleteAllShoppingList()
    }
}