package com.example.user.myapplication2;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.Model.Wire;

import java.util.ArrayList;

public class DisplayWiresByIdPartNumber extends AppCompatActivity {

    Wire wire;
    ArrayList<Wire> arrayListWires = new ArrayList<>();
    int idPartNumber, idFamily;
    int position = 0;

    TextView nameWire, color, color_A, color_B, pin_A, pin_B, connector_A, connector_B;
    TextView textViewTitleFamily, textViewTitlePartNumber, textViewStep;
    String step;
    String titleFamily, titlePartNumber;
    ImageButton buttonNext, buttonPrecedent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_wires);
        idFamily = getIntent().getExtras().getInt("idFamily");
        idPartNumber = getIntent().getExtras().getInt("idPartNumber");
        titleFamily = getIntent().getExtras().getString("titleFamily");
        titlePartNumber = getIntent().getExtras().getString("titlePartNumber");

        textViewTitleFamily = (TextView) findViewById(R.id.titleFamily);
        textViewTitlePartNumber = (TextView) findViewById(R.id.titlePartNumber);

        WiresTask wiresTask = new WiresTask(DisplayWiresByIdPartNumber.this);
        wiresTask.execute();

    }

    class WiresTask extends AsyncTask<Context, Void, ArrayList<Wire>> {

        Context ApplicationContext;
        Activity mActivity;
        ProgressDialog progressDialog;

        public WiresTask(DisplayWiresByIdPartNumber displayWires) {
            super();
            mActivity = displayWires;
        }

        @Override
        protected void onPreExecute() {

            progressDialog = new ProgressDialog(mActivity);
            progressDialog.setMessage("Loading wires");
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(ArrayList<Wire> result) {
            showTitle();
            buttonPrecedent.setClickable(false);
            buttonPrecedent.setVisibility(View.INVISIBLE);
            if (progressDialog != null) {
                progressDialog.dismiss();
            }

        }

        @Override
        protected ArrayList<Wire> doInBackground(Context... params) {

            nameWire = (TextView) findViewById(R.id.nameWire);
            color = (TextView) findViewById(R.id.color);

            color_A = (TextView) findViewById(R.id.color_A);
            color_B = (TextView) findViewById(R.id.color_B);
            pin_A = (TextView) findViewById(R.id.pin_A);
            pin_B = (TextView) findViewById(R.id.pin_B);

            connector_A = (TextView) findViewById(R.id.connector_A);
            connector_B = (TextView) findViewById(R.id.connector_B);

            textViewStep = (TextView) findViewById(R.id.textViewStep);

            buttonNext = (ImageButton) findViewById(R.id.buttonNextWire);
            buttonPrecedent = (ImageButton) findViewById(R.id.buttonPrecedentWire);

            BackgroundTask backgroundTask = new BackgroundTask(mActivity);
            ArrayList<Wire> wires = backgroundTask.getListWiresByIdPartNumber(idPartNumber);
            arrayListWires = wires;
            return wires;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }

    public void setStep() {
        int positionHuman = position + 1;
        step = positionHuman + "/" + arrayListWires.size();
        textViewStep.setText(step);
    }

    public void showNextWire(View view) {
        if (assertListExist()) {
            if (!assertMaxPosition()) {
                wire = getWireInPosition(position);
                showValues();
                setStep();
                setButtonVisibility();
                position++;
            }else{startDoneActivity();}
        }
    }

    public void showPrecedentWire(View view) {
        if (assertListExist()) {
            if (!assertMinPosition()) {
                position--;
                wire = getWireInPosition(position);
                showValues();
                setStep();
                setButtonVisibility();
            } else {
                finish();
            }
        }
    }

    public void startDoneActivity() {
        Intent intent = new Intent(DisplayWiresByIdPartNumber.this, MainActivity.class);
        intent.putExtra("idFamily", idFamily);
        intent.putExtra("idPartNumber", idPartNumber);
        intent.putExtra("titleFamily", titleFamily);
        intent.putExtra("titlePartNumber", titlePartNumber);
        startActivity(intent);
    }

    public void setButtonVisibility() {

        if (assertMaxPosition()) {
            updateImageButton(buttonNext);
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
        buttonPrecedent.setVisibility(View.VISIBLE);
        buttonPrecedent.setClickable(true
        );
        buttonNext.setImageResource(R.drawable.right);
        buttonPrecedent.setImageResource(R.drawable.left);
    }

    public void updateImageButton(ImageButton imageButton) {
        if (imageButton == buttonNext) imageButton.setImageResource(R.drawable.royal_right);
        if (imageButton == buttonPrecedent) imageButton.setImageResource(R.drawable.royal_left);
    }


    public boolean assertMaxPosition() {

        return position >= arrayListWires.size();
    }

    public boolean assertMinPosition() {
        return position == 0;
    }

    public boolean assertListExist() {
        return arrayListWires.size() != 0 && arrayListWires != null;
    }


    public Wire getWireInPosition(int position) {

        return arrayListWires.get(position);
    }

    public void showTitle() {
        textViewTitleFamily.setText(titleFamily);
        textViewTitlePartNumber.setText(titlePartNumber);
    }

    public void setTextViewTitlePartNumber(TextView textViewTitlePartNumber) {
        this.textViewTitlePartNumber = textViewTitlePartNumber;
    }

    public void showValues() {

        showWireInfos();
        showInformation_A();
        showInformation_B();

    }

    public void showWireInfos() {
        showNameWire();
        showColor();
    }

    public void showNameWire() {
        nameWire.setText(wire.getNameWire());
    }

    public void showColor() {
        color.setText(wire.getColor());
    }

    public void showInformation_A() {
        showColor_A();

        showPin_A();
        showConnector_A();
    }

    public void showInformation_B() {
        showColor_B();

        showPin_B();
        showConnector_B();
    }

    public void showColor_A() {
        color_A.setText(wire.getColor_A());
    }


    public void showPin_A() {
        pin_A.setText(wire.getPin_A());
    }

    public void showConnector_A() {
        connector_A.setText(wire.getConnector_A());
    }

    public void showColor_B() {
        color_B.setText(wire.getColor_B());
    }


    public void showPin_B() {
        pin_B.setText(wire.getPin_B());
    }

    public void showConnector_B() {
        connector_B.setText(wire.getConnector_B());
    }
}