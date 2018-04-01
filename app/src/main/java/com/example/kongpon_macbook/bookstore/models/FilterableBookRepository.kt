package com.example.kongpon_macbook.bookstore.models

import java.util.stream.Collectors

abstract class FilterableBookRepository : BookRepository() {

    private val bookList = ArrayList<Book>()

    fun filter(keyword: String): List<Book> {
        return bookList.filter { book: Book ->
            book.title.contains(keyword) || book.publicationYear.toString().contains(keyword)
        }
    }
}