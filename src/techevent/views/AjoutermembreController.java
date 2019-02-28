/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techevent.views;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.layout.AnchorPane;
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
    private void creerprojet(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succes");
        alert.setHeaderText(null);
        alert.setContentText("!!!  Vos membres sont ajoutes !!!");
        alert.showAndWait();

    }

    @FXML
    private void ajoutermembre(ActionEvent event) throws IOException {
        ServiceProjet sp = new ServiceProjet();
        String email = this.email.getText();

        if (email.matches("^[A-Za-z0-9_.]+[@][A-Za-z.]+$")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("listeprojetenseignant.fxml"));
            Parent root = loader.load();
            ListeprojetenseignantController irc = loader.getController();
            listemembre.getItems().addAll(email);
            this.email.clear();
            sp.ajoutermembre(irc.id, sp.getIdbyMail(email), sp.getEnsIdbyProjetId(irc.id));
            System.out.println(irc.id);
            System.out.println(sp.getIdbyMail(email));
            System.out.println(sp.getEnsIdbyProjetId(irc.id));
        } else {
            showError("email non valide");

        }

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
}
