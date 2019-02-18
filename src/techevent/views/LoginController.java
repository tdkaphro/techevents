/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techevent.views;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import techevent.services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author Taboubi
 */
public class LoginController implements Initializable {

    @FXML
    private AnchorPane login;
    @FXML
    private JFXTextField t1;
    @FXML
    private JFXPasswordField t2;
    @FXML
    private JFXButton b1;
    @FXML
    private Label label;
    @FXML
    private JFXButton b11;
    
    public String mail (){
        return t1.getText();
    }
    
    public String mdp (){
        return t2.getText();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void SeConnecter(ActionEvent event) {
        ServiceUser su = new ServiceUser();
        String type;
        String user = t1.getText();
        String mdp = t2.getText();
        if (su.Login(user, mdp)) {
            type = su.TypeUser(user, mdp);
            if (type.equals("Sponsor")) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/accueilsponsor.fxml"));
                try {
                    Parent root = loader.load();
                    AccueilsponsorController ioff = loader.getController();
                    b1.getScene().setRoot(root);
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (type.equals("Etudiant")) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/accueiletudiant.fxml"));
                try {
                    Parent root = loader.load();
                    AccueiletudiantController ioff = loader.getController();
                    b1.getScene().setRoot(root);
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            if (type.equals("Formateur")) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/accueilformateur.fxml"));
                try {
                    Parent root = loader.load();
                    AccueilformateurController ioff = loader.getController();
                    b1.getScene().setRoot(root);
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            if (type.equals("Enseignant")) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/accueilenseignant.fxml"));
                try {
                    Parent root = loader.load();
                    AccueilenseignantController ioff = loader.getController();
                    b1.getScene().setRoot(root);
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("!!!  Verifiez Vos Coordonnes !!!");
            alert.showAndWait();
        }
    }
    
    

    @FXML
    private void MotpassOubliee(ActionEvent event) {
    }

    @FXML
    private void exit(MouseEvent event) {
    }

    @FXML
    private void inscription(MouseEvent event) {
    }

    @FXML
    private void Sinscrire(ActionEvent event) {
    }

}
