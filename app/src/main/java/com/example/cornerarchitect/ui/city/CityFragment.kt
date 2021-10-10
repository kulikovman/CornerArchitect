package com.example.cornerarchitect.ui.city

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cornerarchitect.BR
import com.example.cornerarchitect.R
import com.example.cornerarchitect.base.BaseFragment
import com.example.cornerarchitect.databinding.CityFragmentBinding
import com.example.cornerarchitect.databinding.ItemCityBinding
import com.example.cornerarchitect.utility.log
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CityFragment : BaseFragment<CityFragmentBinding, CityViewModel>() {

    override fun getLayoutId(): Int = R.layout.city_fragment

    override val viewModel: CityViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
    }

    private fun initList() {
        binding.let { layoutBinding ->
            layoutBinding.rvConfig = initRecycleAdapterDataBinding<ItemCityUi, ItemCityBinding>(
                layoutId = R.layout.item_city,
                itemId = BR.item,
                recyclerView = layoutBinding.rv,
                items = viewModel.cityItems,
                onItemClick = viewModel::onClickItemPosition
            )
        }
    }

}