package com.example.regexptest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.regexptest.databinding.FragmentFirstBinding
import com.example.regexptest.databinding.TestPlateBinding
import com.example.regexptest.smoothie.presentation.SmoothieFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class FirstFragment : SmoothieFragment() {

    private var _binding: FragmentFirstBinding? = null
    private lateinit var testPlate: TestPlateBinding

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    override val appId: String = "First"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        testPlate = TestPlateBinding.inflate(layoutInflater, binding.root, false)
        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}