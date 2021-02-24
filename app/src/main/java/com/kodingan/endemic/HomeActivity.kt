package com.kodingan.endemic

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.kodingan.endemic.databinding.ActivityHomeBinding


class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityHomeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(activityHomeBinding.root)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        activityHomeBinding. tabs.setupWithViewPager(activityHomeBinding.viewPager)
        activityHomeBinding.viewPager.adapter = sectionsPagerAdapter


        supportActionBar?.elevation = 0f

    }

    override fun onCreateOptionsMenu(menu:    Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun setMode(selectedMode: Int) {
        when (selectedMode) {
            R.id.nav_map -> {
                val uri = Uri.parse("endemic://maps")
                startActivity(Intent(Intent.ACTION_VIEW, uri))
            }



        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }


}