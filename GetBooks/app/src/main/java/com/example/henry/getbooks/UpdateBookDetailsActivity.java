package com.example.henry.getbooks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    private String title;
    private String author;
    private double price;
    private int bookID;
    private long ISBN;
    private int stock;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_book_details);

        setTitle(getString(R.string.update_book_details));

        Intent data = getIntent();

        title = data.getStringExtra("Title");
        author = data.getStringExtra("Author");
        price = data.getDoubleExtra("Price", 0);
        bookID = data.getIntExtra("BookID", 0);
        ISBN = data.getLongExtra("ISBN", 0);
        stock = data.getIntExtra("Stock", 0);

        titleEditText = findViewById(R.id.editText_update_title);
        ISBNEditText = findViewById(R.id.editText_update_ISBN);
        authorEditText = findViewById(R.id.editText_update_author);
        stockEditText = findViewById(R.id.editText_update_stock);
        priceEditText = findViewById(R.id.editText_update_price);

        titleEditText.setText(title);
        ISBNEditText.setText("" + ISBN);
        authorEditText.setText(author);
        stockEditText.setText("" + stock);
        priceEditText.setText("$" + price);


        //TODO: To add the update method
        updateButton = findViewById(R.id.button_update);




    }








}
