package com.rawat.hilt.ui.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.rawat.hilt.UiState
import com.rawat.hilt.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        homeViewModel.fetchProductWithLiveData()
        observeLiveData()
        return binding.root
    }

    private fun observeLiveData() {
        val textView: TextView = binding.textHome
        homeViewModel.productLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is UiState.Loading -> {
                    Log.e("Faker Live Data", "Loading")
                }

                is UiState.Success -> {
                    Log.e("Faker Live Data", "Success")
                    it.data
                    textView.text = it.data?.joinToString { x -> x.title + "\n\n" }
                }

                is UiState.Error<*> -> {
                    Log.e("Faker", "Failure")
                    textView.text = it.message.toString()
                }
            }
        }
    }

}