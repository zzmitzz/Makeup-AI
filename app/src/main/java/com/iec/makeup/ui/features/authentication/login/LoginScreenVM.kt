package com.iec.makeup.ui.features.authentication.login

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.iec.makeup.core.BaseViewModel
import com.iec.makeup.core.Reducer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import javax.inject.Inject


data class LoginScreenState(
    val isLoading: Boolean = false,
    val isAuthenticated: Boolean = false,
    val username: String?,
    val password: String?,
): Reducer.ViewState

sealed class LoginScreenEvent() : Reducer.ViewEvent{
    data object Login: LoginScreenEvent()
    data object SignUp: LoginScreenEvent()
    data object ForgotPassword: LoginScreenEvent()
    data class OnUsernameChange(val username: String): LoginScreenEvent()
    data class OnPasswordChange(val password: String): LoginScreenEvent()
    data class loadingDialog(val loading: Boolean): LoginScreenEvent()
}
sealed class LoginScreenEffect() : Reducer.ViewEffect{
    data class showToast(val message: String): LoginScreenEffect()
    data class navigateToHome(val data: String): LoginScreenEffect()
    data object navigateToSignUp: LoginScreenEffect()
}

class LoginScreenReducer @Inject constructor(): Reducer<LoginScreenState, LoginScreenEvent, LoginScreenEffect> {
    override fun reduce(currentState: LoginScreenState, event: LoginScreenEvent): Pair<LoginScreenState, LoginScreenEffect?> {
        when(event){
            LoginScreenEvent.ForgotPassword -> {
                return currentState to null
            }
            LoginScreenEvent.Login -> {
                return currentState to null
            }
            LoginScreenEvent.SignUp -> {
                return currentState to null
            }

            is LoginScreenEvent.OnPasswordChange -> {
                val newState = currentState.copy(
                    password = event.password
                )
                return newState to null
            }
            is LoginScreenEvent.OnUsernameChange -> {
                val newState = currentState.copy(
                    username = event.username
                )
                return newState to null
            }
            is LoginScreenEvent.loadingDialog -> {
                val newState = currentState.copy(
                    isLoading = event.loading
                )
                return newState to null
            }
        }
    }

}
@HiltViewModel
class LoginScreenVM @Inject constructor(
) : BaseViewModel<LoginScreenState, LoginScreenEvent, LoginScreenEffect>(
    initialState = LoginScreenState(
        isLoading = false,
        isAuthenticated = false,
        username = null,
        password = null
    ),
    reducer = LoginScreenReducer()
) {
    private val coroutineScope = viewModelScope + CoroutineExceptionHandler { _, throwable ->
        Log.d(TAG, "coroutineScope: ${throwable.message}")
    }
    fun doLogin() {
        coroutineScope.launch {
            Log.d(TAG, "doLogin: ")
            sendEvent(LoginScreenEvent.loadingDialog(true))
            delay(2000)
            sendEvent(LoginScreenEvent.Login)
            sendEvent(LoginScreenEvent.loadingDialog(false))
        }
    }

    fun inputUsername(inputUsername: String) {
        sendEvent(LoginScreenEvent.OnUsernameChange(inputUsername))
    }

    fun inputPassword(inputPassword: String) {
        sendEvent(LoginScreenEvent.OnPasswordChange(inputPassword))
    }


    companion object {
        const val TAG = "LoginScreenVM"

    }
}