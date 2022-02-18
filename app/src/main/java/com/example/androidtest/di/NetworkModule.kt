package com.example.androidtest.di
import com.example.androidtest.data.network.MovieApiClient
import com.example.androidtest.data.repository.MovieRepository
import com.example.androidtest.domain.Movie.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client: OkHttpClient = OkHttpClient.Builder().apply {
        addInterceptor(interceptor)
    }.build()



    @Provides
    @Named("BASE_URL")
    fun provideUrl() = "https://api.themoviedb.org/3/"

    @Provides
    @Named("BASE_URL_IMAGE")
    fun provideImageUrl() = "https://image.tmdb.org/t/p/w185/"

    @Singleton
    @Provides
    fun provideRetrofit(@Named("BASE_URL") baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideMovieApiClient(retrofit: Retrofit): MovieApiClient =
        retrofit.create(MovieApiClient::class.java)

    @Provides
    @Singleton
    fun providePopularMoviesUseCases(repository: MovieRepository): MovieUseCase {
        return MovieUseCase(GetAllPopularMovies(repository), UpdateImageMovie(repository),
            GetMyMovies(repository), InsertMovie(repository))
    }


}