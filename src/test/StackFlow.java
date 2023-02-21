/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Entities.Avis;
import Entities.Voyage;
import Services.AvisService;
import Services.VoyageService;
import com.sun.xml.internal.bind.unmarshaller.Messages;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jazzmyna
 */
public class StackFlow {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
//            Voyage p = new Voyage("30/12/2023", "31/12/2023", "France", "Paris", "1", 1, 2,8);
           //VoyageService ps = new VoyageService();
//            //ps.ajouter(p);
//            //ps.modifier(p);
//            ps.supprimer(p);
            //System.out.println(ps.recuperer(p));
            //Avis p = new Avis(1,1, "hello");      
            AvisService ps = new AvisService();
           //ps.ajouter(p);
//            Avis p = new Avis(1,3,4, "hey");
//            AvisService ps = new AvisService();
//         ps.modifier(p);
//             ps.supprimer(p);
             System.out.println(ps.recuperer());
        } catch (SQLException ex) {
            System.out.println("Error"+ex.getMessage());//nchouf l error
        }
    }
    
}
