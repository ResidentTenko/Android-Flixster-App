package org.test.flixster
import com.google.gson.annotations.SerializedName

/**
 * The Model for storing a single movie  from the API
 *
 * SerializedName tags MUST match the JSON response for the
 * object to correctly parse with the gson library.
 */

class NowPlayingMovies {

    @SerializedName("poster_path")
    var movieImageUrl: String? = null

    @SerializedName("original_title")
    var movieTitle: String? = null

    @SerializedName("overview")
    var movieDescription: String? = null
}