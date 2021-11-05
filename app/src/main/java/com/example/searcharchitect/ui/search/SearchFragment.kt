package com.example.searcharchitect.ui.search

import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import com.example.searcharchitect.BR
import com.example.searcharchitect.R
import com.example.searcharchitect.base.BaseFragment
import com.example.searcharchitect.databinding.ItemSearchBinding
import com.example.searcharchitect.databinding.SearchFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import android.content.Intent
import com.example.searcharchitect.main.MainActivity

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