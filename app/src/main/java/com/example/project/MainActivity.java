package com.example.project;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

@SuppressWarnings("FieldCanBeLocal")
public class MainActivity extends AppCompatActivity implements JsonTask.JsonTaskListener{
    private final String JSON_URL="https://mobprog.webug.se/json-api?login=a23gabst";

    private ArrayList<Spel> spel= new ArrayList<>();
    private ArrayList<RecyclerViewItem> recyclerViewItems=new ArrayList<>();
    private RecyclerViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button button = findViewById(R.id.start_second_activity);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("name", "This is activity 2!");
                startActivity(intent);
            }
        });
    }

    @Override
    public void onPostExecute(String json) {

    }

}
