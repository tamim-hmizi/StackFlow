/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;


/**
 *
 * @author Guide Info
 */
public class Produits {
    private String titre,image,etat;
    private int id,id_client,id_transporteur,type_produit,id_recp,id_voyage;
    private float poids,prix;
    private Date date;

    public Produits() {
    }

    public Produits(String titre, String image, String etat, int id, int id_client, int id_transporteur, int type_produit, int id_recp, int id_voyage, float poids, float prix, Date date) {
        this.titre = titre;
        this.image = image;
        this.etat = etat;
        this.id = id;
        this.id_client = id_client;
        this.id_transporteur = id_transporteur;
        this.type_produit = type_produit;
        this.id_recp = id_recp;
        this.id_voyage = id_voyage;
        this.poids = poids;
        this.prix = prix;
        this.date = date;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
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

    public int getId_transporteur() {
        return id_transporteur;
    }

    public void setId_transporteur(int id_transporteur) {
        this.id_transporteur = id_transporteur;
    }

    public int getType_produit() {
        return type_produit;
    }

    public void setType_produit(int type_produit) {
        this.type_produit = type_produit;
    }

    public int getId_recp() {
        return id_recp;
    }

    public void setId_recp(int id_recp) {
        this.id_recp = id_recp;
    }

    public int getId_voyage() {
        return id_voyage;
    }

    public void setId_voyage(int id_voyage) {
        this.id_voyage = id_voyage;
    }

    public float getPoids() {
        return poids;
    }

    public void setPoids(float poids) {
        this.poids = poids;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Produits{" + "titre=" + titre + ", image=" + image + ", etat=" + etat + ", id=" + id + ", id_client=" + id_client + ", id_transporteur=" + id_transporteur + ", type_produit=" + type_produit + ", id_recp=" + id_recp + ", id_voyage=" + id_voyage + ", poids=" + poids + ", prix=" + prix + ", date=" + date + '}';
    }

   

    

}