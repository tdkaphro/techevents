/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techevent.views;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import techevent.entities.Enseignant;
import techevent.entities.Sponsor;
import techevent.entities.Universite;
import techevent.services.ServiceEnseignant;
import techevent.services.ServiceSponsor;

/**
 * FXML Controller class
 *
 * @author Ciro
 */
public class InscriptionEnseignantController implements Initializable {

    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField prenom;
    @FXML
    private JFXPasswordField motdepasse;
    @FXML
    private JFXTextField mail;
    @FXML
    private JFXTextField numtel;
    @FXML
    private JFXDatePicker datedenaissance;
    @FXML
    private JFXButton choisirimg;
    @FXML
    private ImageView img;
    @FXML
    private JFXButton inscrire;
    @FXML
    private JFXComboBox<Universite> université;
    @FXML
    private JFXTextField departementid;
    File file;
    Universite esprit;
    ObservableList<Universite> univer = FXCollections.observableArrayList(esprit);

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        université.setItems(univer);
        
    }    

    @FXML
    private void choisirimage(ActionEvent event) {
         FileChooser fileChooserr = new FileChooser();
fileChooserr.setTitle("Select PDF files");
fileChooserr.setInitialDirectory(new File("C:"));
FileChooser.ExtensionFilter imageFilter
    = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
fileChooserr.getExtensionFilters().add(imageFilter);
file = fileChooserr.showOpenDialog(img.getScene().getWindow());
Image image = new Image(file.toURI().toString());
img.setImage(image);
    }

    @FXML
    private void ajouter(ActionEvent event) throws IOException {
         Enseignant enseigant= new Enseignant();
       enseigant.setNom(nom.getText());
       enseigant.setPrenom(nom.getText());
       enseigant.setEmail(mail.getText());
       enseigant.setMotpasse(motdepasse.getText());
       enseigant.setDepartement(departementid.getText());
       //enseigant.setUniversiteenseignant(université.getValue());
       enseigant.setPicture(file.toString());
       enseigant.setDatedenaissance(Date.valueOf(datedenaissance.getValue()));
       enseigant.setNumerotelephone(Integer.parseInt(numtel.getText()));
        ServiceEnseignant se= new ServiceEnseignant();
        se.ajouterEnseignant(enseigant, 0);
         FXMLLoader loader = new FXMLLoader();
         img.getScene().getWindow().hide();  
                Stage prStage =new Stage(); 
                loader.setLocation(getClass().getResource("login.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                prStage.setScene(scene);
                prStage.setResizable(false);
                prStage.show();
       
    }
    
}
