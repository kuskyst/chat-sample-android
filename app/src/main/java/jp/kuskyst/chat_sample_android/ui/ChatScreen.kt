package jp.kuskyst.chat_sample_android.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import jp.kuskyst.chat_sample_android.viewmodel.ChatViewModel

@Composable
fun ChatScreen(viewModel: ChatViewModel) {
    val messages by viewModel.messages.collectAsState()

    var input by remember { mutableStateOf(TextFieldValue("")) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Chat Messages", style = MaterialTheme.typography.h6)

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = messages, modifier = Modifier.weight(1f))

        Row {
            BasicTextField(
                value = input,
                onValueChange = { input = it },
                modifier = Modifier.weight(1f).padding(8.dp)
            )
            Button(onClick = {
                viewModel.sendMessage(input.text)
                input = TextFieldValue("")
            }) {
                Text("Send")
            }
        }
    }
}
