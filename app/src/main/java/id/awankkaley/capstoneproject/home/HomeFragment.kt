package id.awankkaley.capstoneproject.home


import android.os.Bundle
import android.view.*
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import id.awankkaley.capstoneproject.R
import id.awankkaley.core.data.Resource
import id.awankkaley.core.ui.PopularAdapter
import id.awankkaley.capstoneproject.databinding.FragmentHomeBinding
import id.awankkaley.capstoneproject.util.gone
import id.awankkaley.capstoneproject.util.visible
import org.koin.android.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()

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
                val bundle = bundleOf("popular" to it)
                view.findNavController().navigate(R.id.detailFragment, bundle)
            }

            homeViewModel.popular.observe(viewLifecycleOwner) { popular ->
                if (popular != null) {
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
