package com.example.gerardo.testrealm.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Gerardo on 01-03-2016.
 */
public class Temporada extends RealmObject {

    @PrimaryKey
    private String idTemporada;
    private String Nombre;
    private String urlImageTemporada;
    private int numDigimon;

    public Temporada() {
    }

    public String getIdTemporada() {
        return idTemporada;
    }

    public void setIdTemporada(String idTemporada) {
        this.idTemporada = idTemporada;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getUrlImageTemporada() {
        return urlImageTemporada;
    }

    public void setUrlImageTemporada(String urlImageTemporada) {
        this.urlImageTemporada = urlImageTemporada;
    }

    public int getNumDigimon() {
        return numDigimon;
    }

    public void setNumDigimon(int numDigimon) {
        this.numDigimon = numDigimon;
    }
}
