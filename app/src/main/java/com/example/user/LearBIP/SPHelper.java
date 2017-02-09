package com.example.user.LearBIP;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by user on 28/01/2017.
 */
public class SPHelper extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public SPHelper(){
         sharedPreferences = getSharedPreferences("infos", Context.MODE_PRIVATE);
         editor = sharedPreferences.edit();
    }

    public boolean hasData(){
        return sharedPreferences.getBoolean("state",false);
    }

    public void freeData(){
        editor.putBoolean("state",false);
        editor.apply();
    }

    public void saveData(String phase,int position, int idCable, int idFamily){
        SharedPreferences sharedPreferences = getSharedPreferences("infos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("state",true);
        editor.putString("phase","connector");
        editor.putInt("position",position);
        editor.putInt("idCable",idCable);
        editor.putInt("idFamily",idFamily);
        editor.apply();
    }

    public void displayData(){
        SharedPreferences sharedPreferences = getSharedPreferences("infos", Context.MODE_PRIVATE);
        String phase = sharedPreferences.getString("phase","");
        int position = sharedPreferences.getInt("position",0);
        int idCable = sharedPreferences.getInt("idCable",0);
        int idFamily = sharedPreferences.getInt("idFamily",0);

        String msg = "Phase : "+phase +", Position : "+position+", IdCable : "+idCable+", IdFamily : "+idFamily;
        Toast.makeText(this.getApplicationContext(), msg,Toast.LENGTH_LONG).show();
    }
}
