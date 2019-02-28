/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techevent.views;

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
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import techevent.services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author Taboubi
 */
public class AccueilsponsorController implements Initializable {

    @FXML
    private Label nom;
    @FXML
    private Label prenom;
    @FXML
    private Label datenaiss;
    @FXML
    private Label entre;
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
            entre.setText(su.getEntreprise(mail, mdp));
            mail1.setText(su.getMail(mail, mdp));
            telephone.setText(su.getNumeroTelephone(mail, mdp));
            
       } catch (IOException ex) {
            Logger.getLogger(AccueilsponsorController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
    }    


  

    @FXML
    private void club(ActionEvent event) {
    }

    @FXML
    private void formation(ActionEvent event) {
    }

    @FXML
    private void evenement(ActionEvent event) throws IOException {
         telephone.getScene().getWindow().hide();
        Stage prStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("offresponsor.fxml"));
        Scene scene = new Scene(root);
        prStage.setScene(scene);
        prStage.setResizable(false);
        prStage.show();
    }

    @FXML
    private void projet(ActionEvent event) {
    }
    
}
