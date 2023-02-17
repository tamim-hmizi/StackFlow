/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author larbi
 */
public class utilisateur {
    private int id_utilisateur;
    private int cin ;
    private int num_tel;
    private String nom;
    private String prenom;
    private String image;
    private String password;
    private String email;
    private String role;

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

  



    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public int getNum_tel() {
        return num_tel;
    }

    public void setNum_tel(int num_tel) {
        this.num_tel = num_tel;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public utilisateur() {
    }

    public utilisateur(String nom, String prenom,int cin, String image,String password, String email,int num_tel,  String role) {
       this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.image = image;
        this.password = password;
        this.email = email;
        this.num_tel = num_tel;
        this.role = role;
    }

    public utilisateur(int id_utilisateur ,String nom, String prenom,int cin, String image,String password, String email,int num_tel,  String role) {
      this.id_utilisateur=id_utilisateur;
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.image = image;
        this.password = password;
        this.email = email;
        this.num_tel = num_tel;
        this.role = role;
    }
    

    @Override
    public String toString() {
        return "utilisateur{" + "id_utilisateur=" + id_utilisateur + ", cin=" + cin + ", num_tel=" + num_tel + ", nom=" + nom + ", prenom=" + prenom + ", image=" + image + ", password=" + password + ", email=" + email + ", role=" + role + '}';
    }

 


    
}
