package com.ranjeetwaje.g_shopping.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ranjeetwaje.g_shopping.R
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.ranjeetwaje.g_shopping.databinding.FragmentMainBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentMainBinding>(inflater,
            R.layout.fragment_main, container, false)

        binding.btnMonthlyGrocceryList.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_mainFragment_to_grocceryListFragment)
        }

        return binding.root
    }
}
