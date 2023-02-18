package com.nirwashh.android.shoplisttdd.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.nirwashh.android.shoplisttdd.databinding.FragmentAddShoppingItemBinding

class AddShoppingItemFragment : Fragment() {
    private var _binding: FragmentAddShoppingItemBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: ShoppingViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddShoppingItemBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[ShoppingViewModel::class.java]
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}