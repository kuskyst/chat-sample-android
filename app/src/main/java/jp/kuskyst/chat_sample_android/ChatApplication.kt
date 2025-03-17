package jp.kuskyst.chat_sample_android

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ChatApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        val dexOutputDir = codeCacheDir
        dexOutputDir.setReadOnly()
    }
}