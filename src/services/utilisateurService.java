/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

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
 * @author larbi
 */
public class utilisateurService implements IService<utilisateur> {
 Connection cnx;
   public utilisateurService() {
        cnx = MyDB.getInstance().getCnx();
    }
    @Override
    public void Create(utilisateur t) throws SQLException {
               String req = "INSERT INTO gestion_utilisateur(id_utilisateur,nom,prenom,cin,image,password,email,num_tel,role) VALUES(?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, t.getId_utilisateur());
        ps.setString(2, t.getNom());
        ps.setString(3, t.getPrenom());
            ps.setInt(4, t.getCin());
            ps.setString(5, t.getImage());
            ps.setString(6, t.getPassword());
            ps.setString(7, t.getEmail());
              ps.setInt(8, t.getNum_tel());
            ps.setString(9, t.getRole());
            
        ps.executeUpdate();
    }

    @Override
    public void Update(utilisateur t) throws SQLException {
        
       String req = "UPDATE gestion_utilisateur SET nom=?,prenom=?,cin=?,image=?,password=?,email=?,num_tel=?,role=? where id_utilisateur = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
       
        ps.setString(1, t.getNom());
        ps.setString(2, t.getPrenom());
            ps.setInt(3, t.getCin());
            ps.setString(4, t.getImage());
            ps.setString(5, t.getPassword());
            ps.setString(6, t.getEmail());
              ps.setInt(7, t.getNum_tel());
            ps.setString(8, t.getRole());
              ps.setInt(9, t.getId_utilisateur());
        ps.executeUpdate();
    }

    @Override
    public void Delete(utilisateur t) throws SQLException {
          String req = "DELETE FROM gestion_utilisateur where id_utilisateur = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, t.getId_utilisateur());
        ps.executeUpdate();
    }

    @Override
    public List<utilisateur> Read(utilisateur t) throws SQLException {
          List<utilisateur> utilisateur = new ArrayList<>();
        String s = "select * from gestion_utilisateur";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(s);
        while (rs.next()) {
            utilisateur u = new utilisateur(rs.getInt("id_utilisateur"), rs.getString("nom"), rs.getString("prenom"),rs.getInt("cin"),rs.getString("image"),rs.getString("password"),rs.getString("email"),rs.getInt("num_tel"),rs.getString("role"));
               
            utilisateur.add(u);
        }
        return utilisateur;
    }
}
