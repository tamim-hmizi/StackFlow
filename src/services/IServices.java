/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface IServices<R>  {
    
     public void ajouter(R r) throws SQLException;
    public void modifier(R r) throws SQLException;
    public void supprimer(R r) throws SQLException;
    public List<R> recuperer() throws SQLException;
    public List<R> recupererSingel(R r) throws SQLException;
    
}
