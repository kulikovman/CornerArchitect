package com.example.cornerarchitect.ui.contact

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.cornerarchitect.BR
import com.example.cornerarchitect.R
import com.example.cornerarchitect.base.BaseFragment
import com.example.cornerarchitect.databinding.ContactFragmentBinding
import com.example.cornerarchitect.databinding.ItemContactBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactFragment : BaseFragment<ContactFragmentBinding, ContactViewModel>() {

    override fun getLayoutId(): Int = R.layout.contact_fragment

    override val viewModel: ContactViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
    }

    private fun initList() {
        binding.let { layoutBinding ->
            layoutBinding.rvConfig = initRecycleAdapterDataBinding<ItemContactUi, ItemContactBinding>(
                layoutId = R.layout.item_contact,
                itemId = BR.item,
                recyclerView = layoutBinding.rv,
                items = viewModel.contactItems,
                onItemClick = viewModel::onClickItemPosition
            )
        }
    }

}