package com.kodingan.endemic.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.kodingan.endemic.R
import com.kodingan.endemic.core.domain.model.Species
import com.kodingan.endemic.databinding.ActivityDetailSpeciesBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailSpeciesActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private lateinit var binding: ActivityDetailSpeciesBinding
    private val detailSpeciesViewModel: DetailSpeciesViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailSpeciesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val detailSpecies = intent.getParcelableExtra<Species>(EXTRA_DATA)
        showDetailSpecies(detailSpecies)
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_white))
        } else {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_not_favorite_white))
        }
    }

    private fun showDetailSpecies(detailSpecies: Species?) {
        detailSpecies?.let {
            supportActionBar?.title = detailSpecies.name
            binding.content.tvDetailDescription.text = detailSpecies.description
            Glide.with(this@DetailSpeciesActivity)
                .load(detailSpecies.image)
                .into(binding.ivDetailImage)

            var statusFavorite = detailSpecies.isFavorite
            setStatusFavorite(statusFavorite)
            binding.fab.setOnClickListener {
                statusFavorite = !statusFavorite
                detailSpeciesViewModel.setFavoriteSpecies(detailSpecies, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }


}