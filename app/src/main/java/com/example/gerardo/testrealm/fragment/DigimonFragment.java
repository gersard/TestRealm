package com.example.gerardo.testrealm.fragment;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.gerardo.testrealm.R;
import com.example.gerardo.testrealm.adapter.ListDigimonAdapter;
import com.example.gerardo.testrealm.adapter.ListTemporadaAdapter;
import com.example.gerardo.testrealm.adapter.RealmDigimonAdapter;
import com.example.gerardo.testrealm.adapter.RealmTemporadaAdapter;
import com.example.gerardo.testrealm.model.Digimon;
import com.example.gerardo.testrealm.model.Temporada;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class DigimonFragment extends Fragment {


    RecyclerView mRecyclerView;

    private Realm mRealm=null;
    private ListDigimonAdapter mAdapter;
    private List<Digimon> mTempList = new ArrayList<>();
    private LinearLayoutManager mLayoutManager;
    private RealmResults<Digimon> digiModel;
    private RealmDigimonAdapter realmDigimonAdapter;

    public DigimonFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRealm = Realm.getInstance(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_digimon, container, false);

        mRecyclerView = (RecyclerView) root.findViewById(R.id.recycler_view_digimon);
        setupRecyclerView();
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        digiModel = mRealm.where(Digimon.class).findAll();
        realmDigimonAdapter = new RealmDigimonAdapter(getActivity(),digiModel,true);

        mAdapter.setRealmAdapter(realmDigimonAdapter);
        mAdapter.notifyDataSetChanged();
    }

    private void setupRecyclerView(){
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());

        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ListDigimonAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.UpdateDigimon(new ListDigimonAdapter.UpdateInterfaz() {
            @Override
            public void updData(View view, int position) {
                getData(view, digiModel.get(position));
            }
        });
        mAdapter.EliminarDigimon(new ListDigimonAdapter.EliminarInterfaz() {
            @Override
            public void delData(View view, int position) {
                eliminarDigimon(position);
                mAdapter.notifyDataSetChanged();
                Toast.makeText(getContext(), "Digimon eliminado correctamente", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void getData(View view, Digimon digimon){
        String id = digimon.getIdDigimon();
        UpdateDialog(id,digimon.getNombreDigimon(),digimon.getAtaque(),digimon.getImageURL(),digimon.getTemporada());
    }

    private void updateDigimon(String id,String nombre,String ataque,String temporada,String imagen){
        mRealm.beginTransaction();
        Digimon digimon = mRealm.where(Digimon.class).equalTo("idDigimon",id).findFirst();
        digimon.setNombreDigimon(nombre);
        digimon.setAtaque(ataque);
        digimon.setTemporada(temporada);
        digimon.setImageURL(imagen);
        mRealm.commitTransaction();
    }

    private void eliminarDigimon(int position){
        mRealm.beginTransaction();
        digiModel.remove(position);
        mRealm.commitTransaction();
    }
    //Update Dialog
    private void UpdateDialog(final String id,String nombre, String ataque, String Url, String temporada){
        String Title = "Actualizar Digimon";
        LayoutInflater inflater = LayoutInflater.from(getContext());
        //Inflo el Dialog
        View itemView = inflater.inflate(R.layout.dialog_update_data, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(itemView);

        final EditText nombreUpdate = (EditText) itemView.findViewById(R.id.dialog_nombre);
        final EditText ataqueUpdate = (EditText) itemView.findViewById(R.id.dialog_ataque);
        final Spinner temporadaUpdate = (Spinner) itemView.findViewById(R.id.dialog_temporada);
        final EditText imagenUpdate = (EditText) itemView.findViewById(R.id.dialog_imagen);

        nombreUpdate.setText(nombre);
        ataqueUpdate.setText(ataque);
        //temporadaUpdate.setSelection();
        imagenUpdate.setText(Url);

        builder.setCancelable(false).setPositiveButton(Title, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                updateDigimon(id,nombreUpdate.getText().toString(),ataqueUpdate.getText().toString(),
                        temporadaUpdate.getSelectedItem().toString(),imagenUpdate.getText().toString());
                mAdapter.notifyDataSetChanged();
                Toast.makeText(getContext(), "Digimon Actualizado", Toast.LENGTH_SHORT).show();
            }
        }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }


}
