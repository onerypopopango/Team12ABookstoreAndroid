package com.example.henry.getbooks;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class Book{

    private String title;
    private String author;
    private double price;
    private int bookID;
    private long ISBN;
    private int stock;
    private int categoryID;
    private Bitmap bitmap;

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Book(String title, String author, double price, int bookID, long ISBN, int stock, int categoryID){
        this.title = title;
        this.author = author;
        this.price = price;
        this.bookID = bookID;
        this.ISBN = ISBN;
        this.stock = stock;
        this.categoryID = categoryID;
    }

    public static List<Book> listBooks(String IPAddress) {
        List<Book> list = new ArrayList<Book>();
        try {

            final String host = "http://" + IPAddress + "/GetBooks/WCFService.svc";

            JSONArray jsonArray = JSONParser.getJSONArrayFromUrl(host+"/Books");
            JSONObject jsonObject;
            String dataTitle = null;
            String dataAuthor = null;
            double dataPrice = 0;
            int dataBookID = 0;
            long dataISBN = 0;
            int dataStock = 0;
            int dataCategoryID = 0;

            for (int i=0; i<jsonArray.length(); i++) {

                jsonObject = jsonArray.getJSONObject(i);

                dataTitle = jsonObject.getString("Title");
                dataAuthor = jsonObject.getString("Author");
                dataPrice = jsonObject.getDouble("Price");
                dataBookID = jsonObject.getInt("BookID");
                dataISBN = jsonObject.getLong("ISBN");
                dataStock = jsonObject.getInt("Stock");
                dataCategoryID = jsonObject.getInt("CategoryID");
                Book book = new Book(dataTitle, dataAuthor, dataPrice, dataBookID, dataISBN, dataStock, dataCategoryID);
                list.add(book);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public static List<Book> searchBookByName(String searchValue, String IPAddress) {
        List<Book> list = new ArrayList<Book>();
        try {

            final String host = "http://" + IPAddress + "/GetBooks/WCFService.svc";

            JSONArray jsonArray = JSONParser.getJSONArrayFromUrl(host+"/Books/"+searchValue);
            JSONObject jsonObject;
            String dataTitle = null;
            String dataAuthor = null;
            double dataPrice = 0;
            int dataBookID = 0;
            long dataISBN = 0;
            int dataStock = 0;
            int dataCategoryID = 0;

            for(int i = 0; i < jsonArray.length(); i++){

                jsonObject = jsonArray.getJSONObject(i);
                dataTitle = jsonObject.getString("Title");
                dataAuthor = jsonObject.getString("Author");
                dataPrice = jsonObject.getDouble("Price");
                dataBookID = jsonObject.getInt("BookID");
                dataISBN = jsonObject.getLong("ISBN");
                dataStock = jsonObject.getInt("Stock");
                dataCategoryID = jsonObject.getInt("CategoryID");
                Book book = new Book(dataTitle, dataAuthor, dataPrice, dataBookID, dataISBN, dataStock, dataCategoryID);
                list.add(book);
            }

        } catch (Exception e) {
        }
        return list;
    }

    public static Bitmap getPhoto(String id, String IPAddress) {
        try {
            final String imageURL = "http://" + IPAddress + "/GetBooks/images";

            URL url = new URL(String.format("%s/%s.jpg",imageURL, id));
            URLConnection conn = url.openConnection();
            InputStream ins = conn.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(ins);
            ins.close();
            return bitmap;
        } catch (Exception e) {
            Log.e("Book.getPhoto()", "Bitmap error");
        }
        return(null);
    }

    public static void updateBook(Book book, String IPAddress){
        final String host = "http://" + IPAddress + "/GetBooks/WCFService.svc";

        JSONObject jBook = new JSONObject();

        try {
            jBook.put("Author", book.getAuthor());
            jBook.put("BookID", book.getBookID());
            jBook.put("CategoryID", book.getCategoryID());
            jBook.put("ISBN1", book.getISBN());
            jBook.put("Price", book.getPrice());
            jBook.put("Stock", book.getStock());
            jBook.put("Title", book.getTitle());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String result = JSONParser.postStream(host+"/Update", jBook.toString());

    }




    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
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
