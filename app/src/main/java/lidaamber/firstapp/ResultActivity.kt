package lidaamber.firstapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_results.*
import kotlinx.android.synthetic.main.film_item.view.*
import lidaamber.firstapp.model.Film

/**
 * @author lidaamber
 */
class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        supportActionBar?.title = "Films"

        val testList = listOf(
            Film("First", 1),
            Film("Second", 2)
        )

        showFilms(testList)
    }

    private fun showFilms(films: List<Film>) {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = FilmAdapter(films)
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