package com.example.cornerarchitect.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.cornerarchitect.BR
import com.example.cornerarchitect.utility.databinding.DataBindingAdapter
import com.example.cornerarchitect.utility.databinding.DataBindingRecyclerViewConfig

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

    /*override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initStatusBar()
        initTopPadding()
    }

    protected open fun initStatusBar() {
        transparentStatusBarWithBlackIcons(activity)
    }

    protected open fun initTopPadding() {
        binding.root.apply {
            setOnApplyWindowInsetsListener { _, insets ->
                setPadding(0, insets.systemWindowInsetTop, 0, 0)
                return@setOnApplyWindowInsetsListener insets
            }
        }
    }*/


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

    fun initDefaultImeDoneAction(editText: EditText, extraAction: (() -> Unit)? = null) {
        editText.setOnEditorActionListener { _, _, _ ->
            hideKeyboard()
            extraAction?.invoke()
            true
        }
    }

    fun hideKeyboard() {
        if (isAdded) {
            activity?.hideKeyboard()
        }
    }

    fun showKeyboard(editText: EditText) {
        val imm = editText.context
            .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT)
    }

}