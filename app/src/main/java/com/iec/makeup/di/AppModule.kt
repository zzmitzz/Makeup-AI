package com.iec.makeup.di

import android.graphics.Insets.add
import com.iec.makeup.core.DataStoreInterface
import com.iec.makeup.core.PersistentState
import com.iec.makeup.core.PreferenceKeys
import com.iec.makeup.core.utils.Constants.BASE_URL
import com.iec.makeup.core.utils.Constants.TIME_OUT
import com.iec.makeup.data.remote.api.AuthEndpoint
import com.iec.makeup.data.repository.AuthRepository
import com.iec.makeup.data.repository.AuthRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.http.headers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.Duration
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(dataStore: DataStoreInterface): Retrofit {
        var token: String? = null
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val authInterceptor = Interceptor { chain ->
            val request = chain.request().newBuilder()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer $token")
                .build()
            chain.proceed(request)
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(authInterceptor)
            .connectTimeout(TIME_OUT, TimeUnit.MILLISECONDS)
            .build()


        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthEndpoint(retrofit: Retrofit): AuthEndpoint =
        retrofit.create(AuthEndpoint::class.java)
}

@InstallIn(SingletonComponent::class)
@Module
abstract class ImplementationsModule {

    @Binds
    abstract fun bindAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository

    @Binds
    abstract fun bindDataStore(dataStore: PersistentState): DataStoreInterface
}