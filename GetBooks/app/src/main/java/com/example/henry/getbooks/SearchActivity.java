package com.example.henry.getbooks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private ArrayAdapter<String> categoryListAdapter;
    private Spinner mCategoryListSpinner;
    private RecyclerView mRecyclerViewBooksList;
    private BooksListAdapter adapter;
    private List<Book> booksList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        setTitle("Search");

        mRecyclerViewBooksList = (RecyclerView) findViewById(R.id.recyclerview_search_book);
//        booksList = new ArrayList<Book>();
//        booksList.add(new Book("TitleA", "AuthorA", 2));
//        booksList.add(new Book("TitleB", "AuthorB", 3));
//
//        adapter = new BooksListAdapter(this, booksList);
//        mRecyclerViewBooksList.setAdapter(adapter);
////        mRecyclerViewBooksList.setLayoutManager(new LinearLayoutManager(this));
//        mRecyclerViewBooksList.setLayoutManager(new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false));




    }
}
