package com.example.user.LearBIP;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.Model.Family;

import org.acra.ACRA;
import org.acra.ReportField;
import org.acra.ReportingInteractionMode;
import org.acra.annotation.ReportsCrashes;
import org.acra.sender.HttpSender;

import java.util.ArrayList;

@ReportsCrashes(
        formUri = "https://oweisyahya.cloudant.com/acra-Bip/_design/acra-storage/_update/report",
        reportType = HttpSender.Type.JSON,
        httpMethod = HttpSender.Method.POST,
        formUriBasicAuthLogin = "ondessorbookeenceredlike",
        formUriBasicAuthPassword = "46a70ce892184e13189aad937815d0afb37eb991",
        customReportContent = {
                ReportField.APP_VERSION_CODE,
                ReportField.APP_VERSION_NAME,
                ReportField.ANDROID_VERSION,
                ReportField.PACKAGE_NAME,
                ReportField.REPORT_ID,
                ReportField.BUILD,
                ReportField.STACK_TRACE
        },
        mode = ReportingInteractionMode.TOAST,
        resToastText = R.string.toast_crash
)

public class DisplayFamilys extends AppCompatActivity {

    RecyclerView recyclerViewFamilys;
    RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(R.style.AppTheme_NoActionBar);
        super.onCreate(savedInstanceState);
        ACRA.init(this.getApplication());

        FamilysTask familysTask = new FamilysTask(DisplayFamilys.this);
        familysTask.execute();

    }

    public void showLastTest(View v){
        //TODO Replace code with real one to open Activity wanted
        //TODO try to redesign app desktop
        displayData();
        redirectToLastTest();
    }
    public void redirectToLastTest(){
        SharedPreferences sharedPreferences = getSharedPreferences("infos", Context.MODE_PRIVATE);
        String phase = sharedPreferences.getString("phase","");
        boolean hasData = sharedPreferences.getBoolean("hasData",false);

        int idFamily = sharedPreferences.getInt("idFamily",0);
        int idCable = sharedPreferences.getInt("idCable",0);
        if(hasData){
            Intent intent;
            if(phase.equals("connector")){
                 intent = new Intent(DisplayFamilys.this, DisplayConnectors.class);
            }
            else {
                intent = new Intent(DisplayFamilys.this, DisplayWires.class);

            }

            intent.putExtra("idFamily", idFamily);
            intent.putExtra("idCable",idCable);
            intent.putExtra("titleFamily", "Family");
            intent.putExtra("titleCable","Cable");
            startActivity(intent);
        }
    }

    public void displayData(){
        SharedPreferences sharedPreferences = getSharedPreferences("infos", Context.MODE_PRIVATE);
        String phase = sharedPreferences.getString("phase","");
        int position = sharedPreferences.getInt("position",0);
        int idCable = sharedPreferences.getInt("idCable",0);
        int idFamily = sharedPreferences.getInt("idFamily",0);
        String msg = "Les informations de votre dernier test :" +"\nPhase : "+phase +"\nPosition : "+position+"\nIdCable : "+idCable+"\nIdFamily : "+idFamily;
        Toast.makeText(this.getApplicationContext(), msg,Toast.LENGTH_LONG).show();
    }

    public void setButtonContinue(){
        SharedPreferences sharedPreferences = getSharedPreferences("infos", Context.MODE_PRIVATE);
        boolean hasData = sharedPreferences.getBoolean("hasData",true);
        ImageButton continueButton = (ImageButton) findViewById(R.id.buttonContinue);
        if(!hasData) {
            continueButton.setVisibility(View.GONE);
        }
        else{
            continueButton.setVisibility(View.VISIBLE);
        }
    }


    public void sendIdFamily(View v) {

        int idFamily = Integer.parseInt(v.getTag().toString());
        TextView namePassByUserTextView = (TextView) findViewById(R.id.namePassByUser);
        String titleFamily = namePassByUserTextView.getText().toString();
        Intent intent = new Intent(DisplayFamilys.this, DisplayCables.class);
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

            setButtonContinue();
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
