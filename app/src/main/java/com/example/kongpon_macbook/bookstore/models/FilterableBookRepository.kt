package com.example.kongpon_macbook.bookstore.models

import java.util.stream.Collectors

abstract class FilterableBookRepository : BookRepository() {

    protected val bookList = ArrayList<Book>()

    override fun getBooks(): java.util.ArrayList<Book> {
        return bookList
    }

    fun filter(keyword: String): List<Book> {
        return bookList.filter({ book: Book ->
            book.title.contains(keyword, true) || book.publicationYear.toString().contains(keyword, true)
        })
    }
}