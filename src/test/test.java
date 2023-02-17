/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;
import entities.utilisateur;
import java.sql.SQLException;
import services.utilisateurService;
import utils.MyDB;

/**
 *
 * @author larbi
 */
public class test {
     public static void main(String[] args) {
  try{
 utilisateur u = new utilisateur(4,"tasnim","larbhbkbi",12345678,"image","password","amine@gmail.com",12345678,"admin");
   utilisateurService us = new utilisateurService();
   //us.Create(u);
    //us.Update(u);
    
    us.Delete(u);

            System.out.println(us.Read(u));
}catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
}
}
