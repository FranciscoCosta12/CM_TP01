package ipvc.estg.cm_tp01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import ipvc.estg.cm_tp01.adapter.UserAdapter
import ipvc.estg.cm_tp01.api.EndPoints
import ipvc.estg.cm_tp01.api.OutputPost
import ipvc.estg.cm_tp01.api.ServiceBuilder
import ipvc.estg.cm_tp01.api.User
import kotlinx.android.synthetic.main.activity_feed.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Feed : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)

        //Go to Notes
        val button1 = findViewById<ImageView>(R.id.imageView7)
        button1.setOnClickListener{
            val intent = Intent(this, Notes::class.java)
            startActivity(intent)
        }

        //Go to Map
        val button2 = findViewById<ImageView>(R.id.imageView8)
        button2.setOnClickListener{
            val intent = Intent(this, MapsActivity::class.java)
            startActivity(intent)
        }

        val request = ServiceBuilder.buildService(EndPoints::class.java)
        val call = request.getUsers()

        call.enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>){
                if(response.isSuccessful){
                    recyclerView.apply {
                        setHasFixedSize(true)
                        layoutManager = LinearLayoutManager(this@Feed)
                        adapter = UserAdapter(response.body()!!)
                    }
                }
            }
            override fun onFailure(call: Call<List<User>>, t: Throwable){
                Toast.makeText(this@Feed,"${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun getSingle(view: View){
        val request = ServiceBuilder.buildService(EndPoints::class.java)
        val call = request.getUserById(2)

        call.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>){
                if(response.isSuccessful){
                    val c: User = response.body()!!
                    Toast.makeText(this@Feed, c.address.zipcode, Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<User>, t: Throwable){
                Toast.makeText(this@Feed,"${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun post(view: View){
        val request = ServiceBuilder.buildService(EndPoints::class.java)
        val call = request.postTest("teste")

        call.enqueue(object : Callback<OutputPost> {
            override fun onResponse(call: Call<OutputPost>, response: Response<OutputPost>){
                if(response.isSuccessful){
                    val c: OutputPost = response.body()!!
                    Toast.makeText(this@Feed, c.id.toString() + "-" + c.title, Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<OutputPost>, t: Throwable){
                Toast.makeText(this@Feed,"${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}

