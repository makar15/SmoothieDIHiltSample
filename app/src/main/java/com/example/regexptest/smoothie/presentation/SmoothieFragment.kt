package com.example.regexptest.smoothie.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.regexptest.R
import com.example.regexptest.databinding.FragmentSmoothieBinding
import com.example.regexptest.smoothie.di.singleton.SmoothieSingletonComponent
import com.example.regexptest.smoothie.presentation.viewmodel.SmoothieViewModel
import com.example.regexptest.smoothie.presentation.viewmodel.SmoothieViewModelFactory
import com.example.regexptest.smoothie.presentation.viewmodel.SmoothieViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SmoothieFragment : Fragment() {

    private val appId by lazy { getAppUniqueId() }

    @Inject
    lateinit var viewModelFactory: SmoothieViewModelFactory

    private val smoothieSingletonEntryPoint by lazy {
        SmoothieSingletonComponent.getComponent(
            appId = appId,
        )
    }

    private val smoothieViewModel: SmoothieViewModel by viewModels {
        SmoothieViewModelProvider.provideFactory(
            assistedFactory = viewModelFactory,
            singletonDependencies = smoothieSingletonEntryPoint,
        )
    }

    private var _binding: FragmentSmoothieBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSmoothieBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()
        val actionId = when (val currentDestId = navController.currentDestination?.id) {
            R.id.NotesFragment -> R.id.action_NotesFragment_to_CalendarFragment
            R.id.CalendarFragment -> R.id.action_CalendarFragment_to_NotesFragment
            else -> error("Unknown destination: $currentDestId")
        }

        binding.buttonSecond.setOnClickListener {
            navController.navigate(actionId)
        }
    }

    override fun onStart() {
        super.onStart()
        val appId = smoothieViewModel.component?.singletonDependencies()?.appId()
        Log.d("MYTAG", "\n\nAppId = $appId ; $this")
        smoothieViewModel.doSomething()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getAppUniqueId(): String {
        val appUniqueId = requireArguments().getString(EXTRA_APP_UNIQUE_ID, "")
        if (appUniqueId.isNullOrBlank()) {
            throw IllegalStateException("You must provide a unique app id in arguments")
        }
        return appUniqueId
    }

    companion object {
        const val EXTRA_APP_UNIQUE_ID = "extra_app_unique_id"
    }
}