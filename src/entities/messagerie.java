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
public class messagerie {
    private int id_session;
    private int id_client; 
    private int id_transporteur;
    private String chat_transporteur;
       private String chat_client;

    public int getId_session() {
        return id_session;
    }

    public void setId_session(int id_session) {
        this.id_session = id_session;
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

    public String getChat_transporteur() {
        return chat_transporteur;
    }

    public void setChat_transporteur(String chat_transporteur) {
        this.chat_transporteur = chat_transporteur;
    }

    public String getChat_client() {
        return chat_client;
    }

    public void setChat_client(String chat_client) {
        this.chat_client = chat_client;
    }

    public messagerie(int id_session, int id_client, int id_transporteur, String chat_transporteur, String chat_client) {
        this.id_session = id_session;
        this.id_client = id_client;
        this.id_transporteur = id_transporteur;
        this.chat_transporteur = chat_transporteur;
        this.chat_client = chat_client;
    }

    public messagerie(int id_client, int id_transporteur, String chat_transporteur, String chat_client) {
        this.id_client = id_client;
        this.id_transporteur = id_transporteur;
        this.chat_transporteur = chat_transporteur;
        this.chat_client = chat_client;
    }

    @Override
    public String toString() {
        return "messagerie{" + "id_session=" + id_session + ", id_client=" + id_client + ", id_transporteur=" + id_transporteur + ", chat_transporteur=" + chat_transporteur + ", chat_client=" + chat_client + '}';
    }
       
}
