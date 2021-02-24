package com.kodingan.endemic.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.kodingan.endemic.R
import com.kodingan.endemic.core.data.Resource
import com.kodingan.endemic.core.ui.SpeciesAdapter
import com.kodingan.endemic.databinding.FragmentHomeBinding
import com.kodingan.endemic.detail.DetailSpeciesActivity
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val speciesAdapter = SpeciesAdapter()
            speciesAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailSpeciesActivity::class.java)
                intent.putExtra(DetailSpeciesActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            homeViewModel.species.observe(viewLifecycleOwner, { species ->
                if (species != null) {
                    when (species) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            speciesAdapter.setData(species.data)
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.viewError.root.visibility = View.VISIBLE
                            binding.viewError.tvError.text = species.message ?: getString(R.string.something_wrong)
                        }
                    }
                }
            })

            with(binding.rvSpecies) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = speciesAdapter
            }
        }
    } 

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
