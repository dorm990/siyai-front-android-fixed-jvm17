package com.example.feature.feed.impl

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import coil.compose.AsyncImage
import com.example.feature.details.api.DetailsEntry
import com.example.feature.favorites.api.FavoritesEntry
import org.orbitmvi.orbit.compose.collectAsState

class FeedScreen : Screen {
    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    override fun Content() {
        val vm = getViewModel<FeedViewModel>()
        val s by vm.collectAsState()
        val nav = LocalNavigator.current

        Column(Modifier.fillMaxSize()) {
            TopAppBar(
                title = { Text("Feed") },
                actions = {
                    if (BuildConfig.DEBUG) {
                        TextButton(onClick = { throw RuntimeException("Test Crash from Feed") }) {
                            Text("Crash")
                        }
                    }
                    TextButton(onClick = { nav?.push(FavoritesEntry.start()) }) { Text("Favorites") }
                }
            )

            when {
                s.loading -> Box(Modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
                    CircularProgressIndicator()
                }
                s.error != null -> Text("Ошибка: ${'$'}{s.error}", modifier = Modifier.padding(16.dp))
                else -> LazyColumn(Modifier.fillMaxSize().padding(12.dp)) {
                    items(s.items) { p ->
                        Card(
                            Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                                .clickable { nav?.push(DetailsEntry.start(p.id)) }
                        ) {
                            Row(Modifier.padding(12.dp), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                                AsyncImage(model = p.thumbnail, contentDescription = null, modifier = Modifier.size(72.dp))
                                Column {
                                    Text(p.title, style = MaterialTheme.typography.titleMedium)
                                    Text(p.description, maxLines = 2)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
