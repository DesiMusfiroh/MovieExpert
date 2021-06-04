import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.made.movieexpert.R
import com.made.movieexpert.databinding.ItemsCatalogueFavoriteBinding
import com.made.movieexpert.domain.model.Movie
import com.made.movieexpert.ui.detail.movie.DetailMovieActivity
import com.made.movieexpert.utils.Constants

class FavoriteMovieAdapter : RecyclerView.Adapter<FavoriteMovieAdapter.MovieViewHolder>() {
    private var listMovies = ArrayList<Movie>()

    fun setMovies(movies: List<Movie>?) {
        if (movies == null) return
        this.listMovies.clear()
        this.listMovies.addAll(movies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemsCatalogueFavoriteBinding = ItemsCatalogueFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemsCatalogueFavoriteBinding)
    }

    override fun getItemCount(): Int = listMovies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = listMovies[position]
        holder.bind(movie)
    }

    class MovieViewHolder(private val binding: ItemsCatalogueFavoriteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            with(binding) {
                tvItemName.text = movie.name
                tvItemRating.text = movie.rating.toString()
                tvItemDesc.text = movie.desc
                tvItemPopularity.text = StringBuilder("Popularity : ${movie.popularity}")
                Glide.with(itemView.context)
                        .load(Constants.POSTER_URL + movie.poster)
                        .apply(
                                RequestOptions.placeholderOf(R.drawable.ic_loading)
                                        .error(R.drawable.ic_error))
                        .into(imgPoster)

                itemView.setOnClickListener {
                    val intent =  Intent(itemView.context, DetailMovieActivity::class.java)
                    intent.putExtra(DetailMovieActivity.EXTRA_MOVIE, movie)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }
}
