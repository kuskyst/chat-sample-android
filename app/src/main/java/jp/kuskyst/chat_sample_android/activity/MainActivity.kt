package jp.kuskyst.chat_sample_android.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import jp.kuskyst.chat_sample_android.ui.ChatScreen
import jp.kuskyst.chat_sample_android.viewmodel.ChatViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: ChatViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChatScreen(viewModel)
        }
    }
}
