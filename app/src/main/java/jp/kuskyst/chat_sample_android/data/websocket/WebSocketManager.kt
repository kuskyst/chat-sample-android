package jp.kuskyst.chat_sample_android.data.websocket

import okhttp3.*
import okio.ByteString
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WebSocketManager @Inject constructor() {

    private val client = OkHttpClient()
    private var webSocket: WebSocket? = null

    private val request = Request.Builder().url("ws://localhost:8080/ws").build()

    private var listener: WebSocketListener? = null

    fun connect(listener: WebSocketListener) {
        this.listener = listener
        webSocket = client.newWebSocket(request, listener)
    }

    fun sendMessage(message: String) {
        webSocket?.send(message)
    }

    fun close() {
        webSocket?.close(1000, "Goodbye")
    }
}
