/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Abonnement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import typeenumeration.Type;
import utils.MyDB;
import entities.utilisateur;
/**
 *
 * @author tamim
 */
public class AbonnementService implements IService<Abonnement>{
    private Connection cnx;

    public AbonnementService() {
        this.cnx = MyDB.getInstance().getCnx();
    }
    
    @Override
    public void Create(Abonnement t) throws SQLException {
       String req = "INSERT INTO gestion_abonnement (id_client,type ,remise ,nb_jour) VALUES(?,?,?,?)";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, t.getId_client());
        ps.setString(2, t.getType().toString());
        ps.setInt(3, t.getRemise());
        ps.setInt(4, t.getNb_jour());
        ps.executeUpdate();
    }

    @Override
    public List<Abonnement> Read() throws SQLException {
       List<Abonnement> abonnements = new ArrayList<>();
        String s = "select * from gestion_abonnement";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(s);
        while (rs.next()) {
            Abonnement a = new Abonnement(rs.getInt("id"),rs.getInt("id_client"),Type.valueOf(rs.getString("type")),rs.getInt("remise"),rs.getInt("nb_jour"),rs.getDate("date_debut").toString());
            abonnements.add(a);
        }
        return abonnements;
    }

    @Override
    public void Update(Abonnement t) throws SQLException {
         String req = "UPDATE gestion_abonnement SET id_client = ?,type = ?,remise = ?,nb_jour = ? where id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, t.getId_client());
        ps.setString(2, t.getType().toString());
        ps.setInt(3, t.getRemise());
        ps.setInt(4, t.getNb_jour());
        ps.setInt(5, t.getId());
        ps.executeUpdate();
    }

    @Override
    public void Delete(int t) throws SQLException {
        String req = "DELETE FROM gestion_abonnement where id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1,t);
        ps.executeUpdate();
        
    }
    public List<Abonnement> Recherche(int t) throws SQLException{
        List<Abonnement> abonnements = new ArrayList<>();
        String s = "SELECT * FROM gestion_abonnement WHERE id_client='"+t+"';";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(s);
        while (rs.next()) {
            Abonnement a = new Abonnement(rs.getInt("id"),rs.getInt("id_client"),Type.valueOf(rs.getString("type")),rs.getInt("remise"),rs.getInt("nb_jour"),rs.getDate("date_debut").toString());
            abonnements.add(a);
        }
        if(abonnements.isEmpty())
            return new ArrayList<>();
        return abonnements;
    }
  
}
