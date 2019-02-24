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
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import techevent.entities.Formateur;
import techevent.services.ServiceFormateur;

public class inscriptionformateurcontroller {

    @FXML
    private JFXButton inscrire;

    @FXML
    private JFXTextField nom;

    @FXML
    private JFXTextField prenom;

    @FXML
    private JFXDatePicker datedenaissance;

    @FXML
    private JFXTextField numtel;

    @FXML
    private JFXPasswordField motdepasse;

    @FXML
    private JFXTextField mail;

    @FXML
    private JFXComboBox<String> cbdomaine;

    @FXML
    private JFXButton choisirimg;

    @FXML
    private ImageView img;
File file ;
    @FXML
    void ajouter(ActionEvent event) {
       Formateur form = new Formateur();
       form.setNom(nom.getText());
       form.setPrenom(nom.getText());
       form.setEmail(mail.getText());
       form.setMotpasse(motdepasse.getText());
       form.setDomaine(cbdomaine.getValue());
       form.setPicture(file.toString());
       form.setDatedenaissance(Date.valueOf(datedenaissance.getValue()));
       form.setNumerotelephone(Integer.parseInt(numtel.getText()));
       ServiceFormateur sf = new ServiceFormateur();
       if(form.getNom()==null||form.getPrenom()==null){
       sf.ajouterFormateur(form);
       }
    }

    @FXML
    void choisirimage(ActionEvent event) {
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
}