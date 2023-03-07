/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Stock;
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
public class StockService implements IServiceFT<Stock>{

    Connection cnx;

    public StockService() {
        cnx = MyDB.getInstance().getCnx();
    }
    
    @Override
    public void Create(Stock t) throws SQLException {
       // ResultSet rs = stmt.getGeneratedKeys();
        //System.out.println(t.getId_produit());
        String req = "INSERT INTO gestion_stock(id_produit,emplacement) VALUES("
                + "'" + t.getId_produit() + "','" + t.getEmplacement()+ "')";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
        System.out.println("done");
    }

    @Override
    public void Update(Stock t) throws SQLException {
        String req = "UPDATE gestion_stock SET id_produit = ?,emplacement = ? where id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, t.getId_produit());
        ps.setString(2, t.getEmplacement());
        ps.setInt(3, t.getId());
        ps.executeUpdate();
    }

    @Override
    public void Delete(Stock t) throws SQLException {
         String req = "DELETE FROM gestion_stock WHERE id = "+"'"+t.getId()+"'";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
    }

    @Override
    public List<Stock> Read() throws SQLException {
        List<Stock> stocks = new ArrayList<>();
        String s = "select * from gestion_stock";
        Statement st = cnx.createStatement();
        ResultSet rs =  st.executeQuery(s);
        
        while(rs.next()){
            Stock stc = new Stock();
            stc.setId_produit(rs.getInt("id_produit"));
            stc.setEmplacement(rs.getString("emplacement"));
            stc.setId(rs.getInt("id"));
            
            
            stocks.add(stc);
            
        }
        return stocks;
    }

    @Override
    public List<Stock> ReadSingle(Stock t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
