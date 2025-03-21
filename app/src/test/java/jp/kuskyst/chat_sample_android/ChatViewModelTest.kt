package jp.kuskyst.chat_sample_android

import com.tinder.scarlet.Message
import com.tinder.scarlet.WebSocket
import io.reactivex.subjects.PublishSubject
import jp.kuskyst.chat_sample_android.data.scarlet.ChatService
import jp.kuskyst.chat_sample_android.viewmodel.ChatViewModel2

import org.junit.Test
import org.mockito.Mockito.*

class ChatViewModelTest {

    private val mockChatService = mock(ChatService::class.java)
    private val messageSubject = PublishSubject.create<WebSocket.Event.OnMessageReceived>()

    @Test
    fun testMessagesReceived() {
        //`when`(mockChatService.observeWebSocketEvent()).thenReturn(messageSubject.toFlowable())
        val viewModel = ChatViewModel2(mockChatService)
        messageSubject.onNext(WebSocket.Event.OnMessageReceived(Message.Text("Hello from server")))
        // メッセージが ViewModel に反映されたかを確認
        // assert(viewModel.messages.value.contains("Hello from server"))
    }

    @Test
    fun testSendMessage() {
        val viewModel = ChatViewModel2(mockChatService)
        viewModel.sendMessage("Hello")
        // sendMessage が呼ばれたことを確認
        // verify(mockChatService).sendMessage("Hello")
    }
}
