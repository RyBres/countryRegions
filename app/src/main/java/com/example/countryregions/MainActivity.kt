package com.example.countryregions

import android.os.Bundle
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val getRegion = findViewById<Button>(R.id.select_region)
        val regions = findViewById<Spinner>(R.id.regions)
        val states = findViewById<TextView>(R.id.states)

        // Set up Spinner adapter
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.regions,
            android.R.layout.simple_spinner_dropdown_item
        )
        regions.adapter = adapter

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        getRegion.setOnClickListener {
            val region = regions.selectedItem?.toString() ?: "Unknown"
            val stateList = getStates(region)

            // Use joinToString to prevent crashes
            states.text = if (stateList.isNotEmpty()) stateList.joinToString("\n") else "No states found"
        }
    }

    fun getStates(region: String): List<String> {
        return when (region) {
            "Northeast" -> listOf("Connecticut", "Maine", "Massachusetts", "New Hampshire", "Rhode Island", "Vermont", "New Jersey", "New York", "Pennsylvania")
            "Midwest" -> listOf("Illinois", "Indiana", "Iowa", "Kansas", "Michigan", "Minnesota", "Missouri", "Nebraska", "North Dakota", "Ohio", "South Dakota", "Wisconsin")
            "South" -> listOf("Alabama", "Arkansas", "Delaware", "Florida", "Georgia", "Kentucky", "Louisiana", "Maryland", "Mississippi", "North Carolina", "Oklahoma", "South Carolina", "Tennessee", "Texas", "Virginia", "West Virginia")
            "West" -> listOf("Alaska", "Arizona", "California", "Colorado", "Hawaii", "Idaho", "Montana", "Nevada", "New Mexico", "Oregon", "Utah", "Washington", "Wyoming")
            else -> listOf()
        }
    }
}
