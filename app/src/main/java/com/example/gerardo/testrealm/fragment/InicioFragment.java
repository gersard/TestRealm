package com.example.gerardo.testrealm.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.gerardo.testrealm.R;
import com.example.gerardo.testrealm.adapter.ListTemporadaAdapter;
import com.example.gerardo.testrealm.adapter.RealmTemporadaAdapter;
import com.example.gerardo.testrealm.model.Temporada;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class InicioFragment extends Fragment {


    RecyclerView mRecyclerView;

    private Realm mRealm=null;
    private ListTemporadaAdapter mAdapter;
    private List<Temporada> mTempList = new ArrayList<>();
    private LinearLayoutManager mLayoutManager;
    private RealmResults<Temporada> tempModel;
    private RealmTemporadaAdapter realmTemporadaAdapter;


    private String nombreTemp,urlImage;
    private int idTemp,numDigimon;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRealm = Realm.getInstance(getContext());
        mAdapter = new ListTemporadaAdapter(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_inicio, container, false);
        mRecyclerView = (RecyclerView) root.findViewById(R.id.recycler_view_temporadas);
        setupRecyclerView();
        mAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetalleFragment fragment = new DetalleFragment();
                Bundle temporada = new Bundle();
                temporada.putString("temporada",mAdapter.getTemporada(mRecyclerView.getChildAdapterPosition(v)));
                fragment.setArguments(temporada);
                final FragmentTransaction ft = getFragmentManager()
                        .beginTransaction();
                ft.replace(R.id.content_frame, fragment, "tag");
                ft.addToBackStack("tag");
                ft.commit();
            }
        });

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        tempModel = mRealm.where(Temporada.class).findAll();
        realmTemporadaAdapter = new RealmTemporadaAdapter(getActivity(),tempModel,true);
        mAdapter.setRealmAdapter(realmTemporadaAdapter);
        mAdapter.notifyDataSetChanged();
    }
    //Agregar Temporada
    public void agregarTemporada(){
        mRealm.beginTransaction();



        Temporada temp = mRealm.createObject(Temporada.class);
        temp.setIdTemporada("7");
        temp.setNombre("Adventure Tri");
        temp.setNumDigimon(8);
        temp.setUrlImageTemporada("http://www.hobbyconsolas.com/sites/default/files/noticias/129368/digimon-adventure-tri-primeros-cinco-minutos-01.jpg");
        mRealm.commitTransaction();
    }

    //Obtener data
    private void obtenerTemporadas(View view,Temporada temporada){

    }

    private void setupRecyclerView(){
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());

        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ListTemporadaAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);

    }


}
