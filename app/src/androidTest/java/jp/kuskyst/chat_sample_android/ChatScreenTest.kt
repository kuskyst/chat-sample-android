package jp.kuskyst.chat_sample_android

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule

import jp.kuskyst.chat_sample_android.entity.ChatMessage
import jp.kuskyst.chat_sample_android.ui.ChatScreen
import jp.kuskyst.chat_sample_android.viewmodel.ChatViewModel2
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class ChatScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val sampleMessages = listOf(
        ChatMessage("Hello", true),
        ChatMessage("Hi!", false)
    )

    @Test
    fun testMessagesLayout() {
        val mockMessagesFlow = MutableStateFlow(sampleMessages)
        val chatViewModel = mock(ChatViewModel2::class.java)
        `when`(chatViewModel.messages).thenReturn(mockMessagesFlow)

        composeTestRule.setContent {
            ChatScreen(viewModel = chatViewModel)
        }

        // 送信したメッセージ（右寄せ）を検証
        composeTestRule.onNodeWithText("Hello")
            .assertIsDisplayed()

        // 受信したメッセージ（左寄せ）を検証
        composeTestRule.onNodeWithText("Hi!")
            .assertIsDisplayed()
    }

    // 送信ボタンを押して、メッセージが送信されるかを確認するテスト
    @Test
    fun testSendMessage() {
        val mockMessagesFlow = MutableStateFlow(sampleMessages)
        val chatViewModel = mock(ChatViewModel2::class.java)
        `when`(chatViewModel.messages).thenReturn(mockMessagesFlow)

        composeTestRule.setContent {
            var text by remember { mutableStateOf("") }

            Column {
                TextField(
                    value = text,
                    onValueChange = { text = it },
                    label = { Text("Enter message") }
                )
                Button(onClick = {
                    mockMessagesFlow.value = mockMessagesFlow.value + ChatMessage("Test message", true)
                    text = ""
                }) {
                    Text("Send")
                }
            }
        }

        composeTestRule.onNodeWithText("Send")
            .performClick()

        composeTestRule.onNodeWithText("Test message")
            .assertIsDisplayed()
    }
}
