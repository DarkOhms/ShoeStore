package com.udacity.shoestore.shoes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.StoreViewModel
import com.udacity.shoestore.databinding.FragmentDetailsBinding


/**
 * A simple [Fragment] subclass.
 * Use the [DetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailsFragment : Fragment() {
    lateinit var binding: FragmentDetailsBinding
    private val storeViewModel: StoreViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)

        binding.newShoeData = storeViewModel.newShoeData
        binding.detailsViewModel = storeViewModel

        storeViewModel.navigate.observe(viewLifecycleOwner){
            if(it){
                findNavController().navigate(DetailsFragmentDirections.actionDetailsFragmentToShoeFragment())
                storeViewModel.onNavigationComplete()
            }
        }
        return binding.root
    }

}