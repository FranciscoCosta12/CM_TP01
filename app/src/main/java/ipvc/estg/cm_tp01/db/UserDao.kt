package ipvc.estg.cm_tp01.db

import androidx.room.*

@Dao
interface UserDao {

    @Query("SELECT * FROM noteinfo ORDER BY id DESC")
    fun getAllUserInfo(): List<UserEntity>?

    @Insert
    fun insertUser(user: UserEntity?)

    @Delete
    fun deleteUser(user: UserEntity?)

    @Update
    fun updateUser(user: UserEntity?)
}