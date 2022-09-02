package com.frenchfriesfamily.whowantstobeamillionaire.utils

import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer

//TODO: fix audio problems
object Audio {
    var muteState = 100
    fun runAudio(mediaPlayer : MediaPlayer){
        mediaPlayer.start()
    }
    fun pauseAudio(mediaPlayer : MediaPlayer){
        mediaPlayer.pause()
    }
    fun muteAudio(context: Context){
        var mAudioManager = context.getSystemService(AudioManager::class.java)
        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0)
        muteState = 0
    }
    fun unmuteAudio(context: Context){
        var mAudioManager = context.getSystemService(AudioManager::class.java)
        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 100, 0)
        muteState = 100
    }
}