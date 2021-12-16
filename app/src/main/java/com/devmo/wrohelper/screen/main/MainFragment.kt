package com.devmo.wrohelper.screen.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.devmo.wrohelper.R
import com.devmo.wrohelper.databinding.MainFragmentBinding
import com.devmo.wrohelper.util.Injection


class MainFragment : Fragment() {

    private lateinit var mainViewModel: MainViewModel

    private lateinit var binding: MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding: MainFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.main_fragment, container, false
        )

        binding.lifecycleOwner = viewLifecycleOwner
        mainViewModel =
            ViewModelProvider(
                requireActivity(),
                Injection.provideViewModelFactory(requireContext())
            ).get(MainViewModel::class.java)

        binding.model = mainViewModel

        mainViewModel.issueIds.observe(viewLifecycleOwner) {
            val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
                requireContext(),
                android.R.layout.simple_dropdown_item_1line,
                it
            )
            binding.issueIdAutoCompleteTextView.setAdapter(adapter)
        }

        binding.issueIdAutoCompleteTextView.onItemClickListener = object : AdapterView.OnItemClickListener {
            override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                mainViewModel.fetchIssue(parent?.getItemAtPosition(position).toString())
            }
        }

        mainViewModel.fetchIssueIds()

        return binding.root
    }
}
