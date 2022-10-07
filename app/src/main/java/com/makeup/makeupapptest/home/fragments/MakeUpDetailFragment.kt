package com.makeup.makeupapptest.home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.makeup.makeupapptest.R
import com.makeup.makeupapptest.databinding.FragmentMakeUpDetailBinding
import com.makeup.makeupapptest.home.viewmodels.MainActivityViewModel
import com.makeup.makeupapptest.util.loadImageWithGlide
import com.makeup.makeupapptest.util.removeExtraTags
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MakeUpDetailFragment : Fragment() {

    private lateinit var binding: FragmentMakeUpDetailBinding
    private val parentViewModel: MainActivityViewModel by sharedViewModel()
    private val args: MakeUpDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMakeUpDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parentViewModel.setTitle("Back")
        parentViewModel.showBackButton(true)
        parentViewModel.showSortButton(false)
        binding.iconImageView.loadImageWithGlide(args.data.imageLink, R.drawable.no_image)
        binding.nameTextView.text = "Product name: ${args.data.name?.trim()?.let { removeExtraTags(it) }}"
        binding.descriptionTextView.text = removeExtraTags("${args.data.description}")
        if (args.data.priceSign == null) {
            binding.priceTextView.text = "Product price: $ ${args.data.price}"
        } else {
            binding.priceTextView.text = "Product price: ${args.data.priceSign} ${args.data.price}"
        }
    }

    companion object {

        fun newInstance() =
            MakeUpDetailFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}
