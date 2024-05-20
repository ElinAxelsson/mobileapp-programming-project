package com.example.project;

import android.annotation.SuppressLint;
import android.view.Menu;
import android.view.MenuItem;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("FieldCanBeLocal")
public class MainActivity extends AppCompatActivity implements JsonTask.JsonTaskListener{
    private Gson gson;
    private static final Object JSON_FILE = "spel.json";
    private final String JSON_URL="https://mobprog.webug.se/json-api?login=a23eliax";

    ArrayList<Spel> spel= new ArrayList<>();
    ArrayList<RecyclerViewItem> recyclerViewItems=new ArrayList<>();
    private RecyclerViewAdapter adapter;

    @SuppressLint("SetJavaScriptEnabled")
    public void showInternalWebPage(){
        myWebView = findViewById(R.id.My_WebView);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.setWebViewClient(new WebViewClient());
        myWebView.loadUrl("file:///android_asset/about.html");

    }

    WebView myWebView;
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myWebView = findViewById(R.id.My_WebView);
        myWebView.setWebViewClient(new WebViewClient());
        myWebView.setVisibility(WebView.GONE);  // Göm WebView vid start

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        gson = new Gson();

        adapter=new RecyclerViewAdapter(this, recyclerViewItems, new RecyclerViewAdapter.OnClickListener() {
            @Override
            public void onClick(RecyclerViewItem item) {
                Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
        new JsonFile(this, this).execute((String) JSON_FILE);
        Log.d("Hej", "EO");
        RecyclerView view = findViewById(R.id.recyclerView);
        view.setLayoutManager(new LinearLayoutManager(this));
        view.setAdapter(adapter);
        new JsonTask(this).execute(JSON_URL);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_internal_web) {
            Log.d("hej", "Will display internal web page");

            myWebView.setVisibility(WebView.VISIBLE);  // Visa WebView
            showInternalWebPage();

            return true;
        }
        if (id == android.R.id.home) {
            // Hantera back-knappen i toolbaren
            if (myWebView.getVisibility() == WebView.VISIBLE && myWebView.canGoBack()) {
                myWebView.goBack();
            } else {
                onBackPressed();
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onBackPressed() {
        if (myWebView.getVisibility() == WebView.VISIBLE && myWebView.canGoBack()) {
            myWebView.goBack();
        } else if (myWebView.getVisibility() == WebView.VISIBLE) {
            myWebView.setVisibility(WebView.GONE);  // Göm WebView när användaren trycker på back och det inte finns någon tidigare sida
        } else {
            super.onBackPressed();  // Gå tillbaka till föregående aktivitet
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onPostExecute(String json) {
        Log.d("Hej", "" + json);
        Type type = new TypeToken<List<Spel>>() {}.getType();
        List<Spel> listOfGames = gson.fromJson(json, type);
        spel.clear();
        if(listOfGames != null) {
            spel.addAll(listOfGames);
            for (int i = 0; i < spel.size(); i++) {
                Log.d("Hej Elinparsear", spel.get(i).toString());
                recyclerViewItems.add(new RecyclerViewItem(spel.get(i).toString()));
            }
        }
        adapter.notifyDataSetChanged();
    }


}
