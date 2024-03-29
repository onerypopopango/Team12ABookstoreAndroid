package com.example.henry.getbooks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BookDetailsActivity extends AppCompatActivity {

    private Button editButton;

    private TextView titleTextView;
    private TextView ISBNTextView;
    private TextView authorTextView;
    private TextView stockTextView;
    private TextView priceTextView;
    private TextView categoryIDTextView;

    private String title;
    private String author;
    private double price;
    private int bookID;
    private long ISBN;
    private int stock;
    private int categoryID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_details);

        setTitle(getString(R.string.book_details));

        Intent data = getIntent();

        title = data.getStringExtra("Title");
        author = data.getStringExtra("Author");
        price = data.getDoubleExtra("Price", 0);
        bookID = data.getIntExtra("BookID", 0);
        ISBN = data.getLongExtra("ISBN", 0);
        stock = data.getIntExtra("Stock", 0);
        categoryID = data.getIntExtra("CategoryID", 0);

        titleTextView = findViewById(R.id.textView_title);
        ISBNTextView = findViewById(R.id.textView_ISBN);
        authorTextView = findViewById(R.id.textView_author);
        stockTextView = findViewById(R.id.textView_stock);
        priceTextView = findViewById(R.id.textView_price);
        categoryIDTextView = findViewById(R.id.textView_categoryID);

        titleTextView.setText(title);
        ISBNTextView.setText("" + ISBN);
        authorTextView.setText(author);
        stockTextView.setText("" + stock);
        priceTextView.setText(String.format("$%.2f", price));
        categoryIDTextView.setText(""+ categoryID);

        editButton = (Button) findViewById(R.id.button_details_edit);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UpdateBookDetailsActivity.class);
                intent.putExtra("Title", title);
                intent.putExtra("Author", author);
                intent.putExtra("Price", price);
                intent.putExtra("BookID", bookID);
                intent.putExtra("ISBN", ISBN);
                intent.putExtra("Stock", stock);
                intent.putExtra("CategoryID", categoryID);
                startActivity(intent);
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            return true;
        }
        return false;
    }
}
