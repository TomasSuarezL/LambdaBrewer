package com.dev.lambda.lambdabrewer.Fragments;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dev.lambda.lambdabrewer.Data.RecetasContract;
import com.dev.lambda.lambdabrewer.Data.RecetasDbHelper;
import com.dev.lambda.lambdabrewer.DetalleActivity;
import com.dev.lambda.lambdabrewer.Model.Receta;
import com.dev.lambda.lambdabrewer.R;
import com.dev.lambda.lambdabrewer.Recycler.RecetasAdapter;
import com.vstechlab.easyfonts.EasyFonts;

import java.net.URL;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Recetas.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Recetas#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Recetas extends Fragment implements RecetasAdapter.RecetasAdapterOnClickHandler{

    public Recetas() {
        // Required empty public constructor
    }

    private RecetasDbHelper mDbHelper;
    private RecyclerView mRecyclerView;
    private RecetasAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recetas, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_recetas);

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setHasFixedSize(true);

        mAdapter = new RecetasAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

        new FetchRecetasTask().execute();

        return view;
    }


    @Override
    public void onClick(Receta receta) {
        Context context = getContext();
        Class destinationClass = DetalleActivity.class;
        Intent intentToStartDetailActivity = new Intent(context, destinationClass);
        intentToStartDetailActivity.putExtra(Intent.EXTRA_TEXT, receta.ID);
        startActivity(intentToStartDetailActivity);
    }

    public class FetchRecetasTask extends AsyncTask<Void, Void, Receta[]> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Receta[] doInBackground(Void ... params) {

            mDbHelper = new RecetasDbHelper(getContext());
            SQLiteDatabase db = mDbHelper.getReadableDatabase();

            // Define a projection that specifies which columns from the database
            // you will actually use after this query.
            String[] projection = {
                    RecetasContract.RecetasEntry._ID,
                    RecetasContract.RecetasEntry.COLUMN_NAME_NOMBRE,
                    RecetasContract.RecetasEntry.COLUMN_NAME_ESTILO
            };

            Cursor cursor = db.query(
                    RecetasContract.RecetasEntry.TABLE_NAME,                     // The table to query
                    projection,                               // The columns to return
                    null,                                // The columns for the WHERE clause
                    null,                            // The values for the WHERE clause
                    null,                                     // don't group the rows
                    null,                                     // don't filter by row groups
                    null                                 // The sort order
            );

            Receta[] recetas = new Receta[cursor.getCount()];

            int i = 0;
            while(cursor.moveToNext()){
                recetas[i] = new Receta();
                recetas[i].nombre = cursor.getString(cursor.getColumnIndex(RecetasContract.RecetasEntry.COLUMN_NAME_NOMBRE));
                recetas[i].estilo = cursor.getString(cursor.getColumnIndex(RecetasContract.RecetasEntry.COLUMN_NAME_ESTILO));
                recetas[i].ID = cursor.getInt(cursor.getColumnIndex(RecetasContract.RecetasEntry._ID));
                i++;
            }

            return recetas;
        }

        @Override
        protected void onPostExecute(Receta[] recetasData) {
            if (recetasData != null) {
                mAdapter.setRecetasData(recetasData);
            } else {

            }
        }
    }

}







