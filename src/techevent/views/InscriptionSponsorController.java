/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techevent.views;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
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
import techevent.entities.Sponsor;
import techevent.services.ServiceEtudiant;
import techevent.services.ServiceSponsor;

/**
 * FXML Controller class
 *
 * @author Ciro
 */
public class InscriptionSponsorController implements Initializable {

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
    private JFXTextField entreprise;
    File f;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void choisirimage(ActionEvent event) {
        FileChooser fileChooserr = new FileChooser();
        fileChooserr.setTitle("Select PDF files");
        fileChooserr.setInitialDirectory(new File("C:"));
        FileChooser.ExtensionFilter imageFilter
                = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
        fileChooserr.getExtensionFilters().add(imageFilter);
        f = fileChooserr.showOpenDialog(img.getScene().getWindow());
        Image image = new Image(f.toURI().toString());
        img.setImage(image);
    }

    @FXML
    private void ajouter(ActionEvent event) throws IOException {
        Sponsor e = new Sponsor();
        e.setNomentreprise(entreprise.getText());
        e.setEmail(mail.getText());
        e.setNom(nom.getText());
        e.setPrenom(prenom.getText());
        e.setDatedenaissance(Date.valueOf(datedenaissance.getValue()));
        e.setNumerotelephone(Integer.parseInt(numtel.getText()));
        e.setMotpasse(motdepasse.getText());
        ServiceSponsor se = new ServiceSponsor();
        se.ajouterSponsor(e);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succes");
        alert.setHeaderText(null);
        alert.setContentText("!!!  Inscription effectue avec succes !!!");
        alert.showAndWait();
        FXMLLoader loader = new FXMLLoader();
        mail.getScene().getWindow().hide();
        Stage prStage = new Stage();
        loader.setLocation(getClass().getResource("login.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        prStage.setScene(scene);
        prStage.setResizable(false);
        prStage.show();
    }

}
