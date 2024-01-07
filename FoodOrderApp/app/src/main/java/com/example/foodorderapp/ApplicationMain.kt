package com.example.foodorderapp

import android.app.Application
import android.content.Context

class ApplicationMain: Application(), UpdateSelectedItem {

    lateinit var context: Context
    lateinit var orderListModel: ArrayList<OrderListModel>

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        orderListModel = ArrayList<OrderListModel>()
    }

    public fun getMyContext(): Context{
        return context
    }

    override fun addItems(name: String, price: Int) {
        orderListModel.add(OrderListModel(name, price))
    }

    override fun getItems(): ArrayList<OrderListModel> {
        TODO("Not yet implemented")
    }
}