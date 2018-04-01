package com.example.kongpon_macbook.bookstore.presenters

import com.example.kongpon_macbook.bookstore.models.Book

interface BookView {
    fun setBookList(books: ArrayList<Book>)
}