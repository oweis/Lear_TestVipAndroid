package com.example.user.myapplication2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    Button button;
    TextView textView,clientTextView,dateTextView;
    String serveur_url = "http://192.168.5.1/testLear/serveur.php";
    String family_url =  "http://192.168.5.1/testLear/getFamily.php";
   // String family_url = "http://192.168.5.1:8080/Lear_API/webapi/familys/search/idFamily/1";
    RequestQueue requestQueue;
    Button buttonFamily,buttonShowFamily;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button = (Button) findViewById(R.id.bn);
        textView = (TextView) findViewById(R.id.textView);

        buttonShowFamily = (Button) findViewById(R.id.buttonShowFamily);
        clientTextView = (TextView) findViewById(R.id.clientTextView);
        dateTextView = (TextView) findViewById(R.id.dateTextView);
        //requestQueue.start();

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                StringRequest stringRequest = new StringRequest(Request.Method.POST,serveur_url,
                        new Response.Listener<String>(){
                            @Override
                            public void onResponse(String response) {
                                requestQueue.start();
                                textView.setText(response);

                            }
                        },new Response.ErrorListener(){

                    @Override
                    public void onErrorResponse(VolleyError error) {
                            textView.setText("Error ...");
                            error.printStackTrace();                    }
                    }
                );

                MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
            }
        });

        buttonShowFamily.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, family_url,(String) null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    clientTextView.setText(response.getString("client"));
                                    dateTextView.setText(response.getString("date"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,"Something went wrong",Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                });
                MySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);

            }
        });

        Cache cache = new DiskBasedCache(getCacheDir(),1024*1025);
        Network network = new BasicNetwork(new HurlStack());

        requestQueue = new RequestQueue(cache,network);
        requestQueue.start();

        buttonFamily = (Button) findViewById(R.id.buttonFamily);
        buttonFamily.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,DisplayList.class));
            }
        });
    }
}
