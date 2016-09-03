package com.example.user.myapplication2;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.user.Model.Family;
import com.example.user.Model.Fixture;
import com.example.user.Model.PartNumber;
import com.example.user.Model.Wire;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 16/08/2016.
 */
public class BackgroundTask {

    // PHP API URLS
            /*
                //String json_root_url = "http://192.168.43.214/testLear/";
                //String json_familys_url = json_root_url + "getFamilys.php";
                //String json_partNumbers_url = json_root_url + "getPartNumbers.php?idFamily=";
                //String json_wires_url = json_root_url + "getWires.php?idPartNumber=";
                //String json_fixtures_url = json_root_url + "getFixtures.php";

             */
    Context context;

    ArrayList<Family> arrayListFamily = new ArrayList<>();
    ArrayList<PartNumber> arrayListPartNumber = new ArrayList<>();
    ArrayList<Wire> arrayListWire = new ArrayList<>();
    ArrayList<Fixture> arrayListFixture = new ArrayList<>();

     String json_root_url = "http://192.168.5.1:8080/Lear_API/webapi/";
    //String json_root_url = "http://10.0.2.2:8080/Lear_API/webapi/";


    String json_familys_url = json_root_url + "familys";
    String json_partNumbers_url = json_root_url + "partnumbers/search/idFamily/";
    String json_wires_url = json_root_url + "wires/search/adapt/idPartNumber/";
    String json_fixtures_url = json_root_url +"fixtures/search/idPartNumber/";

    String json_partNumbersByIdFamily_url;
    String json_WiresByIdPartNumber_url;
    String json_FixturesByIdPartNumber_url;



    public BackgroundTask(Context context) {
        this.context = context;
    }

    public ArrayList<Family> getListFamilys() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, json_familys_url, (String) null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        int count = 0;
                        while (count < response.length()) {
                            try {

                                JSONObject jsonObject = response.getJSONObject(count);
                                Family family = new Family(jsonObject.getInt("id"),
                                        jsonObject.getString("namePassByUser"),
                                        jsonObject.getString("nameUsedInLear"),
                                        jsonObject.getString("nameUsedInClient"),
                                        jsonObject.getString("date"),
                                        jsonObject.getString("client"),
                                        jsonObject.getString("extra")
                                );


                                arrayListFamily.add(family);
                                count++;

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Error...", Toast.LENGTH_SHORT);
            }
        }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                params.put("Connection", "Keep-Alive");
                params.put("Content-Type", "text/html; charset=UTF-8");
                params.put("Keep-Alive", "timeout=5, max=100");

                return params;
            }
        };
        MySingleton.getInstance(context).addToRequestQueue(jsonArrayRequest);
        return arrayListFamily;
    }

    public ArrayList<PartNumber> getListPartNumbersByIdFamily(int idFamily) {

        json_partNumbersByIdFamily_url = json_partNumbers_url + idFamily;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, json_partNumbersByIdFamily_url, (String) null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        int count = 0;
                        while (count < response.length()) {
                            try {

                                JSONObject jsonObject = response.getJSONObject(count);
                                PartNumber partNumber = new PartNumber(jsonObject.getInt("id"),
                                        jsonObject.getInt("idFamily"),
                                        jsonObject.getString("nameUsedInLear"),
                                        jsonObject.getString("nameUsedInClient"),
                                        jsonObject.getString("level"),
                                        jsonObject.getString("date"),
                                        jsonObject.getString("extra")
                                );


                                arrayListPartNumber.add(partNumber);
                                count++;

                            } catch (JSONException e) {

                                e.printStackTrace();
                            }

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Error...", Toast.LENGTH_SHORT);
            }
        });
        MySingleton.getInstance(context).addToRequestQueue(jsonArrayRequest);
        return arrayListPartNumber;
    }


    public ArrayList<Fixture> getListFixturesByIdPartNumber(int idPartNumber) {
        json_FixturesByIdPartNumber_url = json_fixtures_url + idPartNumber;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, json_FixturesByIdPartNumber_url, (String) null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        int count = 0;
                        while (count < response.length()) {
                            try {

                                JSONObject jsonObject = response.getJSONObject(count);
                                Fixture fixture = new Fixture(jsonObject.getInt("id"),
                                        jsonObject.getInt("idFamily"),
                                        jsonObject.getString("nameFixture"),
                                        jsonObject.getString("drawing"));

                                arrayListFixture.add(fixture);
                                count++;

                            } catch (JSONException e) {

                                e.printStackTrace();
                            }

                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Error...", Toast.LENGTH_SHORT);
            }
        });

        MySingleton.getInstance(context).addToRequestQueue(jsonArrayRequest);
        return arrayListFixture;
    }


    public ArrayList<Wire> getListWiresByIdPartNumber(int idPartNumber) {
        json_WiresByIdPartNumber_url = json_wires_url + idPartNumber;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, json_WiresByIdPartNumber_url, (String) null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        int count = 0;
                        while (count < response.length()) {
                            try {

                                JSONObject jsonObject = response.getJSONObject(count);
                                Wire wire = new Wire(jsonObject.getInt("id"),
                                        jsonObject.getInt("idFamily"),
                                        jsonObject.getInt("idPartNumber"),
                                        jsonObject.getString("nameWire"),
                                        jsonObject.getString("color"),
                                        jsonObject.getString("connector_A"),
                                        jsonObject.getString("pin_A"),
                                        jsonObject.getString("color_A"),
                                        jsonObject.getString("splice_A"),
                                        jsonObject.getString("connector_B"),
                                        jsonObject.getString("pin_B"),
                                        jsonObject.getString("color_B"),
                                        jsonObject.getString("splice_B")
                                );

                                wire.setDate_creation(new Date());

                                arrayListWire.add(wire);
                                count++;

                            } catch (JSONException e) {

                                e.printStackTrace();
                            }

                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Error...", Toast.LENGTH_SHORT);
            }
        });

        MySingleton.getInstance(context).addToRequestQueue(jsonArrayRequest);
        return arrayListWire;

    }


}