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
import javafx.scene.control.TextField;
import techevent.entities.Offre;
import techevent.services.ServiceOffre;

/**
 * FXML Controller class
 *
 * @author Taboubi
 */
public class InterfaceOffre2Controller implements Initializable {

    @FXML
    private TextField idoffre;
    @FXML
    private TextField ideven;
    @FXML
    private TextField idspons;
    @FXML
    private TextField prix;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ModifierOffre(ActionEvent event) {
       
    }

    @FXML
    private void Retour(ActionEvent event) {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/InterfaceOffre1.fxml"));
        try {
            Parent root = loader.load();
            InterfaceOffre1Controller ioff = loader.getController();
            idspons.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(InterfaceOffre5Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
