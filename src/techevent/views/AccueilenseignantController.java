/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techevent.views;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import techevent.services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author Taboubi
 */
public class AccueilenseignantController implements Initializable {

    @FXML
    private ImageView btnlogout;
    @FXML
    private JFXButton btnclub;
    @FXML
    private JFXButton btnformation;
    @FXML
    private JFXButton btnevenement;
    @FXML
    private JFXButton btnprojet;
    @FXML
    private JFXButton b1;
    @FXML
    private Label role;
    @FXML
    private Label nom;
    @FXML
    private Label prenom;
    @FXML
    private Label datenaiss;
    @FXML
    private Label departement;
    @FXML
    private Label telephone;
    @FXML
    private Label mail1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ServiceUser su=new ServiceUser();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/login.fxml"));
            Parent root = loader.load();
            LoginController irc = loader.getController();
            String mail=irc.mail();
            String mdp=irc.mdp();
            nom.setText(su.getNom(mail, mdp));
            prenom.setText(su.getPrenom(mail, mdp));
            datenaiss.setText(su.getDateNaissance(mail, mdp));
            departement.setText(su.getDepartement(mail, mdp));
            mail1.setText(su.getMail(mail, mdp));
            telephone.setText(su.getNumeroTelephone(mail, mdp));
            
       } catch (IOException ex) {
            Logger.getLogger(AccueilsponsorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void MesClubs(ActionEvent event) {
    }

    @FXML
    private void MesFormations(ActionEvent event) {
    }

    @FXML
    private void MesEvenements(ActionEvent event) {
    }

    @FXML
    private void MesProjets(ActionEvent event) {
    }

    @FXML
    private void ModifierProfil(ActionEvent event) {
    }
    
}
