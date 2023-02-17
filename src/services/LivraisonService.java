package services;

import utils.*;
import entities.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivraisonService implements IService<Livraison> {

    Connection cnx;

    public LivraisonService() {
        this.cnx = MyDB.getInstance().getCnx();
    }

    @Override
    public void Create(Livraison t) throws SQLException {

        String req = "INSERT INTO gestion_livraison(id_transporteur,prix,id_stock) VALUES(?,?,?)";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, t.getId_transporteur());
        ps.setInt(2, t.getPrix());
        ps.setInt(3, t.getId_stock());
        ps.executeUpdate();

    }

    @Override
    public List<Livraison> Read(Livraison t) throws SQLException {
        List<Livraison> livraisons = new ArrayList<>();
        String s = "select * from gestion_livraison";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(s);
        while (rs.next()) {
            Livraison l = new Livraison(rs.getInt("id"), rs.getInt("id_transporteur"), rs.getInt("prix"),
                    rs.getInt("id_stock"));
            livraisons.add(l);
        }
        return livraisons;
    }

    @Override
    public void Update(Livraison t) throws SQLException {
        String req = "UPDATE gestion_livraison SET id_transporteur = ?,prix = ?,id_stock = ? where id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, t.getId_transporteur());
        ps.setInt(2, t.getPrix());
        ps.setInt(3, t.getId_stock());
        ps.setInt(4, t.getId());
        ps.executeUpdate();
    }

    @Override
    public void Delete(Livraison t) throws SQLException {
        String req = "DELETE FROM gestion_livraison where id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, t.getId());
        ps.executeUpdate();
    }

}
