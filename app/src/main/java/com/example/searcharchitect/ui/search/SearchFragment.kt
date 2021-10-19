package com.example.searcharchitect.ui.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.searcharchitect.BR
import com.example.searcharchitect.R
import com.example.searcharchitect.base.BaseFragment
import com.example.searcharchitect.databinding.ItemSearchBinding
import com.example.searcharchitect.databinding.SearchFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<SearchFragmentBinding, SearchViewModel>() {

    override fun getLayoutId(): Int = R.layout.search_fragment

    override val viewModel: SearchViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
    }

    private fun initList() {
        binding.let { layoutBinding ->
            layoutBinding.rvConfig = initRecycleAdapterDataBinding<ItemSearchUi, ItemSearchBinding>(
                layoutId = R.layout.item_search,
                itemId = BR.item,
                recyclerView = layoutBinding.rv,
                items = viewModel.searchItems,
                onItemClick = viewModel::onClickItemPosition
            )
        }
    }

}