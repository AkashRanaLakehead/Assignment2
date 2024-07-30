package com.example.assignment2mp

import AddExpenseFragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private val expenses = mutableListOf<Triple<String, Double, String>>()  // List to store reason, amount, and currency

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_add_expense -> replaceFragment(AddExpenseFragment())
                R.id.nav_view_summary -> replaceFragment(ViewSummaryFragment.newInstance(expenses))
            }
            true
        }
        bottomNavigation.selectedItemId = R.id.nav_add_expense  // Set the default selected item
    }

    fun addExpense(reason: String, amount: Double, currency: String) {
        expenses.add(Triple(reason, amount, currency))
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()  // Commit the transaction to switch fragments
    }
}
