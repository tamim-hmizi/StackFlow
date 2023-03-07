/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;
import entities.messagerie;
import entities.utilisateur;
import java.sql.SQLException;
import services.messagerieService;
import services.utilisateurService;
import typeenumeration.Role;
import utils.MyDB;

/**
 *
 * @author larbi
 */
public class test {
     public static void main(String[] args) {
  try{
 utilisateur u = new utilisateur("aa","test",12345678,"image","password","amine@gmail.com",12345678,Role.transporteur);
 utilisateurService us = new utilisateurService();
   us.Create(u);
    //us.Update(u);
    // us.Delete(u);
//messagerie m = new messagerie(1,2,"test","aaaa");
 // messagerieService ms = new messagerieService();
  //   System.out.println(ms.Read());

 //ms.Create(m);
 //ms.Update(m);
  //ms.Delete(m);
  

            System.out.println(us.Read());
}catch(SQLException ex) {
            System.out.println("not going in"+ex.getMessage());
        }
}
}
