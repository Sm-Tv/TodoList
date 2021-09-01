package sm_tv_prodactions.com.newtodolist.models.foreignkey

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.security.AlgorithmParameterGenerator

@Entity
data class ParentModel (
    @PrimaryKey(autoGenerate = true)
    val parent_uid: Int,

    @ColumnInfo(name = "main_title")
    var parent_title:String,

    @ColumnInfo(name = "main_timestamp")
    var parent_timestamp: Long
    )

@Entity(foreignKeys = [ForeignKey(
    entity = ParentModel::class,
    parentColumns = arrayOf("parent_uid"),
    childColumns = arrayOf("child_uid"),
    onDelete = ForeignKey.CASCADE
)]

)
data class ChildModel(
    @PrimaryKey(autoGenerate = true)
    val uid: Int,
    val child_uid: Int,
    var child_title:String,
    var child_timestamp: Long,
    var child_done: Boolean
        )