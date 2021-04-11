package ipvc.estg.cm_tp01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<ImageView>(R.id.imageView4)
        button.setOnClickListener{
            val intent = Intent(this, Feed::class.java)
            startActivity(intent)
        }
    }
}