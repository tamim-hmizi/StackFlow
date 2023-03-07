/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiFT;

import entities.Produits;
import entities.Voyage;
import services.ProduitService;
import utils.MyDB;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entities.utilisateur;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Guide Info
 */
public class ProduitDetailsController implements Initializable {

    Connection cnx = MyDB.getInstance().getCnx();
    @FXML
    private TextField titre;
    @FXML
    private ImageView imageView;
    @FXML
    private TextField poids;
    @FXML
    private TextField date;
    @FXML
    private TextField prix;
    @FXML
    private Button ProduitButton;
    ProduitService ps = new ProduitService();
    @FXML
    private Button searchButton;
    @FXML
    private TextField Search;
    @FXML
    private TextField de;
    @FXML
    private TextField a;
    @FXML
    private GridPane grid;
    @FXML
    private Label dateLabel;
    @FXML
    private Label poidsLabel;
    @FXML
    private Label prixLabel;
    @FXML
    private Label deLabel;
    @FXML
    private Label titreLabel;
    @FXML
    private Label aLabel;
    @FXML
    private Label id;
    private Button PrendreOffre;
    @FXML
    private Button download_pdf;
    @FXML
    private Label id_transporteur;
    @FXML
    private Label id_recp;
    @FXML
    private Label id_client;
    @FXML
    private Label id_voyage;
    private FileChooser Fc = new FileChooser();
    @FXML
    private Button Accueil;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        titre.setEditable(false);
        poids.setEditable(false);
        prix.setEditable(false);
        date.setEditable(false);
        de.setEditable(false);
        a.setEditable(false);
        titreLabel.setAlignment(Pos.CENTER_RIGHT);
        grid.setHalignment(titreLabel, HPos.CENTER);
        dateLabel.setAlignment(Pos.CENTER_RIGHT);
        grid.setHalignment(dateLabel, HPos.CENTER);
        poidsLabel.setAlignment(Pos.CENTER_RIGHT);
        grid.setHalignment(poidsLabel, HPos.CENTER);
        prixLabel.setAlignment(Pos.CENTER_RIGHT);
        grid.setHalignment(prixLabel, HPos.CENTER);
        deLabel.setAlignment(Pos.CENTER_RIGHT);
        grid.setHalignment(deLabel, HPos.CENTER);
        aLabel.setAlignment(Pos.CENTER_RIGHT);
        grid.setHalignment(aLabel, HPos.CENTER);
    }

    public void setData(Produits p) {
        try {
            List<Produits> lp = ps.ReadSingle(p);
            for (int i = 0; i < lp.size(); i++) {
                id_client.setText(Integer.toString(lp.get(i).getId_client()));
                id_recp.setText(Integer.toString(lp.get(i).getId_recp()));
                id_transporteur.setText(Integer.toString(lp.get(i).getId_transporteur()));
                id_voyage.setText(Integer.toString(lp.get(i).getId_voyage()));
                id.setText(Integer.toString(lp.get(i).getId()));
                titre.setText(lp.get(i).getTitre());
                Image image = new Image(lp.get(i).getImage());
                imageView.setImage(image);
                poids.setText(Float.toString(lp.get(i).getPoids()));
                prix.setText(Float.toString(lp.get(i).getPrix()) + "DT");
                DateFormat date_format = new SimpleDateFormat("dd-MM-yyyy");
                String date_string = date_format.format(lp.get(i).getDate());
                date.setText(date_string);
                List<Voyage> voyage = new ArrayList<>();
                String s = "select * from gestion_voyage";
                System.out.println(s);
                Statement st = cnx.createStatement();
                ResultSet rs = st.executeQuery(s);
                while (rs.next()) {
                    Voyage v = new Voyage();
                    v.setStation_arrive(rs.getString("station_arrive"));
                    v.setStation_depart(rs.getString("station_depart"));
                    de.setText(v.getStation_depart());
                    a.setText(v.getStation_arrive());
                    voyage.add(v);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProduitDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ProduitButton(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ProduitViewClient.fxml"));
            Parent root = loader.load();
            ProduitViewClientController controller = loader.getController();
            ProduitButton.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ProduitDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void searchButton(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Seach.fxml"));
            Parent root = loader.load();
            SeachController controller = loader.getController();
            Produits p = new Produits();
            p.setTitre(Search.getText());
            controller.setDate(p);
            searchButton.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    

    @FXML
    private void DownloadPDF(ActionEvent event) {
        try {
            List<utilisateur> us = new ArrayList<>();
            String s = "select * from gestion_utilisateur WHERE id_utilisateur = " + id_client.getText();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(s);
            while (rs.next()) {
                utilisateur u = new utilisateur();
                u.setNom(rs.getString("nom"));
                u.setPrenom(rs.getString("prenom"));
                us.add(u);

            }
            System.out.println(us.get(0).getNom());
            Fc.setTitle("Download File");
            Fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
            Fc.setInitialDirectory(new File("C:\\Users\\larbi\\OneDrive\\Documents\\NetBeansProjects\\Amine_stackflow\\Stackflowtest\\src\\Facture"));
            File selectedFile = Fc.showSaveDialog(new Stage());
            if (selectedFile != null) {
                String filePath = selectedFile.getPath();

                Document doc = new Document();
                try {
                    PdfWriter.getInstance(doc, new FileOutputStream(filePath));
                    doc.open();

                    doc.add(new Phrase("Facture n°: 00" + id.getText() + "123", FontFactory.getFont("Cambria", 22)));
                    doc.add(new Paragraph("  "));

                    PdfPTable table = new PdfPTable(4);
                    table.setWidthPercentage(100);
                    PdfPCell cell;

                    cell = new PdfPCell(new Phrase("\nNom&Prenom :" + us.get(0).getNom() + " " + us.get(0).getPrenom(), FontFactory.getFont("Cambria", 12)));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBorderWidth(0);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase("\nTitre à :" + titre.getText(), FontFactory.getFont("Cambria", 12)));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBorderWidth(0);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase("\nPoids :" + poids.getText(), FontFactory.getFont("Cambria", 12)));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBorderWidth(0);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase("\nDate :      " + date.getText(), FontFactory.getFont("Cambria", 12)));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBorderWidth(0);
                    table.addCell(cell);

                    doc.add(table);

                    doc.add(new Paragraph("  "));
                    doc.add(new Paragraph("  "));
                    doc.add(new Paragraph("  "));
                    doc.add(new Paragraph("  "));
                    doc.add(new Paragraph("  "));
                    doc.add(new Paragraph("  "));
                    doc.add(new Paragraph("  "));
                    doc.add(new Paragraph("  "));
                    doc.add(new Paragraph("  "));

                    PdfPTable table1 = new PdfPTable(1);
                    table.setWidthPercentage(100);
                    PdfPCell cell1;

                    cell1 = new PdfPCell(new Phrase("TOTAL de la facture :                               " + prix.getText(), FontFactory.getFont("Cambria", 16)));
                    cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell1.setBorderWidthTop(2);
                    cell1.setBorderWidthBottom(2);
                    cell1.setBorderWidthLeft(0);
                    cell1.setBorderWidthRight(0);
                    cell1.setBorderColor(BaseColor.RED);
                    cell1.setPaddingTop(16);
                    cell1.setPaddingBottom(16);
                    table1.addCell(cell1);

                    doc.add(table1);

                    doc.add(new Paragraph("  "));
                    doc.add(new Paragraph("  "));
                    doc.add(new Paragraph("  "));
                    doc.add(new Paragraph("  "));
                    doc.add(new Paragraph("  "));
                    doc.add(new Paragraph("  "));
                    doc.add(new Paragraph("  "));
                    doc.add(new Paragraph("  "));
                    doc.add(new Paragraph("  "));
                    doc.add(new Paragraph("  "));
                    doc.add(new Paragraph("  "));
                    doc.add(new Paragraph("  "));
                    doc.add(new Phrase("Conditions et modalités de paiement", FontFactory.getFont("Cambria", 13)));
                    doc.add(new Paragraph("Le paiement aura lieu à la livraison", FontFactory.getFont("Comic Sans MS", 10)));

                    doc.close();
                    Desktop.getDesktop().open(new File(filePath));

                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ProduitDetailsController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (DocumentException ex) {
                    Logger.getLogger(ProduitDetailsController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(ProduitDetailsController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProduitDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Accueil(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../interfaces/Acceuil_user.fxml"));
            Parent root = loader.load();
            Accueil.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
