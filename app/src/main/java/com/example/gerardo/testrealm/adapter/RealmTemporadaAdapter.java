package com.example.gerardo.testrealm.adapter;

import android.content.Context;

import com.example.gerardo.testrealm.model.Temporada;

import io.realm.RealmResults;

/**
 * Created by Gerardo on 03-03-2016.
 */
public class RealmTemporadaAdapter extends RealmModelAdapter<Temporada> {
    public RealmTemporadaAdapter(Context context, RealmResults realmResults, boolean automaticUpdate) {
        super(context, realmResults, automaticUpdate);
    }
}
