package com.dev.lambda.lambdabrewer.Recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dev.lambda.lambdabrewer.Model.Detalle;
import com.dev.lambda.lambdabrewer.Model.Receta;
import com.dev.lambda.lambdabrewer.R;

/**
 * Created by Tomas on 2/4/2017.
 */

public class DetalleAdapter extends RecyclerView.Adapter<DetalleAdapter.DetalleAdapterViewHolder>{

    private Detalle[] mDetalleData;

    public DetalleAdapter (){

    }

    @Override
    public DetalleAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.detalle_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem,parent,shouldAttachToParentImmediately);

        return new DetalleAdapterViewHolder(view);
    }

    public class DetalleAdapterViewHolder extends RecyclerView.ViewHolder {

        final TextView mDetalle;
        final TextView mLabel;

        public DetalleAdapterViewHolder(View view) {
            super(view);

            mDetalle = (TextView) view.findViewById(R.id.tv_receta_detalle);
            mLabel = (TextView) view.findViewById(R.id.l_receta_detalle);
        }

    }

    @Override
    public void onBindViewHolder(DetalleAdapter.DetalleAdapterViewHolder holder, int position) {
        Detalle detalleActual = mDetalleData[position];
        holder.mDetalle.setText(detalleActual.Value);
        holder.mLabel.setText(detalleActual.Key);
    }

    @Override
    public int getItemCount() {
        if (null == mDetalleData) return 0;
        return mDetalleData.length;
    }

    public void setDetalleData(Detalle[] detalleData) {
        mDetalleData = detalleData;
        notifyDataSetChanged();
    }
}
