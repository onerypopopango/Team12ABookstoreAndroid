package com.example.henry.getbooks;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class BooksListActivity extends AppCompatActivity {

    private ArrayAdapter<String> categoryListAdapter;
    private Spinner mCategoryListSpinner;
    private RecyclerView mRecyclerViewBooksList;
    private BooksListAdapter adapter;
    private List<Book> booksList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_list);

        setTitle(getString(R.string.list_of_allbooks));

        mRecyclerViewBooksList = (RecyclerView) findViewById(R.id.recyclerview_bookslist);

        new GetHttpResponse().execute();




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bookslist, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search:
                Intent intent = new Intent(this, SearchActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class GetHttpResponse extends AsyncTask<Void, Void, List<Book>>{
        @Override
        protected List<Book> doInBackground(Void... params) {
            List<Book> listOfBooks = Book.listBooks();

            for(Book book : listOfBooks){
                Bitmap bitmap = Book.getPhoto(String.valueOf(book.getISBN()));
                book.setBitmap(bitmap);
            }
            return listOfBooks;
        }
        @Override
        protected void onPostExecute(List<Book> result) {
            adapter = new BooksListAdapter(BooksListActivity.this, result);
            mRecyclerViewBooksList.setAdapter(adapter);
            mRecyclerViewBooksList.setLayoutManager(new GridLayoutManager(BooksListActivity.this, 2, LinearLayoutManager.VERTICAL, false));
        }
    }





}
