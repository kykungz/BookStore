package com.example.kongpon_macbook.bookstore.presenters

import com.example.kongpon_macbook.bookstore.models.BookRepository
import java.util.*

class BookPresenter(
        private val view: BookView,
        private val repository: BookRepository
) : Observer {

    fun start() {
        repository.addObserver(this)
        repository.loadAllBooks()
    }

    override fun update(obj: Observable?, arg: Any?) {
        if(obj == repository) {
            view.setBookList(repository.getBooks())
        }
    }
}