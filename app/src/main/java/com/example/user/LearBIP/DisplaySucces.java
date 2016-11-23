package com.example.user.LearBIP;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class DisplaySucces extends AppCompatActivity {

    int idCable, idFamily;
    String titleFamily, titleCable;
    int totalWire, totalConnector;

    TextView textViewFamily, textViewCable, textViewWire, textViewConnector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_succes);
        textViewFamily = (TextView) findViewById(R.id.familySection);
        textViewCable = (TextView) findViewById(R.id.cableSection);
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
            idCable = getIntent().getExtras().getInt("idCable");
            idFamily = getIntent().getExtras().getInt("idFamily");
            titleFamily = getIntent().getExtras().getString("titleFamily");
            titleCable = getIntent().getExtras().getString("titleCable");
            totalConnector = getIntent().getExtras().getInt("totalConnector");
            totalWire = getIntent().getExtras().getInt("totalWire");
            return null;
        }
    }

    public void startFamily(View view) {
        startActivity(new Intent(DisplaySucces.this, DisplayFamilys.class));
    }

    public void startCable(View view) {
        Intent intent = new Intent(DisplaySucces.this, DisplayCables.class);
        intent.putExtra("idFamily", idFamily);
        intent.putExtra("titleFamily", titleFamily);
        startActivity(intent);
    }

    public void startConnector(View view) {
        Intent intent = new Intent(DisplaySucces.this, DisplayConnectors.class);
        intent.putExtra("idCable", idCable);
        intent.putExtra("titleFamily", titleFamily);
        intent.putExtra("titleCable", titleCable);
        intent.putExtra("idFamily", idFamily);
        startActivity(intent);
    }


    public void setInformation() {
        setFamilySection();
        setCableSection();
        setWireSection();
        setConnectorSection();

    }

    public void setFamilySection() {
        textViewFamily.setText(titleFamily);
    }

    public void setCableSection() {
        textViewCable.setText(titleCable);
    }

    public void setWireSection() {
        textViewWire.setText("" + totalWire);
    }

    public void setConnectorSection() {
       textViewConnector.setText("" + totalConnector);
    }
}
