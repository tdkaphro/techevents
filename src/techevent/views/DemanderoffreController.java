/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techevent.views;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.List;
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
import techevent.entities.Offre;
import techevent.services.ServiceEvenement;
import techevent.services.ServiceOffre;
import techevent.services.ServiceUser;
import techevent.views.LoginController;

/**
 * FXML Controller class
 *
 * @author Taboubi
 */
public class DemanderoffreController implements Initializable {

    @FXML
    private JFXTextField prix;
    @FXML
    private JFXComboBox<String> sponsor;
    @FXML
    private JFXButton b12;
    @FXML
    private JFXButton b121;
    @FXML
    private JFXComboBox<String> evenement;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceUser su = new ServiceUser();
        ServiceEvenement se = new ServiceEvenement();
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root = loader.load();
            LoginController irc = loader.getController();
            String mail = irc.mail1;
            String mdp = irc.mdp1;
            List l = su.getAllSponsorNames();
            ObservableList<String> oblist = FXCollections.observableArrayList(l);
            sponsor.setItems(oblist);
            List l1 = se.getEvenementNomIdByPresidentId(su.getId(mail, mdp));
            ObservableList<String> oblist1 = FXCollections.observableArrayList(l1);
            evenement.setItems(oblist1);
        } catch (IOException ex) {
            Logger.getLogger(OffresponsorController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void Demander(ActionEvent event) {
        ServiceOffre so = new ServiceOffre();
        ServiceEvenement se = new ServiceEvenement();
        ServiceUser su = new ServiceUser();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        try {
            Parent root = loader.load();
            LoginController irc = loader.getController();
            String mail = irc.mail();
            String mdp = irc.mdp();
            Offre f = new Offre();
            int p = Integer.valueOf(prix.getText());
            f.setPrix(p);
            so.DemanderOffre(f, se.getIdbyNom(evenement.getValue()), Character.getNumericValue(sponsor.getValue().charAt(0)));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succes");
            alert.setHeaderText(null);
            alert.setContentText("!!!  Demande ajoute avec succes !!!");
            alert.showAndWait();

        } catch (IOException ex) {
            Logger.getLogger(OffriroffreController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void Retour(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("offreevenement.fxml"));
            Parent root = loader.load();
            OffreevenementController irc = loader.getController();
            b12.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(OffresponsorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
