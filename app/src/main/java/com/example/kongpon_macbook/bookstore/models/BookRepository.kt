package com.example.kongpon_macbook.bookstore.models

import java.util.*

abstract class BookRepository : Observable() {
    abstract fun loadAllBooks()
    abstract fun getBooks(): ArrayList<Book>
}