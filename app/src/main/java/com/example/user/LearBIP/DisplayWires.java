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

import com.example.user.Design.Color;
import com.example.user.Model.Wire;

import java.util.ArrayList;

public class DisplayWires extends AppCompatActivity {

    Wire wire;
    ArrayList<Wire> arrayListWires = new ArrayList<>();
    int idCable, idFamily;
    int position = 0;

    TextView nameWire, color, color_A, color_B, pin_A, pin_B, connector_A, connector_B;
    TextView textViewTitleFamily, textViewTitleCable, textViewStep;
    String step;
    String titleFamily, titleCable;
    ImageButton buttonNext, buttonPrecedent;
    int totalConnector, totalWire;
    int positionHuman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_wires);

        idFamily = getIntent().getExtras().getInt("idFamily");
        idCable = getIntent().getExtras().getInt("idCable");
        titleFamily = getIntent().getExtras().getString("titleFamily");
        titleCable = getIntent().getExtras().getString("titleCable");
        totalConnector = getIntent().getExtras().getInt("totalConnector");

        textViewTitleFamily = (TextView) findViewById(R.id.titleFamily);
        textViewTitleCable = (TextView) findViewById(R.id.titleCable);

        WiresTask wiresTask = new WiresTask(DisplayWires.this);
        wiresTask.execute();

    }

    class WiresTask extends AsyncTask<Context, Void, ArrayList<Wire>> {

        Context ApplicationContext;
        Activity mActivity;
        ProgressDialog progressDialog;

        public WiresTask(DisplayWires displayWires) {
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
            setButtonVisibility();
            buttonPrecedent.setClickable(false);
            totalWire = arrayListWires.size();

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
            ArrayList<Wire> wires = backgroundTask.getListWiresByIdCable(idCable);
            arrayListWires = wires;
            return wires;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }

    public void setStep() {
        positionHuman = position + 1;
        step = positionHuman + "/" + arrayListWires.size();
        textViewStep.setText(step);
    }

    public void showNextWire(View view) {
        if (assertListExist()) {
            if (!assertMaxPosition()) {
                wire = getWireInPosition(position);
                showValues();
                setStep();
                position++;

                setButtonVisibility();
            } else {
                startDoneActivity();
            }
        }
    }

    public void setColorName(View view) {
        TextView c = (TextView) findViewById(R.id.color);
        Color color = new Color();
        String colorName = color.getColorName(c.getText().toString());
        c.setText(colorName);
    }

    public void setColorNameA(View view) {
        TextView color_A = (TextView) findViewById(R.id.color_A);
        Color color = new Color();
        String colorName = color.getColorName(color_A.getText().toString());
        color_A.setText(colorName);
    }

    public void setColorNameB(View view) {
        TextView color_B = (TextView) findViewById(R.id.color_B);
        Color color = new Color();
        String colorName = color.getColorName(color_B.getText().toString());
        color_B.setText(colorName);
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
        Intent intent = new Intent(DisplayWires.this, DisplaySucces.class);
        intent.putExtra("idFamily", idFamily);
        intent.putExtra("idCable", idCable);
        intent.putExtra("titleFamily", titleFamily);
        intent.putExtra("titleCable", titleCable);
        intent.putExtra("totalWire", positionHuman);
        intent.putExtra("totalConnector", totalConnector);
        startActivity(intent);
    }

    public void setButtonVisibility() {

        if (assertMaxPosition()) {
            updateImageButtonNext(buttonNext);
        } else if (assertMinPosition()) {
            updateImageButtonPrecedent(buttonPrecedent);
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
        buttonPrecedent.setClickable(true);
        buttonNext.setVisibility(View.VISIBLE);
        buttonNext.setClickable(true);
        buttonNext.setImageResource(R.drawable.right);
        buttonPrecedent.setImageResource(R.drawable.left);
    }

    public void updateImageButtonNext(ImageButton imageButton) {
        imageButton.setImageResource(R.drawable.rightsuccess);
    }

    public void updateImageButtonPrecedent(ImageButton imageButton) {
        imageButton.setImageResource(R.drawable.leftconnector);
    }

    public boolean assertMaxPosition() {

        return position >= arrayListWires.size() && arrayListWires.size() != 0;
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
        textViewTitleFamily.setText("Nom de famille : " + titleFamily);
        textViewTitleCable.setText("Nom de référence : " + titleCable);
    }

    public void setTextViewTitleCable(TextView textViewTitleCable) {
        this.textViewTitleCable = textViewTitleCable;
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