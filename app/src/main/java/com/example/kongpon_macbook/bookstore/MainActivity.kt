package com.example.kongpon_macbook.bookstore

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Filterable
import com.example.kongpon_macbook.bookstore.models.Book
import com.example.kongpon_macbook.bookstore.models.BookRepository
import com.example.kongpon_macbook.bookstore.models.FilterableBookRepository
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)

        val searchItem: MenuItem? = menu?.findItem(R.id.search_book_button)
        val searchView: SearchView = searchItem?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    bookAdapter.clear()
                    bookAdapter.addAll((bookRepository as FilterableBookRepository).filter(newText))
                }
                return false
            }

        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun setBookList(books: ArrayList<Book>) {
        bookAdapter.clear()
        bookAdapter.addAll(books)
    }
}