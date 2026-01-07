package com.example.feature.auth.impl

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import com.example.feature.feed.api.FeedEntry
import org.orbitmvi.orbit.compose.collectAsState

class LoginScreen : Screen {
    @Composable
    override fun Content() {
        val vm = getViewModel<LoginViewModel>()
        val s by vm.collectAsState()
        val nav = LocalNavigator.current

        LaunchedEffect(s.loggedIn) {
            if (s.loggedIn) nav?.replaceAll(FeedEntry.start())
        }

        Column(
            Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Auth", style = MaterialTheme.typography.headlineMedium)

            OutlinedTextField(
                value = s.login,
                onValueChange = vm::onLoginChanged,
                label = { Text("Login") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = s.password,
                onValueChange = vm::onPasswordChanged,
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth()
            )

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Button(onClick = vm::register) { Text("Register") }
                Button(onClick = vm::login) { Text("Login") }
            }

            s.error?.let { Text(it, color = MaterialTheme.colorScheme.error) }
        }
    }
}
