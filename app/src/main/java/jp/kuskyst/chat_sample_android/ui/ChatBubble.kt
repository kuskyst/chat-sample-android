package jp.kuskyst.chat_sample_android.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import jp.kuskyst.chat_sample_android.entity.ChatMessage

@Composable
fun ChatBubble(message: ChatMessage) {

    Row(
        horizontalArrangement = if (message.isSend) Arrangement.End else Arrangement.Start,
        modifier = Modifier.fillMaxWidth().padding(8.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(if (message.isSend) Color.Blue else Color.Green)
                .padding(12.dp)
        ) {
            Text(
                text = message.message,
                color = if (message.isSend) Color.White else Color.Black,
                style = MaterialTheme.typography.body1
            )
        }
    }
}
