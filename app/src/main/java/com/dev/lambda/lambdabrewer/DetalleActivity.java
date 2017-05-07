package com.dev.lambda.lambdabrewer;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.dev.lambda.lambdabrewer.Data.RecetasContract;
import com.dev.lambda.lambdabrewer.Data.RecetasDbHelper;
import com.dev.lambda.lambdabrewer.Fragments.Recetas;
import com.dev.lambda.lambdabrewer.Model.Detalle;
import com.dev.lambda.lambdabrewer.Model.Receta;
import com.dev.lambda.lambdabrewer.Recycler.DetalleAdapter;
import com.dev.lambda.lambdabrewer.Recycler.RecetasAdapter;

public class DetalleActivity extends AppCompatActivity {

    private int mID;

    private RecyclerView mRecyclerView;
    private DetalleAdapter mAdapter;

    private Toolbar toolbar;

    private RecetasDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receta_detalle);

        toolbar = (Toolbar) findViewById(R.id.detalle_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Intent intentThatStartedThisActivity = getIntent();

        if (intentThatStartedThisActivity != null) {
            if (intentThatStartedThisActivity.hasExtra(Intent.EXTRA_TEXT)) {
                mID = intentThatStartedThisActivity.getIntExtra(Intent.EXTRA_TEXT,0);
            }
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.scrollableview);

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setHasFixedSize(true);

        mAdapter = new DetalleAdapter();
        mRecyclerView.setAdapter(mAdapter);

        new FetchRecetasTask().execute();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home){
            // Navigate back to parent activity (CatalogActivity)
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public class FetchRecetasTask extends AsyncTask<Void, Void, Detalle[]> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Detalle[] doInBackground(Void ... params) {

            mDbHelper = new RecetasDbHelper(getApplicationContext());
            SQLiteDatabase db = mDbHelper.getReadableDatabase();

            // Define a projection that specifies which columns from the database
            // you will actually use after this query.
            String[] projection = {
                    RecetasContract.RecetasEntry._ID,
                    RecetasContract.RecetasEntry.COLUMN_NAME_NOMBRE,
                    RecetasContract.RecetasEntry.COLUMN_NAME_ESTILO
            };

            String selection = RecetasContract.RecetasEntry._ID + " = ?";
            String[] selectionArgs = {String.valueOf(mID)};

            Cursor cursor = db.query(
                    RecetasContract.RecetasEntry.TABLE_NAME,                     // The table to query
                    projection,                                                 // The columns to return
                    selection,                                                       // The columns for the WHERE clause
                    selectionArgs,                                                       // The values for the WHERE clause
                    null,                                                       // don't group the rows
                    null,                                                       // don't filter by row groups
                    null                                                        // The sort order
            );

            Detalle[] receta = new Detalle[cursor.getColumnCount()];
            int i = 0;
            while(cursor.moveToNext()){

                receta[0] = new Detalle();
                receta[1] = new Detalle();
                receta[2] = new Detalle();

                receta[0].Key = "Nombre";
                receta[0].Value = cursor.getString(cursor.getColumnIndex(RecetasContract.RecetasEntry.COLUMN_NAME_NOMBRE));
                receta[1].Key = "ID";
                receta[1].Value = cursor.getString(cursor.getColumnIndex(RecetasContract.RecetasEntry._ID));
                receta[2].Key = "Estilo";
                receta[2].Value = cursor.getString(cursor.getColumnIndex(RecetasContract.RecetasEntry.COLUMN_NAME_ESTILO));
            }
            cursor.close();

            return receta;
        }

        @Override
        protected void onPostExecute(Detalle[] detalleData) {
            if (detalleData != null) {
                mAdapter.setDetalleData(detalleData);
            } else {

            }
        }
    }
}