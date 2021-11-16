package com.corner.searcharchitect.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.corner.searcharchitect.BR
import com.corner.searcharchitect.utility.databinding.DataBindingAdapter
import com.corner.searcharchitect.utility.databinding.DataBindingRecyclerViewConfig
import com.corner.searcharchitect.utility.extension.debounce
import com.corner.searcharchitect.utility.extension.hideKeyboard

abstract class BaseFragment<T : ViewDataBinding, S : BaseViewModel> : Fragment() {

    lateinit var binding: T

    abstract val viewModel: S

    @LayoutRes
    abstract fun getLayoutId(): Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        return binding.run {
            setVariable(BR.vm, viewModel)
            lifecycleOwner = viewLifecycleOwner
            root
        }
    }

    protected open fun <Item : Any, T : ViewDataBinding> initRecycleAdapterDataBinding(
        @LayoutRes layoutId: Int,
        itemId: Int,
        onItemCreate: ((T) -> Unit)? = null,
        onItemBind: ((T, Int) -> Unit)? = null,
        onItemClick: ((Int) -> Unit)? = null,
        recyclerView: RecyclerView,
        items: LiveData<List<Item>>,
    ): DataBindingRecyclerViewConfig<T> {
        val debounce = debounce<Int> {
            onItemClick?.invoke(it)
        }

        return DataBindingRecyclerViewConfig(
            layoutId = layoutId,
            itemId = itemId,
            realisation = object : DataBindingAdapter<T> {
                override fun onCreate(binding: T) {
                    onItemCreate?.invoke(binding)
                }

                override fun onBind(binding: T, position: Int) {
                    onItemBind?.invoke(binding, position)
                }
            },
            onItemClickListener = { _, _, position, _ ->
                debounce(position)
            }
        )
    }

    fun hideKeyboard() {
        if (isAdded) {
            activity?.hideKeyboard()
        }
    }

}