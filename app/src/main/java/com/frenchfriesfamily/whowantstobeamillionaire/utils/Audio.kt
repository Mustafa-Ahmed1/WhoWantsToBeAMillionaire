package com.frenchfriesfamily.whowantstobeamillionaire.utils

import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer

//TODO: fix audio problems
class Audio {
    var muteState = true

    fun runAudio(mediaPlayer: MediaPlayer) {
        mediaPlayer.start()
    }

    fun pauseAudio(mediaPlayer: MediaPlayer) {
        mediaPlayer.pause()
    }

    fun muteAudio(context: Context) {
        val mAudioManager = context.getSystemService(AudioManager::class.java)
        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0)
        muteState = false
    }

    fun unMuteAudio(context: Context) {
        val mAudioManager = context.getSystemService(AudioManager::class.java)
        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 100, 0)
        muteState = true
    }
}