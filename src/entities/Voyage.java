/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author jazzmyna
 */
public class Voyage {
  
    
    private Date date_depart,date_arrivee;
    private String  station_depart, station_arrive,type_voyage;
    private int id_Voyage,plus,moins ;

    public Voyage() {
    }

    public Voyage(Date date_depart, Date date_arrivee, String station_depart, String station_arrive, String type_voyage, int id_Voyage, int plus, int moins) {
        this.date_depart = date_depart;
        this.date_arrivee = date_arrivee;
        this.station_depart = station_depart;
        this.station_arrive = station_arrive;
        this.type_voyage = type_voyage;
        this.id_Voyage = id_Voyage;
        this.plus = plus;
        this.moins = moins;
    }

    public Date getDate_depart() {
        return date_depart;
    }

    public void setDate_depart(Date date_depart) {
        this.date_depart = date_depart;
    }

    public Date getDate_arrivee() {
        return date_arrivee;
    }

    public void setDate_arrivee(Date date_arrivee) {
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

    public int getId_Voyage() {
        return id_Voyage;
    }

    public void setId_Voyage(int id_Voyage) {
        this.id_Voyage = id_Voyage;
    }

    public int getPlus() {
        return plus;
    }

    public void setPlus(int plus) {
        this.plus = plus;
    }

    public int getMoins() {
        return moins;
    }

    public void setMoins(int moins) {
        this.moins = moins;
    }

    @Override
    public String toString() {
        return "Voyage{" + "date_depart=" + date_depart + ", date_arrivee=" + date_arrivee + ", station_depart=" + station_depart + ", station_arrive=" + station_arrive + ", type_voyage=" + type_voyage + ", id_Voyage=" + id_Voyage + ", plus=" + plus + ", moins=" + moins + '}';
    }


    
    
}
