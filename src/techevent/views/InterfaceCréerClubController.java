/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techevent.views;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXSlider;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import techevent.entities.Club;
import techevent.services.ServiceClub;

/**
 * FXML Controller class
 *
 * @author Hannachi
 */
public class InterfaceCréerClubController implements Initializable {

    @FXML
    private Button boutonannuler;
    @FXML
    private TextField textfilednom;
    @FXML
    private JFXComboBox<String> comboboxdomaine;
    ObservableList<String> domaine = FXCollections.observableArrayList(
            "Informatique",
            "Réseau",
            "Robotique",
            "Aéronautique",
            "Chimie",
            "Biologie"
    );
    
    @FXML
    private JFXSlider fraisinscription;
    @FXML
    private Button boutoncréer;
    @FXML
    private JFXDatePicker datedecreation;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboboxdomaine.setItems(domaine);
    }

    @FXML
    private void annuler(ActionEvent event) throws SQLException {          
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceClubEtudiant.fxml"));
            Parent root;
            root = loader.load();
            InterfaceClubEtudiantController irc = loader.getController();
            boutonannuler.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(InterfaceCréerClubController.class.getName()).log(Level.SEVERE, null, ex);
        }         
    }

    @FXML
    private void creerclub(ActionEvent event) throws SQLException {
        try {
            if((textfilednom.getText().equals(""))||(datedecreation.getValue()==null)||(comboboxdomaine.getValue() == null)){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Merci de remplir tous les champs");
            alert.showAndWait();
            }
            else{
            // Besoin de l'id d'utilisateur
            ServiceClub ps = new ServiceClub();
            ps.AjouterClub(new Club(textfilednom.getText(), datedecreation.getValue().toString(), comboboxdomaine.getValue(), (int) fraisinscription.getValue()),5);
            
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Succes");
            alert.setHeaderText(null);
            alert.setContentText("votre demande de création du club a été bien enregistrée");
            alert.showAndWait();
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceClubEtudiant.fxml"));
            Parent root;
            root = loader.load();
            InterfaceClubEtudiantController irc = loader.getController();
            boutoncréer.getScene().setRoot(root);
            }
        } catch (IOException ex) {
            Logger.getLogger(InterfaceCréerClubController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
