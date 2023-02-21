/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Entities.Produits;
import Entities.Stock;
import Services.ProduitService;
import Services.StockService;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Guide Info
 */
public class StackFlow {

    /**
     * @param args the command line arguments
     * @throws java.text.ParseException
     */
    public static void main(String[] args) throws ParseException {
        
        try {
            LocalDate localDate = LocalDate.now();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formatted = localDate.format(dateTimeFormatter);
            Produits p = new Produits("Boite", "image.png", "0", 1, 1, 1, 12, 130,formatted);
            Stock s = new Stock(15, 19, "Ezzahra");
            //Produits p1 = new Produits("parfum1", "image1.png", "1", 1,13, 5,formatted);
            ProduitService ps = new ProduitService();
            StockService ss = new StockService();
           // ps.ajouter(p);
            ss.Update(s);
            System.out.println(ss.Read());
        } catch (SQLException ex) {
            System.out.println("error"+ex.getMessage());
        }
        
        
    }
    
}
