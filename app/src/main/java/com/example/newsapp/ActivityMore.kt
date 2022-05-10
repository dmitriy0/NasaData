package com.example.newsapp

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso


class ActivityMore : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more)

        val title = findViewById<TextView>(R.id.title)
        val author = findViewById<TextView>(R.id.author)
        val description = findViewById<TextView>(R.id.description)
        val image = findViewById<ImageView>(R.id.imageView)

        title.text = intent.getStringExtra("title")
        author.text = intent.getStringExtra("author")
        description.text = intent.getStringExtra("description")
        description.movementMethod = ScrollingMovementMethod()
        Picasso.get().load(intent.getStringExtra("imageUrl")).into(image)
    }
}