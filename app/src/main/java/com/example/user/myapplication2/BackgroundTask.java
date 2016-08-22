package com.example.user.myapplication2;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.user.model.Family;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by user on 16/08/2016.
 */
public class BackgroundTask {

        Context context;
        ArrayList<Family> arrayList=new ArrayList<>();
        String json_familys_url =  "http://192.168.5.1/testLear/getFamilys.php";


    public BackgroundTask(Context context){
        this.context = context;
    }

    public ArrayList<Family> getList(){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, json_familys_url, (String) null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        int count = 0;
                        while(count<response.length()) {
                            try {

                                JSONObject jsonObject = response.getJSONObject(count);
                                Family family = new Family();

                                family.setId(jsonObject.getInt("id"));
                                family.setDate(jsonObject.getString("date"));
                                family.setClient(jsonObject.getString("client"));
                                family.setDate_creation(new Date());
                                family.setExtra(jsonObject.getString("extra"));
                                family.setNamePassByUser(jsonObject.getString("namePassByUser"));
                                family.setNameUsedInLear("nameUsedInLear");
                                family.setNameUsedInClient("nameUsedInClient");
                                arrayList.add(family);
                                count++;

                            } catch (JSONException e) {

                                e.printStackTrace();
                            }

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,"Error...",Toast.LENGTH_SHORT);
            }
        });
        MySingleton.getInstance(context).addToRequestQueue(jsonArrayRequest);
    return arrayList;
    }
}
