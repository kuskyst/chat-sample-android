package jp.kuskyst.chat_sample_android.data.scarlet

import com.tinder.scarlet.WebSocket
import com.tinder.scarlet.ws.Receive
import com.tinder.scarlet.ws.Send
import io.reactivex.Flowable

interface ChatService {
    @Receive
    fun observeWebSocketEvent(): Flowable<WebSocket.Event>

    @Send
    fun sendMessage(message: String)

    @Receive
    fun observeMessages(): Flowable<String>
}