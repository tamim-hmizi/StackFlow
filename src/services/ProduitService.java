/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Produits;
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
 * @author Guide Info
 */
public class ProduitService implements IServiceFT<Produits>{

    Connection cnx;

    public ProduitService() {
        cnx = MyDB.getInstance().getCnx();
    }
    
    @Override
    public void Create(Produits t) throws SQLException {
        String req = "INSERT INTO gestion_produit(titre,image,poids,date,prix,id_client,id_transporteur,type_produit,etat,id_recp,id_voyage) VALUES("
                + "'" + t.getTitre() + "','" + t.getImage() + "','" + t.getPoids() + "','" + t.getDate() + "','" + t.getPrix() + "','" +t.getId_client()+"','" +t.getId_transporteur()+"','" +t.getType_produit()+"','" + t.getEtat() + "','"+t.getId_recp()+"','"+t.getId_voyage()+"')";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
    }

    @Override
    public void Update(Produits t) throws SQLException {
        String req = "UPDATE gestion_produit SET titre = ?,image = ?,poids = ?,date = ?,prix = ?,etat = ? where id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, t.getTitre());
        ps.setString(2, t.getImage());
        ps.setFloat(3, t.getPoids());
        ps.setDate(4, t.getDate());
        ps.setFloat(5, t.getPrix());
        ps.setString(6, "0");
        ps.setInt(7, t.getId());
        ps.executeUpdate();
    }

    @Override
    public void Delete(Produits t) throws SQLException {
        String req = "DELETE FROM gestion_produit WHERE id = "+"'"+t.getId()+"'";
        System.out.println(req);
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
    }

    @Override
    public List<Produits> Read() throws SQLException {
        List<Produits> produits = new ArrayList<>();
        String s = "select * from gestion_produit ORDER BY id desc";
        Statement st = cnx.createStatement();
        ResultSet rs =  st.executeQuery(s);
        while(rs.next()){
            Produits p = new Produits();
            p.setTitre(rs.getString("titre"));
            p.setImage(rs.getString("image"));
            p.setPoids(rs.getFloat("poids"));
            p.setDate(rs.getDate("date"));
            p.setPrix(rs.getInt("prix"));
            p.setEtat(rs.getString("etat"));
            p.setId(rs.getInt("id"));
            
            
            produits.add(p);
            
        }
        return produits;
    }

    @Override
    public List<Produits> ReadSingle(Produits t) throws SQLException {
        List<Produits> produits = new ArrayList<>();
        String s = "select * from gestion_produit WHERE id = " +t.getId();
        Statement st = cnx.createStatement();
        ResultSet rs =  st.executeQuery(s);
        while(rs.next()){
            Produits p = new Produits();
            p.setTitre(rs.getString("titre"));
            p.setImage(rs.getString("image"));
            p.setPoids(rs.getFloat("poids"));
            p.setDate(rs.getDate("date"));
            p.setPrix(rs.getInt("prix"));
            p.setEtat(rs.getString("etat"));
            p.setId_client(rs.getInt("id_client"));
            p.setId_recp(rs.getInt("id_recp"));
            p.setId_transporteur(rs.getInt("id_transporteur"));
            p.setId_voyage(rs.getInt("id_voyage"));
            p.setId(rs.getInt("id"));
            
            
            produits.add(p);
            
        }
        return produits;
    }


 
    
}
