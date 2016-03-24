package com.example.gerardo.testrealm.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gerardo.testrealm.R;
import com.example.gerardo.testrealm.model.Temporada;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Gerardo on 03-03-2016.
 */
public class ListTemporadaAdapter extends RealmRecyclerViewAdapter<Temporada>
        implements View.OnClickListener {

    private Context mContext;
    //Para la animacion
    private int lastPosition;
    private UpdateDataInterface mUpdateDataInterface;
    private DeleteDataInterface mDeleteDataInterface;

    private View.OnClickListener listener;

    public ListTemporadaAdapter(Context context) {
        this.mContext = context;
    }

    public void updateData( UpdateDataInterface updateDataInterface){
        this.mUpdateDataInterface = updateDataInterface;
    }
    public void deleteData( DeleteDataInterface deleteDataInterface){
        this.mDeleteDataInterface = deleteDataInterface;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_temporada,parent,false);
        v.setOnClickListener(this);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder vHolder = (ViewHolder) holder;
        Temporada temp = getItem(position);
        String numTem = "Temporada: "+temp.getIdTemporada();
        vHolder.numTemp.setText(numTem);
        vHolder.nombreTemp.setText(temp.getNombre());
        String nD = String.valueOf(temp.getNumDigimon());
        vHolder.numDigimon.setText("Cantidad Digimons: "+nD);
        vHolder.setDigImage(temp.getUrlImageTemporada());
        setAnimation(vHolder.cardViewTemp, position);
    }

    @Override
    public int getItemCount() {
        if (getRealmAdapter() != null){
            return getRealmAdapter().getCount();
        }
        return 0;
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if(listener != null){
            listener.onClick(v);
        }
    }

    public String getTemporada(int position){
        Temporada temp = getItem(position);
        return temp.getIdTemporada();
    }

    public interface UpdateDataInterface{
        void updateData(View view,int position);
    }

    public interface DeleteDataInterface{
        void deleteData(View view, int position);
    }
    private void setAnimation(View viewToAnimate, int position){
        if (position > lastPosition){
            Animation animation = AnimationUtils.loadAnimation(mContext, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.img_temporada)
        ImageView imageTemp;
        @Bind(R.id.txt_temporada)
        TextView numTemp;
        @Bind(R.id.txt_num_digimon)
        TextView numDigimon;
        @Bind(R.id.txt_nombre_temp)
        TextView nombreTemp;
        @Bind(R.id.card_view_temp)
        CardView cardViewTemp;

        String url;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void setDigImage(String url){
            Picasso.with(mContext).load(url)
                    .into(imageTemp);
        }

    }

}
