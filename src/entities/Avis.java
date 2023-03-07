/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author jazzmyna
 */
public class Avis {
    private int  id_client,avis,id_avis;
   
    private String commentaire;

    public Avis() {
    }

    public Avis(int avis,int id_client, String commentaire) {
        this.avis = avis;
        this.commentaire = commentaire;
        this.id_client = id_client;
    }

    public Avis(int id_client, int avis, int id_avis, String commentaire) {
        this.id_client = id_client;
        this.avis = avis;
        this.id_avis = id_avis;
        this.commentaire = commentaire;
    }

    public int getId_avis() {
        return id_avis;
    }

    public void setId_avis(int id_avis) {
        this.id_avis = id_avis;
    }
    

    public int getAvis() {
        return avis;
    }

    public void setAvis(int avis) {
        this.avis = avis;
    }
 public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }
    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    @Override
    public String toString() {
        return "Avis{" + "id_client=" + id_client + ", avis=" + avis + ", id_avis=" + id_avis + ", commentaire=" + commentaire + '}';
    }

    public void Delete(Avis a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



   
    
    
    
}
