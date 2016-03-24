package com.example.gerardo.testrealm.adapter;

import android.content.Context;

import com.example.gerardo.testrealm.model.Digimon;

import io.realm.RealmResults;

/**
 * Created by Gerardo on 04-03-2016.
 */
public class RealmDigimonAdapter extends RealmModelAdapter<Digimon> {
    public RealmDigimonAdapter(Context context, RealmResults realmResults, boolean automaticUpdate) {
        super(context, realmResults, automaticUpdate);
    }
}
