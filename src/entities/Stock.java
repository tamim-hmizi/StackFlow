/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Guide Info
 */
public class Stock {
    private int id,id_produit;
    private String emplacement;

    public Stock() {
    }

    public Stock(int id_produit, String emplacement) {
        this.id_produit = id_produit;
        this.emplacement = emplacement;
    }

    public Stock(int id, int id_produit, String emplacement) {
        this.id = id;
        this.id_produit = id_produit;
        this.emplacement = emplacement;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public String getEmplacement() {
        return emplacement;
    }

    public void setEmplacement(String emplacement) {
        this.emplacement = emplacement;
    }

    @Override
    public String toString() {
        return "Stock{" + "id=" + id + ", id_produit=" + id_produit + ", emplacement=" + emplacement + '}';
    }
    
    
    
    

    

    
    
    
}
