package com.example.foodorderapp

interface UpdateSelectedItem {

    fun addItems(name: String, price: Int)

    fun getItems(): ArrayList<OrderListModel>

}