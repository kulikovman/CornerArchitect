package com.searcharchitect.two.base

interface EventHandler<T> {

    fun obtainEvent(event: T)

}