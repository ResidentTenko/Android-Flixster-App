package org.test.flixster

/**
 * The Model for storing a single movie  from the API
 *
 * SerializedName tags MUST match the JSON response for the
 * object to correctly parse with the gson library.
 */

class PlayingMovies {
    var movieImageUrl: String? = null
    var movieTitle: String? = null
    var movieDescription: String? = null
}