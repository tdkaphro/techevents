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
import java.util.ArrayList;
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
import techevent.services.ServiceClub;
import techevent.images.ServiceEvenement;
import techevent.services.ServiceOffre;
import techevent.services.ServiceUser;
import techevent.views.LoginController;

/**
 * FXML Controller class
 *
 * @author Taboubi
 */
public class Modifieroffre2Controller implements Initializable {

    @FXML
    private JFXTextField prix;
    @FXML
    private JFXComboBox<String> evenement;
    @FXML
    private JFXButton b12;
    @FXML
    private JFXButton b121;
    @FXML
    private JFXComboBox<String> sponsor;
    public Offre f = new Offre();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root = loader.load();
            LoginController irc = loader.getController();
            String mail = irc.mail();
            String mdp = irc.mdp();

            ServiceEvenement se = new ServiceEvenement();
            ServiceUser su = new ServiceUser();
            ServiceClub sc = new ServiceClub();
            ServiceOffre so = new ServiceOffre();

            //ArrayList<String> l2=so.findAllEvenementforEvenement(se.getEvenementIdByClubId(sc.getIdClubbyPresidentId(su.getId(mail, mdp))));
            ArrayList<String> l2 = so.findAllEvenementforEvenement(se.getEvenementIdByClubId(sc.getIdClubbyPresidentId(2)));
            ObservableList<String> oblist2 = FXCollections.observableArrayList(l2);
            evenement.setItems(oblist2);

            ArrayList<String> l = su.getAllSponsorNames();
            ObservableList<String> oblist = FXCollections.observableArrayList(l);
            sponsor.setItems(oblist);

            FXMLLoader loader2 = new FXMLLoader(getClass().getResource("offreevenement.fxml"));
            Parent root2 = loader2.load();
            OffreevenementController ofc = loader2.getController();
            prix.setText(String.valueOf(OffreevenementController.offr.getPrix()));
            evenement.setValue(se.getNombyId(OffreevenementController.offr.getIdeven()));
            f.setId(OffreevenementController.offr.getId());
            f.setIdspon(OffreevenementController.offr.getIdspon());
            sponsor.setValue(String.valueOf(OffreevenementController.offr.getIdspon()) + " / " + OffreevenementController.offr.getNomspons() + " " + su.getPrenomById(OffreevenementController.offr.getIdspon()));
        } catch (IOException ex) {
            Logger.getLogger(OffresponsorController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void Modifier(ActionEvent event) {
        ServiceOffre so = new ServiceOffre();
        ServiceEvenement se = new ServiceEvenement();

        so.ModifierOffreEven(f.getId(), Integer.parseInt(prix.getText()), se.getIdbyNom(evenement.getValue()), Character.getNumericValue(sponsor.getValue().charAt(0)));
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succes");
        alert.setHeaderText(null);
        alert.setContentText("!!!  Modification Valide !!!");
        alert.showAndWait();
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
