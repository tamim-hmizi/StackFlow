/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author jazzmyna
 */
public class Voyage {

    public static void add(Voyage p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
  
    
    private String date_depart,date_arrivee;
    private String  station_depart, station_arrive,type_voyage;
    private int id_transporteur ,id_client,id_voyage ;

    public Voyage() {
    }

    public Voyage(String date_depart, String date_arrivee, String station_depart, String station_arrive, String type_voyage, int id_transporteur, int id_client) {
        this.date_depart = date_depart;
        this.date_arrivee = date_arrivee;
        this.station_depart = station_depart;
        this.station_arrive = station_arrive;
        this.type_voyage = type_voyage;
        this.id_transporteur = id_transporteur;
        this.id_client = id_client;
    }

    public Voyage(String date_depart, String date_arrivee, String station_depart, String station_arrive, String type_voyage, int id_transporteur, int id_client, int id_voyage) {
        this.date_depart = date_depart;
        this.date_arrivee = date_arrivee;
        this.station_depart = station_depart;
        this.station_arrive = station_arrive;
        this.type_voyage = type_voyage;
        this.id_transporteur = id_transporteur;
        this.id_client = id_client;
        this.id_voyage = id_voyage;
    }
    

    public int getId_voyage() {
        return id_voyage;
    }

    public void setId_voyage(int id_voyage) {
        this.id_voyage = id_voyage;
    }

    public String getDate_depart() {
        return date_depart;
    }

    public void setDate_depart(String date_depart) {
        this.date_depart = date_depart;
    }

    public String getDate_arrivee() {
        return date_arrivee;
    }

    public void setDate_arrivee(String date_arrivee) {
        this.date_arrivee = date_arrivee;
    }

    public String getStation_depart() {
        return station_depart;
    }

    public void setStation_depart(String station_depart) {
        this.station_depart = station_depart;
    }

    public String getStation_arrive() {
        return station_arrive;
    }

    public void setStation_arrive(String station_arrive) {
        this.station_arrive = station_arrive;
    }

    public String getType_voyage() {
        return type_voyage;
    }

    public void setType_voyage(String type_voyage) {
        this.type_voyage = type_voyage;
    }

    public int getId_transporteur() {
        return id_transporteur;
    }

    public void setId_transporteur(int id_transporteur) {
        this.id_transporteur = id_transporteur;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    @Override
    public String toString() {
        return "Voyage{" + "date_depart=" + date_depart + ", date_arrivee=" + date_arrivee + ", station_depart=" + station_depart + ", station_arrive=" + station_arrive + ", type_voyage=" + type_voyage + ", id_transporteur=" + id_transporteur + ", id_client=" + id_client + ", id_voyage=" + id_voyage + '}';
    }

   
	   
}
