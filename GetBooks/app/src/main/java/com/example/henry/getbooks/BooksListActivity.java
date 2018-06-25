package com.example.henry.getbooks;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class BooksListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerViewBooksList;
    private BooksListAdapter adapter;
    private String IPAddress;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_list);
        setTitle(getString(R.string.list_of_allbooks));
        mRecyclerViewBooksList = (RecyclerView) findViewById(R.id.recyclerview_bookslist);
        progressBar = (ProgressBar) findViewById(R.id.progressbar_bookslist);

        SharedPreferences pref;
        pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        IPAddress = pref.getString("IPAddress", "172.17.170.88");

        new GetHttpResponse().execute();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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
            case R.id.settings:
                Intent intentSettings = new Intent(this, PreferencesActivity.class);
                startActivity(intentSettings);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class GetHttpResponse extends AsyncTask<Void, Void, List<Book>>{
        @Override
        protected List<Book> doInBackground(Void... params) {
            List<Book> listOfBooks = Book.listBooks(IPAddress);

            for(Book book : listOfBooks){
                Bitmap bitmap = Book.getPhoto(String.valueOf(book.getISBN()), IPAddress);
                book.setBitmap(bitmap);
            }
            return listOfBooks;
        }

        @Override
        protected void onPostExecute(List<Book> result) {
            //Setting the grid
            progressBar.setVisibility(View.GONE);
            adapter = new BooksListAdapter(BooksListActivity.this, result);
            mRecyclerViewBooksList.setAdapter(adapter);
            mRecyclerViewBooksList.setLayoutManager(new GridLayoutManager(BooksListActivity.this, 2, LinearLayoutManager.VERTICAL, false));
            //Animation effect for loading the grid
            int resId = R.anim.layout_animation_fall_down;
            final Context context = mRecyclerViewBooksList.getContext();
            LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(context, resId);
            mRecyclerViewBooksList.setLayoutAnimation(animation);

        }
    }



}
