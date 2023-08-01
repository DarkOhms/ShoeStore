package com.udacity.shoestore.shoes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.StoreViewModel
import com.udacity.shoestore.databinding.FragmentShoeBinding

class ShoeFragment : Fragment() {

    private lateinit var binding: FragmentShoeBinding
    private val storeViewModel: StoreViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_shoe, container, false)

        binding.shoeViewModel = storeViewModel

        storeViewModel.navigate.observe(viewLifecycleOwner){
            if(it){
                findNavController().navigate(ShoeFragmentDirections.actionShoeFragmentToDetailsFragment())
                storeViewModel.onNavigationComplete()
            }
        }
        storeViewModel.shoeList.observe(viewLifecycleOwner){
            //TODO populate shoe list from viewModel
            if(!it.isNullOrEmpty()){
                it.forEach {shoe ->
                    //create a shoe item
                    val shoeItem = inflater.inflate(R.layout.shoe_item, binding.shoeList, false)
                    //add layout parameters
                    shoeItem.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
                    //find views
                    val shoeItemNameView = shoeItem.findViewById<TextView>(R.id.shoe_name)
                    val shoeItemMakerView = shoeItem.findViewById<TextView>(R.id.shoe_maker)
                    val shoeItemSizeView = shoeItem.findViewById<TextView>(R.id.shoe_size)
                    val shoeItemDescriptionView = shoeItem.findViewById<TextView>(R.id.shoe_description)
                    //insert data
                    shoeItemNameView.text = shoe.name
                    shoeItemMakerView.text = "By: " + shoe.company
                    shoeItemSizeView.text = "Size: " + shoe.size.toString()
                    shoeItemDescriptionView.text = shoe.description
                    //add child view to parent
                    binding.shoeList.addView(shoeItem)
                }
            }
        }
        val menuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider{
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.logout_menu,menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                findNavController().navigate(ShoeFragmentDirections.actionShoeFragmentToLoginFragment())
                return true
            }
        }, this.viewLifecycleOwner, Lifecycle.State.RESUMED)
        return binding.root
    }

}