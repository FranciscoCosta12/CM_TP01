package ipvc.estg.cm_tp01.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope

@Database(entities = [NoteEntity::class], version = 1)
abstract class NotesDb : RoomDatabase() {


    abstract fun notesDao():NotesDao?

    companion object {

        @Volatile
        private var INSTANCE: NotesDb?= null

        fun getAppDatabase(context: Context): NotesDb?{

            if(INSTANCE == null) {
                INSTANCE = Room.databaseBuilder<NotesDb>(
                    context.applicationContext, NotesDb::class.java, "AppDB"
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE
        }
    }
}