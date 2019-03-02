package com.gromyk.playground.api

import com.gromyk.playground.api.services.GenreService
import com.gromyk.playground.api.services.TmdbService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


//ApiFactory to create TMDB Api
object ApiFactory : KoinComponent {
    // Creating Auth Interceptor to add api_key query in front of all the requests.
    private val authInterceptor = Interceptor { chain ->
        val newUrl = chain.request().url()
            .newBuilder()
            .addQueryParameter("api_key", ApiCredentials.tmdbApiKey)
            .build()

        val newRequest = chain.request()
            .newBuilder()
            .url(newUrl)
            .build()
        chain.proceed(newRequest)
    }

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    //OkHttpClient for building http request url
    private val tmdbClient =
        OkHttpClient()
            .newBuilder()
            .addInterceptor(authInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()

    fun retrofit(): Retrofit = Retrofit.Builder()
        .client(tmdbClient)
        .baseUrl(BaseUrl.BASE_REST_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val tmdbApi: TmdbService by inject()
    val genresApi: GenreService by inject()
}