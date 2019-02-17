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
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Taboubi
 */
public class InterfaceOffreController implements Initializable {

    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button button3;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AfficherTousLesOffres(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/InterfaceOffre1.fxml"));
        try {
            Parent root = loader.load();
            InterfaceOffre1Controller ioff = loader.getController();
            button1.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(InterfaceOffre5Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Offrir(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/InterfaceOffre5.fxml"));
        try {
            Parent root = loader.load();
            InterfaceOffre5Controller ioff = loader.getController();
            button2.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(InterfaceOffre5Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Demander(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/InterfaceOffre4.fxml"));
        try {
            Parent root = loader.load();
            InterfaceOffre4Controller ioff = loader.getController();
            button3.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(InterfaceOffre5Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
