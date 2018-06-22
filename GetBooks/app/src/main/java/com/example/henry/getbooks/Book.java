package com.example.henry.getbooks;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class Book{

    final static String host = "http://172.17.170.88/booksca/WCFService.svc";
    final static String imageURL = "http://172.17.170.88/booksca/images";

    private String title;
    private String author;
    private double price;
    private int bookID;
    private long ISBN;
    private int stock;
    private Bitmap bitmap;

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Book(String title, String author, double price, int bookID, long ISBN, int stock){
        this.title = title;
        this.author = author;
        this.price = price;
        this.bookID = bookID;
        this.ISBN = ISBN;
        this.stock = stock;
    }

    public static List<Book> listBooks() {
        List<Book> list = new ArrayList<Book>();
        try {
            JSONArray jsonArray = JSONParser.getJSONArrayFromUrl(host+"/Books");
            JSONObject jsonObject;
            String dataTitle = null;
            String dataAuthor = null;
            double dataPrice = 0;
            int dataBookID = 0;
            long dataISBN = 0;
            int dataStock = 0;


            for (int i=0; i<jsonArray.length(); i++) {

                jsonObject = jsonArray.getJSONObject(i);
                dataTitle = jsonObject.getString("Title");
                dataAuthor = jsonObject.getString("Author");
                dataPrice = jsonObject.getDouble("Price");
                dataBookID = jsonObject.getInt("BookID");
                dataISBN = jsonObject.getLong("ISBN");
                dataStock = jsonObject.getInt("Stock");
                Book book = new Book(dataTitle, dataAuthor, dataPrice, dataBookID, dataISBN, dataStock);
                list.add(book);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public static Bitmap getPhoto(String id) {
        try {
            URL url = new URL(String.format("%s/%s.jpg",imageURL, id));
//            URL url = new URL(String.format("http://172.17.170.88/booksca/images/9780060555665.jpg"));

            URLConnection conn = url.openConnection();
            InputStream ins = conn.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(ins);
            ins.close();
            return bitmap;
        } catch (Exception e) {
            Log.e("Employee.getPhoto()", "Bitmap error");
        }
        return(null);
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public long getISBN() {
        return ISBN;
    }

    public void setISBN(long ISBN) {
        this.ISBN = ISBN;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
