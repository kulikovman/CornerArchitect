package com.corner.searcharchitect.ui.search

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import com.corner.searcharchitect.BR
import com.corner.searcharchitect.R
import com.corner.searcharchitect.base.BaseFragment
import com.corner.searcharchitect.databinding.ItemSearchBinding
import com.corner.searcharchitect.databinding.SearchFragmentBinding
import com.corner.searcharchitect.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<SearchFragmentBinding, SearchViewModel>() {

    override fun getLayoutId(): Int = R.layout.search_fragment

    override val viewModel: SearchViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        initSearch()

        viewModel.restartApp = ::restartApp
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
            cityEdittext.doAfterTextChanged {
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

    private fun restartApp() {
        activity?.apply {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finishAffinity()
        }
    }

}