package com.example.cornerarchitect.ui.person

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.cornerarchitect.BR
import com.example.cornerarchitect.R
import com.example.cornerarchitect.base.BaseFragment
import com.example.cornerarchitect.databinding.ItemPeopleBinding
import com.example.cornerarchitect.databinding.PeopleFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PeopleFragment : BaseFragment<PeopleFragmentBinding, PeopleViewModel>() {

    override fun getLayoutId(): Int = R.layout.people_fragment

    override val viewModel: PeopleViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
    }

    private fun initList() {
        binding.let { layoutBinding ->
            layoutBinding.rvConfig = initRecycleAdapterDataBinding<ItemPeopleUi, ItemPeopleBinding>(
                layoutId = R.layout.item_people,
                itemId = BR.item,
                recyclerView = layoutBinding.rv,
                items = viewModel.peopleItems,
                onItemClick = viewModel::onClickItemPosition
            )
        }
    }

}