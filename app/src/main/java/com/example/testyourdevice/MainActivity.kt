package com.example.testyourdevice

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.AudioManager
import android.media.AudioTrack
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnRed = findViewById<Button>(R.id.button1)
        val btnGreen = findViewById<Button>(R.id.button2)
        val btnBlue = findViewById<Button>(R.id.button3)
        val btnReceiver = findViewById<Button>(R.id.button4)
        val btnSpeaker = findViewById<Button>(R.id.button5)
        val btnVibration = findViewById<Button>(R.id.button6)
        val btnPhoto = findViewById<Button>(R.id.button7)
        val btnVideo = findViewById<Button>(R.id.button8)
        val btnExit = findViewById<Button>(R.id.button9)

        btnRed.setOnClickListener{
            val bg = findViewById(R.id.background) as androidx.constraintlayout.widget.ConstraintLayout
            bg.setBackgroundColor(Color.RED)
            Executors.newSingleThreadScheduledExecutor().schedule({
                bg.setBackgroundColor(Color.WHITE)
            }, 2, TimeUnit.SECONDS)
        }

        btnGreen.setOnClickListener{
            val bg = findViewById(R.id.background) as androidx.constraintlayout.widget.ConstraintLayout
            bg.setBackgroundColor(Color.GREEN)
            Executors.newSingleThreadScheduledExecutor().schedule({
                bg.setBackgroundColor(Color.WHITE)
            }, 2, TimeUnit.SECONDS)
        }

        btnBlue.setOnClickListener{
            val bg = findViewById(R.id.background) as androidx.constraintlayout.widget.ConstraintLayout
            bg.setBackgroundColor(Color.BLUE)
            Executors.newSingleThreadScheduledExecutor().schedule({
                bg.setBackgroundColor(Color.WHITE)
            }, 2, TimeUnit.SECONDS)
        }

        btnReceiver.setOnClickListener {
            val audioManager: AudioManager = getSystemService(AUDIO_SERVICE) as AudioManager
            audioManager?.setMode(AudioManager.MODE_IN_CALL)
            audioManager?.setSpeakerphoneOn(false)

            var mediaPlayer: MediaPlayer? = MediaPlayer.create(this, R.raw.beep)
            mediaPlayer?.start()
            Executors.newSingleThreadScheduledExecutor().schedule({
                mediaPlayer?.stop()
            }, 2, TimeUnit.SECONDS)
        }

        btnSpeaker.setOnClickListener {
            var mediaPlayer: MediaPlayer? = MediaPlayer.create(this, R.raw.beep)
            mediaPlayer?.start()
            Executors.newSingleThreadScheduledExecutor().schedule({
                mediaPlayer?.stop()
            }, 2, TimeUnit.SECONDS)
        }

        btnVibration.setOnClickListener {
            val v = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE))
            } else {
                //deprecated in API 26
                v.vibrate(500)
            }
        }

        btnPhoto.setOnClickListener {
            val intent = Intent("android.media.action.IMAGE_CAPTURE")
            startActivity(intent)
        }

        btnVideo.setOnClickListener {
            startActivity(Intent("android.media.action.VIDEO_CAPTURE"))
        }

        btnExit.setOnClickListener {
            finish()
            System.exit(0)
        }
    }
}