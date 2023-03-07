/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import typeenumeration.Type;

/**
 *
 * @author tamim
 */
public class Abonnement {

    private int id, id_client, remise, nb_jour;
    private Type type;
    private String date_debut;

    public Abonnement() {
    }

    public Abonnement(int id_client, Type type, int remise, int nb_jour) {
        this.id_client = id_client;
        this.type = type;
        this.remise = remise;
        this.nb_jour = nb_jour;
    }

    public Abonnement(int id, int id_client, Type type, int remise, int nb_jour) {
        this.id = id;
        this.id_client = id_client;
        this.type = type;
        this.remise = remise;
        this.nb_jour = nb_jour;
    }
    
    public Abonnement(int id, int id_client, Type type, int remise, int nb_jour, String date_debut) {
        this.id = id;
        this.id_client = id_client;
        this.type = type;
        this.remise = remise;
        this.nb_jour = nb_jour;
        this.date_debut = date_debut;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getRemise() {
        return remise;
    }

    public void setRemise(int remise) {
        this.remise = remise;
    }

    public int getNb_jour() {
        return nb_jour;
    }

    public void setNb_jour(int nb_jour) {
        this.nb_jour = nb_jour;
    }

    public String getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(String date_debut) {
        this.date_debut = date_debut;
    }

    @Override
    public String toString() {
        return "Abonnement{" + "id=" + id + ", id_client=" + id_client + ", remise=" + remise + ", nb_jour=" + nb_jour + ", type=" + type + ", date_debut=" + date_debut + '}';
    }

    

}
