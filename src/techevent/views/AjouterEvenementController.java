/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techevent.views;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import jdk.nashorn.internal.ir.BreakNode;
import techevent.entities.Evenement;
import techevent.entities.Sponsor;
import techevent.services.ServiceClub;
import techevent.services.ServiceEvenement;

/**
 * FXML Controller class
 *
 * @author Ciro
 */
public class AjouterEvenementController implements Initializable {

    @FXML
    private JFXTextField nomid;
    @FXML
    private JFXDatePicker dateid;
    @FXML
    private JFXTextArea descriptionid;
    @FXML
    private JFXButton ajouterid;
    @FXML
    private JFXComboBox<String> typeid;
    private Label sponsorisélab;

    @FXML
    private JFXTextField autretypeid;
    @FXML
    private Label descid;
    @FXML
    private JFXButton retour;
    @FXML
    private JFXButton reeset;
    @FXML
    private Label sponsorisélab1;

    @FXML
    private JFXSlider prixid;
    @FXML
    private JFXComboBox<String> financeid;
    @FXML
    private JFXTextField capacite;
    int idf; 
    File file ; 
    String a1;
    String a2; 
    String a3 ; 
    String a4 ; 
    

    /**
     * Initializes the controller class.
     */
    // List des domaines
    ObservableList<String> list = FXCollections.observableArrayList("Physiques/Sportives", "Culturelles", "Gala", "Communautaires", "Autre..");
    // liste etat finnance
    ObservableList<String> listf = FXCollections.observableArrayList("Payant", "Non Payant");

    @FXML
    private JFXTextField local;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        prixid.setDisable(true);
        autretypeid.setDisable(true);
        typeid.setItems(list);
        financeid.setItems(listf);

    }

    @FXML
    private void keyevent(KeyEvent event) {
        descid.setText(String.valueOf(255 - descriptionid.getText().length() - 1) + "characteres");
        if (descriptionid.getText().length() > 255) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Avertissement");
            alert.setHeaderText("Erreur de Description");
            alert.setContentText("Vous ne pouvez pas depasser 255 caractéres");
            alert.showAndWait();
        }
    }

    @FXML
    private void ajouter(ActionEvent event) throws IOException, SQLException {
        ServiceEvenement se = new ServiceEvenement();
        if (nomid.getText().equals("") || dateid.getValue() == null || typeid.getValue() == null || local.getText().equals("") || capacite.getText() == null || typeid.getValue() == null) {

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Merci de remplir tous les champs");
            alert.showAndWait();
        } else if (dateid.getValue().toEpochDay() - LocalDate.now().toEpochDay() < 0) {

            Alert alert2 = new Alert(AlertType.INFORMATION);
            alert2.setTitle("Erreur");
            alert2.setHeaderText(null);
            alert2.setContentText("Vous ne pouvez pas choisir une date inférieur");
            alert2.showAndWait();
        }  else {
            Evenement a = new Evenement();

            a.setNom(nomid.getText());
            a.setEtatdefinancement(financeid.getValue());
            a.setLocalisation(local.getText());
            a.setDateorganisation(Date.valueOf(dateid.getValue()));
            a.setCapacite(Integer.parseInt(capacite.getText()));
            a.setType(typeid.getValue());
            a.setDescription(descriptionid.getText());

            if (financeid.getValue().equals("Payant")) {
                a.setPrix(prixid.getValue());
            }   
              ServiceClub sc = new ServiceClub();
            if (a.getType().equals("Autre..")) {
                a.setSoustypeautre(autretypeid.getText());
                a.setType(autretypeid.getText());
                se.ajouterevenementdeclub2(a, sc.getMonClubId(idf));
                
            } else {
                a.setType(typeid.getValue());
                se.ajouterevenementdeclub(a, sc.getMonClubId(idf));
            }

            descid.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("acceuilevenmnt.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            AcceuilevenmntController mc = loader.getController();
            mc.initData(idf,file,a1,a2,a3,a4);
            Stage prStage = new Stage();
            prStage.setScene(scene);
            prStage.setResizable(false);
            prStage.show();
    }
    }
        

    @FXML
    private void passerautre(ActionEvent event
    ) {
        if (typeid.getValue().equals("Autre..")) {
            autretypeid.setDisable(false);
        }

    }

    @FXML
    private void retourmenu(ActionEvent event) throws IOException {
     descid.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("acceuilevenmnt.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            AcceuilevenmntController mc = loader.getController();
            mc.initData(idf,file,a1,a2,a3,a4);
            Stage prStage = new Stage();
            prStage.setScene(scene);
            prStage.setResizable(false);
            prStage.show();
    }

    @FXML
    private void reset(ActionEvent event
    ) {

        nomid.clear();
        descriptionid.clear();
        autretypeid.clear();
        typeid.setValue("");
        financeid.setValue("");
        capacite.clear();
        local.clear();
        prixid.setValue(0);

    }

    @FXML
    private void pay(ActionEvent event
    ) {
        if (financeid.getValue().equals("Payant")) {
            prixid.setDisable(false);
        }
        if (financeid.getValue().equals("Non Payant")) {
            prixid.setDisable(true);
        }
    }

    void initData(int idf, File file, String a1, String a2, String a3, String a4) {
          this.idf=idf;
        this.file=file;
        this.a1=a1;
        this.a2=a2;
        this.a3=a3;
        this.a4=a4;
    }

     
}
