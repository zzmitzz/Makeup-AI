package com.iec.makeup.ui.features.authentication

import androidx.compose.runtime.Immutable
import com.iec.makeup.core.BaseViewModel
import com.iec.makeup.core.Reducer
import com.iec.makeup.core.model.User
import dagger.hilt.android.lifecycle.HiltViewModel

@Immutable
data class AuthScreenState(
    val isLoading: Boolean = false,
    val userName: String = "",
    val password: String = "",
    val showPassword: Boolean = false
): Reducer.ViewState

@Immutable
sealed class AuthScreenEvent : Reducer.ViewEvent {
    data class Loading(val isLoading: Boolean) : AuthScreenEvent()
    data class UserNameChanged(val userName: String) : AuthScreenEvent()
    data class PasswordChanged(val password: String) : AuthScreenEvent()
    data class Login(val user: User) : AuthScreenEvent()
    data object Register : AuthScreenEvent()
    data class ShowPassword(val showPassword: Boolean) : AuthScreenEvent()
}


sealed class AuthScreenEffect : Reducer.ViewEffect {
    data object NavigateToRegister : AuthScreenEffect()
    data class NavigateToHome(val userName: String) : AuthScreenEffect()
}
class AuthScreenReducer : Reducer<AuthScreenState, AuthScreenEvent, AuthScreenEffect> {
    override fun reduce(
        currentState: AuthScreenState,
        event: AuthScreenEvent
    ): Pair<AuthScreenState, AuthScreenEffect?> {
        when(event){
            is AuthScreenEvent.UserNameChanged -> {
                return currentState.copy(userName = event.userName) to null
            }
            is AuthScreenEvent.PasswordChanged -> {
                return currentState.copy(password = event.password) to null
            }
            is AuthScreenEvent.Login -> {
                return currentState to AuthScreenEffect.NavigateToHome(currentState.userName)
            }
            is AuthScreenEvent.Register -> {
                return currentState to AuthScreenEffect.NavigateToRegister
            }
            is AuthScreenEvent.ShowPassword -> {
                return currentState.copy(showPassword = !currentState.showPassword) to null
            }

            is AuthScreenEvent.Loading -> {
                return currentState.copy(isLoading = event.isLoading) to null
            }
        }
    }
    private fun
}




@HiltViewModel
class AuthScreenVM : BaseViewModel<AuthScreenState, AuthScreenEvent, AuthScreenEffect>(
    reducer = AuthScreenReducer(),
    initialState = AuthScreenState()
){
}