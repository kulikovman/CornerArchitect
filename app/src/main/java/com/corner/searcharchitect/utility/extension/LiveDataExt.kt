package com.corner.searcharchitect.utility.extension

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData

fun <T1, R> combine(
    t1: LiveData<T1>,
    block: (T1?) -> R
): LiveData<R> {
    return MutableLiveData<R>().apply {
        value = block(t1.value)
    }
}

fun <T1, T2, R> combine(
    t1: LiveData<T1>,
    t2: LiveData<T2>,
    block: (T1?, T2?) -> R
): LiveData<R> {
    return MediatorLiveData<R>().apply {
        addSource(t1) { value = block(it, t2.value) }
        addSource(t2) { value = block(t1.value, it) }
    }
}

fun <T1, T2, T3, R> combine(
    t1: LiveData<T1>,
    t2: LiveData<T2>,
    t3: LiveData<T3>,
    block: (T1?, T2?, T3?) -> R
): LiveData<R> {
    return MediatorLiveData<R>().apply {
        addSource(t1) { value = block(it, t2.value, t3.value) }
        addSource(t2) { value = block(t1.value, it, t3.value) }
        addSource(t3) { value = block(t1.value, t2.value, it) }
    }
}

fun <T1, T2, T3, T4, R> combine(
    t1: LiveData<T1>,
    t2: LiveData<T2>,
    t3: LiveData<T3>,
    t4: LiveData<T4>,
    block: (T1?, T2?, T3?, T4?) -> R
): LiveData<R> {
    return MediatorLiveData<R>().apply {
        addSource(t1) { value = block(it, t2.value, t3.value, t4.value) }
        addSource(t2) { value = block(t1.value, it, t3.value, t4.value) }
        addSource(t3) { value = block(t1.value, t2.value, it, t4.value) }
        addSource(t4) { value = block(t1.value, t2.value, t3.value, it) }
    }
}

fun <T1, T2, T3, T4, T5, R> combine(
    t1: LiveData<T1>,
    t2: LiveData<T2>,
    t3: LiveData<T3>,
    t4: LiveData<T4>,
    t5: LiveData<T5>,
    block: (T1?, T2?, T3?, T4?, T5?) -> R
): LiveData<R> {
    return MediatorLiveData<R>().apply {
        addSource(t1) { value = block(it, t2.value, t3.value, t4.value, t5.value) }
        addSource(t2) { value = block(t1.value, it, t3.value, t4.value, t5.value) }
        addSource(t3) { value = block(t1.value, t2.value, it, t4.value, t5.value) }
        addSource(t4) { value = block(t1.value, t2.value, t3.value, it, t5.value) }
        addSource(t5) { value = block(t1.value, t2.value, t3.value, t4.value, it) }
    }
}

fun <T1, T2, T3, T4, T5, T6, R> combine(
    t1: LiveData<T1>,
    t2: LiveData<T2>,
    t3: LiveData<T3>,
    t4: LiveData<T4>,
    t5: LiveData<T5>,
    t6: LiveData<T6>,
    block: (T1?, T2?, T3?, T4?, T5?, T6?) -> R
): LiveData<R> {
    return MediatorLiveData<R>().apply {
        addSource(t1) { value = block(it, t2.value, t3.value, t4.value, t5.value, t6.value) }
        addSource(t2) { value = block(t1.value, it, t3.value, t4.value, t5.value, t6.value) }
        addSource(t3) { value = block(t1.value, t2.value, it, t4.value, t5.value, t6.value) }
        addSource(t4) { value = block(t1.value, t2.value, t3.value, it, t5.value, t6.value) }
        addSource(t5) { value = block(t1.value, t2.value, t3.value, t4.value, it, t6.value) }
        addSource(t6) { value = block(t1.value, t2.value, t3.value, t4.value, t5.value, it) }
    }
}

fun <T1, T2, T3, T4, T5, T6, T7, R> combine(
    t1: LiveData<T1>,
    t2: LiveData<T2>,
    t3: LiveData<T3>,
    t4: LiveData<T4>,
    t5: LiveData<T5>,
    t6: LiveData<T6>,
    t7: LiveData<T7>,
    block: (T1?, T2?, T3?, T4?, T5?, T6?, T7?) -> R
): LiveData<R> {
    return MediatorLiveData<R>().apply {
        addSource(t1) { value = block(it, t2.value, t3.value, t4.value, t5.value, t6.value, t7.value) }
        addSource(t2) { value = block(t1.value, it, t3.value, t4.value, t5.value, t6.value, t7.value) }
        addSource(t3) { value = block(t1.value, t2.value, it, t4.value, t5.value, t6.value, t7.value) }
        addSource(t4) { value = block(t1.value, t2.value, t3.value, it, t5.value, t6.value, t7.value) }
        addSource(t5) { value = block(t1.value, t2.value, t3.value, t4.value, it, t6.value, t7.value) }
        addSource(t6) { value = block(t1.value, t2.value, t3.value, t4.value, t5.value, it, t7.value) }
        addSource(t7) { value = block(t1.value, t2.value, t3.value, t4.value, t5.value, t6.value, it) }
    }
}

fun <T1, T2, T3, T4, T5, T6, T7, T8, R> combine(
    t1: LiveData<T1>,
    t2: LiveData<T2>,
    t3: LiveData<T3>,
    t4: LiveData<T4>,
    t5: LiveData<T5>,
    t6: LiveData<T6>,
    t7: LiveData<T7>,
    t8: LiveData<T8>,
    block: (T1?, T2?, T3?, T4?, T5?, T6?, T7?, T8?) -> R
): LiveData<R> {
    return MediatorLiveData<R>().apply {
        addSource(t1) { value = block(it, t2.value, t3.value, t4.value, t5.value, t6.value, t7.value, t8.value) }
        addSource(t2) { value = block(t1.value, it, t3.value, t4.value, t5.value, t6.value, t7.value, t8.value) }
        addSource(t3) { value = block(t1.value, t2.value, it, t4.value, t5.value, t6.value, t7.value, t8.value) }
        addSource(t4) { value = block(t1.value, t2.value, t3.value, it, t5.value, t6.value, t7.value, t8.value) }
        addSource(t5) { value = block(t1.value, t2.value, t3.value, t4.value, it, t6.value, t7.value, t8.value) }
        addSource(t6) { value = block(t1.value, t2.value, t3.value, t4.value, t5.value, it, t7.value, t8.value) }
        addSource(t7) { value = block(t1.value, t2.value, t3.value, t4.value, t5.value, t6.value, it, t8.value) }
        addSource(t8) { value = block(t1.value, t2.value, t3.value, t4.value, t5.value, t6.value, t7.value, it) }
    }
}