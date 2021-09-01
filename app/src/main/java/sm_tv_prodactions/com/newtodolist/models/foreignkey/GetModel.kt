package sm_tv_prodactions.com.newtodolist.models.foreignkey

import androidx.room.Embedded
import androidx.room.Relation

data class GetModel(
    @Embedded
    val parent_model: ParentModel,
    @Relation(parentColumn = "parent_uid", entity = ChildModel::class, entityColumn = "child_uid")
    val child_model: List<ChildModel>
)
