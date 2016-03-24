package com.example.gerardo.testrealm.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Gerardo on 01-03-2016.
 */
public class Digimon extends RealmObject {

    @PrimaryKey
    private String idDigimon;
    private String nombreDigimon;
    private String imageURL;
    private String temporada;
    private String ataque;

    public Digimon() {
    }

    public String getIdDigimon() {
        return idDigimon;
    }

    public void setIdDigimon(String idDigimon) {
        this.idDigimon = idDigimon;
    }

    public String getNombreDigimon() {
        return nombreDigimon;
    }

    public void setNombreDigimon(String nombreDigimon) {
        this.nombreDigimon = nombreDigimon;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getTemporada() {
        return temporada;
    }

    public void setTemporada(String temporada) {
        this.temporada = temporada;
    }

    public String getAtaque() {
        return ataque;
    }

    public void setAtaque(String ataque) {
        this.ataque = ataque;
    }
}
