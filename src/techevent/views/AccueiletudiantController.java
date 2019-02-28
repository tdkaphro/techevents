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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import techevent.services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author Taboubi
 */
public class AccueiletudiantController implements Initializable {

    @FXML
    private JFXButton b1;
    @FXML
    private Button btnclub;
    @FXML
    private Button btnformation;
    @FXML
    private Button btnevenement;
    @FXML
    private JFXButton btnprojet;
    @FXML
    private JFXButton btnlogout;
    @FXML
    private Label nom;
    @FXML
    private Label prenom;
    @FXML
    private Label datenaiss;
    @FXML
    private Label classe;
    @FXML
    private Label mail1;
    @FXML
    private Label telephone;
    @FXML
    private Label role;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
            ServiceUser su=new ServiceUser();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root = loader.load();
            LoginController irc = loader.getController();
            String mail=irc.mail();
            String mdp=irc.mdp();
            nom.setText(su.getNom(mail, mdp));
            prenom.setText(su.getPrenom(mail, mdp));
            datenaiss.setText(su.getDateNaissance(mail, mdp));
            classe.setText(su.getClasse(mail, mdp));
            mail1.setText(su.getMail(mail, mdp));
            telephone.setText(su.getNumeroTelephone(mail, mdp));
            role.setText(su.getRole(mail, mdp));
            
       } catch (IOException ex) {
            Logger.getLogger(AccueiletudiantController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void EditProfile(ActionEvent event) {
    }

    @FXML
    private void MesProjets(ActionEvent event) {
    }

    @FXML
    private void MesEvenements(ActionEvent event) {
    }

    @FXML
    private void MesFormations(ActionEvent event) {
    }

    @FXML
    private void MesClubs(ActionEvent event) {
    }
    
}
