package sm_tv_prodactions.com.newtodolist.data

import androidx.lifecycle.LiveData
import androidx.room.*
import sm_tv_prodactions.com.newtodolist.models.Note
import sm_tv_prodactions.com.newtodolist.models.foreignkey.ChildModel
import sm_tv_prodactions.com.newtodolist.models.foreignkey.ParentModel

@Dao
interface NoteDao {

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    fun insert(vararg note: Note)

    @Update
    fun update(vararg note: Note)

    @Delete
    fun delete(note: Note)

    @Query("DELETE FROM Note_table")
    fun deleteAllNotes()

    @Query("SELECT * FROM Note_table ORDER BY uid DESC")
    fun getAllLiveData(): LiveData<List<Note>>


    //foreignKey
    @Transaction @Query("SELECT * FROM ChildModel WHERE child_uid = :child_parent_uid")
    fun getForeignKeyPersonalNote(child_parent_uid: Int): LiveData<List<ChildModel>>

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    fun insertParentModel(vararg parentModel: ParentModel)

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    fun insertChildModel(vararg childModel: ChildModel)

    @Query("SELECT * FROM ParentModel ORDER BY parent_uid DESC")
    fun getParentModel(): LiveData<List<ParentModel>>
////////
    @Query("SELECT * FROM ChildModel ORDER BY uid DESC")
    fun getChildModel(): LiveData<List<ChildModel>>

    @Update
    fun updateChildModel(vararg childModel: ChildModel)

    @Delete
    fun deleteChildModel(childModel: ChildModel)

    @Delete
    fun deleteParentModel(parentModel: ParentModel)

    @Query("DELETE  FROM ChildModel WHERE child_uid = :child_parent_uid")
    fun deleteAllChildInParent(child_parent_uid: Int)

    @Query("DELETE  FROM ParentModel")
    fun deleteAllParentModel()



}
