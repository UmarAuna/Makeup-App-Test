package com.makeup.makeupapptest.home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.makeup.makeupapptest.R
import com.makeup.makeupapptest.databinding.DialogSortingBinding
import com.makeup.makeupapptest.home.adapter.SortAdapter
import com.makeup.makeupapptest.home.viewmodels.SortingDataDialogViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SortingDialog : BottomSheetDialogFragment() {

    private lateinit var binding: DialogSortingBinding
    private val viewModel: SortingDataDialogViewModel by viewModel()
    private val directions by lazy { SortingDialogDirections }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogSortingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.init()
        val sortAdapter = SortAdapter(
            arrayListOf(),
            SortAdapter.ClickListenerSort { sort ->
                val direction = directions.actionSortingDialogToMakeUpListFragment(sort, true)
                findNavController().navigate(direction)
            }
        )
        binding.sortingRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.sortingRecyclerView.adapter = sortAdapter

        viewModel.observeSortData.observe(viewLifecycleOwner) { sort ->
            sortAdapter.updateList(sort)
        }
    }

    override fun getTheme() = R.style.CustomBottomSheetDialogTheme

    companion object {
        fun newInstance() =
            SortingDialog().apply {
                arguments = Bundle().apply {
                }
            }
    }
}
