package com.example.user.LearBIP;

import android.app.Activity;
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

import com.example.user.Model.Cable;

import java.util.ArrayList;

public class DisplayCables extends AppCompatActivity {

    static int idFamily;
    static String titleFamily;
    RecyclerView recyclerViewCables;
    RecyclerView.LayoutManager layoutManager;
    TextView textViewTitleFamily;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        idFamily = getIntent().getExtras().getInt("idFamily");
        titleFamily = getIntent().getExtras().getString("titleFamily");

        setContentView(R.layout.activity_display_cables);

        recyclerViewCables = (RecyclerView) findViewById(R.id.recyclerViewCables);
        layoutManager = new LinearLayoutManager(this);
        recyclerViewCables.setLayoutManager(layoutManager);
        recyclerViewCables.setHasFixedSize(true);

        CablesTask cablesTask = new CablesTask(DisplayCables.this);
        cablesTask.execute();

    }

    public void getIdCable(View v) {

        int idCable = Integer.parseInt(v.getTag().toString());
        TextView nameUsedInLearTextView = (TextView) findViewById(R.id.nameUsedInLear);
        String titleCable = nameUsedInLearTextView.getText().toString();
        Intent intent = new Intent(DisplayCables.this, DisplayConnectors.class);
        intent.putExtra("idCable", idCable);
        intent.putExtra("titleFamily", titleFamily);
        intent.putExtra("titleCable", titleCable);
        intent.putExtra("idFamily", idFamily);
        startActivity(intent);
    }

    public void showTitle() {
        textViewTitleFamily.setText("Nom de famille : " + titleFamily);
    }


    class CablesTask extends AsyncTask<Context, Void, ArrayList<Cable>> {

        Context ApplicationContext;
        Activity mActivity;
        ProgressDialog progressDialog;
        RecyclerView recyclerViewCables;

        public CablesTask(DisplayCables displayCablesByIdFamily) {
            super();
            mActivity = displayCablesByIdFamily;
        }

        @Override
        protected void onPreExecute() {

            progressDialog = new ProgressDialog(mActivity);
            progressDialog.setMessage("Loading part numbers");
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(ArrayList<Cable> result) {
            showTitle();
            recyclerViewCables = (RecyclerView) mActivity.findViewById(R.id.recyclerViewCables);
            RecyclerView.Adapter adapter = new RecyclerAdapterCable(result);
            recyclerViewCables.setAdapter(adapter);
            if (progressDialog != null) {
                progressDialog.dismiss();
            }

        }

        @Override
        protected ArrayList<Cable> doInBackground(Context... params) {
            textViewTitleFamily = (TextView) findViewById(R.id.titleFamily);
            BackgroundTask backgroundTask = new BackgroundTask(mActivity);
            ArrayList<Cable> cables = backgroundTask.getListCablesByIdFamily(DisplayCables.idFamily);
            return cables;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }
}