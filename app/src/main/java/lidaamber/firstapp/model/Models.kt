package lidaamber.firstapp.model

import com.google.gson.annotations.SerializedName

/**
 * @author lidaamber
 */
class Film(var title: String, @SerializedName("episode_id") var episodeNumber: Int)

class FilmResults(val results: List<Film>)
