package com.dev.lambda.lambdabrewer.Data;

import android.provider.BaseColumns;

import java.sql.Date;

/**
 * Created by Tomas on 18/3/2017.
 */
public final class RecetasContract {

    private RecetasContract() {}

    /* Inner class that defines the table contents */
        public static class RecetasEntry implements BaseColumns {
        public static final String TABLE_NAME = "recetas";
        public static final String COLUMN_NAME_NOMBRE = "nombre";
        public static final String COLUMN_NAME_ESTILO = "estilo";
        public static final String COLUMN_NAME_FECHA = "fecha";
        public static final String COLUMN_NAME_VOLUMEN = "volumen";
        public static final String COLUMN_NAME_COLOR = "color";
        public static final String COLUMN_NAME_ALCOHOL = "alcohol";
        public static final String COLUMN_NAME_DI = "densidadInicial";
        public static final String COLUMN_NAME_DF = "densidadFinal";
        public static final String COLUMN_NAME_IBUS = "ibus";

    }

    public static final String CREATE_TABLE_RECETAS = "CREATE TABLE " + RecetasEntry.TABLE_NAME + " (" +
            RecetasEntry._ID + " INTEGER PRIMARY KEY," +
            RecetasEntry.COLUMN_NAME_NOMBRE + " TEXT," +
            RecetasEntry.COLUMN_NAME_ESTILO + " TEXT," +
            RecetasEntry.COLUMN_NAME_FECHA + " TEXT," +
            RecetasEntry.COLUMN_NAME_VOLUMEN + " REAL," +
            RecetasEntry.COLUMN_NAME_COLOR + " REAL," +
            RecetasEntry.COLUMN_NAME_ALCOHOL + " REAL," +
            RecetasEntry.COLUMN_NAME_DI + " INTEGER," +
            RecetasEntry.COLUMN_NAME_DF + " INTEGER," +
            RecetasEntry.COLUMN_NAME_IBUS + " REAL)";

    public static final String DELETE_RECETAS =
            "DROP TABLE IF EXISTS " + RecetasEntry.TABLE_NAME;

}
