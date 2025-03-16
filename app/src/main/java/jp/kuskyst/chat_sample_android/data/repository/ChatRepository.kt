package jp.kuskyst.chat_sample_android.data.repository

import jp.kuskyst.chat_sample_android.data.websocket.ChatWebSocketListener
import jp.kuskyst.chat_sample_android.data.websocket.WebSocketManager
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChatRepository @Inject constructor(
    private val webSocketManager: WebSocketManager
) {
    private val listener = ChatWebSocketListener()

    init {
        webSocketManager.connect(listener)
    }

    val messages: StateFlow<String> = listener.messageFlow

    fun sendMessage(message: String) {
        webSocketManager.sendMessage(message)
    }

    fun closeConnection() {
        webSocketManager.close()
    }
}
