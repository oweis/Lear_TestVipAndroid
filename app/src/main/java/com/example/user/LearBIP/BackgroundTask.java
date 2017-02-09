package com.example.user.LearBIP;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.user.Model.Connector;
import com.example.user.Model.Family;
import com.example.user.Model.Cable;
import com.example.user.Model.Wire;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 16/08/2016.
 */
public class BackgroundTask {
    Context context;

    ArrayList<Family> arrayListFamily = new ArrayList<>();
    ArrayList<Cable> arrayListCable = new ArrayList<>();
    ArrayList<Wire> arrayListWire = new ArrayList<>();
    ArrayList<Connector> arrayListConnector = new ArrayList<>();

     String json_root_url = "http://192.168.5.1:8081/Lear_API/webapi/";
    //String json_root_url = "http://10.0.2.2:8080/Lear_API/webapi/";


    String json_familys_url = json_root_url + "familys";
    String json_cables_url = json_root_url + "cables/search/idFamily/";
    String json_wires_url = json_root_url + "wires/search/adapt/idCable/";
    String json_connectors_url = json_root_url +"connectors/search/idCable/";

    String json_cablesByIdFamily_url;
    String json_WiresByIdCable_url;
    String json_ConnectorByIdCable_url;



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
                                        jsonObject.getString("client")
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

    public ArrayList<Cable> getListCablesByIdFamily(int idFamily) {

        json_cablesByIdFamily_url = json_cables_url + idFamily;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, json_cablesByIdFamily_url, (String) null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        int count = 0;
                        while (count < response.length()) {
                            try {

                                JSONObject jsonObject = response.getJSONObject(count);
                                Cable cable = new Cable(jsonObject.getInt("id"),
                                        jsonObject.getInt("idFamily"),
                                        jsonObject.getString("nameUsedInLear"),
                                        jsonObject.getString("nameUsedInClient"),
                                        jsonObject.getString("level"),
                                        jsonObject.getString("date")
                                );


                                arrayListCable.add(cable);
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
        return arrayListCable;
    }


    public ArrayList<Connector> getListConnectorByIdCable(int idCable) {
        json_ConnectorByIdCable_url = json_connectors_url + idCable;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, json_ConnectorByIdCable_url, (String) null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        int count = 0;
                        while (count < response.length()) {
                            try {

                                JSONObject jsonObject = response.getJSONObject(count);
                                Connector connector = new Connector(jsonObject.getInt("id"),
                                        jsonObject.getInt("idFamily"),
                                        jsonObject.getString("nameConnector")
                                );

                                arrayListConnector.add(connector);
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
        return arrayListConnector;
    }


    public ArrayList<Wire> getListWiresByIdCable(int idCable) {
        json_WiresByIdCable_url = json_wires_url + idCable;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, json_WiresByIdCable_url, (String) null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        int count = 0;
                        while (count < response.length()) {
                            try {

                                JSONObject jsonObject = response.getJSONObject(count);
                                Wire wire = new Wire(jsonObject.getInt("id"),
                                        jsonObject.getInt("idFamily"),
                                        jsonObject.getInt("idCable"),
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