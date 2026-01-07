package com.example.feature.feed.impl

import androidx.lifecycle.ViewModel
import com.example.core.analytics.AnalyticsTracker
import com.example.core.db.FavoritesDao
import com.example.core.network.ProductDto
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import javax.inject.Inject

data class FeedState(
    val items: List<ProductDto> = emptyList(),
    val favIds: Set<Int> = emptySet(),
    val loading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val getProducts: GetProductsUseCase,
    private val favoritesDao: FavoritesDao,
    private val analytics: AnalyticsTracker
) : ViewModel(), ContainerHost<FeedState, Nothing> {

    override val container = container<FeedState, Nothing>(FeedState())

    init {
        analytics.trackScreen("Feed")
        load()
    }

    fun load() = intent {
        reduce { state.copy(loading = true, error = null) }
        runCatching {
            val items = getProducts.execute()
            val fav = favoritesDao.getAllIds().toSet()
            reduce { state.copy(items = items, favIds = fav, loading = false) }
        }.onFailure {
            reduce { state.copy(loading = false, error = it.message ?: "Ошибка") }
        }
    }
}
