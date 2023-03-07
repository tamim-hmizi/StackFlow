/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Reclamation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyDB;
/**
 *
 * @author ASUS
 */
public class ReclamationService implements IServices<Reclamation> {
    
     Connection cnx;

    public ReclamationService() {
        cnx = MyDB.getInstance().getCnx();
    }
     @Override
    public void ajouter(Reclamation r) throws SQLException {
        String req = "INSERT INTO `gestion_reclamation`( `id_type`, `email`, `sujet`, `message`) VALUES("  
                + "'" + r.getId_type()+ "','" + r.getEmail() + "','" + r.getSujet() + "','" + r.getMessage() + "')";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
    }

     @Override
    public void modifier(Reclamation r) throws SQLException {
        String req = "UPDATE gestion_reclamation SET id_type = ?,email = ?,sujet = ?,message = ? where id_reclamation = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, 1);
         System.out.println(r.getMessage());
        ps.setString(2, r.getEmail());
        ps.setString(3, r.getSujet());
        ps.setString(4, r.getMessage());
        ps.setInt(5, r.getId_reclamation());
        ps.executeUpdate();
        
    }

     @Override
    public void supprimer(Reclamation r) throws SQLException {
        String req = "DELETE FROM gestion_reclamation where id_reclamation = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1,r.getId_reclamation());
        ps.executeUpdate();
    }

     @Override
    public List<Reclamation> recuperer() throws SQLException {
        List<Reclamation> reclamation = new ArrayList<>();
        String s = "select * from gestion_reclamation";
        Statement st = cnx.createStatement();
        ResultSet rs =  st.executeQuery(s);
        while(rs.next()){
            Reclamation p = new Reclamation();
            p.setEmail(rs.getString("email"));
            p.setSujet(rs.getString("sujet"));
            p.setMessage(rs.getString("message"));
            p.setId_reclamation(rs.getInt("id_reclamation"));
            p.setId_type(rs.getInt("id_type"));
            
            reclamation.add(p);
            
        }
        return reclamation;
}

    @Override
    public List<Reclamation> recupererSingel(Reclamation r) throws SQLException {
        List<Reclamation> reclamation = new ArrayList<>();
        String s = "select * from gestion_reclamation where id_reclamation = "+r.getId_reclamation();
        Statement st = cnx.createStatement();
        ResultSet rs =  st.executeQuery(s);
        while(rs.next()){
            Reclamation p = new Reclamation();
            p.setEmail(rs.getString("email"));
            p.setSujet(rs.getString("sujet"));
            p.setMessage(rs.getString("message"));
            p.setId_reclamation(rs.getInt("id_reclamation"));
            p.setId_type(rs.getInt("id_type"));
            
            reclamation.add(p);
            
        }
        return reclamation;
    }
}
