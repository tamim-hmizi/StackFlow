/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;



import entities.Type;
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
public class TypeService implements IServices<Type> {
    
     Connection cnx;

    public TypeService() {
        cnx = MyDB.getInstance().getCnx();
    }
     @Override
    public void ajouter(Type t) throws SQLException {
        String req = "INSERT INTO `gestion_type`(`type`, `gestion`) VALUES("
                + "'" + t.getType() + "','" + t.getGestion() + "')";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
    }

     @Override
    public void modifier(Type t) throws SQLException {
        String req = "UPDATE gestion_type SET type = ?,gestion = ? where id_type = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, t.getType());
        ps.setString(2, t.getGestion());
        ps.setInt(3, t.getId_type());
        ps.executeUpdate();
        
    }

     @Override
    public void supprimer(Type t) throws SQLException {
        String req = "DELETE FROM gestion_type where id_type = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1,t.getId_type());
        ps.executeUpdate();
    
    }

     @Override
    public List<Type> recuperer() throws SQLException {
        List<Type> type = new ArrayList<>();
        String s = "select * from gestion_type";
        Statement st = cnx.createStatement();
        ResultSet rs =  st.executeQuery(s);
        while(rs.next()){
            Type p = new Type();
            p.setType(rs.getString("type"));
            p.setGestion(rs.getString("gestion"));
            p.setId_type(rs.getInt("id_type"));
            
            
            type.add(p);
            
        }
        return type;
}

    @Override
    public List<Type> recupererSingel(Type r) throws SQLException {
        List<Type> type = new ArrayList<>();
        String s = "select * from gestion_type where id_type = "+r.getId_type();
        Statement st = cnx.createStatement();
        ResultSet rs =  st.executeQuery(s);
        while(rs.next()){
            Type p = new Type();
            p.setType(rs.getString("type"));
            p.setGestion(rs.getString("gestion"));
            p.setId_type(rs.getInt("id_type"));
            
            
            type.add(p);
            
        }
        return type;
    }
}
