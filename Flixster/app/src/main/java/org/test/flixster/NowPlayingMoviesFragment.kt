package org.test.flixster

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Headers


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
     * Constructing the view
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_now_playing_movies_list, container, false)
        val progressBar = view.findViewById<View>(R.id.progress) as ContentLoadingProgressBar
        val recyclerView = view.findViewById<View>(R.id.list) as RecyclerView
        val context = view.context
        recyclerView.layoutManager = GridLayoutManager(context, 1)
        updateAdapter(progressBar, recyclerView)
        return view
    }

    /**
     * Updates the RecyclerView adapter with new data.  This is where the
     * networking magic happens!
     */
    private fun updateAdapter(progressBar: ContentLoadingProgressBar, recyclerView: RecyclerView) {
        progressBar.show()

        // Create and set up an AsyncHTTPClient() here
        val client = AsyncHttpClient()
        val params = RequestParams()
        params["api-key"] = API_KEY

        // Using the client, perform the HTTP request
        client[
                "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed",
                params,
                object : JsonHttpResponseHandler() {
                    /**
                     * The onSuccess function gets called when
                     * HTTP response status is "200 OK"
                     */
                    override fun onSuccess(
                        statusCode: Int,
                        headers: Headers,
                        json: JsonHttpResponseHandler.JSON
                    ) {
                        // The wait for a response is over
                        progressBar.hide()

                        val resultsJson = json.jsonObject.get("results").toString()
                        val gson = Gson()
                        val arrayMovieType = object : TypeToken<List<NowPlayingMovies>>() {}.type
                        val movies : List<NowPlayingMovies> = gson.fromJson(resultsJson, arrayMovieType)
                        recyclerView.adapter = NowPlayingRVAdapter(movies, this@NowPlayingMoviesFragment)

                        // Look for this in Logcat:
                        Log.d("NowPlayingMoviesFragment", "response successful")
                        Log.i ("SpamResult", "${json.toString()}")
                    }

                    override fun onFailure(
                        statusCode: Int,
                        headers: Headers?,
                        errorResponse: String,
                        t: Throwable?
                    ) {
                        // The wait for a response is over
                        progressBar.hide()

                        // If the error is not null, log it!
                        t?.message?.let {
                            Log.e("NowPlayingMoviesFragment", errorResponse)
                        }
                    }
                }
        ]
    }
    /**
     * What happens when a particular movie is clicked.
     */
    override fun onItemClick(item: NowPlayingMovies) {
        Toast.makeText(context, "test: " + item.movieTitle, Toast.LENGTH_LONG).show()
    }

}