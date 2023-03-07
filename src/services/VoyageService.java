/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Voyage;
import utils.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jazzmyna
 */
public class VoyageService implements IServiceYZ<Voyage>{
Connection cnx;

    public VoyageService() {
        cnx = MyDB.getInstance().getCnx();
        
    }
    
    public void Create(Voyage t) throws SQLException {
        String req = "INSERT INTO Gestion_Voyage(station_depart,station_arrive,type_voyage,date_depart,date_arrivee) VALUES("
                + "'" + t.getStation_depart() + "','" + t.getStation_arrive()+ "','" + t.getType_voyage()+ "','"+t.getDate_depart()+ "','"+t.getDate_arrivee() +"')";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
    
    }

    
    public void Update(Voyage t) throws SQLException {
        String req = "UPDATE gestion_voyage SET station_depart = ?,station_arrive= ?,type_voyage = ? ,date_depart = ?,date_arrivee = ? where id_voyage = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, t.getStation_depart());
        ps.setString(2, t.getStation_arrive());
        ps.setString(3, t.getType_voyage());
        ps.setDate(4, t.getDate_depart());
        ps.setDate(5, t.getDate_arrivee());
        ps.setInt(6, t.getId_Voyage());
        ps.executeUpdate();
    }

    
    public void Delete(Voyage t) throws SQLException {
        String req = "DELETE from gestion_Voyage where id_voyage = "+t.getId_Voyage();
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
    }

    
    public List<Voyage> Read() throws SQLException {
          List<Voyage> voyage = new ArrayList<>();
        String s = "select * from Gestion_Voyage ";
        Statement st = cnx.createStatement();
        ResultSet rs =  st.executeQuery(s);
        while(rs.next()){
            Voyage p = new Voyage();
            p.setId_Voyage(rs.getInt("id_voyage"));
            p.setStation_depart(rs.getString("station_depart"));
            p.setStation_arrive(rs.getString("station_arrive"));
            p.setType_voyage(rs.getString("type_voyage"));
            p.setDate_depart(rs.getDate("date_depart"));
            p.setDate_arrivee(rs.getDate("date_arrivee"));
            
            
        
            voyage.add(p);
        }
                   
        return voyage;
    }

    @Override
    public List<Voyage> ReadSingel(Voyage t) throws SQLException {
        List<Voyage> voyage = new ArrayList<>();
        String s = "select * from Gestion_Voyage where id_voyage = "+t.getId_Voyage();
        Statement st = cnx.createStatement();
        ResultSet rs =  st.executeQuery(s);
        while(rs.next()){
            Voyage p = new Voyage();
            p.setId_Voyage(rs.getInt("id_voyage"));
            p.setStation_depart(rs.getString("station_depart"));
            p.setStation_arrive(rs.getString("station_arrive"));
            p.setType_voyage(rs.getString("type_voyage"));
            p.setDate_depart(rs.getDate("date_depart"));
            p.setDate_arrivee(rs.getDate("date_arrivee"));
            
            
        
            voyage.add(p);
        }
                   
        return voyage;
    }
    
    
    
}

