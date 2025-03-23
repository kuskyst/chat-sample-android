package jp.kuskyst.chat_sample_android.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tinder.scarlet.Message
import com.tinder.scarlet.WebSocket
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import jp.kuskyst.chat_sample_android.data.scarlet.ChatService
import jp.kuskyst.chat_sample_android.entity.ChatMessage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ChatViewModel2 @Inject constructor(
    private val chatService: ChatService
) : ViewModel() {

    private val _messages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val messages: StateFlow<List<ChatMessage>> = _messages.asStateFlow()

    private val disposable = CompositeDisposable()

    init {
        observeWebSocketEvents()
        observeMessages()
    }

    fun sendMessage(text: String) {
        if (text.isNotBlank()) {
            _messages.update { currentMessages ->
                currentMessages + ChatMessage(text, true)
            }
            chatService.sendMessage(text)
        }
    }

    private fun observeWebSocketEvents() {
        chatService.observeWebSocketEvent()
            .subscribe { event ->
                when (event) {
                    is WebSocket.Event.OnMessageReceived -> {
                        val text = event.message as Message.Text
                        if (_messages.value.isNotEmpty() &&
                                _messages.value.last().message != text.value.replace("\"", "")) {
                            _messages.update { currentMessages ->
                                currentMessages + ChatMessage(text.value.replace("\"", ""), false)
                            }
                        }
                    }
                    else -> {
                        Log.d("ChatViewModel", "Other event: $event")
                    }
                }
            }
    }

    private fun observeMessages() {
        val subscription = chatService.observeMessages()
            .subscribe(
                { message ->
                    if (_messages.value.isNotEmpty() &&
                            _messages.value.last().message != message.replace("\"", "")) {
                        _messages.update { currentMessages ->
                            currentMessages + ChatMessage(message.replace("\"", ""), false)
                        }
                    }
                },
                { error ->
                    Log.e("ChatViewModel", "Error receiving message", error)
                }
            )
        disposable.add(subscription)
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }

}
