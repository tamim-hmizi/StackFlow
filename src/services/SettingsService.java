/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Settings;
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
public class SettingsService implements IService<Settings> {

    Connection cnx;

    public SettingsService() {
        this.cnx = MyDB.getInstance().getCnx();
    }

    @Override
    public void Create(Settings t) throws SQLException {
        List<Settings> list = this.Read();
        for (Settings set : list) {
            if (set.getId_user() == utilisateur.getCurrent_user().getId_utilisateur()) {
                this.Update(t);
            } else {
                String req = "INSERT INTO settings (id_user,fullscreen,theme) VALUES(?,?,?)";
                PreparedStatement ps = cnx.prepareStatement(req);
                ps.setInt(1, t.getId_user());
                ps.setString(2, String.valueOf(t.isFullscreen()));
                ps.setString(3, t.getTheme());
                ps.executeUpdate();
            }
        }

    }

    @Override
    public void Update(Settings t) throws SQLException {
        String req = "UPDATE settings SET fullscreen=?,theme=? where id= ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, String.valueOf(t.isFullscreen()));
        ps.setString(2, t.getTheme());
        ps.setInt(3, t.getId());
        ps.executeUpdate();
    }

    @Override
    public void Delete(int t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Settings> Read() throws SQLException {
        List<Settings> Settings = new ArrayList<>();
        String s = "select * from settings";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(s);
        while (rs.next()) {
            Settings u = new Settings(rs.getInt("id_user"), Boolean.parseBoolean(rs.getString("fullscreen")), rs.getString("theme"));

            Settings.add(u);
        }
        return Settings;
    }

}
