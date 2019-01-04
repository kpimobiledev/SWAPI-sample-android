package lidaamber.firstapp.api

import lidaamber.firstapp.model.FilmResults
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
 * @author lidaamber
 */
private const val url = "https://swapi.co/api/"

val RETROFIT = Retrofit.Builder()
    .baseUrl(url)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

interface Api {

    @GET("films")
    fun getFilms(): Call<FilmResults>
}