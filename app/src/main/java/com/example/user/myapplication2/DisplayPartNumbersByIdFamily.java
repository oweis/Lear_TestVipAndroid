package com.example.user.myapplication2;

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

import com.example.user.Model.Family;
import com.example.user.Model.PartNumber;

import java.util.ArrayList;

public class DisplayPartNumbersByIdFamily extends AppCompatActivity {

 
    static int idFamily;
    static String titleFamily;
    RecyclerView recyclerViewPartNumbers;
    RecyclerView.LayoutManager layoutManager;

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

        PartNumbersTask partNumbersTask = new PartNumbersTask(DisplayPartNumbersByIdFamily.this);
        partNumbersTask.execute();

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

    class PartNumbersTask extends AsyncTask<Context, Void, ArrayList<PartNumber> > {

        Context ApplicationContext;
        Activity mActivity;
        ProgressDialog progressDialog ;
        RecyclerView recyclerViewPartNumbers;

        public PartNumbersTask (DisplayPartNumbersByIdFamily activity) {
            super();
            mActivity = activity;
        }

        @Override
        protected void onPreExecute() {

            progressDialog = new ProgressDialog(mActivity);
            progressDialog.setMessage("loading");
            progressDialog.show();
      }

        @Override
        protected void onPostExecute(ArrayList<PartNumber>  result) {

            recyclerViewPartNumbers = (RecyclerView) mActivity.findViewById(R.id.recyclerViewPartNumbers);
            RecyclerView.Adapter adapter = new RecyclerAdapterPartNumber(result);
            recyclerViewPartNumbers.setAdapter(adapter);
            if (progressDialog != null) {
                progressDialog.dismiss();
            }

        }

        @Override
        protected ArrayList<PartNumber> doInBackground(Context... params) {

            BackgroundTask backgroundTask = new BackgroundTask(mActivity);
            ArrayList<PartNumber> partNumbers = backgroundTask.getListPartNumbersByIdFamily(DisplayPartNumbersByIdFamily.idFamily);
            return partNumbers;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
}