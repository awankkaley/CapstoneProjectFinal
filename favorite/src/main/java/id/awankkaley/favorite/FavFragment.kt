package id.awankkaley.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import id.awankkaley.capstoneproject.R
import id.awankkaley.capstoneproject.detail.DetailViewModel
import id.awankkaley.capstoneproject.util.gone
import id.awankkaley.capstoneproject.util.visible
import id.awankkaley.core.data.Resource
import id.awankkaley.core.ui.PopularAdapter
import id.awankkaley.favorite.databinding.FragmentFavBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
class FavFragment : Fragment() {
    private val detailViewModel: DetailViewModel by sharedViewModel()

    private var _binding: FragmentFavBinding? = null
    private val binding get() = _binding!!
    private var getFavJob: Job? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }


    @InternalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val popularAdapter = PopularAdapter()
            popularAdapter.onItemClick = { it ->
                detailViewModel.sendPopularForDetail(it)
                view.findNavController().navigate(R.id.detailFragment)
            }
            getFavJob = viewLifecycleOwner.lifecycleScope.launch {
                viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    detailViewModel.uiState.collect {
                        when (it) {
                            is Resource.Success -> {
                                popularAdapter.setData(it.data)
                                binding.tvEmpty.gone()
                            }
                            is Resource.Error -> {
                                binding.tvEmpty.visible()
                            }
                            else -> {
                            }
                        }

                    }
                }

            }

            with(binding.rvFav) {
                layoutManager = GridLayoutManager(context, 2)
                adapter = popularAdapter
            }


        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        getFavJob?.cancel()
        _binding = null
    }

}