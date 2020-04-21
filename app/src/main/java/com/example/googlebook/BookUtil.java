package com.example.googlebook;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class BookUtil {

    public static String getJson(String u) throws IOException {
        URL url = new URL(u);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.connect();
        InputStream inputStream = httpURLConnection.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader reader = new BufferedReader(inputStreamReader);
        StringBuilder output = new StringBuilder();
        String line = reader.readLine();
        while (line != null) {
            output.append(line);
            line = reader.readLine();
        }
        return output.toString();
    }

    public static Bitmap getImage(String src){
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
//            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap image = BitmapFactory.decodeStream(input);
            Bitmap resized = Bitmap.createScaledBitmap(image, 150, 200, true);
            return resized;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static List<Book> getBookData(String j) throws IOException {
        List<Book> books = new ArrayList<Book>();
        try {
            JSONArray json = new JSONObject(getJson(j)).getJSONArray("items");
            for (int i = 0; i < json.length(); i++) {
                JSONObject jsonBook = json.getJSONObject(i);
                String bookAuthor = "";
                String bookTitle = "";
                Double bookPrice = 0.0;
                String bookDescription = "";
                String bookImageUrl = "";
                String bookInfo = "";

                JSONObject bookSaleInfo = jsonBook.getJSONObject("saleInfo");
                JSONObject bookVolumeInfo = jsonBook.getJSONObject("volumeInfo");
                if (bookSaleInfo.has("retailPrice")) {
                    bookPrice = bookSaleInfo.getJSONObject("retailPrice").getDouble("amount");
                }
                if (bookVolumeInfo.has("title")) {
                    bookTitle = jsonBook.getJSONObject("volumeInfo").getString("title");
                }

                if (bookVolumeInfo.has("authors")) {
                    bookAuthor = jsonBook.getJSONObject("volumeInfo").getJSONArray("authors").getString(0);
                }

                if (bookVolumeInfo.has("description")) {
                    bookDescription = jsonBook.getJSONObject("volumeInfo").getString("description");
                }
                if (bookVolumeInfo.has("infoLin")) {
                    bookInfo = jsonBook.getJSONObject("volumeInfo").getString("infoLink");
                }
                if (bookVolumeInfo.has("imageLinks")) {
                    bookImageUrl = jsonBook.getJSONObject("volumeInfo").getJSONObject("imageLinks").getString("thumbnail");
                }
                Book book = new Book(bookTitle,bookAuthor,bookDescription,bookPrice,bookInfo,getImage(bookImageUrl));
                books.add(book);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return books;
    }

    ;
}
