package com.example.googlebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private Button searchButton;
    private EditText searchText;
    private ProgressBar progressBar;
    private String searchUrl = "https://www.googleapis.com/books/v1/volumes?q=";
    private String maxResult = "&maxResults=5";

    private List<Book> books = new ArrayList<Book>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.book_list);
        searchButton = (Button) findViewById(R.id.searchButton);
        searchText = (EditText) findViewById(R.id.searchText);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);


        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new BookAdapter(books);
        recyclerView.setAdapter(adapter);



        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String search = searchText.getText().toString().replaceAll("\\s+","%20");
                if(!search.isEmpty()){
                DownloadAsync downloadAsync = new DownloadAsync();
                downloadAsync.execute(search);}
            }
        });


    }


    private class DownloadAsync extends AsyncTask<String,Void,List<Book>> {
        List<Book> b;

        protected void onPreExecute(){
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);

        }

        @Override
        protected List<Book> doInBackground(String... strings) {
            try {

                b = BookUtil.getBookData(searchUrl+strings[0]+maxResult);
            } catch (IOException e){
                e.printStackTrace();
            }
            return b;

        }


        @Override
        protected void onPostExecute(List<Book> b) {
            super.onPostExecute(b);
            books.clear();
            books.addAll(b);
            adapter.notifyDataSetChanged();
            progressBar.setVisibility(View.GONE);
        }
    }

}
