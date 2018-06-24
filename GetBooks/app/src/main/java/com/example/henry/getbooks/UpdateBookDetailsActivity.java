package com.example.henry.getbooks;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class UpdateBookDetailsActivity extends AppCompatActivity {

    private Button updateButton;

    private EditText titleEditText;
    private EditText ISBNEditText;
    private EditText authorEditText;
    private EditText stockEditText;
    private EditText priceEditText;
    private EditText categoryIDEditText;

    private String title;
    private String author;
    private double price;
    private int bookID;
    private long ISBN;
    private int stock;
    private int categoryID;

    private String IPAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_book_details);

        setTitle(getString(R.string.update_book_details));

        Intent data = getIntent();

        SharedPreferences pref;
        pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        IPAddress = pref.getString("IPAddress", "172.17.170.88");

        title = data.getStringExtra("Title");
        author = data.getStringExtra("Author");
        price = data.getDoubleExtra("Price", 0);
        bookID = data.getIntExtra("BookID", 0);
        ISBN = data.getLongExtra("ISBN", 0);
        stock = data.getIntExtra("Stock", 0);
        categoryID = data.getIntExtra("CategoryID", 0);

        titleEditText = findViewById(R.id.editText_update_title);
        ISBNEditText = findViewById(R.id.editText_update_ISBN);
        authorEditText = findViewById(R.id.editText_update_author);
        stockEditText = findViewById(R.id.editText_update_stock);
        priceEditText = findViewById(R.id.editText_update_price);
        categoryIDEditText = findViewById(R.id.editText_update_categoryID);

        titleEditText.setText(title);
        ISBNEditText.setText("" + ISBN);
        authorEditText.setText(author);
        stockEditText.setText("" + stock);
        priceEditText.setText(String.format("$%.2f", price));
        categoryIDEditText.setText(""+ categoryID);

        updateButton = findViewById(R.id.button_update);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String titleStringEditText = titleEditText.getText().toString();
                String authorStringEditText = authorEditText.getText().toString();
                double priceDoubleEditText = Double.parseDouble(priceEditText.getText().toString().substring(1));
                long ISBNLongEditText = Long.parseLong(ISBNEditText.getText().toString());
                int stockIntEditText = Integer.parseInt(stockEditText.getText().toString());
                int categoryIDIntEditText = Integer.parseInt(categoryIDEditText.getText().toString());

                Book b = new Book(titleStringEditText, authorStringEditText, priceDoubleEditText, bookID, ISBNLongEditText, stockIntEditText, categoryIDIntEditText);
                new AsyncTask<Book, Void, Void>(){

                    @Override
                    protected Void doInBackground(Book... books) {
                        Book.updateBook(books[0], IPAddress);
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void result) {
                        Intent intent = new Intent(getApplicationContext(), BooksListActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    }
                }.execute(b);

            }
        });

    }








}
