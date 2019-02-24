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
import techevent.services.ServiceFormateur;
import techevent.services.ServiceFormation;
import techevent.services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author theboy
 */
public class AccueilformateurController implements Initializable {

    @FXML
    private Label role;

    @FXML
    private Label nom;

    @FXML
    private Label prenom;

    @FXML
    private Label datenaiss;

    @FXML
    private Label telephone;

    @FXML
    private Label mail1;

    

    @FXML
    private ImageView img;
    @FXML
    private int id1;
    void MesFormations(ActionEvent event) {
         
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ServiceFormateur sf = new ServiceFormateur();
        sf.afficherFormateur(id1);
    }

    void initData(int id) {
        id1=id;    
                }

   


}
