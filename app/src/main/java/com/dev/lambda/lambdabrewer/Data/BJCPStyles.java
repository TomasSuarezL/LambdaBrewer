package com.dev.lambda.lambdabrewer.Data;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Tomas on 7/5/2017.
 */

class BJCPStyles {
    private static final BJCPStyles ourInstance = new BJCPStyles();

    static BJCPStyles getInstance() {
        return ourInstance;
    }

    private Context context;
    public void init(Context context){
        this.context = context.getApplicationContext();
    }

    private BJCPStyles() {

        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());

            JSONArray categories = obj.getJSONObject("styleguide").getJSONArray("class");//.getJSONObject("category");

            JSONArray category1 = categories.getJSONObject(0).getJSONArray("category");

            String SAB = category1.getJSONObject(0).getString("name");

            Log.i("INFO", SAB);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = context.getAssets().open("styleguideBJCP.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
