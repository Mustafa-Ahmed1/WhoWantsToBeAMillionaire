package com.frenchfriesfamily.whowantstobeamillionaire.utils

import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import com.frenchfriesfamily.whowantstobeamillionaire.R

class Audio {

    private var soundState = true

    fun muteAudio(context: Context) {
        val audioManager = context.getSystemService(AudioManager::class.java)
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0)
        soundState = false
    }

    fun unMuteAudio(context: Context) {
        val audioManager = context.getSystemService(AudioManager::class.java)
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 100, 0)
        soundState = true
    }

    fun playButtonSound(context: Context) {
        MediaPlayer.create(context, R.raw.click_button_audio).start()
    }

}