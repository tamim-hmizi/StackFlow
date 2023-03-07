/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author tamim
 */
public class Livraison {

    private int id, id_transporteur, prix, id_abonnement, id_stock_debut, id_stock_fin, id_client;

    public Livraison() {
    }

    public Livraison(int id_transporteur, int prix, int id_abonnement, int id_stock_debut, int id_stock_fin, int id_client) {
        this.id_transporteur = id_transporteur;
        this.prix = prix;
        this.id_abonnement = id_abonnement;
        this.id_stock_debut = id_stock_debut;
        this.id_stock_fin = id_stock_fin;
        this.id_client = id_client;
    }

    public Livraison(int id, int id_transporteur, int prix, int id_abonnement, int id_stock_debut, int id_stock_fin, int id_client) {
        this.id = id;
        this.id_transporteur = id_transporteur;
        this.prix = prix;
        this.id_abonnement = id_abonnement;
        this.id_stock_debut = id_stock_debut;
        this.id_stock_fin = id_stock_fin;
        this.id_client = id_client;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public int getId_abonnement() {
        return id_abonnement;
    }

    public void setId_abonnement(int id_abonnement) {
        this.id_abonnement = id_abonnement;
    }

    public int getId_stock_debut() {
        return id_stock_debut;
    }

    public void setId_stock_debut(int id_stock_debut) {
        this.id_stock_debut = id_stock_debut;
    }

    public int getId_stock_fin() {
        return id_stock_fin;
    }

    public void setId_stock_fin(int id_stock_fin) {
        this.id_stock_fin = id_stock_fin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_transporteur() {
        return id_transporteur;
    }

    public void setId_transporteur(int id_transporteur) {
        this.id_transporteur = id_transporteur;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Livraison{" + "id=" + id + ", id_transporteur=" + id_transporteur + ", prix=" + prix + ", id_abonnement=" + id_abonnement + ", id_stock_debut=" + id_stock_debut + ", id_stock_fin=" + id_stock_fin + ", id_client=" + id_client + '}';
    }

    

}
