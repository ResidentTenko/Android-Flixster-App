package org.test.flixster

import android.widget.Toast
import androidx.fragment.app.Fragment


// --------------------------------//
// CHANGE THIS TO BE YOUR API KEY  //
// --------------------------------//
private const val API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed"

/**
 * The class for the only fragment in the app, which contains the progress bar,
 * recyclerView, and performs the network calls to the Movie Database API.
 */
class NowPlayingMoviesFragment : Fragment(), OnListFragmentInteractionListener{

    /**
     * What happens when a particular movie is clicked.
     */
    override fun onItemClick(item: NowPlayingMovies) {
        Toast.makeText(context, "test: " + item.movieTitle, Toast.LENGTH_LONG).show()
    }

}