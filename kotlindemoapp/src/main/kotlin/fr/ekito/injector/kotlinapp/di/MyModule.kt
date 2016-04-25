package fr.ekito.injector.kotlinapp.di

import fr.ekito.injector.Module
import fr.ekito.injector.kotlinapp.ws.GitHubService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by arnaud on 25/04/2016.
 */
class MyModule : Module() {
    override fun load() {
        val httpClient = httpClient()

        provide(httpClient, OkHttpClient::class.java)
        provide(githubWS(httpClient), GitHubService::class.java)
    }

    private fun httpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC

        val httpClient = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
        return httpClient
    }

    private fun githubWS(httpClient: OkHttpClient): GitHubService {

        val gsonConverterFactory = GsonConverterFactory.create()
        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .client(httpClient).addConverterFactory(gsonConverterFactory)
                .build()

        val service = retrofit.create<GitHubService>(GitHubService::class.java)
        return service
    }
}
