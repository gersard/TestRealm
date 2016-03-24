package com.example.gerardo.testrealm.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gerardo.testrealm.R;
import com.example.gerardo.testrealm.model.Digimon;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Gerardo on 04-03-2016.
 */
public class ListDigimonAdapter extends RealmRecyclerViewAdapter<Digimon> {

    private Context mContext;
    //Para la animacion
    private int lastPosition;
    //cambio
    private UpdateInterfaz mUpdateInterfaz;
    //Eliminar
    private EliminarInterfaz mEliminarInterfaz;

    public void UpdateDigimon(UpdateInterfaz updateInterfaz){
        this.mUpdateInterfaz = updateInterfaz;
    }
    public void EliminarDigimon(EliminarInterfaz eliminarInterfaz){
        this.mEliminarInterfaz = eliminarInterfaz;
    }

    public ListDigimonAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_digimon,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ViewHolder viewH = (ViewHolder) holder;
        Digimon digimon = getItem(position);
        viewH.nombreDigimon.setText(digimon.getNombreDigimon());
        viewH.ataqueDigimon.setText(digimon.getAtaque());
        viewH.tempDigimon.setText(digimon.getTemporada());
        viewH.setDigImage(digimon.getImageURL());
        viewH.imgUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUpdateInterfaz.updData(v, position);
            }
        });
        viewH.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEliminarInterfaz.delData(v,position);
            }
        });
        setAnimation(viewH.cardViewDigimon, position);
    }

    @Override
    public int getItemCount() {
        if (getRealmAdapter() != null){
            return getRealmAdapter().getCount();
        }
        return 0;
    }

    private void setAnimation(View viewToAnimate, int position){
        if (position > lastPosition){
            Animation animation = AnimationUtils.loadAnimation(mContext, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    public interface UpdateInterfaz{
        void updData(View view,int position);
    }
    public interface EliminarInterfaz{
        void delData(View view,int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.imagen_digimon)
        ImageView imgDigimon;
        @Bind(R.id.txt_nombre_digimon)
        TextView nombreDigimon;
        @Bind(R.id.txt_ataque_digimon)
        TextView ataqueDigimon;
        @Bind(R.id.txt_temporada_digimon)
        TextView tempDigimon;
        @Bind(R.id.card_view_digimon)
        CardView cardViewDigimon;
        @Bind(R.id.img_update)
        ImageView imgUpdate;
        @Bind(R.id.img_delete)
        ImageView imgDelete;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
        public void setDigImage(String url){
            Picasso.with(mContext).load(url)
                    .into(imgDigimon);
        }
    }
}
