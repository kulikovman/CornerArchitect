package com.example.cornerarchitect.ui.specialization

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.cornerarchitect.BR
import com.example.cornerarchitect.R
import com.example.cornerarchitect.base.BaseFragment
import com.example.cornerarchitect.databinding.ItemSpecializationBinding
import com.example.cornerarchitect.databinding.SpecializationFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SpecializationFragment : BaseFragment<SpecializationFragmentBinding, SpecializationViewModel>() {

    override fun getLayoutId(): Int = R.layout.specialization_fragment

    override val viewModel: SpecializationViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
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

}