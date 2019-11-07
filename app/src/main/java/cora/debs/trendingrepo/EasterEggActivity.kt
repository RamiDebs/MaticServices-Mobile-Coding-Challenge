package cora.debs.trendingrepo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.media.MediaPlayer

class EasterEggActivity : AppCompatActivity() {
    var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mediaPlayer = MediaPlayer.create(this, R.raw.swaggg)
        setContentView(R.layout.activity_easter_egg)
        mediaPlayer?.start()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        mediaPlayer?.stop()
    }
}
