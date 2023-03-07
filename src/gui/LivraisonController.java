/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Abonnement;
import entities.Livraison;
import entities.utilisateur;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import services.LivraisonService;

/**
 * FXML Controller class
 *
 * @author tamim
 */
public class LivraisonController implements Initializable {

    @FXML
    private TextField id_tansporteur;
    @FXML
    private TextField prix;
    @FXML
    private TextField id_abonnement;
    @FXML
    private TextField id_stock_debut;
    @FXML
    private TextField id_stock_fin;
    @FXML
    private TextField id_modif;
    @FXML
    private TextField prix_modif;
    @FXML
    private TextField id_abonnement_modif;
    @FXML
    private TextField id_stock_debut_modif;
    @FXML
    private TextField id_stock_fin_modif;
    @FXML
    private TextField id_transporteur_modif;
    @FXML
    private TextField id_supp;
    @FXML
    private ListView<Livraison> list;
    @FXML
    private Button btn_ajout;
    @FXML
    private Button btn_modif;
    @FXML
    private Button btn_supp;
    @FXML
    private Button btn_export;
    @FXML
    private Button Accueil;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        UpdateList();
        // controlle  de saisie

    }

    LivraisonService ls = new LivraisonService();

    public void UpdateList() {
        try {
            List<Livraison> livraisons = ls.Read();
            ObservableList<Livraison> data = FXCollections.observableArrayList(livraisons);
            list.setItems(data);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        id_tansporteur.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                id_tansporteur.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        prix.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                prix.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        id_abonnement.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                id_abonnement.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        id_stock_debut.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                id_stock_debut.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        id_stock_fin.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                id_stock_fin.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        id_modif.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                id_modif.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        prix_modif.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                prix_modif.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        id_abonnement_modif.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                id_abonnement_modif.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        id_stock_debut_modif.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                id_stock_debut_modif.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        id_stock_fin_modif.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                id_stock_fin_modif.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        id_transporteur_modif.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                id_transporteur_modif.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        id_supp.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                id_supp.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        btn_supp.setDisable(true);
        id_supp.textProperty().addListener((observable, oldValue, newValue) -> {
            btn_supp.setDisable(newValue.isEmpty());
        });
        btn_modif.setDisable(true);
        id_stock_fin_modif.textProperty().addListener((observable, oldValue, newValue) -> {
            btn_modif.setDisable(newValue.isEmpty() || id_transporteur_modif.getText().isEmpty() || id_modif.getText().isEmpty() || id_stock_debut_modif.getText().isEmpty() || id_abonnement_modif.getText().isEmpty() || prix_modif.getText().isEmpty());
        });
        btn_ajout.setDisable(true);
        id_stock_fin.textProperty().addListener((observable, oldValue, newValue) -> {
            btn_ajout.setDisable(newValue.isEmpty() || prix.getText().isEmpty() || id_abonnement.getText().isEmpty() || id_stock_debut.getText().isEmpty() || id_tansporteur.getText().isEmpty());
        });
        id_supp.clear();
        id_modif.clear();
        id_transporteur_modif.clear();
        id_stock_fin_modif.clear();
        prix_modif.clear();
        id_abonnement_modif.clear();
        id_stock_debut_modif.clear();
        id_stock_fin.clear();
        id_stock_debut.clear();
        id_abonnement.clear();
        prix.clear();
        id_tansporteur.clear();
    }

    @FXML
    private void ajouter(ActionEvent event) {
        try {
            Livraison l = new Livraison();
            l.setId_transporteur(Integer.parseInt(id_tansporteur.getText()));
            l.setPrix(Integer.parseInt(prix.getText()));
            l.setId_abonnement(Integer.parseInt(id_abonnement.getText()));
            l.setId_stock_debut(Integer.parseInt(id_stock_debut.getText()));
            l.setId_stock_fin(Integer.parseInt(id_stock_fin.getText()));
            l.setId_client(utilisateur.getCurrent_user().getId_utilisateur());
            ls.Create(l);
            UpdateList();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void modifier(ActionEvent event) {
        try {
            Livraison l = new Livraison();
            l.setId(Integer.parseInt(id_modif.getText()));
            l.setId_transporteur(Integer.parseInt(id_transporteur_modif.getText()));
            l.setPrix(Integer.parseInt(prix_modif.getText()));
            l.setId_abonnement(Integer.parseInt(id_abonnement_modif.getText()));
            l.setId_stock_debut(Integer.parseInt(id_stock_debut_modif.getText()));
            l.setId_stock_fin(Integer.parseInt(id_stock_fin_modif.getText()));
            ls.Update(l);
            UpdateList();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void supprimer(ActionEvent event) {
        try {
            ls.Delete(Integer.parseInt(id_supp.getText()));
            UpdateList();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void export(ActionEvent event) {
        ObservableList<Livraison> livraisons = list.getItems();

        // Create a new workbook and sheet
        XSSFWorkbook workbook = new XSSFWorkbook();
        String sheetName = "livraisons";
        workbook.createSheet(sheetName);

        // Create the header row
        String[] headers = {"ID", "ID transporteur", "Prix", "Id abonnement", "Id stock debut", "Id stock fin","Id Client"};
        Row headerRow = workbook.getSheet(sheetName).createRow(0);
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }

        // Create the data rows
        int rowNum = 1;
        for (Livraison livraison : livraisons) {
            Row row = workbook.getSheet(sheetName).createRow(rowNum++);
            row.createCell(0).setCellValue(livraison.getId());
            row.createCell(1).setCellValue(livraison.getId_transporteur());
            row.createCell(2).setCellValue(livraison.getPrix());
            row.createCell(3).setCellValue(livraison.getId_abonnement());
            row.createCell(4).setCellValue(livraison.getId_stock_debut());
            row.createCell(5).setCellValue(livraison.getId_stock_fin());
            row.createCell(6).setCellValue(livraison.getId_client());
        }

        // Show the file chooser dialog
        Stage stage = (Stage) btn_export.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save livraisons");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel files (*.xlsx)", "*.xlsx"));
        File file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            // Save the workbook to the selected file
            try {
                FileOutputStream outputStream = new FileOutputStream(file);
                workbook.write(outputStream);
                workbook.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @FXML
    private void Accueil(ActionEvent event) throws IOException {
                Parent root = FXMLLoader.load(getClass().getResource("../interfaces/Acceuil_user.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
