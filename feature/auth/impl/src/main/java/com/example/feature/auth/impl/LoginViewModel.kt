package com.example.feature.auth.impl

import androidx.lifecycle.ViewModel
import com.example.core.analytics.AnalyticsTracker
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import javax.inject.Inject

data class LoginState(
    val login: String = "",
    val password: String = "",
    val error: String? = null,
    val loggedIn: Boolean = false
)

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val useCase: LoginUseCase,
    private val analytics: AnalyticsTracker
) : ViewModel(), ContainerHost<LoginState, Nothing> {

    override val container = container<LoginState, Nothing>(LoginState())

    init {
        analytics.trackScreen("Auth_Login")
    }

    fun onLoginChanged(v: String) = intent { reduce { state.copy(login = v) } }
    fun onPasswordChanged(v: String) = intent { reduce { state.copy(password = v) } }

    fun register() = intent {
        runCatching { useCase.register(state.login, state.password) }
            .onFailure { reduce { state.copy(error = it.message ?: "Ошибка") } }
    }

    fun login() = intent {
        val ok = runCatching { useCase.login(state.login, state.password) }.getOrDefault(false)
        reduce { state.copy(loggedIn = ok, error = if (ok) null else "Неверный логин/пароль") }
    }
}
