package ipvc.estg.cm_tp01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import ipvc.estg.cm_tp01.adapter.RecyclerViewAdapter
import ipvc.estg.cm_tp01.db.UserEntity
import kotlinx.android.synthetic.main.activity_notes.*

class Notes : AppCompatActivity() , RecyclerViewAdapter.RowClickListener {
    lateinit var recyclerViewAdapter: RecyclerViewAdapter
    lateinit var viewModel: NotesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@Notes)
            recyclerViewAdapter = RecyclerViewAdapter(this@Notes)
            adapter = recyclerViewAdapter
            val divider = DividerItemDecoration(applicationContext, StaggeredGridLayoutManager.VERTICAL)
            addItemDecoration(divider)
        }

        viewModel = ViewModelProviders.of(this).get(NotesViewModel::class.java)
        viewModel.getAllUsersObservers().observe(this, Observer{
            recyclerViewAdapter.setListData(ArrayList(it))
            recyclerViewAdapter.notifyDataSetChanged()
        })

        saveButton.setOnClickListener{
            val name = titleInput.text.toString()
            val email = descInput.text.toString()

            if(saveButton.text.equals("Save")){
                val user = UserEntity(0,name, email)
                viewModel.insertUserInfo(user)
            }else{
                val user = UserEntity(titleInput.getTag(titleInput.id).toString().toInt(), name, email)
                viewModel.updateUserInfo(user)
                saveButton.setText("Save")
            }
            titleInput.setText("")
            descInput.setText("")
        }
    }

    override fun onDeleteUserClickListener(user: UserEntity) {
        viewModel.deleteUserInfo(user)
    }

    override fun onItemClickListener(user: UserEntity) {
        titleInput.setText(user.title)
        descInput.setText(user.desc)
        titleInput.setTag(titleInput.id, user.id)
        saveButton.setText("Update")
    }
}