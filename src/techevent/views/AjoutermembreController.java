/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techevent.views;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
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
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import techevent.entities.Enseignant;
import techevent.entities.Projet;
import techevent.entities.User;
import techevent.services.ServiceProjet;

/**
 * FXML Controller class
 *
 * @author Wael
 */
public class AjoutermembreController implements Initializable {

    @FXML
    private JFXButton b1;
    @FXML
    private JFXButton b11;
    @FXML
    private JFXTextField email;
    @FXML
    private ListView<String> listemembre;
    @FXML
    private AnchorPane cre;
File file;
int idf1;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    private static void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);

        alert.showAndWait();
    }

    @FXML
    private void creerprojet(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("listeprojetenseignant.fxml"));
        this.cre.getChildren().setAll(pane); 
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succes");
        alert.setHeaderText(null);
        alert.setContentText("!!!  Vos membres sont ajoutes !!!");
        alert.showAndWait();

    }

    @FXML
    private void ajoutermembre(ActionEvent event) throws IOException {
        ServiceProjet sp = new ServiceProjet();
        String email1= email.getText();
        int b = sp.getIdbyMail(email.getText());
        System.out.println(b);
      //if (email1.matches("^[A-Za-z0-9_.]+[@][A-Za-z.]+$")) {
              FXMLLoader loader = new FXMLLoader();
                cre.getScene().getWindow().hide();  
                Stage prStage =new Stage(); 
                loader.setLocation(getClass().getResource("listeprojetenseignant.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                ListeprojetenseignantController l = loader.getController();
                 l.initData(idf1,file);
                prStage.setScene(scene);
                prStage.setResizable(false);
                prStage.show();
            ListeprojetenseignantController irc = loader.getController();
            listemembre.getItems().addAll(email1);
             sp.ajoutermembre(irc.id, b, sp.getEnsIdbyProjetId(irc.id));
               this.email.clear();
    //  } else {
        //    showError("email non valide");

        //}

    }

    @FXML
    private void supprmier(ActionEvent event) {
        ServiceProjet sp = new ServiceProjet();
        sp.supprimermembre(sp.getIdbyMail(listemembre.getSelectionModel().getSelectedItem()));
        listemembre.getItems().removeAll(
                listemembre.getSelectionModel().getSelectedItems()
        );;
        listemembre.refresh();
    }

    @FXML
    private void annuler(ActionEvent event) throws IOException {
 AnchorPane pane = FXMLLoader.load(getClass().getResource("listeprojetenseignant.fxml"));
        this.cre.getChildren().setAll(pane);         
    }

    void initData(int idf, File file) {
     idf=idf1;
        this.file=file;
    }
}
