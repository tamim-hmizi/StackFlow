/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Avis;
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
public class AvisService implements IService<Avis>{
Connection cnx;

 public AvisService() {
        cnx = StackFlowDB.getInstance().getCnx();
         
    }

    @Override
    public void ajouter(Avis t) throws SQLException {
        String req = "INSERT INTO Gestion_avis(id_client,avis,commentaire) VALUES("
                + "'" + t.getId_client()+ "','" + t.getAvis()+ "','" + t.getCommentaire()+ "')";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
    }

    @Override
    public void modifier(Avis t) throws SQLException {
        String req = "UPDATE gestion_avis SET id_client = ?,avis= ?,commentaire = ?  where id_avis = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, t.getId_client());
        ps.setInt(2, t.getAvis());
        ps.setString(3, t.getCommentaire());
        ps.setInt(4, t.getId_avis());
        
        ps.executeUpdate(); 
    }

    @Override
    public void supprimer(Avis t) throws SQLException {
        String req = "DELETE from gestion_Avis where id_Avis = "+t.getId_avis();
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
    }

    @Override
    public List<Avis> recuperer() throws SQLException {
           List<Avis> avis = new ArrayList<>();
        String s = "select * from Gestion_Avis";
        Statement st = cnx.createStatement();
        ResultSet rs =  st.executeQuery(s);
        while(rs.next()){
            Avis p = new Avis();
            p.setId_avis(rs.getInt("id_avis"));
            p.setId_client(rs.getInt("id_client"));
            p.setAvis(rs.getInt("avis"));
            p.setCommentaire(rs.getString("commentaire"));
            
           
            
            
        
            avis.add(p);
        }
        return avis;
    }
}
