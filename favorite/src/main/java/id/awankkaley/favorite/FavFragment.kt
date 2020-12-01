package id.awankkaley.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import id.awankkaley.capstoneproject.R
import id.awankkaley.capstoneproject.util.visible
import id.awankkaley.core.ui.PopularAdapter
import id.awankkaley.favorite.databinding.FragmentFavBinding
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavFragment : Fragment() {
    private val favViewModel: FavViewModel by viewModel()

    private var _binding: FragmentFavBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        loadKoinModules(viewModelModule)
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
            favViewModel.favorite.observe(viewLifecycleOwner, {
                popularAdapter.setData(it)
                if (it.isEmpty())
                    binding.tvEmpty.visible()

            })

            with(binding.rvFav) {
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