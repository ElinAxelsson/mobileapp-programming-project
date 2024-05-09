package com.example.project;

import android.annotation.SuppressLint;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

@SuppressWarnings("FieldCanBeLocal")
public class MainActivity extends AppCompatActivity implements JsonTask.JsonTaskListener{
    private final String JSON_URL="https://mobprog.webug.se/json-api?login=a23gabst";

    private ArrayList<Spel> spel= new ArrayList<>();
    private ArrayList<RecyclerViewItem> recyclerViewItems=new ArrayList<>();
    private RecyclerViewAdapter adapter;

    public void showInternalWebPage(){
        // TODO: Add your code for showing internal web page here
        WebView webView = findViewById(R.id.My_WebView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        myWebView.loadUrl("file:///android_asset/about.html");
    }

    WebView myWebView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myWebView = findViewById(R.id.My_WebView);
        myWebView.setWebViewClient(new WebViewClient()); // Do not open in Chrome!

        spel.add(new Spel("1","a23eliax", "Catan", "Brädspel", 399));
        spel.add(new Spel("2","a23eliax", "Ticket to Ride", "Brädspel", 299));
        spel.add(new Spel("3","a23eliax", "Risk", "Brädspel", 349));
        spel.add(new Spel("4","a23eliax", "Uno", "Kortspel", 49));
        spel.add(new Spel("5","a23eliax", "Monopol", "Brädspel", 249));

        for (int i=0;i<spel.size();i++) {
            Log.d("Logga", spel.get(i).toString());
            recyclerViewItems.add(new RecyclerViewItem(spel.get(i).toString()));
        }

        adapter=new RecyclerViewAdapter(this, recyclerViewItems, new RecyclerViewAdapter.OnClickListener() {
            @Override
            public void onClick(RecyclerViewItem item) {
                Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });

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
            Log.d("hej","Will display internal web page");

            showInternalWebPage();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPostExecute(String json) {

    }

}
