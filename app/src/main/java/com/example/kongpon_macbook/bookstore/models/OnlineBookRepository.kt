package com.example.kongpon_macbook.bookstore.models

import android.os.AsyncTask
import org.json.JSONArray
import org.json.JSONObject
import java.net.URL
import java.util.ArrayList

class OnlineBookRepository : FilterableBookRepository() {
    private val bookList = ArrayList<Book>()

    override fun loadAllBooks() {
        val url = URL("https://theory.cpe.ku.ac.th/~jittat/courses/sw-spec/ebooks/books.json")
        val loadDataFromURL = URLDownloadTask()
        loadDataFromURL.execute(url)
    }

    override fun getBooks(): ArrayList<Book> {
        return bookList
    }

    private inner class URLDownloadTask : AsyncTask<URL, Void, ArrayList<Book>>() {
        override fun doInBackground(vararg params: URL): ArrayList<Book> {
            val url: URL = params[0]
            val response: String = url.readText()
            val jsonArray = JSONArray(response)
            val books = ArrayList<Book>()
            for (index: Int in 0..jsonArray.length() - 1) {
                val jsonBook: JSONObject = jsonArray.getJSONObject(index)
                books.add(Book(
                        jsonBook.get("id") as Int,
                        jsonBook.get("title") as String,
                        jsonBook.get("price") as Double,
                        jsonBook.get("pub_year") as Int,
                        jsonBook.get("img_url") as String
                ))
            }
            println("finished download")
            return books
        }

        override fun onPostExecute(result: ArrayList<Book>) {
            bookList.clear()
            bookList.addAll(result)
            setChanged()
            notifyObservers()
        }

    }
}