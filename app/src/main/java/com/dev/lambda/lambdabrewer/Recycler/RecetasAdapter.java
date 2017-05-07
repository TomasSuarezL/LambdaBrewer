package com.dev.lambda.lambdabrewer.Recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dev.lambda.lambdabrewer.Model.Receta;
import com.dev.lambda.lambdabrewer.R;

/**
 * Created by Tomas on 25/3/2017.
 */

public class RecetasAdapter extends RecyclerView.Adapter<RecetasAdapter.RecetasAdapterViewHolder> {

    private Receta[] mRecetasData;

    private final RecetasAdapterOnClickHandler mClickHandler;

    public interface RecetasAdapterOnClickHandler {
        void onClick(Receta receta);
    }

    //CONSTRUCTOR
    public RecetasAdapter (RecetasAdapterOnClickHandler clickHandler){
        mClickHandler = clickHandler;
    }


    public class RecetasAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final TextView mRecetaNombre;
        final TextView mRecetaEstilo;
        final TextView mRecetaID;

        public RecetasAdapterViewHolder(View view) {
            super(view);

            mRecetaNombre = (TextView) view.findViewById(R.id.tv_receta_nombre);
            mRecetaEstilo = (TextView) view.findViewById(R.id.tv_receta_estilo);
            mRecetaID = (TextView) view.findViewById(R.id.tv_receta_id);

            view.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            Receta receta = mRecetasData[adapterPosition];
            mClickHandler.onClick(receta);
        }
    }


    @Override
    public RecetasAdapter.RecetasAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.receta_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem,parent,shouldAttachToParentImmediately);

        return new RecetasAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecetasAdapter.RecetasAdapterViewHolder holder, int position) {
        Receta recetaActual = mRecetasData[position];
        holder.mRecetaNombre.setText(recetaActual.nombre);
        holder.mRecetaEstilo.setText(recetaActual.estilo);
        holder.mRecetaID.setText(String.valueOf(recetaActual.ID));
    }

    @Override
    public int getItemCount() {
        if (null == mRecetasData) return 0;
        return mRecetasData.length;
    }

    public void setRecetasData(Receta[] detalleData) {
        mRecetasData = detalleData;
        notifyDataSetChanged();
    }

}
