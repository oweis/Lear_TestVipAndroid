package com.example.user.myapplication2;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

//import com.example.user.AsyncTask.FamilysTask;
import com.example.user.Model.Family;

import java.util.ArrayList;

public class DisplayFamilys extends AppCompatActivity {

    static RecyclerView recyclerViewFamilys;
    static RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Family> arrayListFamily = new ArrayList<>();
    ProgressDialog pDialog;
    static BackgroundTask backgroundTask;
    static ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_familys);
        recyclerViewFamilys = (RecyclerView) findViewById(R.id.recyclerViewFamilys);
         progressDialog = new ProgressDialog(this);


        layoutManager = new LinearLayoutManager(this);
        recyclerViewFamilys.setLayoutManager(layoutManager);
        recyclerViewFamilys.setHasFixedSize(true);

        backgroundTask = new BackgroundTask(DisplayFamilys.this);
        FamilysTask familysTask = new FamilysTask();
        familysTask.execute();

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

    class FamilysTask extends AsyncTask<String, Void, ArrayList<Family> > {

        @Override
        protected ArrayList<Family> doInBackground(String... params) {
            ArrayList<Family> familys =  DisplayFamilys.backgroundTask.getListFamilys();
            return familys;
        }

        @Override
        protected void onPostExecute(ArrayList<Family>  result) {
            DisplayFamilys.progressDialog.dismiss();
            DisplayFamilys.adapter = new RecyclerAdapterFamily(result);
            DisplayFamilys.recyclerViewFamilys.setAdapter(DisplayFamilys.adapter);
        }

        @Override
        protected void onPreExecute() {

            DisplayFamilys.progressDialog.setMessage("Sber :)");
        //    DisplayFamilys.progressDialog.setIndeterminate(true);
            DisplayFamilys.progressDialog.show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }


    }
