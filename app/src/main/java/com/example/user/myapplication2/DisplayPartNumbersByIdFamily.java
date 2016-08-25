package com.example.user.myapplication2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.user.model.Family;
import com.example.user.model.PartNumber;

import java.util.ArrayList;

public class DisplayPartNumbersByIdFamily extends AppCompatActivity {

    RecyclerView recyclerViewPartNumbers;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<PartNumber> arrayListPartNumbersByIdFamily = new ArrayList<>();
    int idFamily;
    String titleFamily;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        idFamily = getIntent().getExtras().getInt("idFamily");
        titleFamily = getIntent().getExtras().getString("titleFamily");

        setContentView(R.layout.activity_display_part_numbers);
        recyclerViewPartNumbers = (RecyclerView) findViewById(R.id.recyclerViewPartNumbers);
        layoutManager = new LinearLayoutManager(this);
        recyclerViewPartNumbers.setLayoutManager(layoutManager);
        recyclerViewPartNumbers.setHasFixedSize(true);
        BackgroundTask backgroundTask = new BackgroundTask(DisplayPartNumbersByIdFamily.this);
        arrayListPartNumbersByIdFamily = backgroundTask.getListPartNumbersByIdFamily(idFamily);
        adapter = new RecyclerAdapterPartNumber(arrayListPartNumbersByIdFamily);
        recyclerViewPartNumbers.setAdapter(adapter);
    }


    public void getIdPartNumber(View v) {

        int idPartNumber = Integer.parseInt(v.getTag().toString());
        TextView nameUsedInLearTextView = (TextView) findViewById(R.id.nameUsedInLear);
        String titlePartNumber = nameUsedInLearTextView.getText().toString();
        Intent intent = new Intent(DisplayPartNumbersByIdFamily.this, DisplayWiresByIdPartNumber.class);
        intent.putExtra("idPartNumber", idPartNumber);
        intent.putExtra("titleFamily", titleFamily);
        intent.putExtra("titlePartNumber", titlePartNumber);
        startActivity(intent);
    }
}