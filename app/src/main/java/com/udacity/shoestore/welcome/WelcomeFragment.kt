package com.udacity.shoestore.welcome

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
import com.udacity.shoestore.databinding.FragmentWelcomeBinding
import com.udacity.shoestore.login.LoginFragmentDirections

class WelcomeFragment : Fragment() {

    private lateinit var binding: FragmentWelcomeBinding
    private val storeViewModel: StoreViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_welcome, container, false)
        //set the viewModel for data binding
        binding.welcomeViewModel = storeViewModel

        storeViewModel.navigate.observe(viewLifecycleOwner){
            if(it){
                findNavController().navigate(WelcomeFragmentDirections.actionWelcomeFragmentToInstructionsFragment())
                storeViewModel.onNavigationComplete()
            }
        }

        return binding.root
    }

}