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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Taboubi
 */
public class InterfaceOffre1Controller implements Initializable {

    @FXML
    private TableView<?> tableoffre;
    @FXML
    private TableColumn<?, ?> idoffre;
    @FXML
    private TableColumn<?, ?> prix;
    @FXML
    private TableColumn<?, ?> idspons;
    @FXML
    private TableColumn<?, ?> ideven;
    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button button3;
    @FXML
    private Button button4;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AfficherOffre(ActionEvent event) {
    }

    @FXML
    private void ModifierOffre(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/InterfaceOffre2.fxml"));
        try {
            Parent root = loader.load();
            InterfaceOffre2Controller ioff = loader.getController();
            button2.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(InterfaceOffre5Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    @FXML
    private void SupprimerOffre(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/InterfaceOffre3.fxml"));
        try {
            Parent root = loader.load();
            InterfaceOffre3Controller ioff = loader.getController();
            button3.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(InterfaceOffre5Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Retour(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/InterfaceOffre1.fxml"));
        try {
            Parent root = loader.load();
            InterfaceOffre1Controller ioff = loader.getController();
            button4.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(InterfaceOffre5Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
