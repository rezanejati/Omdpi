package nejati.me.omdbapi.module

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Module
import dagger.Provides
import nejati.me.omdbapi.base.BaseApplication
import nejati.me.omdbapi.base.StaticValue
import nejati.me.omdbapi.webServices.api.RetroClient
import nejati.me.omdbapi.di.scope.CustomScope
import nejati.me.omdbapi.webServices.helper.Const
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Authors:
 * Reza Nejati <rn.nejati@gmail.com>
 * Copyright © 2020
 */
@Module
class ApiModule {

    @CustomScope
    @Provides
    internal fun provideRetrofit(gson: Gson,okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(Const.BASEURl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

            .baseUrl(Const.BASEURl)
            .client(okHttpClient)
            .build()
    }

    @CustomScope
    @Provides
    internal fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        return gsonBuilder.create()
    }


    @CustomScope
    @Provides
    internal fun provideComicsApi(retrofit: Retrofit): RetroClient {
        return retrofit.create(RetroClient::class.java)
    }

    @CustomScope
    @Provides
    internal fun provideOkhttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
        client.connectTimeout(StaticValue.TimeOut, TimeUnit.SECONDS)
        client.readTimeout(StaticValue.TimeOut, TimeUnit.SECONDS)
        client.writeTimeout(StaticValue.TimeOut, TimeUnit.SECONDS)
        client.addInterceptor(ChuckInterceptor(BaseApplication.get()))

        return client.build()
    }
}
