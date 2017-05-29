package com.dev.lambda.lambdabrewer.Data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Tomas on 20/5/2017.
 */

@Entity
public class Receta {
    @PrimaryKey(autoGenerate = true)
    private int id_estilo;

    private String estilo;
    @ColumnInfo(name = "color_min")
    private String colorMin;
    @ColumnInfo(name = "color_max")
    private String colorMax;

    private String alcoholMin;
    private String densidadInicial;
    private String densidadFinal;
    private String IBUs;
}
