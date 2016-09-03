package com.example.user.myapplication2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class DisplaySucces extends AppCompatActivity {

    int idPartNumber, idFamily;
    String titleFamily, titlePartNumber;
    int totalWire, totalConnector;

    TextView textViewFamily, textViewPartNumber, textViewWire, textViewConnector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_succes);
        textViewFamily = (TextView) findViewById(R.id.familySection);
        textViewPartNumber = (TextView) findViewById(R.id.partNumberSection);
        textViewConnector = (TextView) findViewById(R.id.connectorSection);
        textViewWire = (TextView) findViewById(R.id.wireSection);
        SuccessTask successTask = new SuccessTask(DisplaySucces.this);
        successTask.execute();

    }

    class SuccessTask extends AsyncTask<Context, Void, String> {

        Activity mActivity;

        public SuccessTask(DisplaySucces displaySucces) {
            super();
            mActivity = displaySucces;

        }

        @Override
        protected void onPreExecute() {


        }

        @Override
        protected void onPostExecute(String  result) {
            setInformation();
        }

        @Override
        protected String doInBackground(Context... params) {
            idPartNumber = getIntent().getExtras().getInt("idPartNumber");
            idFamily = getIntent().getExtras().getInt("idFamily");
            titleFamily = getIntent().getExtras().getString("titleFamily");
            titlePartNumber = getIntent().getExtras().getString("titlePartNumber");
            totalConnector = getIntent().getExtras().getInt("totalConnector");
            totalWire = getIntent().getExtras().getInt("totalWire");
            return null;
        }
    }

    public void startFamily(View view) {
        startActivity(new Intent(DisplaySucces.this, DisplayFamilys.class));
    }

    public void startPartNumber(View view) {
        Intent intent = new Intent(DisplaySucces.this, DisplayPartNumbers.class);
        intent.putExtra("idFamily", idFamily);
        intent.putExtra("titleFamily", titleFamily);
        startActivity(intent);
    }

    public void startFixture(View view) {
        Intent intent = new Intent(DisplaySucces.this, DisplayFixtures.class);
        intent.putExtra("idPartNumber", idPartNumber);
        intent.putExtra("titleFamily", titleFamily);
        intent.putExtra("titlePartNumber", titlePartNumber);
        intent.putExtra("idFamily", idFamily);
        startActivity(intent);
    }


    public void setInformation() {
        setFamilySection();
        setPartNumberSection();
        setWireSection();
        setConnectorSection();

    }

    public void setFamilySection() {
        textViewFamily.setText(titleFamily);
    }

    public void setPartNumberSection() {
        textViewPartNumber.setText(titlePartNumber);
    }

    public void setWireSection() {
        textViewWire.setText("" + totalWire);
    }

    public void setConnectorSection() {
       textViewConnector.setText("" + totalConnector);
    }
}
