package com.example.wallpaperapp.di

import com.example.wallpaperapp.Utils.Constants.BASE_URL
import com.example.wallpaperapp.data.api.PicSumApi
import com.example.wallpaperapp.data.api.WallpaperRepostiryImpl
import com.example.wallpaperapp.domain.repository.WallpaperRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

// Step 1 CLEAN PROJECT
@Module
@InstallIn(SingletonComponent::class)
interface AppModule {

    companion object {
        @Provides
        @Singleton
        fun provideRetrofitApi(): PicSumApi {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(PicSumApi::class.java)
        }

        @Provides
        @Singleton
        fun provideWallpaperRepositoryImpl(picSumApi: PicSumApi): WallpaperRepostiryImpl {
            return WallpaperRepostiryImpl(picSumApi)
        }
    }

    @Binds
    @Singleton
    fun bindWallpaperRepository(repositoryImpl: WallpaperRepostiryImpl): WallpaperRepository
}