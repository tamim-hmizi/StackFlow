/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.messagerie;
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
public class messagerieService implements IService<messagerie> {

    Connection cnx;

    public messagerieService() {
        cnx = MyDB.getInstance().getCnx();
    }

    @Override
    public void Create(messagerie t) throws SQLException {

        String req = "INSERT INTO gestion_messagerie(sender,receiver,message) VALUES(?,?,?)";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, t.getSender());
        ps.setInt(2, t.getReceiver());
        ps.setString(3, t.getMessage());

        ps.executeUpdate();
    }

    @Override
    public void Update(messagerie t) throws SQLException {
        String req = "UPDATE gestion_messagerie SET sender=?,receiver=?,message=? where id_session = ?";
        PreparedStatement ps = cnx.prepareStatement(req);

        ps.setInt(1, t.getSender());
        ps.setInt(2, t.getReceiver());
        ps.setString(3, t.getMessage());

        ps.setInt(5, t.getId_session());
        ps.executeUpdate();
    }

    @Override
    public void Delete(int t) throws SQLException {
        String req = "DELETE FROM gestion_messagerie where id_session = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, t);
        ps.executeUpdate();
    }

    @Override
    public List<messagerie> Read() throws SQLException {
        List<messagerie> messagerie = new ArrayList<>();
        String s = "select * from gestion_messagerie";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(s);
        while (rs.next()) {
            messagerie m = new messagerie(rs.getInt("id_session"), rs.getInt("sender"), rs.getInt("receiver"), rs.getString("message"));

            messagerie.add(m);
        }
        return messagerie;
    }

    public List<String> Read(int sender, int receiver) throws SQLException {
        List<String> messagerie = new ArrayList<>();
        String s = "select * from gestion_messagerie where (sender='" + sender + "' AND receiver='" + receiver + "') OR (sender='" + receiver + "' AND receiver='" + sender + "') ;";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(s);
        while (rs.next()) {
            messagerie.add(rs.getString("sender")+" : "+rs.getString("message"));
        }
        return messagerie;
    }

}
