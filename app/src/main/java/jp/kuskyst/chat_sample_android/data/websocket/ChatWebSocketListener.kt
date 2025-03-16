package jp.kuskyst.chat_sample_android.data.websocket

import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString
import kotlinx.coroutines.flow.MutableStateFlow

class ChatWebSocketListener : WebSocketListener() {

    val messageFlow = MutableStateFlow<String>("")

    override fun onOpen(webSocket: WebSocket, response: Response) {
        println("WebSocket Opened")
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        messageFlow.value = text
    }

    override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
        messageFlow.value = bytes.utf8()
    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        webSocket.close(1000, null)
        println("WebSocket Closing: $reason")
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        println("WebSocket Error: ${t.message}")
    }
}
