package com.dev.lambda.lambdabrewer;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.dev.lambda.lambdabrewer.Data.RecetasContract;
import com.dev.lambda.lambdabrewer.Data.RecetasDbHelper;
import com.vstechlab.easyfonts.EasyFonts;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import es.dmoral.toasty.Toasty;

public class AltaReceta extends AppCompatActivity {

    private Toolbar toolbar;

    private TextInputEditText tNombre;
    private TextInputEditText tVolumen;
    private TextInputEditText tColor;
    private TextInputEditText tDI;
    private TextInputEditText tDF;
    private TextInputEditText tAlcohol;
    private TextInputEditText tIbus;
    private Spinner sEstilo;

    private RecetasDbHelper mRecetasHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_receta);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //FIND VIEW BY IDS
        tNombre = (TextInputEditText) findViewById(R.id.et_nombre);
        tVolumen = (TextInputEditText) findViewById(R.id.et_volumen);
        tColor = (TextInputEditText) findViewById(R.id.et_color);
        tDI = (TextInputEditText) findViewById(R.id.et_densidad_inicial);
        tDF = (TextInputEditText) findViewById(R.id.et_densidad_final);
        tAlcohol = (TextInputEditText) findViewById(R.id.et_alcohol);
        tIbus = (TextInputEditText) findViewById(R.id.et_ibus);

        sEstilo = (Spinner) findViewById(R.id.spinner);

        populateSpinner();
        mRecetasHelper = new RecetasDbHelper(this);
        //VALIDACIONES

    }

    public void populateSpinner() {
//        //POPULATE SPINNER
//        // Create an ArrayAdapter using the string array and a default spinner layout
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
//                R.array.Estilos, android.R.layout.simple_spinner_item);
//        // Specify the layout to use when the list of choices appears
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        // Apply the adapter to the spinner
//        sEstilo.setAdapter(adapter);
//
//        sEstilo.setSelection(0);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == android.R.id.home){
            // Navigate back to parent activity (CatalogActivity)
            NavUtils.navigateUpFromSameTask(this);
        return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void nuevaReceta(View view) {

        if (tNombre.getText().toString().equals(""))
            {
               return;
            }

        SQLiteDatabase db = mRecetasHelper.getWritableDatabase();

        ContentValues newReceta = new ContentValues();
        newReceta.put(RecetasContract.RecetasEntry.COLUMN_NAME_NOMBRE, tNombre.getText().toString());
        newReceta.put(RecetasContract.RecetasEntry.COLUMN_NAME_COLOR, tColor.getText().toString());
        newReceta.put(RecetasContract.RecetasEntry.COLUMN_NAME_VOLUMEN, tVolumen.getText().toString());
        newReceta.put(RecetasContract.RecetasEntry.COLUMN_NAME_DI, tDI.getText().toString());
        newReceta.put(RecetasContract.RecetasEntry.COLUMN_NAME_DF, tDF.getText().toString());
        newReceta.put(RecetasContract.RecetasEntry.COLUMN_NAME_ALCOHOL, tAlcohol.getText().toString());
        newReceta.put(RecetasContract.RecetasEntry.COLUMN_NAME_IBUS, tIbus.getText().toString());
        newReceta.put(RecetasContract.RecetasEntry.COLUMN_NAME_FECHA, Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        newReceta.put(RecetasContract.RecetasEntry.COLUMN_NAME_ESTILO, sEstilo.getSelectedItem().toString());

        long newRowID = db.insert(RecetasContract.RecetasEntry.TABLE_NAME, null, newReceta );

        Toasty.success(this,"Receta Agregada: " + tNombre.getText().toString(), Toast.LENGTH_SHORT, true).show();
    }
}
