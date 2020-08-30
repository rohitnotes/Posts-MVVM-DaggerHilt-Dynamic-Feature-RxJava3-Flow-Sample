package com.smarttoolfactory.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "post")
data class PostEntity(
    @PrimaryKey
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String
)

/**
 * * Data class that contains [PostAttention] data.
 * [PostEntity.id] is in [PostEntity] class, [PostAttention.postId] is in [PostAttention]
 * both points to same value.
 *
 * * [PostAttention.id] is auto generated by insertion to table.
 *
 * * Index let's this table to be sorted by postId which makes all
 * rows with same postId to be found faster.
 */
@Entity(
    tableName = "post_attention",
    indices = [Index(value = ["postId"])],
    foreignKeys = [
        ForeignKey(
            entity = PostEntity::class,
            parentColumns = ["id"],
            childColumns = ["postId"],
            onDelete = ForeignKey.NO_ACTION
        )
    ]
)
data class PostAttention(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val postId: Int,
    var displayCountF: Int = 0,
    var isFavorite: Boolean = false
)

/**
 * @Embedded tag is for having nested entities that are contained inside another entity. For
 * instance Songs are embedded inside an Album.
 *
 * @Relation is for having relation between entities based on pairing one or more properties,
 * such as ids. For instance Person with id, having Pets that has userId that is exactly same
 * with each other.
 *
 * * ParentColumn name from [PostEntity] class is matched with entityColumn
 * from [PostAttention.postId]
 */
class PostAndAllLikesDisplays {

    @Embedded
    var postEntity: PostEntity? = null

    // 🔥 'id' comes from Post, 'postId' comes from Post. Both are the same ids
    @Relation(parentColumn = "id", entityColumn = "postId", entity = PostAttention::class)
    var attention: PostAttention? = null
}
