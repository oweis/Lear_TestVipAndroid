package com.example.user.LearBIP;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by user on 16/08/2016.
 */
public class MySingleton {

    private static MySingleton mySingleton;
    private RequestQueue requestQueue;
    private static Context mCtx;

    private MySingleton(Context context){
        mCtx = context;
        requestQueue = getRequestQueue();
    }

    public RequestQueue getRequestQueue() {
    if(requestQueue==null){
        requestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
    }
        return requestQueue;
    }

    public static synchronized MySingleton getInstance(Context context){
        if(mySingleton==null){
            mySingleton = new MySingleton(context);
        }
        return mySingleton;
    }

    public<T> void addToRequestQueue(Request<T> request){
        requestQueue.add(request);
    }

}
