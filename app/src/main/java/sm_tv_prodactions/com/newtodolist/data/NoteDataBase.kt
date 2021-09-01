package sm_tv_prodactions.com.newtodolist.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import sm_tv_prodactions.com.newtodolist.models.Note
import sm_tv_prodactions.com.newtodolist.models.foreignkey.ParentModel
import sm_tv_prodactions.com.newtodolist.models.ShoppingList
import sm_tv_prodactions.com.newtodolist.models.foreignkey.ChildModel


@Database(entities = [Note::class, ShoppingList::class,
    ParentModel::class, ChildModel::class], version = 20)
abstract class NoteDataBase: RoomDatabase() {

    abstract fun noteDao(): NoteDao
    abstract fun shoppingDao(): ShoppingDao

    companion object {
        @Volatile
        private var INSTANCE: NoteDataBase? = null

        fun getDatabase(context: Context): NoteDataBase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDataBase::class.java,
                    "user_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }




}