package com.gotenna.ukcrime.ui.crimes

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuItemCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gotenna.ukcrime.R
import com.gotenna.ukcrime.data.Crime
import com.gotenna.ukcrime.ui.detail.DetailActivity

class CrimesActivity : AppCompatActivity() {

    lateinit var crimesViewModel: CrimesViewModel

    private lateinit var crimesRecyclerView: RecyclerView
    private lateinit var crimesAdapter: RecyclerView.Adapter<CrimesAdapter.ViewHolder>


    companion object {
        const val EXTRA_CRIME = "com.gotenna.ukcrime.EXTRA_CRIME"
    }

    private val crimeObserver = Observer<List<Crime>> { crimes ->
        if (crimes == null) {
            return@Observer
        }
        findViewById<View>(R.id.crimes_progress_bar).visibility = View.GONE
        crimesAdapter = CrimesAdapter(crimes) {crime ->
            val detailsIntent = Intent(applicationContext, DetailActivity::class.java)
            detailsIntent.putExtra(EXTRA_CRIME, crime)
            startActivity(detailsIntent)
        }
        crimesRecyclerView.adapter = crimesAdapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        crimesViewModel = ViewModelProvider(this).get(CrimesViewModel::class.java)


        // Setup the recycler view
        crimesRecyclerView = findViewById<RecyclerView>(R.id.crimes_list)
        crimesRecyclerView.setHasFixedSize(true)
        val linearLayoutManager = LinearLayoutManager(this)
        crimesRecyclerView.setLayoutManager(linearLayoutManager)
        val dividerItemDecoration = DividerItemDecoration(this, linearLayoutManager.orientation)
        crimesRecyclerView.addItemDecoration(dividerItemDecoration)

        observeNoLocationLondon()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val item = menu!!.findItem(R.id.spinner)
        val spinner = MenuItemCompat.getActionView(item) as Spinner

        // Prepare adapter for spinner
        val adapter = ArrayAdapter.createFromResource(this,
                R.array.filter_dropdown_array, R.layout.item_spinner_selected)
        adapter.setDropDownViewResource(R.layout.item_spinner_unselected)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
                // Switch on the different sorting selections
                when (position) {
                    0 -> observeNoLocationLondon()
                    1 -> observeLocation()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        } // set the listener, to perform actions based on item selection
        return true
    }

    fun observeNoLocationLondon() {
        crimesViewModel.getCrimesNoLocationLondon().observe(this, crimeObserver)
    }

    fun observeLocation() {
        crimesViewModel.getCrimesLocation().observe(this, crimeObserver)
    }
}