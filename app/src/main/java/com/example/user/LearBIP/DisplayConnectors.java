package com.example.user.LearBIP;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.user.Model.Connector;

import java.util.ArrayList;

public class DisplayConnectors extends AppCompatActivity {

    Connector connector;
    ArrayList<Connector> arrayListConnectors = new ArrayList<>();
    int idCable, idFamily;
    int position = 0;
    int positionHuman;

    TextView nameConnector;
    TextView textViewTitleFamily, textViewTitleCable, textViewStep;
    String step;
    String titleFamily, titleCable;
    ImageButton buttonNext, buttonPrecedent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_connector);

        idCable = getIntent().getExtras().getInt("idCable");
        idFamily = getIntent().getExtras().getInt("idFamily");
        titleFamily = getIntent().getExtras().getString("titleFamily");
        titleCable = getIntent().getExtras().getString("titleCable");


        ConnectorsTask fixtureTask = new ConnectorsTask(DisplayConnectors.this);
        fixtureTask.execute();

    }

    class ConnectorsTask extends AsyncTask<Context, Void, ArrayList<Connector>> {

        Context ApplicationContext;
            Activity mActivity;
        ProgressDialog progressDialog;

        public ConnectorsTask(DisplayConnectors displayConnectors) {
            super();
            mActivity = displayConnectors;

        }

        @Override
        protected void onPreExecute() {

            progressDialog = new ProgressDialog(mActivity);
            progressDialog.setMessage("Loading connectors ...");
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(ArrayList<Connector> result) {
            showTitle();
            buttonPrecedent.setClickable(false);
            buttonPrecedent.setVisibility(View.INVISIBLE);

            if (progressDialog != null) {
                progressDialog.dismiss();
            }

        }

        @Override
        protected ArrayList<Connector> doInBackground(Context... params) {
            textViewTitleFamily = (TextView) findViewById(R.id.titleFamily);
            textViewTitleCable = (TextView) findViewById(R.id.titleCable);

            nameConnector = (TextView) findViewById(R.id.nameConnector);

            textViewStep = (TextView) findViewById(R.id.textViewStep);

            buttonNext = (ImageButton) findViewById(R.id.buttonNextConnector);
            buttonPrecedent = (ImageButton) findViewById(R.id.buttonPrecedentConnector);

            BackgroundTask backgroundTask = new BackgroundTask(mActivity);
            ArrayList<Connector> connectors = backgroundTask.getListConnectorByIdCable(idCable);
            arrayListConnectors = connectors;
            return connectors;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }

    public void setStep() {
         positionHuman = position + 1;
        step = positionHuman + "/" + arrayListConnectors.size();
        textViewStep.setText(step);
    }

    public void showNextConnector(View view) {
        if (assertListExist()) {
            if (!assertMaxPosition()) {
                connector = getConnectorInPosition(position);
                showValues();
                setStep();
                position++;
                setButtonVisibility();
            } else {
                startWiresActivity();
            }
        }
    }

    public void showPrecedentConnector(View view) {
        if (assertListExist()) {
            if (!assertMinPosition()) {
                position--;
                connector = getConnectorInPosition(position);
                showValues();
                setStep();
                setButtonVisibility();
            }
        } else {
            finish();
        }

    }

    public void startWiresActivity() {
     //   int totalConnector = arrayListConnectors.size();
        Intent intent = new Intent(DisplayConnectors.this, DisplayWires.class);
        intent.putExtra("idFamily", idFamily);
        intent.putExtra("idCable", idCable);
        intent.putExtra("titleFamily", titleFamily);
        intent.putExtra("titleCable", titleCable);
        intent.putExtra("totalConnector", positionHuman);
        startActivity(intent);
    }

    public void setButtonVisibility() {

        if (assertMaxPosition()) {
            updateImageButtonNext(buttonNext);
        } else if (assertMinPosition()) {
           hideImageButton(buttonPrecedent);
        } else {
            showImageButton();
        }
    }

    public void hideImageButton(ImageButton imageButton) {
        imageButton.setVisibility(View.INVISIBLE);
        imageButton.setClickable(false);
    }

    public void showImageButton() {
        buttonNext.setImageResource(R.drawable.right);
        buttonPrecedent.setImageResource(R.drawable.left);
        buttonPrecedent.setVisibility(View.VISIBLE);
        buttonPrecedent.setClickable(true);
    }

    public void updateImageButtonNext(ImageButton imageButton) {
        imageButton.setImageResource(R.drawable.rightwire);
    }

    public void updateImageButtonPrecedent(ImageButton imageButton) {
        imageButton.setImageResource(R.drawable.royal_left);
    }


    public boolean assertMaxPosition() {

        return position >= arrayListConnectors.size();
    }

    public boolean assertMinPosition() {
        return position == 0;
    }

    public Connector getConnectorInPosition(int position) {

        return arrayListConnectors.get(position);
    }


    public boolean assertListExist() {
        return arrayListConnectors.size() != 0 && arrayListConnectors != null;
    }

    public void showValues() {
        showNameFixture();
    }

    public void showTitle() {
        textViewTitleFamily.setText("Nom de famille : " + titleFamily);
        textViewTitleCable.setText("Nom de référence : " + titleCable);
    }

    public void showNameFixture() {
        nameConnector.setText(connector.getNameConnector());
    }

}