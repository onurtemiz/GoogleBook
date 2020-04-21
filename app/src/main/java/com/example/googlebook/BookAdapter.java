package com.example.googlebook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BookAdapter  extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {
    private List<Book> books;
    private View v;
    private Context c;

    public static class BookViewHolder extends RecyclerView.ViewHolder {
        public TextView bookName;
        public TextView bookAuthor;
        public TextView bookDescription;
        public TextView bookPrice;
        public ImageView bookImage;
        public BookViewHolder (View itemView){
            super(itemView);
            bookName = (TextView) itemView.findViewById(R.id.bookName);
            bookAuthor = (TextView) itemView.findViewById(R.id.bookAuthor);
            bookDescription = (TextView) itemView.findViewById(R.id.bookDescription);
            bookPrice = (TextView) itemView.findViewById(R.id.bookPrice);
            bookImage = (ImageView) itemView.findViewById(R.id.bookImage);
        }

    }

    public BookAdapter(List<Book> b){
        books = b;
    }

    @Override
    public BookAdapter.BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        final Context c = parent.getContext();
        v = LayoutInflater.from(c).inflate(R.layout.book,parent,false);
        BookViewHolder viewHolder = new BookViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BookAdapter.BookViewHolder viewHolder, int position){
        Book book = books.get(position);
        TextView bookName = viewHolder.bookName;
        TextView bookAuthor = viewHolder.bookAuthor;
        TextView bookDescription = viewHolder.bookDescription;
        TextView bookPrice = viewHolder.bookPrice;
        ImageView bookImage = viewHolder.bookImage;

        bookName.setText(book.getBookName());
        bookAuthor.setText(book.getBookAuthor());
        bookDescription.setText(book.getBookDescription());
        bookPrice.setText(book.getBookPrice());
        bookImage.setImageBitmap(book.getBookImage());

    }

    @Override
    public int getItemCount(){
        return books.size();
    }
}
