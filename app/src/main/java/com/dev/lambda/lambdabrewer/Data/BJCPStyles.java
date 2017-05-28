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

public class BJCPStyles {
    private static final BJCPStyles ourInstance = new BJCPStyles();

    private static InputStream is;

    public static BJCPStyles getInstance() {
        return ourInstance;
    }

    static JSONArray styles;


    public void init(InputStream isParam){
        this.is = isParam;
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());

            styles = obj.getJSONObject("styleguide").getJSONArray("class").getJSONObject(0).getJSONArray("category");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getNameOfStyleTest(){
        try {
            return styles.getJSONObject(0)// CATEGORY[0]= PRIMER ESTILO: Standard American Beer
                    .getString("name"); //NOMBRE DE CATEGORY[0} = "Standard American Beer"
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private BJCPStyles() {

    }


    private String loadJSONFromAsset() {
        String json = null;
        try {
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
