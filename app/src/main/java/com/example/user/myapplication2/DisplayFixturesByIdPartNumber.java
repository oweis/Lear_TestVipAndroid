package com.example.user.myapplication2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.example.user.Model.Fixture;
import java.util.ArrayList;

public class DisplayFixturesByIdPartNumber extends AppCompatActivity {

    Fixture Fixture;
    ArrayList<Fixture> arrayListFixturesByidPartNumber = new ArrayList<>();
    int idPartNumber;
    int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            idPartNumber =  getIntent().getExtras().getInt("idPartNumber");

            setContentView(R.layout.activity_display_fixtures);

          BackgroundTask backgroundTask = new BackgroundTask(DisplayFixturesByIdPartNumber.this);
          arrayListFixturesByidPartNumber = backgroundTask.getListFixturesByIdPartNumber(idPartNumber);

    }

    TextView nameFixture,color,color_A,color_B,pin_A,pin_B,splice_A,splice_B,connector_A,connector_B;

    public void showFirstFixture(View view){

        Fixture = getFixtureInPosition(0);
        position = 0;
        showValues();

    }
    public void showNextFixture(View view){
        if(!assertMaxPosition(position)){
            position++;
            Fixture = getFixtureInPosition(position);
            showValues();
        }
    }
    public void showPrecedentFixture(View view){
        if(!assertMinPosition(position)){
            position--;
            Fixture = getFixtureInPosition(position);
            showValues();
        }

    }

    public boolean assertMaxPosition(int position){
        return position>arrayListFixturesByidPartNumber.size();
    }

    public boolean assertMinPosition(int position){
        return position == 0;
    }

    public Fixture getFixtureInPosition(int position){
        return arrayListFixturesByidPartNumber.get(position);
    }

    public void showValues(){
        showFixture();
    }

    public void showFixture(){
        nameFixture = (TextView) findViewById(R.id.nameFixture);
        nameFixture.setText(Fixture.getNameFixture());
    }
}
