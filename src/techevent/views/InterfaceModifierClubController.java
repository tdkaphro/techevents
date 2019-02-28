/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techevent.views;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import techevent.entities.Club;
import techevent.services.ServiceClub;
import techevent.services.ServiceEtudiant;

/**
 * FXML Controller class
 *
 * @author Hannachi
 */
public class InterfaceModifierClubController implements Initializable {

    private Button bountonannuler;
    @FXML
    private Button boutonconfirmer;
    @FXML
    private TextField nom;
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
    private Button boutonannuler;
    @FXML
    private JFXTextField president;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            comboboxdomaine.setItems(domaine);
            ServiceClub sc = new ServiceClub();
            ResultSet rs=sc.afficherClubParPresident(5);
            nom.setText(rs.getString(6));
            fraisinscription.setValue(rs.getDouble(5));
            comboboxdomaine.setValue(rs.getString(4));
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceModifierClubController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }    

    @FXML
    private void annuler(ActionEvent event) {
        
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceClubPresident.fxml"));
            Parent root;
            root = loader.load();
            InterfaceClubPresidentController irc = loader.getController();
            boutonannuler.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(InterfaceClubPresidentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void confirmer(ActionEvent event) throws SQLException {
         try {
            ServiceClub cl = new ServiceClub();
            ServiceClub cl2=new ServiceClub();
            if(president.getText().equals("")){
            cl.ModifierClub(5, comboboxdomaine.getValue(),nom.getText(), (int) fraisinscription.getValue()); // id d'etudiant
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succes");
            alert.setHeaderText(null);
            alert.setContentText("Votre club a été bien modifié");
            alert.showAndWait();
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceClubPresident.fxml"));
            Parent root;
            root = loader.load();
            InterfaceClubPresidentController irc = loader.getController();
            boutonconfirmer.getScene().setRoot(root);
            
            }
            else {
            ServiceEtudiant se= new ServiceEtudiant();
            if(! se.testerEmail(5, president.getText())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Adresse Invalide");
            alert.setContentText("Merci de choisir une adresse email correcte correspondante à un étudiant dans votre université ");
            alert.showAndWait();
            }
            else{
            ServiceClub cl3=new ServiceClub();
            ServiceClub cl4=new ServiceClub();
            cl3.ModifierClub(5, comboboxdomaine.getValue(),nom.getText(), (int) fraisinscription.getValue()); // id d'etudiant
            cl4.changerPresident(5, president.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succes");
            alert.setHeaderText(null);
            alert.setContentText("Votre club a été bien modifié ansi que le président ");
            alert.showAndWait();
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceClubEtudiant.fxml"));
            Parent root;
            root = loader.load();
            InterfaceClubEtudiantController irc = loader.getController();
            boutonconfirmer.getScene().setRoot(root);
            }     
          }
        } catch (IOException ex) {
            Logger.getLogger(InterfaceCréerClubController.class.getName()).log(Level.SEVERE, null, ex);
        }
      }     
    }
    

