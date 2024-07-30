import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.example.assignment2mp.R

class ViewSummaryFragment : Fragment() {

    companion object {
        fun newInstance(expenses: List<Triple<String, Double, String>>): ViewSummaryFragment {
            val fragment = ViewSummaryFragment()
            val args = Bundle()
            args.putSerializable("expenses", ArrayList(expenses))
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var expensesListView: ListView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_view_summary, container, false)
        expensesListView = view.findViewById(R.id.expensesListView)
        val expenses = arguments?.getSerializable("expenses") as? ArrayList<Triple<String, Double, String>>
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, expenses?.map { "${it.first} - ${it.second} ${it.third}" } ?: listOf())
        expensesListView.adapter = adapter
        return view
    }
}
