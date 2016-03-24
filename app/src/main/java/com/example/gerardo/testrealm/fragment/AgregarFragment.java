package com.example.gerardo.testrealm.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import java.util.concurrent.atomic.AtomicLong;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class AgregarFragment extends Fragment {

    RecyclerView mRecyclerView;
    private Realm mRealm=null;
    private ListDigimonAdapter mAdapter;
    private List<Digimon> mDigimonList = new ArrayList<>();
    private LinearLayoutManager mLayoutManager;
    private RealmResults<Digimon> digimonModel;
    private RealmDigimonAdapter realmDigimonAdapter;

    EditText nombreDigimon,ataqueDigimon,urlImagen;
    Button guardarDigimon;
    Spinner spinnerTemp;

    //private String nombreDigimon,urlImagen,ataqueDigimon;


    public AgregarFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRealm = Realm.getInstance(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_agregar, container, false);

        nombreDigimon = (EditText) root.findViewById(R.id.edit_nombre_digimon);
        ataqueDigimon = (EditText) root.findViewById(R.id.edit_ataque_digimon);
        urlImagen = (EditText) root.findViewById(R.id.edit_urlimagen_digimon);
        spinnerTemp = (Spinner) root.findViewById(R.id.spinner_temporadas);
        guardarDigimon = (Button) root.findViewById(R.id.btn_agregar_digimon);

        guardarDigimon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nombreDigimon.length()>0 && ataqueDigimon.length() >0 && urlImagen.length()>0 && spinnerTemp.getSelectedItemPosition()>0){
                    agregarDigimon();
                    Toast.makeText(getContext(), "Digimon almacenado correctamente", Toast.LENGTH_SHORT).show();
                    setearCampos();

                }else{
                    Toast.makeText(getContext(), "Ingrese toda la informacion", Toast.LENGTH_SHORT).show();
                }

            }
        });

        return root;
    }

    //Agregar Digimon
    public void agregarDigimon(){
        mRealm.beginTransaction();
        Digimon dig = mRealm.createObject(Digimon.class);
        dig.setIdDigimon(nombreDigimon.getText().toString());
        dig.setTemporada(spinnerTemp.getSelectedItem().toString());
        dig.setNombreDigimon(nombreDigimon.getText().toString());
        dig.setAtaque(ataqueDigimon.getText().toString());
        dig.setImageURL(urlImagen.getText().toString());
        mRealm.commitTransaction();
    }

    public void setearCampos(){
        nombreDigimon.setText("");
        ataqueDigimon.setText("");
        urlImagen.setText("");
    }

}
