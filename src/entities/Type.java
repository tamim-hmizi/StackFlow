/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author ASUS
 */
public class Type {
    
     private int id_type;
     private String type,gestion;

    public Type() {
    }

    public Type(int id_type, String type, String gestion) {
        this.id_type = id_type;
        this.type = type;
        this.gestion = gestion;
    }

    public Type(String type, String gestion) {
        this.type = type;
        this.gestion = gestion;
    }

    
    public int getId_type() {
        return id_type;
    }

    public void setId_type(int id_type) {
        this.id_type = id_type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGestion() {
        return gestion;
    }

    public void setGestion(String gestion) {
        this.gestion = gestion;
    }

    @Override
    public String toString() {
        return "Type{" + "id_type=" + id_type + ", type=" + type + ", gestion=" + gestion + '}';
    } 
    
}
