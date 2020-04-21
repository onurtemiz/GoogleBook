package com.example.googlebook;

import android.graphics.Bitmap;

public class Book {
    private String bookName;
    private String bookAuthor;
    private String bookDescription;
    private String bookPrice = "";
    private String bookInfo;
    private Bitmap bookImage;

    public Book (String name, String author, String description, Double price, String info, Bitmap image){
        bookName = name;
        bookAuthor = author;
        bookDescription = description;
        if(price != 0.0){
        bookPrice = Double.toString(price) + " TL";}
        bookImage = image;
        bookInfo = info;
    }

    public String getBookName() {
        return bookName;
    }

    public Bitmap getBookImage() {
        return bookImage;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public String getBookPrice() {
        return bookPrice;
    }

    public String getBookInfo() {
        return bookInfo;
    }
}
