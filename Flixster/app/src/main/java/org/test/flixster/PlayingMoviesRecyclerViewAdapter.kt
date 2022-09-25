package org.test.flixster

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import androidx.recyclerview.widget.RecyclerView


/**
 * [RecyclerView.Adapter] that can display [PlayingMovies]
 * Primary Constructor takes a list of Playing Movies
 */
class PlayingMoviesRecyclerViewAdapter(private val myMovies: List<PlayingMovies>)
    :RecyclerView.Adapter<PlayingMoviesRecyclerViewAdapter.MovieViewHolder>() {

    /**
     * This inner class lets us refer to all the different View elements
     * (Yes, the same ones as in the XML layout files!)
     * Provide a direct reference to each of the views within a data model
     * Used to cache the views within the item layout for fast access
     * extends the RecyclerView.ViewHolder class
     *
     * Notice that the primary construction of this class (val mView: View) takes in a variable
     * of type view. A view can be anything TextView, ImageView, Button, ImageButton, etc
     */
    inner class MovieViewHolder(val mView: View): RecyclerView.ViewHolder(mView)
    {
        /**
         *  Member variables and their type of the inner class
         *  We don't need the init since we have joined the declaration and initialization together
         */

        val movieImage:ImageView = itemView.findViewById(R.id.movieImageTv)
        val movieTitle: TextView = itemView.findViewById(R.id.movieTitleTv)
        val movieDescription: TextView = itemView.findViewById(R.id.movieDescriptionTv)
    }

    /**
     *Specify the look of the ViewHolder as it comes into the screen; It is the look of each row
     * notice that the look of the ViewHolder is based on the look created in the row xml
     * so all of that design we did is what the user will see
     * parent is of type ViewGroup and viewType is of type int
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        // create a variable and set it to the context property of ViewGroup objects
        val context = parent.context
        // inflate the custom layout
        val inflater = LayoutInflater.from(context)
        // connect it to the flixter_item_row xml
        val contactView = inflater.inflate(R.layout.flixter_item_row, parent,false)

        return MovieViewHolder(contactView)
    }

    /**
     * On bind view holder takes the views that have been recycled off screen
     * and uses them to show the new models currently scrolling into the user's screen
     * Basically we are assigning values to each of the views as they come back into screen
     * The holder variable is of type MovieViewHolder
     */
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        /**
         * Kicks In: The new movie scrolls into the screen
         * Start with a list of movies (myMovies in this case)
         * myMovies contains the value of movies already in the list
         * Choose a movie in that list and assign it to a varibale via list indexing
         * val movie = myMovies[position]
         * As the movie scrolls into the screen, the values are given to the ViewHolder variable
         * holder. Holder's properties declared and initialized in the inner class are then set to
         * the value of the movie in the list
         */
        // get the position of the current data model
        val movie = myMovies[position]
        // set the holder text values to match the values of the data model from the class
        holder.movieTitle.text = movie.movieTitle
        holder.movieDescription.text = movie.movieDescription

        // removed one of the '/' - check if this breaks
        var glideMovieImageUrl = "https://image.tmdb.org/t/p/w500" + movie.movieImageUrl

        // set the holder for the movie image - set it in bindViewHolder
        Glide.with(holder.mView)
            .load(glideMovieImageUrl)
            .centerInside()
            .into(holder.movieImage)
    }

    // get the current number of movies in the list
    override fun getItemCount(): Int {return myMovies.size}

}