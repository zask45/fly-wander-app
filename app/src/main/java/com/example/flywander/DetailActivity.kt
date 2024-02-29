package com.example.flywander

import android.os.Build
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ATTRACTION_PLACE = "extra_attraction_place"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val data = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_ATTRACTION_PLACE, AttractionPlace::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_ATTRACTION_PLACE)
        }

        val title: TextView = findViewById(R.id.tv_detail_page_title)
        val location: TextView = findViewById(R.id.tv_detail_page_location)
        val description: TextView = findViewById(R.id.tv_detail_page_description)
        val image: ImageView = findViewById(R.id.iv_detail_page_image)

        val dataTitle = data?.name.toString()
        val dataLocation = data?.location.toString()
        val dataDescription = data?.description.toString()

        title.text = dataTitle
        location.text = dataLocation
        description.text = dataDescription
        image.setImageResource(data!!.photo)

        val shareButton: ImageButton = findViewById(R.id.action_share)
        shareButton.setOnClickListener {
            // implementasi share data ketika shareButton ditekan
            shareData(dataTitle, dataLocation, dataDescription)
        }

    }

    fun shareData(title: String, location: String, description: String) {
        val subject = "FlyWander - $title (Preview)"
        val content = "[Gunakan aplikasi FlyWander untuk melihat konten lebih lanjut!]\n\n" +
                "\nPREVIEW '${title.uppercase()}'\n" +
                "Lokasi: $location\n" +
                "\n$description"
        val shareIntent = ShareCompat.IntentBuilder.from(this)
            .setType("message/rfc822")
            .setSubject(subject)
            .setText(content)
            .intent
        startActivity(shareIntent)
    }
}