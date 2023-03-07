/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.ClientRecp;
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
public class ClientRecpService implements IServiceFT<ClientRecp>{

    Connection cnx;

    public ClientRecpService() {
        cnx = MyDB.getInstance().getCnx();
    }
    
    @Override
    public void Create(ClientRecp t) throws SQLException {
        String req = "INSERT INTO gestion_client_recp(nom,prenom,tel,email) VALUES("
                + "'" + t.getNom()+ "','" + t.getPrenom()+"','"+t.getTel()+"','"+t.getEmail() +"')";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
        System.out.println("done");
    }

    @Override
    public void Update(ClientRecp t) throws SQLException {
        String req = "UPDATE gestion_client_recp SET nom = ?,prenom = ?,tel = ?,email = ? where id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, t.getNom());
        ps.setString(2, t.getPrenom());
        ps.setString(3, t.getTel());
        ps.setString(4, t.getEmail());
        ps.setInt(5, t.getId());
        ps.executeUpdate();
    }

    @Override
    public void Delete(ClientRecp t) throws SQLException {
        String req = "DELETE FROM gestion_client_recp WHERE id = "+"'"+t.getId()+"'";
        System.out.println(req);
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
    }

    @Override
    public List<ClientRecp> Read() throws SQLException {
        List<ClientRecp> clientrecp = new ArrayList<>();
        String s = "select * from gestion_client_recp ORDER BY id desc LIMIT 1";
        Statement st = cnx.createStatement();
        ResultSet rs =  st.executeQuery(s);
        while(rs.next()){
            ClientRecp p = new ClientRecp();
            p.setNom(rs.getString("nom"));
            p.setPrenom(rs.getString("prenom"));
            p.setTel(rs.getString("tel"));
            p.setEmail(rs.getString("email"));
            p.setId(rs.getInt("id"));
            
            
            clientrecp.add(p);
            
        }
        return clientrecp;
    }

    @Override
    public List<ClientRecp> ReadSingle(ClientRecp t) throws SQLException {
        List<ClientRecp> clientrecp = new ArrayList<>();
        String s = "select * from gestion_client_recp ";
        Statement st = cnx.createStatement();
        ResultSet rs =  st.executeQuery(s);
        while(rs.next()){
            ClientRecp p = new ClientRecp();
            p.setNom(rs.getString("nom"));
            p.setPrenom(rs.getString("prenom"));
            p.setTel(rs.getString("tel"));
            p.setEmail(rs.getString("email"));
            p.setId(rs.getInt("id"));
            
            
            clientrecp.add(p);
            
        }
        return clientrecp;
    }

    
    
}
