package com.makeup.makeupapptest.home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.makeup.makeupapptest.databinding.FragmentMakeUpListBinding
import com.makeup.makeupapptest.home.adapter.MakeUpAdapter
import com.makeup.makeupapptest.home.models.ProductListItem
import com.makeup.makeupapptest.home.viewmodels.MainActivityViewModel
import com.makeup.makeupapptest.home.viewmodels.MakeUpListFragmentViewModel
import com.makeup.makeupapptest.util.Status
import com.makeup.makeupapptest.util.customviews.LoadingDialog
import com.makeup.makeupapptest.util.showToast
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MakeUpListFragment : Fragment() {

    private lateinit var binding: FragmentMakeUpListBinding
    private val parentViewModel: MainActivityViewModel by sharedViewModel()
    private val viewModel: MakeUpListFragmentViewModel by viewModel()
    private lateinit var makeupAdapter: MakeUpAdapter
    private var loadingDialog: LoadingDialog? = null
    private val args: MakeUpListFragmentArgs by navArgs()
    private val directions by lazy { MakeUpListFragmentDirections }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMakeUpListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadingDialog = LoadingDialog.newInstance(requireContext())
        parentViewModel.setTitle("Home")
        parentViewModel.showBackButton(false)
        parentViewModel.showSortButton(true)
        parentViewModel.sortButtonClicked()
        viewModel.sortedData(args)
        viewModel.init()

        requireActivity()
            .onBackPressedDispatcher
            .addCallback(
                viewLifecycleOwner,
                object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        activity?.finish()
                    }
                }
            )
        makeupAdapter = MakeUpAdapter(
            arrayListOf(),
            MakeUpAdapter.ClickListenerProduct { product ->
                val direction = directions.actionMakeUpListFragmentToMakeUpDetailFragment(product)
                findNavController().navigate(direction)
            }
        )
        makeupAdapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        binding.productRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.productRecyclerView.adapter = makeupAdapter

        if (args.fromDialog) {
            getSortedData()
        } else {
            fetchDataOnline()
        }
    }

    private fun fetchDataOnline() {
        viewModel.observeMakeupList.observe(viewLifecycleOwner) { product ->
            when (product.status) {
                Status.SUCCESS -> {
                    loadingDialog?.dismiss()
                    product.data?.let { renderList(it) }
                }
                Status.LOADING -> loadingDialog?.show()
                Status.ERROR -> {
                    getCachedData()
                    loadingDialog?.dismiss()
                    showToast("${product.message}")
                }
            }
        }
    }

    private fun getCachedData() {
        viewModel.observedCachedMakeUp.observe(viewLifecycleOwner) { product ->
            renderList(product)
        }
    }

    private fun getSortedData() {
        viewModel.observeSortedData.observe(viewLifecycleOwner) { product ->
            renderList(product)
        }
    }

    private fun renderList(makeup: List<ProductListItem>) {
        makeupAdapter.addData(makeup)
        makeupAdapter.notifyDataSetChanged()
    }

    override fun onPause() {
        super.onPause()
        loadingDialog?.dismiss()
    }

    companion object {
        fun newInstance() =
            MakeUpListFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}
