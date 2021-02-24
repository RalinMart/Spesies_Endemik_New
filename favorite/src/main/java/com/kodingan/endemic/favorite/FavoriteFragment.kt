package com.kodingan.endemic.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.kodingan.endemic.core.ui.SpeciesAdapter
import com.kodingan.endemic.favorite.databinding.FragmentFavoriteBinding
import com.kodingan.endemic.detail.DetailSpeciesActivity
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import com.kodingan.endemic.favorite.di.favoriteModule

class FavoriteFragment : Fragment() {
    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private val binding get() = _binding!!
    private var _binding: FragmentFavoriteBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        loadKoinModules(favoriteModule)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val speciesAdapter = SpeciesAdapter()
            speciesAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity,  DetailSpeciesActivity::class.java)
                intent.putExtra(DetailSpeciesActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            with(binding.rvFavorite) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = speciesAdapter
            }

            favoriteViewModel.favoriteSpecies.observe(viewLifecycleOwner, { dataSpecies ->
                speciesAdapter.setData(dataSpecies)
                binding.viewEmpty.root.visibility = if (dataSpecies.isNotEmpty()) View.GONE else View.VISIBLE
            })


        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}