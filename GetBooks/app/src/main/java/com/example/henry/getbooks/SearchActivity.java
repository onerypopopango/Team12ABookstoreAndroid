package com.example.henry.getbooks;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private ArrayAdapter<String> categoryListAdapter;
    private Spinner mCategoryListSpinner;
    private RecyclerView mRecyclerViewBooksList;
    private BooksListAdapter adapter;
    private String IPAddress;
    private ImageButton searchButton;
    private EditText searchTextbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        setTitle(getString(R.string.search_book));

        mRecyclerViewBooksList = (RecyclerView) findViewById(R.id.recyclerview_search_book);

        SharedPreferences pref;
        pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        IPAddress = pref.getString("IPAddress", "172.17.170.88");

        searchButton = (ImageButton) findViewById(R.id.imageButton_icon__search);
        searchTextbox = (EditText) findViewById(R.id.editText_search_title);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchValue = searchTextbox.getText().toString();

                new GetHttpResponse().execute(searchValue);
            }
        });

    }




    private class GetHttpResponse extends AsyncTask<String, Void, List<Book>> {
        @Override
        protected List<Book> doInBackground(String... params) {
            List<Book> listOfBooks = Book.searchBookByName(params[0], IPAddress);

            for(Book book : listOfBooks){
                Bitmap bitmap = Book.getPhoto(String.valueOf(book.getISBN()), IPAddress);
                book.setBitmap(bitmap);
            }
            return listOfBooks;
        }
        @Override
        protected void onPostExecute(List<Book> result) {
            adapter = new BooksListAdapter(SearchActivity.this, result);
            mRecyclerViewBooksList.setAdapter(adapter);
            mRecyclerViewBooksList.setLayoutManager(new GridLayoutManager(SearchActivity.this, 2, LinearLayoutManager.VERTICAL, false));
        }
    }


}
