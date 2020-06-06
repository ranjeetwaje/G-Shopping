package com.ranjeetwaje.g_shopping.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ranjeetwaje.g_shopping.R
import com.ranjeetwaje.g_shopping.adapters.GroceryListAdapter
import com.ranjeetwaje.g_shopping.database.GroceryItem
import com.ranjeetwaje.g_shopping.databinding.FragmentGrocceryListBinding
import com.ranjeetwaje.g_shopping.network.GroceryData
import com.ranjeetwaje.g_shopping.viewmodel.GrocceryListViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [GrocceryListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GrocceryListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private val viewModel: GrocceryListViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProviders.of(this, GrocceryListViewModel.ViewModelFactory(activity.application))
            .get(GrocceryListViewModel::class.java)
    }

    private var viewModelAdapter: GroceryListAdapter? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.itemList.observe(viewLifecycleOwner, Observer<List<GroceryData>> { items ->
            items?.apply {
                viewModelAdapter?.itemList = items
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentGrocceryListBinding>(inflater,
            R.layout.fragment_groccery_list, container, false)
//        val binding = FragmentGrocceryListBinding.inflate(inflater)

        binding.lifecycleOwner = viewLifecycleOwner

        binding.groceryListViewModel = viewModel

        viewModelAdapter = GroceryListAdapter()

        binding.root.findViewById<RecyclerView>(R.id.item_list_recyclerView).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = viewModelAdapter
        }

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment GrocceryListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            GrocceryListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
