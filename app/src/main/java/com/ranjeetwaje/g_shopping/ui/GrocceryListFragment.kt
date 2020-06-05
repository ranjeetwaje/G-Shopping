package com.ranjeetwaje.g_shopping.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.ranjeetwaje.g_shopping.R
import com.ranjeetwaje.g_shopping.database.GroceryDatabase
import com.ranjeetwaje.g_shopping.databinding.FragmentGrocceryListBinding
import com.ranjeetwaje.g_shopping.viewmodel.GrocceryListViewModel
import com.ranjeetwaje.g_shopping.viewmodel.GroceryListViewModelFactory

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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentGrocceryListBinding>(inflater,
            R.layout.fragment_groccery_list, container, false)
        val application = requireNotNull(this.activity).application

        // Create an instance of the ViewModel Factory.
        val dataSource = GroceryDatabase.getInstance(application).groceryItemDao
        val viewModelFactory = GroceryListViewModelFactory(dataSource, application)

        // Get a reference to the ViewModel associated with this fragment.
        val groceryListViewModel = ViewModelProviders.of(this,
            viewModelFactory).get(GrocceryListViewModel::class.java)
        binding.groceryListViewModel = groceryListViewModel

        binding.lifecycleOwner = this

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
