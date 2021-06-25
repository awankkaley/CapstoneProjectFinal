package id.awankkaley.capstoneproject.home


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import id.awankkaley.capstoneproject.R
import id.awankkaley.core.data.Resource
import id.awankkaley.core.ui.PopularAdapter
import id.awankkaley.capstoneproject.databinding.FragmentHomeBinding
import id.awankkaley.capstoneproject.detail.DetailViewModel
import id.awankkaley.capstoneproject.util.gone
import id.awankkaley.capstoneproject.util.visible
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
class HomeFragment : Fragment() {


    private val homeViewModel: HomeViewModel by viewModel()
    private val detailViewModel: DetailViewModel by sharedViewModel()


    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val popularAdapter = PopularAdapter()
            popularAdapter.onItemClick = { it ->
                detailViewModel.sendPopularForDetail(it)
                view.findNavController().navigate(R.id.detailFragment)
            }
            viewLifecycleOwner.lifecycleScope.launch {
                viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    homeViewModel.uiState.collect { popular ->
                        when (popular) {
                            is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                            is Resource.Success -> {
                                binding.progressBar.gone()
                                popularAdapter.notifyDataSetChanged()
                                popularAdapter.setData(popular.data)
                            }
                            is Resource.Error -> {
                                binding.progressBar.gone()
                                binding.viewError.root.visible()
                                binding.viewError.tvError.text =
                                    popular.message ?: getString(R.string.something_wrong)
                            }
                        }
                    }
                }
            }

            with(binding.rvMovies) {
                layoutManager = GridLayoutManager(context, 2)
                adapter = popularAdapter
            }


        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
