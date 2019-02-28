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
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import techevent.entities.Enseignant;
import techevent.entities.Etudiant;
import techevent.entities.Universite;
import techevent.services.ServiceEnseignant;
import techevent.services.ServiceEtudiant;

/**
 * FXML Controller class
 *
 * @author Ciro
 */
public class InscriptionEtudiantController implements Initializable {

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
    private JFXComboBox<Universite> universite;
    @FXML
    private JFXTextField classe;
    File file;
    Universite esprit;
    ObservableList<Universite> univer = FXCollections.observableArrayList(esprit);

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        universite.setItems(univer);
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
        if (nom.getText().equals("") || datedenaissance.getValue() == null || prenom.getText() == null || classe.getText().equals("") || mail.getText() == null || numtel.getText() == null || motdepasse.getText() == null || universite.getValue() == null) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Merci de remplir tous les champs");
            alert.showAndWait();
        } else if (numtel.getText().matches("[0-9]+")) {

            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setTitle("Erreur");
            alert2.setHeaderText(null);
            alert2.setContentText("le champ numéro de téléphone doit avoir seulement des numéro");
            alert2.showAndWait();
        } else {
            Etudiant etudiant = new Etudiant();
            etudiant.setNom(nom.getText());
            etudiant.setPrenom(nom.getText());
            etudiant.setEmail(mail.getText());
            etudiant.setMotpasse(motdepasse.getText());

            //etudiant.getClasse(classe.getText());
            etudiant.setPicture(file.toString());
            etudiant.setUniversite(universite.getValue());
            etudiant.setPicture(file.toString());
            etudiant.setDatedenaissance(Date.valueOf(datedenaissance.getValue()));
            etudiant.setNumerotelephone(Integer.parseInt(numtel.getText()));
            ServiceEtudiant see = new ServiceEtudiant();
            see.AjouterEtudiant(etudiant);
            FXMLLoader loader = new FXMLLoader();
            img.getScene().getWindow().hide();
            Stage prStage = new Stage();
            loader.setLocation(getClass().getResource("login.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            prStage.setScene(scene);
            prStage.setResizable(false);
            prStage.show();

        }

    }
}
