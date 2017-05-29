package com.dev.lambda.lambdabrewer.Data;

import android.content.Context;
import android.util.Log;

import com.dev.lambda.lambdabrewer.Model.Estilo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;

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

    public String[] getStylesArray(){
        int categoryLen = styles.length();
        int subCategoryLen;

        ArrayList<String> estilosList = new ArrayList<>();

        try {

            for (int i=0 ; i < categoryLen ; i++ ){
                JSONArray subcategory = styles.getJSONObject(i).getJSONArray("subcategory");
                subCategoryLen = subcategory.length();

                for (int j=0 ; j < subCategoryLen ; j++){
                    estilosList.add(subcategory.getJSONObject(j).getString("id") + " - " + subcategory.getJSONObject(j).getString("name"));
                }

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        String[] mEstilosArray = new String[estilosList.size()];
        return estilosList.toArray(mEstilosArray);
    }

    public Estilo getEstiloBjcpPorNombre(String nombre){
        int categoryLen = styles.length();
        int subCategoryLen;

        Estilo estilo = new Estilo();

        try {

            for (int i=0 ; i < categoryLen ; i++ ){
                JSONArray subcategory = styles.getJSONObject(i).getJSONArray("subcategory");
                subCategoryLen = subcategory.length();

                for (int j=0 ; j < subCategoryLen ; j++){

                    if(Objects.equals(subcategory.getJSONObject(j).getString("id") + " - " + subcategory.getJSONObject(j).getString("name"), nombre)){
                        JSONObject stats = subcategory.getJSONObject(j).getJSONObject("stats");

                        estilo.estilo = subcategory.getJSONObject(j).getString("id") + " - " + subcategory.getJSONObject(j).getString("name");
                        estilo.IBUmin = stats.getJSONObject("ibu").getString("low");
                        estilo.IBUmax = stats.getJSONObject("ibu").getString("high");
                        estilo.alcoholMin = stats.getJSONObject("abv").getString("low");
                        estilo.alcoholMax = stats.getJSONObject("abv").getString("high");
                        estilo.colorMin = stats.getJSONObject("srm").getString("low");
                        estilo.colorMax = stats.getJSONObject("srm").getString("high");
                        estilo.densidadInicialMin = stats.getJSONObject("og").getString("low");
                        estilo.densidadInicialMax = stats.getJSONObject("og").getString("high");
                        estilo.densidadFinalMin = stats.getJSONObject("fg").getString("low");
                        estilo.densidadFinalMax = stats.getJSONObject("fg").getString("high");
                    }
                }

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return estilo;
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
