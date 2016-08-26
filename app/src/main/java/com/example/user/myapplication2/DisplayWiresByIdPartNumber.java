package com.example.user.myapplication2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.Model.Wire;

import java.util.ArrayList;

public class DisplayWiresByIdPartNumber extends AppCompatActivity {

    Wire wire;
    ArrayList<Wire> arrayListWires = new ArrayList<>();
    int idPartNumber;
    int position = 0;

    TextView nameWire, color, color_A, color_B, pin_A, pin_B, splice_A, splice_B, connector_A, connector_B;
    TextView textViewStep;
    TextView textViewTitleFamily, textViewTitlePartNumber;
    String step;
    String titleFamily, titlePartNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_wires);

        idPartNumber = getIntent().getExtras().getInt("idPartNumber");
        titleFamily = getIntent().getExtras().getString("titleFamily");
        titlePartNumber = getIntent().getExtras().getString("titlePartNumber");

        textViewTitleFamily = (TextView) findViewById(R.id.titleFamily);
        textViewTitlePartNumber = (TextView) findViewById(R.id.titlePartNumber);

        nameWire = (TextView) findViewById(R.id.nameWire);
        color = (TextView) findViewById(R.id.color);
        color_A = (TextView) findViewById(R.id.color_A);
        color_B = (TextView) findViewById(R.id.color_B);
        pin_A = (TextView) findViewById(R.id.pin_A);
        pin_B = (TextView) findViewById(R.id.pin_B);
        splice_A = (TextView) findViewById(R.id.splice_A);
        splice_B = (TextView) findViewById(R.id.splice_B);
        connector_A = (TextView) findViewById(R.id.connector_A);
        connector_B = (TextView) findViewById(R.id.connector_B);

        textViewStep = (TextView) findViewById(R.id.textViewStep);


        BackgroundTask backgroundTask = new BackgroundTask(DisplayWiresByIdPartNumber.this);
        arrayListWires = backgroundTask.getListWiresByIdPartNumber(idPartNumber);

//        showFirstWire();
    }

    public void setStep() {
        step = position + "/" + arrayListWires.size();
        textViewStep.setText(step);
    }

    public void showFirstWire() {
        position = 1;
        //  wire = getWireInPosition(position);
        //showValues();
        //setStep();

    }

    public void showNextWire(View view) {

        if (!assertMaxPosition(position)) {

            position++;
            String size = String.valueOf(arrayListWires.size() + " " + position);
            Toast.makeText(getApplicationContext(), size, Toast.LENGTH_SHORT).show();
            wire = getWireInPosition(position);
            showValues();
            setStep();

        }
    }


    public void showPrecedentWire(View view) {

        if (!assertMinPosition(position)) {

            position--;
            String size = String.valueOf(arrayListWires.size() + " " + position);
            Toast.makeText(getApplicationContext(), size, Toast.LENGTH_SHORT).show();
            wire = getWireInPosition(position);
            showValues();
            setStep();
        }

    }

    public boolean assertMaxPosition(int position) {

        return position > arrayListWires.size();
    }

    public boolean assertMinPosition(int position) {
        return position == 0;
    }

    public Wire getWireInPosition(int position) {

        return arrayListWires.get(position);
    }

    public void showTitle() {
        textViewTitleFamily.setText(titleFamily);
        textViewTitlePartNumber.setText(titlePartNumber);
    }

    public void showValues() {

        showWireInfos();
        showInfos_A();
        showInfos_B();

    }

    public void showWireInfos(){
        showNameWire();
        showColor();
    }

    public void showNameWire() {
        nameWire.setText(wire.getNameWire());
    }

    public void showColor() {
        color.setText(wire.getColor());
    }

    public void showInfos_A() {
        showColor_A();
        showSplice_A();
        showPin_A();
        showConnector_A();
    }

    public void showInfos_B() {
        showColor_B();
        showSplice_B();
        showPin_B();
        showConnector_B();
    }

    public void showColor_A() {
        color_A.setText(wire.getColor_A());
    }

    public void showSplice_A() {
        splice_A.setText(wire.getSplice_A());
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

    public void showSplice_B() {
        splice_B.setText(wire.getSplice_B());
    }

    public void showPin_B() {
        pin_B.setText(wire.getPin_B());
    }

    public void showConnector_B() {
        connector_B.setText(wire.getConnector_B());
    }
}