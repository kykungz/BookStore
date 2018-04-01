package com.example.kongpon_macbook.bookstore

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.kongpon_macbook.bookstore.models.Book
import com.example.kongpon_macbook.bookstore.models.BookRepository
import com.example.kongpon_macbook.bookstore.models.MockBookRepository
import com.example.kongpon_macbook.bookstore.models.OnlineBookRepository
import com.example.kongpon_macbook.bookstore.presenters.BookPresenter
import com.example.kongpon_macbook.bookstore.presenters.BookView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BookView { // View

    private lateinit var bookRepository: BookRepository // Model
    private lateinit var bookPresenter: BookPresenter // Presenter
    private lateinit var bookAdapter: ArrayAdapter<Book>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
//        bookRepository = MockBookRepository()
        bookRepository = OnlineBookRepository()
        bookAdapter = TwoLineArrayAdapter(this, ArrayList())
        bookPresenter = BookPresenter(this, bookRepository)

        bookListView.adapter = bookAdapter
        bookPresenter.start()
    }

    override fun setBookList(books: ArrayList<Book>) {
        bookAdapter.clear()
        bookAdapter.addAll(books)
    }
}