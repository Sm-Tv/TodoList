package sm_tv_prodactions.com.newtodolist.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "ShoppingList_table")

data class ShoppingList (
@PrimaryKey(autoGenerate = true)
val uid: Int,

@ColumnInfo(name = "title")
var title:String,

@ColumnInfo(name = "timestamp")
var timestamp: Long,

@ColumnInfo(name = "done")
var done: Boolean

): Parcelable

