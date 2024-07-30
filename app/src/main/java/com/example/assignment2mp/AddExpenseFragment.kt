import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.assignment2mp.MainActivity
import com.example.assignment2mp.R

class AddExpenseFragment : Fragment() {
    private lateinit var reasonInput: EditText
    private lateinit var amountInput: EditText
    private lateinit var currencySpinner: Spinner
    private lateinit var addButton: Button
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_expense, container, false)
        reasonInput = view.findViewById(R.id.reasonInput)
        amountInput = view.findViewById(R.id.amountInput)
        currencySpinner = view.findViewById(R.id.currencySpinner)
        addButton = view.findViewById(R.id.addButton)
        progressBar = view.findViewById(R.id.progressBar)

        // Initialize the spinner with currencies
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.currency_options,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            currencySpinner.adapter = adapter
        }

        addButton.setOnClickListener {
            val reason = reasonInput.text.toString().trim()
            val amount = amountInput.text.toString().trim().toDoubleOrNull()
            val currency = currencySpinner.selectedItem.toString()
            if (amount != null) {
                showLoading()
                (activity as MainActivity).addExpense(reason, amount, currency)
                reasonInput.setText("")
                amountInput.setText("")
            }
        }
        return view
    }

    private fun showLoading() {
        progressBar.visibility = View.VISIBLE
        Handler(Looper.getMainLooper()).postDelayed({
            progressBar.visibility = View.GONE
            Toast.makeText(context, "Expense added successfully", Toast.LENGTH_SHORT).show()
        }, 2000)  // Delay for 2 seconds
    }
}
