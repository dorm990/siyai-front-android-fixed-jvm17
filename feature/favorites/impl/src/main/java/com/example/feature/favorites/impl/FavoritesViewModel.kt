package com.example.feature.favorites.impl

import androidx.lifecycle.ViewModel
import com.example.core.analytics.AnalyticsTracker
import com.example.core.db.FavoritesDao
import com.example.core.network.ProductDto
import com.example.core.network.ProductsApi
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import javax.inject.Inject

data class FavoritesState(
    val items: List<ProductDto> = emptyList(),
    val loading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val dao: FavoritesDao,
    private val api: ProductsApi,
    private val analytics: AnalyticsTracker
) : ViewModel(), ContainerHost<FavoritesState, Nothing> {

    override val container = container<FavoritesState, Nothing>(FavoritesState())

    init {
        analytics.trackScreen("Favorites")
        load()
    }

    fun load() = intent {
        reduce { state.copy(loading = true, error = null) }
        runCatching {
            val ids = dao.getAllIds()
            val items = ids.map { api.getProduct(it) }
            reduce { state.copy(items = items, loading = false) }
        }.onFailure {
            reduce { state.copy(loading = false, error = it.message ?: "Ошибка") }
        }
    }
}
