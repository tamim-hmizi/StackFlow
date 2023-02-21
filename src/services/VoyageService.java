/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Voyage;
import Utils.StackFlowDB;
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
public class VoyageService implements IService<Voyage>{
Connection cnx;

    public VoyageService() {
        cnx = StackFlowDB.getInstance().getCnx();
        
    }
    @Override
    public void ajouter(Voyage t) throws SQLException {
        String req = "INSERT INTO Gestion_Voyage(station_depart,station_arrive,type_voyage,date_depart,date_arrivee,id_transporteur,id_client) VALUES("
                + "'" + t.getStation_depart() + "','" + t.getStation_arrive()+ "','" + t.getType_voyage()+ "','"+t.getDate_depart()+ "','"+t.getDate_arrivee()+ "','"+t.getId_transporteur()+ "','"+t.getId_client() +"')";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
    
    }

    @Override
    public void modifier(Voyage t) throws SQLException {
        String req = "UPDATE gestion_voyage SET station_depart = ?,station_arrive= ?,type_voyage = ? ,date_depart = ?,date_arrivee = ?,id_transporteur = ?,id_client = ? where id_voyage = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, t.getStation_depart());
        ps.setString(2, t.getStation_arrive());
        ps.setString(3, t.getType_voyage());
        ps.setString(4, t.getDate_depart());
        ps.setString(5, t.getDate_arrivee());
        ps.setInt(6, t.getId_transporteur());
        ps.setInt(7, t.getId_client());
        ps.setInt(8, t.getId_voyage());
        ps.executeUpdate();
    }

    @Override
    public void supprimer(Voyage t) throws SQLException {
        String req = "DELETE from gestion_Voyage where id_voyage = "+t.getId_voyage();
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
    }

    @Override
    public List<Voyage> recuperer() throws SQLException {
          List<Voyage> voyage = new ArrayList<>();
        String s = "select * from Gestion_Voyage";
        Statement st = cnx.createStatement();
        ResultSet rs =  st.executeQuery(s);
        while(rs.next()){
            Voyage p = new Voyage();
            p.setId_voyage(rs.getInt("id_voyage"));
            p.setStation_depart(rs.getString("station_depart"));
            p.setStation_arrive(rs.getString("station_arrive"));
            p.setType_voyage(rs.getString("type_voyage"));
            p.setDate_depart(rs.getString("date_depart"));
            p.setDate_arrivee(rs.getString("date_arrivee"));
            p.setId_transporteur(rs.getInt("id_transporteur"));
            p.setId_client(rs.getInt("id_client"));
            
            
        
            voyage.add(p);
        }
                   
        return voyage;
    }

    
    
    
}

