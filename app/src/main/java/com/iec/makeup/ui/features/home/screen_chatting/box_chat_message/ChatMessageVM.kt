package com.iec.makeup.ui.features.home.screen_chatting.box_chat_message

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.viewModelScope
import com.iec.makeup.core.BaseViewModel
import com.iec.makeup.core.Reducer
import com.iec.makeup.core.model.Message
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.plus
import javax.inject.Inject


data class MessageScreenState(
    val chats: SnapshotStateList<Message> = mutableStateListOf(),
    val userInput: String? = null,
    val targetIsTyping: Boolean = false,
) : Reducer.ViewState


sealed class MessageScreenEvent : Reducer.ViewEvent {
    data class InputMessageChange(val message: String) : MessageScreenEvent()
    data class SendMessage(val message: Message) : MessageScreenEvent()
    data object RefreshMessage : MessageScreenEvent()
}

sealed class MessageScreenEffect : Reducer.ViewEffect {
    data class ShowToast(val message: String) : MessageScreenEffect()
    data class ShowError(val error: String) : MessageScreenEffect()
}

class MessageScreenReducer : Reducer<MessageScreenState, MessageScreenEvent, MessageScreenEffect> {
    override fun reduce(
        currentState: MessageScreenState,
        event: MessageScreenEvent
    ): Pair<MessageScreenState, MessageScreenEffect?> {
        return when (event) {
            is MessageScreenEvent.InputMessageChange -> {
                return currentState.copy(
                    userInput = event.message
                ) to null
            }

            is MessageScreenEvent.SendMessage -> {
                val newChats = currentState.chats.apply {
                    add(event.message)
                }
                return currentState.copy(
                    chats = newChats
                ) to null
            }

            is MessageScreenEvent.RefreshMessage -> {
                return currentState to null
            }

            else -> {
                currentState to null
            }
        }
    }
}

@HiltViewModel
class ChatMessageVM @Inject constructor(

) : BaseViewModel<MessageScreenState, MessageScreenEvent, MessageScreenEffect>(
    initialState = MessageScreenState(),
    reducer = MessageScreenReducer()
) {

    private val scope = viewModelScope + CoroutineExceptionHandler { _, error ->
        Log.d("ChatMessageVM", "Error: ${error.message}")
    }

    fun onMessageChange(message: String) {
        sendEvent(MessageScreenEvent.InputMessageChange(message))
    }

    fun onSendMessage() {
        if (state.value.userInput != null) {
            val userMessage = Message(
                message = state.value.userInput!!,
                isFromUser = true,
                timestamp = System.currentTimeMillis()
            )
            sendEvent(
                MessageScreenEvent.SendMessage(
                    userMessage
                )
            )

        }
    }

}