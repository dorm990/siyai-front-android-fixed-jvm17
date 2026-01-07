package com.example.feature.details.impl

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import coil.compose.AsyncImage

class DetailsScreen(private val productId: Int) : Screen {
    @Composable
    override fun Content() {
        val vm = getViewModel<DetailsViewModel>()
        var tick by remember { mutableStateOf(0) }

        LaunchedEffect(productId) { vm.load(productId) { tick++ } }

        Column(Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            AsyncImage(model = vm.thumb, contentDescription = null, modifier = Modifier.fillMaxWidth().height(220.dp))
            Text(vm.title, style = MaterialTheme.typography.headlineSmall)
            Text(vm.desc)
            Button(onClick = { vm.toggle(productId) { tick++ } }) {
                Text(if (vm.isFav) "Remove favorite" else "Add favorite")
            }
        }
    }
}
