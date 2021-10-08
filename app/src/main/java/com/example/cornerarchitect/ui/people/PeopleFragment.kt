package com.example.cornerarchitect.ui.people

import android.os.Bundle
import android.view.View
import android.widget.AbsListView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.cornerarchitect.BR
import com.example.cornerarchitect.R
import com.example.cornerarchitect.base.BaseFragment
import com.example.cornerarchitect.databinding.ItemPeopleBinding
import com.example.cornerarchitect.databinding.PeopleFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cornerarchitect.utility.log
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PeopleFragment : BaseFragment<PeopleFragmentBinding, PeopleViewModel>() {

    override fun getLayoutId(): Int = R.layout.people_fragment

    override val viewModel: PeopleViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        initSearchVisibility()
    }

    private fun initList() {
        binding.rvConfig = initRecycleAdapterDataBinding<ItemPeopleUi, ItemPeopleBinding>(
            layoutId = R.layout.item_people,
            itemId = BR.item,
            recyclerView = binding.rv,
            items = viewModel.peopleItems,
            onItemClick = viewModel::onClickItemPosition
        )
    }

    private fun initSearchVisibility() {
        lifecycleScope.launch {
            delay(50)
            viewModel.peopleItems.value?.let { items ->
                val layoutManager = binding.rv.layoutManager as LinearLayoutManager
                val lastVisiblePosition = layoutManager.findLastVisibleItemPosition() + 1

                log("lastVisiblePosition = $lastVisiblePosition / items = ${items.size}")
                viewModel.isSearchVisibility.value = lastVisiblePosition < items.size
            }
        }
    }

}