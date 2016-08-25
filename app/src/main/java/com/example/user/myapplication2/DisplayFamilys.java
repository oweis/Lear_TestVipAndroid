package com.example.user.myapplication2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.example.user.model.Family;

import java.util.ArrayList;

public class DisplayFamilys extends AppCompatActivity {

    RecyclerView recyclerViewFamilys;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Family> arrayListFamily = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_familys);
        recyclerViewFamilys = (RecyclerView) findViewById(R.id.recyclerViewFamilys);
        layoutManager = new LinearLayoutManager(this);
        recyclerViewFamilys.setLayoutManager(layoutManager);
        recyclerViewFamilys.setHasFixedSize(true);
        BackgroundTask backgroundTask = new BackgroundTask(DisplayFamilys.this);
        arrayListFamily = backgroundTask.getListFamilys();
        adapter = new RecyclerAdapterFamily(arrayListFamily);
        recyclerViewFamilys.setAdapter(adapter);
    }

    public void getIdFamily(View v) {

        int idFamily = Integer.parseInt(v.getTag().toString());
        TextView namePassByUserTextView = (TextView) findViewById(R.id.namePassByUser);
        String titleFamily = namePassByUserTextView.getText().toString();
        Intent intent = new Intent(DisplayFamilys.this, DisplayPartNumbersByIdFamily.class);
        intent.putExtra("idFamily", idFamily);
        intent.putExtra("titleFamily", titleFamily);
        startActivity(intent);

    }
}
