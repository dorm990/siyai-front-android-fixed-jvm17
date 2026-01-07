package com.example.siyai

import android.content.Context
import android.os.Bundle
import com.example.core.analytics.AnalyticsTracker
import com.google.firebase.analytics.FirebaseAnalytics
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

class FirebaseAnalyticsTracker(private val fa: FirebaseAnalytics) : AnalyticsTracker {
    override fun trackScreen(screenName: String) {
        val b = Bundle().apply { putString("screen_name", screenName) }
        fa.logEvent("screen_open", b)
    }
}

@Module
@InstallIn(SingletonComponent::class)
object AnalyticsModule {
    @Provides @Singleton
    fun provideFirebaseAnalytics(@ApplicationContext ctx: Context): FirebaseAnalytics =
        FirebaseAnalytics.getInstance(ctx)

    @Provides @Singleton
    fun provideTracker(fa: FirebaseAnalytics): AnalyticsTracker = FirebaseAnalyticsTracker(fa)
}
