package com.example.kongpon_macbook.bookstore.models


class MyBookRepository : FilterableBookRepository() {

    override fun loadAllBooks() {
        bookList.clear()
        bookList.add(Book(1,"How to win BNK election",500.0))
        bookList.add(Book(2,"How to write Android App",199.0))
        bookList.add(Book(5,"Sleep today",39.99))
        setChanged()
        notifyObservers()
    }

    fun addBook(book: Book): Boolean {
        if (!bookList.contains(book)) {
            bookList.add(book)
            setChanged()
            notifyObservers()
            return true
        }
        return false
    }

}