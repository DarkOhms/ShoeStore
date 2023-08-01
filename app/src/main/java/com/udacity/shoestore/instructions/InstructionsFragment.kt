package com.udacity.shoestore.instructions

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
import com.udacity.shoestore.databinding.FragmentInstructionsBinding


class InstructionsFragment : Fragment() {

    lateinit var binding: FragmentInstructionsBinding
    private val storeViewModel: StoreViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_instructions, container, false)
        //set the viewModel for data binding
        binding.instructionsViewModel = storeViewModel

        storeViewModel.navigate.observe(viewLifecycleOwner){
            if(it){
                findNavController().navigate(InstructionsFragmentDirections.actionInstructionsFragmentToShoeFragment())
                storeViewModel.onNavigationComplete()
            }
        }

        return binding.root
    }

}