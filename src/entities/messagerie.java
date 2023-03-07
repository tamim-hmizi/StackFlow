/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.net.ServerSocket;

/**
 *
 * @author larbi
 */
public class messagerie {
    private int id_session;
    private int sender; 
    private int receiver;
 private String message;

    public messagerie(ServerSocket serverSocket) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId_session() {
        return id_session;
    }

    public void setId_session(int id_session) {
        this.id_session = id_session;
    }

    public int getSender() {
        return sender;
    }

    public void setSender(int sender) {
        this.sender = sender;
    }

    public int getReceiver() {
        return receiver;
    }

    public void setReceiver(int receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public messagerie() {
    }

    public messagerie(int id_session, int sender, int receiver, String message) {
        this.id_session = id_session;
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
    }

    public messagerie(int sender, int receiver, String message) {
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
    }

    @Override
    public String toString() {
        return "messagerie{" + "id_session=" + id_session + ", sender=" + sender + ", receiver=" + receiver + ", message=" + message + '}';
    }
 

 


    



       
}
