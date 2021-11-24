package com.searcharchitect.one.utility.databinding

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewConfiguration
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.annotation.NonNull
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.searcharchitect.common.utility.log

@BindingAdapter(value = ["rvItems", "rvConfig"])
fun <ItemType, BindingType : ViewDataBinding> setupRecyclerView(
    recyclerView: RecyclerView,
    items: List<ItemType>?,
    dataBindingRecyclerViewConfig: DataBindingRecyclerViewConfig<BindingType>?
) {

    log("RecyclerView set ${items?.size} items")

    if (dataBindingRecyclerViewConfig == null) {
        return
    }

    var oldItems: MutableList<ItemType>? = null

    recyclerView.tag?.let {
        @Suppress("UNCHECKED_CAST")
        oldItems = it as MutableList<ItemType>

    }

    if (oldItems == null) {
        oldItems = mutableListOf()
        recyclerView.tag = oldItems
    }

    if (recyclerView.adapter == null) {

        val mLayoutManager: RecyclerView.LayoutManager = recyclerView.layoutManager!!
        recyclerView.layoutManager = mLayoutManager
        //recyclerView.itemAnimator = null

        recyclerView.touchscreenBlocksFocus = true

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            recyclerView.defaultFocusHighlightEnabled = false
        }

        dataBindingRecyclerViewConfig.let {
            recyclerView.adapter = DataBindingRecyclerAdapter(
                items = oldItems,
                layoutId = it.layoutId,
                itemId = it.itemId,
                realisation = dataBindingRecyclerViewConfig.realisation,
                onItemClickListener = dataBindingRecyclerViewConfig.onItemClickListener,
                onItemDoubleClickListener = dataBindingRecyclerViewConfig.onItemDoubleClickListener,
                onItemLongClickListener = dataBindingRecyclerViewConfig.onItemLongClickListener
            )
        }
    }

    if (oldItems !== items) {
        oldItems?.let { old ->
            old.clear()
            items?.let {
                old.addAll(items)
            }
        }
    }

    recyclerView.adapter?.notifyDataSetChanged()
}


data class DataBindingRecyclerViewConfig<BindingType : ViewDataBinding>(
    val layoutId: Int,
    val itemId: Int,
    val realisation: DataBindingAdapter<BindingType>? = null,
    val onItemClickListener: AdapterView.OnItemClickListener? = null,
    val onItemLongClickListener: AdapterView.OnItemLongClickListener? = null,
    val onItemDoubleClickListener: AdapterView.OnItemClickListener? = null
)


class DataBindingRecyclerAdapter<ItemType, BindingType : ViewDataBinding>(
    private val items: List<ItemType>?,
    private val layoutId: Int,
    private val itemId: Int,
    private val realisation: DataBindingAdapter<BindingType>? = null,
    private val onItemClickListener: AdapterView.OnItemClickListener? = null,
    private val onItemLongClickListener: AdapterView.OnItemLongClickListener? = null,
    private val onItemDoubleClickListener: AdapterView.OnItemClickListener? = null
) : RecyclerView.Adapter<DataBindingRecyclerAdapter.BindingViewHolder>(),
    DataBindingAdapter<BindingType> {

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        val binding = DataBindingUtil.inflate<BindingType>(
            LayoutInflater.from(parent.context),
            layoutId,
            parent,
            false
        )
        onCreate(binding)
        val result = BindingViewHolder(binding)
        if (onItemClickListener != null || onItemDoubleClickListener != null) {
            binding.root.setOnClickListener(object : DoubleClickListener() {
                override fun onSingleClick(view: View) {
                    onItemClickListener?.onItemClick(
                        null,
                        view,
                        result.adapterPosition,
                        view.id.toLong()
                    )
                }

                override fun onDoubleClick(view: View) {
                    onItemDoubleClickListener?.onItemClick(
                        null,
                        view,
                        result.adapterPosition,
                        view.id.toLong()
                    )
                }
            })
        }

        onItemLongClickListener?.let {
            binding.root.setOnLongClickListener { view ->
                it.onItemLongClick(null, view, result.adapterPosition, view.id.toLong())
                false
            }
        }

        return result
    }

    override fun onBindViewHolder(
        @NonNull holder: BindingViewHolder,
        @SuppressLint("RecyclerView") position: Int
    ) {
        if (itemId != -1) {
            holder.binding.setVariable(itemId, items!![holder.adapterPosition])
        }
        @Suppress("UNCHECKED_CAST")
        onBind(holder.binding as BindingType, holder.adapterPosition)
        holder.binding.executePendingBindings()
    }

    override fun onCreate(binding: BindingType) {
        realisation?.onCreate(binding)
    }

    override fun onBind(binding: BindingType, position: Int) {
        realisation?.onBind(binding, position)
    }

    class BindingViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)

}


interface DataBindingAdapter<BindingType : ViewDataBinding> {

    fun onCreate(binding: BindingType)
    fun onBind(binding: BindingType, position: Int)

}


abstract class DoubleClickListener : View.OnClickListener {

    private var lastClickTime: Long = 0

    override fun onClick(v: View) {
        val clickTime = System.currentTimeMillis()

        if (clickTime - lastClickTime < DOUBLE_CLICK_TIME_DELTA) {
            onDoubleClick(v)
            lastClickTime = 0
        } else {
            onSingleClick(v)
        }

        lastClickTime = clickTime
    }

    abstract fun onSingleClick(view: View)

    abstract fun onDoubleClick(view: View)

    companion object {
        private val DOUBLE_CLICK_TIME_DELTA = ViewConfiguration.getDoubleTapTimeout().toLong()
    }

}