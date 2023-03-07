/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Livraison;
import entities.utilisateur;
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
 * @author tamim
 */
public class LivraisonService implements IService<Livraison>{

    Connection cnx;

    public LivraisonService() {
        this.cnx = MyDB.getInstance().getCnx();
    }
    
    @Override
    public void Create(Livraison t) throws SQLException {
        String req = "INSERT INTO gestion_livraison(id_transporteur,prix,id_abonnement,id_stock_debut,id_stock_fin,id_client) VALUES(?,?,?,?,?,?)";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, t.getId_transporteur());
        ps.setInt(2, t.getPrix());
        ps.setInt(3, t.getId_abonnement());
        ps.setInt(4, t.getId_stock_debut());
        ps.setInt(5, t.getId_stock_fin());
        ps.setInt(6, t.getId_client());
        ps.executeUpdate();
    }

    @Override
    public List<Livraison> Read() throws SQLException {
     List<Livraison> livraisons = new ArrayList<>();
        String s = "select * from gestion_livraison";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(s);
        while (rs.next()) {
            Livraison l = new Livraison(rs.getInt("id"), rs.getInt("id_transporteur"), rs.getInt("prix"),
                    rs.getInt("id_abonnement"),rs.getInt("id_stock_debut"),rs.getInt("id_stock_fin"),rs.getInt("id_client"));
            livraisons.add(l);
        }
        return livraisons;
    }

    @Override
    public void Update(Livraison t) throws SQLException {
        String req = "UPDATE gestion_livraison SET id_transporteur = ?,prix = ?,id_abonnement=?,id_stock_debut = ?,id_stock_fin = ?, id_client = ? where id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, t.getId_transporteur());
        ps.setInt(2, t.getPrix());
        ps.setInt(3, t.getId_abonnement());
        ps.setInt(4, t.getId_stock_debut());
        ps.setInt(5, t.getId_stock_fin());
        ps.setInt(6, t.getId_client());
        ps.setInt(7, t.getId());
        ps.executeUpdate();
    }

    @Override
    public void Delete(int t) throws SQLException {
        String req = "DELETE FROM gestion_livraison where id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, t);
        ps.executeUpdate();
    }
    public List<Livraison> Read_For_User() throws SQLException{
        List<Livraison> livraisons = new ArrayList<>();
        String s = "select * from gestion_livraison WHERE id_client = '"+utilisateur.getCurrent_user().getId_utilisateur()+"';";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(s);
        while (rs.next()) {
            Livraison l = new Livraison(rs.getInt("id"), rs.getInt("id_transporteur"), rs.getInt("prix"),
                    rs.getInt("id_abonnement"),rs.getInt("id_stock_debut"),rs.getInt("id_stock_fin"),rs.getInt("id_client"));
            livraisons.add(l);
        }
        return livraisons;
    }
    
}
