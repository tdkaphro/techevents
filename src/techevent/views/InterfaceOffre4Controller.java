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
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import techevent.entities.Offre;
import techevent.services.ServiceOffre;

/**
 * FXML Controller class
 *
 * @author Taboubi
 */
public class InterfaceOffre4Controller implements Initializable {

    @FXML
    private TextField idevenem;
    @FXML
    private TextField prix1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Demander(ActionEvent event) {
        int even=Integer.parseInt(idevenem.getText());
        int p=Integer.parseInt(prix1.getText());
        Offre o=new Offre();
        o.setPrix(p);
        ServiceOffre so=new ServiceOffre();
        so.DemanderOffre(o, even);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message");
        alert.setHeaderText(null);
        alert.setContentText("!!!  Succes !!!");
        alert.showAndWait();
    }

    @FXML
    private void Retour(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/InterfaceOffre.fxml"));
        try {
            Parent root = loader.load();
            InterfaceOffreController ioff = loader.getController();
            idevenem.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(InterfaceOffre5Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
