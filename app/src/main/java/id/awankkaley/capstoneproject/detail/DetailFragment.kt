package id.awankkaley.capstoneproject.detail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import id.awankkaley.capstoneproject.R
import id.awankkaley.core.domain.model.Popular
import id.awankkaley.capstoneproject.databinding.FragmentDetailBinding
import id.awankkaley.core.util.Util
import id.awankkaley.capstoneproject.util.imageViewGlide
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.koin.android.viewmodel.ext.android.viewModel

private const val ARG_PARAM1 = "popular"

class DetailFragment : Fragment() {
    private val detailViewModel: DetailViewModel by viewModel()
    private var data: Popular? = null
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            data = it.getParcelable(ARG_PARAM1)
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        binding.imgDetail.imageViewGlide(data?.backdropPath)
        binding.tvDetailTitle.text = data?.title
        binding.tvDetailOverview.text = data?.overview
        binding.tvDetailPopularity.text = data?.popularity.toString()
        binding.tvDetailVote.text = "${data?.voteAverage.toString()}/10.0"
        binding.tvDetailRelease.text = Util.convertDate(data?.releaseDate)

        var statusFavorite = data?.isFavorite
        setStatusFavorite(statusFavorite!!)
        binding.btnFav.onClick {
            statusFavorite = !statusFavorite!!
            data?.let { it1 -> detailViewModel.setFavorite(it1, statusFavorite!!) }
            setStatusFavorite(statusFavorite!!)
        }

        return binding.root
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.btnFav.setImageDrawable(context?.let {
                ContextCompat.getDrawable(
                    it,
                    R.drawable.ic_baseline_favorite_24
                )
            })
        } else {
            binding.btnFav.setImageDrawable(context?.let {
                ContextCompat.getDrawable(
                    it,
                    R.drawable.ic_baseline_favorite_border_24
                )
            })
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}