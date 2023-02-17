package services;

import utils.*;
import entities.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AbonnementIService implements IService<Abonnement> {
    Connection cnx;

    public AbonnementIService() {
        this.cnx = MyDB.getInstance().getCnx();
    }

    @Override
    public void Create(Abonnement t) throws SQLException {
        // TODO Auto-generated method stub
        String req = "INSERT INTO gestion_abonnement (type ,id_produit ,remise) VALUES(?,?,?)";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, t.getType());
        ps.setInt(2, t.getId_produit());
        ps.setInt(3, t.getRemise());
        ps.executeUpdate();
    }

    @Override
    public List<Abonnement> Read(Abonnement t) throws SQLException {
        List<Abonnement> abonnements = new ArrayList<>();
        String s = "select * from gestion_abonnement";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(s);
        while (rs.next()) {
            Abonnement a = new Abonnement(rs.getInt("id"), rs.getInt("type"), rs.getInt("id_produit"),
                    rs.getInt("remise"));
            abonnements.add(a);
        }
        return abonnements;
    }

    @Override
    public void Update(Abonnement t) throws SQLException {
        String req = "UPDATE gestion_abonnement SET type = ?,id_produit = ?,remise = ? where id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, t.getType());
        ps.setInt(2, t.getId_produit());
        ps.setInt(3, t.getRemise());
        ps.setInt(4, t.getId());
        ps.executeUpdate();

    }

    @Override
    public void Delete(Abonnement t) throws SQLException {
        String req = "DELETE FROM gestion_abonnement where id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, t.getId());
        ps.executeUpdate();

    }

}
