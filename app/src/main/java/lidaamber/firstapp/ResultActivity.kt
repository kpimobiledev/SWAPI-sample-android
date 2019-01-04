package lidaamber.firstapp

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_results.*
import kotlinx.android.synthetic.main.film_item.view.*
import lidaamber.firstapp.api.Api
import lidaamber.firstapp.api.RETROFIT
import lidaamber.firstapp.model.Film
import lidaamber.firstapp.model.FilmResults
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.BufferedReader
import java.io.File

/**
 * @author lidaamber
 */
class ResultActivity : AppCompatActivity() {

    companion object {
        const val FILENAME = "films.txt"
    }

    private val api = RETROFIT.create(Api::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        supportActionBar?.title = "Films"

        if (!showFilmsFromFile()) getFilmsFromServer()
    }

    private fun showFilms(films: List<Film>) {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = FilmAdapter(films)
    }

    private fun getFilmsFromServer() {
        api.getFilms().enqueue(object : Callback<FilmResults> {
            override fun onFailure(call: Call<FilmResults>, t: Throwable) {
                runOnUiThread { Toast.makeText(this@ResultActivity, "Something went wrong", Toast.LENGTH_LONG).show() }
            }

            override fun onResponse(call: Call<FilmResults>, response: Response<FilmResults>) {
                response.body()?.let { filmResults ->
                    val films = filmResults.results.sortedBy { it.episodeNumber }
                    runOnUiThread { showFilms(films) }
                    saveFilmsToFile(films)
                }
            }

        })
    }

    private fun showFilmsFromFile() : Boolean {
        val file = File(filesDir, FILENAME)

        if (!file.exists()) {
            return false
        }

        val input = openFileInput(FILENAME)

        val reader = BufferedReader(input.reader())
        val films = reader.readLines().map { line ->
            val index = line.indexOf(":")
            val title = line.substring(0, index)
            val number = line.substring(index + 1, line.length).toInt()
            return@map Film(title, number)
        }

        input.close()

        showFilms(films)

        return true
    }

    fun saveFilmsToFile(films: List<Film>) {
        val file = File(filesDir, FILENAME)
        if (!file.exists()) {
            file.createNewFile()
        }

        val output = openFileOutput(FILENAME, Context.MODE_PRIVATE)

        val filmsData = films.map { film ->
            return@map "${film.title}:${film.episodeNumber}"
        }.joinToString("\n")

        output.write(filmsData.toByteArray())
        output.close()
    }
}

class FilmAdapter(private val films: List<Film>) : RecyclerView.Adapter<FilmAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.film_item, parent, false)

        return Holder(v)
    }

    override fun getItemCount() = films.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val currentFilm = films[position]
        holder.itemView.filmNameText.text = currentFilm.title
        holder.itemView.filmEpisodeText.text = currentFilm.episodeNumber.toString()
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView)

}