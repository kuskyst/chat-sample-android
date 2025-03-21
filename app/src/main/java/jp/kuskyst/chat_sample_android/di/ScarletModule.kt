package jp.kuskyst.chat_sample_android.di

import com.tinder.scarlet.Scarlet
import com.tinder.scarlet.messageadapter.moshi.MoshiMessageAdapter
import com.tinder.scarlet.streamadapter.rxjava2.RxJava2StreamAdapterFactory
import com.tinder.scarlet.websocket.okhttp.newWebSocketFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jp.kuskyst.chat_sample_android.data.scarlet.ChatService
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ScarletModule {

    @Provides
    @Singleton
    fun provideScarlet(okHttpClient: OkHttpClient): Scarlet {
        return Scarlet.Builder()
            .webSocketFactory(okHttpClient.newWebSocketFactory("ws://10.0.2.2:8080/ws"))
            .addMessageAdapterFactory(MoshiMessageAdapter.Factory())
            .addStreamAdapterFactory(RxJava2StreamAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideChatService(scarlet: Scarlet): ChatService {
        return scarlet.create()
    }

}