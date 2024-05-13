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

    public void showInternalWebPage(){
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

        gson = new Gson();

        adapter=new RecyclerViewAdapter(this, recyclerViewItems, new RecyclerViewAdapter.OnClickListener() {
            @Override
            public void onClick(RecyclerViewItem item) {
                Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
        new JsonFile(this, this).execute((String) JSON_FILE);
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
    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onPostExecute(String json) {
        Log.d("Hej", "" + json);
        Type type = new TypeToken<List<Spel>>() {}.getType();
        List<Spel> listOfMountains = gson.fromJson(json, type);
        spel.clear();
        if(listOfMountains != null) {
            spel.addAll(listOfMountains);
            for (int i = 0; i < spel.size(); i++) {
                Log.d("Elinparsear", spel.get(i).toString());
                recyclerViewItems.add(new RecyclerViewItem(spel.get(i).toString()));
            }
        }
        adapter.notifyDataSetChanged();
    }

}
