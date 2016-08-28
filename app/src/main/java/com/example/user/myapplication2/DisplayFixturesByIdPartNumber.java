package com.example.user.myapplication2;

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
import android.widget.Toast;

import com.example.user.Model.Fixture;

import java.util.ArrayList;

public class DisplayFixturesByIdPartNumber extends AppCompatActivity {

    Fixture fixture;
    ArrayList<Fixture> arrayListFixtures = new ArrayList<>();
    int idPartNumber, idFamily;
    int position = 0;

    TextView nameFixture;
    TextView textViewTitleFamily, textViewTitlePartNumber, textViewStep;
    String step;
    String titleFamily, titlePartNumber;
    ImageButton buttonNext, buttonPrecedent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_fixtures);

        idPartNumber = getIntent().getExtras().getInt("idPartNumber");
        idFamily = getIntent().getExtras().getInt("idFamily");
        titleFamily = getIntent().getExtras().getString("titleFamily");
        titlePartNumber = getIntent().getExtras().getString("titlePartNumber");


        FixturesTask fixtureTask = new FixturesTask(DisplayFixturesByIdPartNumber.this);
        fixtureTask.execute();

    }

    class FixturesTask extends AsyncTask<Context, Void, ArrayList<Fixture>> {

        Context ApplicationContext;
        Activity mActivity;
        ProgressDialog progressDialog;

        public FixturesTask(DisplayFixturesByIdPartNumber displayFixtures) {
            super();
            mActivity = displayFixtures;

        }

        @Override
        protected void onPreExecute() {

            progressDialog = new ProgressDialog(mActivity);
            progressDialog.setMessage("Loading connectors ...");
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(ArrayList<Fixture> result) {
            showTitle();
            buttonPrecedent.setClickable(false);
            buttonPrecedent.setVisibility(View.INVISIBLE);

            if (progressDialog != null) {
                progressDialog.dismiss();
            }

        }

        @Override
        protected ArrayList<Fixture> doInBackground(Context... params) {
            textViewTitleFamily = (TextView) findViewById(R.id.titleFamily);
            textViewTitlePartNumber = (TextView) findViewById(R.id.titlePartNumber);

            nameFixture = (TextView) findViewById(R.id.nameFixture);

            textViewStep = (TextView) findViewById(R.id.textViewStep);

            buttonNext = (ImageButton) findViewById(R.id.buttonNextFixture);
            buttonPrecedent = (ImageButton) findViewById(R.id.buttonPrecedentFixture);

            BackgroundTask backgroundTask = new BackgroundTask(mActivity);
            ArrayList<Fixture> fixtures = backgroundTask.getListFixturesByIdPartNumber(idPartNumber);
            arrayListFixtures = fixtures;
            return fixtures;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }

    public void setStep() {
        int positionHuman = position + 1;
        step = positionHuman + "/" + arrayListFixtures.size();
        textViewStep.setText(step);
    }

    public void showNextFixture(View view) {
        if (assertListExist()) {
            if (!assertMaxPosition()) {
                fixture = getFixtureInPosition(position);
                showValues();
                setStep();
                setButtonVisibility();
                position++;
            } else {
                startWiresActivity();
            }
        }
    }

    public void showPrecedentFixture(View view) {
        if (assertListExist()) {
            if (!assertMinPosition()) {
                position--;
                fixture = getFixtureInPosition(position);
                showValues();
                setStep();
                setButtonVisibility();
            }
        }
        else {finish();}

    }

    public void startWiresActivity() {
        Intent intent = new Intent(DisplayFixturesByIdPartNumber.this, DisplayWiresByIdPartNumber.class);
        intent.putExtra("idFamily", idFamily);
        intent.putExtra("idPartNumber", idPartNumber);
        intent.putExtra("titleFamily", titleFamily);
        intent.putExtra("titlePartNumber", titlePartNumber);
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
        buttonNext.setImageResource(R.drawable.right);
        buttonPrecedent.setImageResource(R.drawable.left);
        buttonPrecedent.setVisibility(View.VISIBLE);
        buttonPrecedent.setClickable(true);
    }

    public void updateImageButtonNext(ImageButton imageButton) {imageButton.setImageResource(R.drawable.royal_right);}
    public void updateImageButtonPrecedent(ImageButton imageButton) {imageButton.setImageResource(R.drawable.royal_left);}



    public boolean assertMaxPosition() {

        return position >= arrayListFixtures.size();
    }

    public boolean assertMinPosition() {
        return position == 0;
    }

    public Fixture getFixtureInPosition(int position) {

        return arrayListFixtures.get(position);
    }


    public boolean assertListExist() {
        return arrayListFixtures.size() != 0 && arrayListFixtures != null;
    }

    public void showValues() {
        showNameFixture();
    }

    public void showTitle() {
        textViewTitleFamily.setText(titleFamily);
        textViewTitlePartNumber.setText(titlePartNumber);
    }

    public void showNameFixture() {
        nameFixture.setText(fixture.getNameFixture());
    }

}