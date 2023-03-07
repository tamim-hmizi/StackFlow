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
public class Reclamation {
    private int id_reclamation,id_type;
    private String email,sujet,message;

    public Reclamation() {
    }
    
    public Reclamation(int id_reclamation, int id_type, String email, String sujet, String message) {
        this.id_reclamation = id_reclamation;
        this.id_type = id_type;
        this.email = email;
        this.sujet = sujet;
        this.message = message;
    }

    public Reclamation(int id_type, String email, String sujet, String message) {
        this.id_type = id_type;
        this.email = email;
        this.sujet = sujet;
        this.message = message;
    }

    
    
    public int getId_reclamation() {
        return id_reclamation;
    }

    public void setId_reclamation(int id_reclamation) {
        this.id_reclamation = id_reclamation;
    }

    public int getId_type() {
        return id_type;
    }

    public void setId_type(int id_type) {
        this.id_type = id_type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id_reclamation=" + id_reclamation + ", id_type=" + id_type + ", email=" + email + ", sujet=" + sujet + ", message=" + message + '}';
    }
    
    
}
