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
import javafx.scene.control.Label;
import techevent.services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author Taboubi
 */
public class AccueilformateurController  {

    @FXML
    private Label role;
    @FXML
    private Label nom;
    @FXML
    private Label prenom;
    @FXML
    private Label datenaiss;
    @FXML
    private Label domaine;
    @FXML
    private Label telephone;
    @FXML
    private Label mail1;

    

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

    

    
}
