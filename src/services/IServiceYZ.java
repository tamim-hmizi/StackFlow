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
 * @author jazzmyna
 */
public interface IServiceYZ <T>{
    public void Create(T t) throws SQLException;

    public List<T> Read() throws SQLException;

    public void Update(T t) throws SQLException;

    public void Delete(T t) throws SQLException;
    
    public List<T> ReadSingel(T t) throws SQLException;
    
}
