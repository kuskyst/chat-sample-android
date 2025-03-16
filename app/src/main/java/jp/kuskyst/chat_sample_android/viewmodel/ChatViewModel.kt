package jp.kuskyst.chat_sample_android.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.kuskyst.chat_sample_android.data.repository.ChatRepository
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val chatRepository: ChatRepository
) : ViewModel() {

    val messages: StateFlow<String> = chatRepository.messages

    fun sendMessage(message: String) {
        viewModelScope.launch {
            chatRepository.sendMessage(message)
        }
    }

    override fun onCleared() {
        super.onCleared()
        chatRepository.closeConnection()
    }
}
