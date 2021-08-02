package sm_tv_prodactions.com.newtodolist.data

import androidx.lifecycle.LiveData
import androidx.room.*
import sm_tv_prodactions.com.newtodolist.models.Note
import sm_tv_prodactions.com.newtodolist.models.ShoppingList


@Dao
interface ShoppingDao {

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    fun insert(vararg shoppingList: ShoppingList)

    @Update
    fun update(vararg shoppingList: ShoppingList)

    @Delete
    fun delete(shoppingList: ShoppingList)

    @Query("DELETE FROM ShoppingList_table")
    fun deleteAllShoppingList()

    @Query("SELECT * FROM ShoppingList_table ORDER BY uid DESC")
    fun getAllLiveData(): LiveData<List<ShoppingList>>
}