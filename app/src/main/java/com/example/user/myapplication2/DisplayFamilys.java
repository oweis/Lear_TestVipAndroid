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

//import com.example.user.AsyncTask.FamilysTask;
import com.example.user.Model.Family;

import java.util.ArrayList;

public class DisplayFamilys extends AppCompatActivity {

    RecyclerView recyclerViewFamilys;
    RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(R.style.AppTheme_NoActionBar);

        super.onCreate(savedInstanceState);
        FamilysTask familysTask = new FamilysTask(DisplayFamilys.this);
        familysTask.execute();

    }

    public void sendIdFamily(View v) {

        int idFamily = Integer.parseInt(v.getTag().toString());
        TextView namePassByUserTextView = (TextView) findViewById(R.id.namePassByUser);
        String titleFamily = namePassByUserTextView.getText().toString();
        Intent intent = new Intent(DisplayFamilys.this, DisplayPartNumbers.class);
        intent.putExtra("idFamily", idFamily);
        intent.putExtra("titleFamily", titleFamily);
        startActivity(intent);

    }


    class FamilysTask extends AsyncTask<Context, Void, ArrayList<Family>> {

        Context ApplicationContext;
        Activity mActivity;
        ProgressDialog progressDialog;

        public FamilysTask(DisplayFamilys displayFamilys) {
            super();
            mActivity = displayFamilys;
        }

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(mActivity);
            progressDialog.setMessage("loading");
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(ArrayList<Family> result) {

            //setTheme(R.style.whiteScreenTheme);
            setContentView(R.layout.activity_display_familys);

            recyclerViewFamilys = (RecyclerView) findViewById(R.id.recyclerViewFamilys);
            layoutManager = new LinearLayoutManager(mActivity);
            recyclerViewFamilys.setLayoutManager(layoutManager);
            recyclerViewFamilys.setHasFixedSize(true);

            recyclerViewFamilys = (RecyclerView) mActivity.findViewById(R.id.recyclerViewFamilys);
            RecyclerView.Adapter adapter = new RecyclerAdapterFamily(result);
            recyclerViewFamilys.setAdapter(adapter);

            if (progressDialog != null) {
                progressDialog.dismiss();
            }
        }

        @Override
        protected ArrayList<Family> doInBackground(Context... params) {
            BackgroundTask backgroundTask = new BackgroundTask(mActivity);
            ArrayList<Family> familys = backgroundTask.getListFamilys();
            return familys;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }
}
