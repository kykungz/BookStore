package com.example.kongpon_macbook.bookstore.models

class CartRepository : FilterableBookRepository() {

    override fun loadAllBooks() {
        bookList.clear()
        setChanged()
        notifyObservers()
    }

    fun checkout() {
        
    }

}