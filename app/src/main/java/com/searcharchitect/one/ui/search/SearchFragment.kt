package com.searcharchitect.one.ui.search

import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import com.searcharchitect.one.BR
import com.searcharchitect.one.R
import com.searcharchitect.one.base.BaseFragment
import com.searcharchitect.one.databinding.ItemSearchBinding
import com.searcharchitect.one.databinding.SearchFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<SearchFragmentBinding, SearchViewModel>() {

    override fun getLayoutId(): Int = R.layout.search_fragment

    override val viewModel: SearchViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        initSearch()
    }

    private fun initList() {
        binding.apply {
            rvConfig = initRecycleAdapterDataBinding<ItemSearchUi, ItemSearchBinding>(
                layoutId = R.layout.item_search,
                itemId = BR.item,
                recyclerView = rv,
                items = viewModel.items,
                onItemClick = viewModel::onClickItemPosition
            )
        }
    }

    private fun initSearch() {
        binding.apply {
            locationEdittext.doAfterTextChanged {
                viewModel.updateContactList()
            }

            specializationEdittext.doAfterTextChanged {
                viewModel.updateContactList()
            }

            nameEdittext.doAfterTextChanged {
                viewModel.updateContactList()
            }
        }
    }

}