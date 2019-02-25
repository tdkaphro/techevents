/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techevents.views;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.ConditionalFeature.FXML;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import techevent.entities.User;
import techevent.services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author Yassine
 */
public class LoginController implements Initializable {

    @FXML
    private JFXTextField t1;
    @FXML
    private JFXPasswordField t2;
    @FXML
    private AnchorPane login;
    @FXML
    private javafx.scene.control.Label label;

    @FXML
    private JFXButton b1;
    @FXML
    private JFXButton b11;
    public static User u1 = new User() {
    };

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void inscription(MouseEvent event) throws IOException {

    }

    @FXML
    private void exit(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void SeConnecter(javafx.event.ActionEvent event) {

        ServiceUser su = new ServiceUser();
        u1.setEmail(t1.getText());
        u1.setMotpasse(t2.getText());
        String email = t1.getText();
        String mdp = t2.getText();
        if (su.Login(email, mdp)) {
            if (su.TypeUser(email, mdp).equals("Etudiant")) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("accueiletudiant.fxml"));
                    Parent root = loader.load();
                    AccueiletudiantController irc = loader.getController();
                    t1.getScene().setRoot(root);
                } catch (IOException ex) {
                    Logger.getLogger(OffresponsorController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (su.TypeUser(email, mdp).equals("Sponsor")) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("accueilsponsor.fxml"));
                    Parent root = loader.load();
                    AccueilsponsorController irc = loader.getController();
                    t1.getScene().setRoot(root);
                } catch (IOException ex) {
                    Logger.getLogger(OffresponsorController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (su.TypeUser(email, mdp).equals("Enseignant")) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("accueilenseignant.fxml"));
                    Parent root = loader.load();
                    AccueilenseignantController irc = loader.getController();
                    t1.getScene().setRoot(root);
                } catch (IOException ex) {
                    Logger.getLogger(OffresponsorController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (su.TypeUser(email, mdp).equals("Formateur")) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("accueilformateur.fxml"));
                    Parent root = loader.load();
                    AccueilformateurController irc = loader.getController();
                    t1.getScene().setRoot(root);
                } catch (IOException ex) {
                    Logger.getLogger(OffresponsorController.class.getName()).log(Level.SEVERE, null, ex);
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

}
