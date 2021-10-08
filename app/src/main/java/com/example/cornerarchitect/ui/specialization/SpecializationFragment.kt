package com.example.cornerarchitect.ui.specialization

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cornerarchitect.BR
import com.example.cornerarchitect.R
import com.example.cornerarchitect.base.BaseFragment
import com.example.cornerarchitect.databinding.ItemSpecializationBinding
import com.example.cornerarchitect.databinding.SpecializationFragmentBinding
import com.example.cornerarchitect.utility.log
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SpecializationFragment : BaseFragment<SpecializationFragmentBinding, SpecializationViewModel>() {

    override fun getLayoutId(): Int = R.layout.specialization_fragment

    override val viewModel: SpecializationViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        initSearchVisibility()
    }

    private fun initList() {
        binding.let { layoutBinding ->
            layoutBinding.rvConfig = initRecycleAdapterDataBinding<ItemSpecializationUi, ItemSpecializationBinding>(
                layoutId = R.layout.item_specialization,
                itemId = BR.item,
                recyclerView = layoutBinding.rv,
                items = viewModel.specializationItems,
                onItemClick = viewModel::onClickItemPosition
            )
        }
    }

    private fun initSearchVisibility() {
        lifecycleScope.launch {
            delay(30)
            viewModel.specializationItems.value?.let { items ->
                val layoutManager = binding.rv.layoutManager as LinearLayoutManager
                val lastVisiblePosition = layoutManager.findLastVisibleItemPosition() + 1

                log("lastVisiblePosition = $lastVisiblePosition / items = ${items.size}")
                viewModel.isSearchVisibility.value = lastVisiblePosition < items.size
            }
        }
    }

}