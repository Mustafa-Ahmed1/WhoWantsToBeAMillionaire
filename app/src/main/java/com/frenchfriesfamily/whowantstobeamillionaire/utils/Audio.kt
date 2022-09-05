package com.frenchfriesfamily.whowantstobeamillionaire.utils

import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import com.frenchfriesfamily.whowantstobeamillionaire.R

class Audio {
    var soundState = true

    fun runAudio(mediaPlayer: MediaPlayer) {
        mediaPlayer.start()
    }

    fun muteAudio(context: Context) {
        val mAudioManager = context.getSystemService(AudioManager::class.java)
        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0)
        soundState = false
    }

    fun unMuteAudio(context: Context) {
        val mAudioManager = context.getSystemService(AudioManager::class.java)
        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 100, 0)
        soundState = true
    }

    fun playButtonSound(context: Context) {
        MediaPlayer.create(context, R.raw.push_audio).start()
    }

}