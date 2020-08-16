package com.developer.chithlal.quiz.dependencyinjection

import com.developer.chithlal.quiz.network.QuizApiInterface
import com.developer.chithlal.quiz.util.DefaultConfig
import com.developer.chithlal.quiz.network.NetworkHandler
import com.squareup.okhttp.logging.HttpLoggingInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class RetrofitModule() {

    @Provides
    fun provideApiInterface(retrofit: Retrofit): QuizApiInterface =
        retrofit.create(QuizApiInterface::class.java)

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(DefaultConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

   /* @Provides
    fun provideClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .build()*/
    @Provides
    fun provideLoggingIntercepter(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    @Provides
    fun provideNetworkHandler(apiInterface:QuizApiInterface): NetworkHandler {
       return NetworkHandler(apiInterface)
   }
}