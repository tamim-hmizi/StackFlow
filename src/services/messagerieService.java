/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.messagerie;
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
        String req = "INSERT INTO gestion_messagerie(id_client,id_transporteur,chat_transporteur,chat_client) VALUES(?,?,?,?)";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, t.getId_client());
        ps.setInt(2, t.getId_transporteur());
        ps.setString(3, t.getChat_transporteur());
        ps.setString(4, t.getChat_client());

        ps.executeUpdate();
    }

    @Override
    public void Update(messagerie t) throws SQLException {
        String req = "UPDATE gestion_messagerie SET id_client=?,id_transporteur=?,chat_transporteur=?,chat_client=? where id_session = ?";
        PreparedStatement ps = cnx.prepareStatement(req);

        ps.setInt(1, t.getId_client());
        ps.setInt(2, t.getId_transporteur());
        ps.setString(3, t.getChat_transporteur());
        ps.setString(4, t.getChat_client());
        ps.setInt(5, t.getId_session());
        ps.executeUpdate();
    }

    @Override
    public void Delete(messagerie t) throws SQLException {
        String req = "DELETE FROM gestion_messagerie where id_session = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, t.getId_session());
        ps.executeUpdate();
    }

    @Override
    public List<messagerie> Read(messagerie t) throws SQLException {
        List<messagerie> messagerie = new ArrayList<>();
        String s = "select * from gestion_messagerie";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(s);
        while (rs.next()) {
            messagerie m = new messagerie(rs.getInt("id_session"), rs.getInt("id_client"), rs.getInt("id_transporteur"),
                    rs.getString("chat_transporteur"), rs.getString("chat_client"));

            messagerie.add(m);
        }
        return messagerie;
    }

}
