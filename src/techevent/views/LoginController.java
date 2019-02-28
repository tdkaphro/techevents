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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
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
    private void SeConnecter(ActionEvent event) throws IOException, SQLException {
        ServiceUser su = new ServiceUser();
        String type;
        int idf ;
        String user = t1.getText();
        String mdp = t2.getText();
        if (su.Login(user, mdp)) {
            ResultSet  rs = su.TypeUser(user, mdp);
            rs.next();
            type = rs.getString("Dtype");
            idf = rs.getInt("id");
            if (type.equals("Sponsor")) {
               FXMLLoader loader = new FXMLLoader();
                label.getScene().getWindow().hide();  
                Stage prStage =new Stage(); 
                loader.setLocation(getClass().getResource("accueilformateur.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                AccueilformateurController mc = loader.getController();
                mc.initData(idf);
                prStage.setScene(scene);
                prStage.setResizable(false);
                prStage.show();
            }
            if (type.equals("Etudiant")) {
              FXMLLoader loader = new FXMLLoader();
                label.getScene().getWindow().hide();  
                Stage prStage =new Stage(); 
                loader.setLocation(getClass().getResource("accueiletudiant.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                AccueiletudiantController mc = loader.getController();
                mc.initData(idf);
                prStage.setScene(scene);
                prStage.setResizable(false);
                prStage.show();

            }
            if (type.equals("Formateur")) {
                FXMLLoader loader = new FXMLLoader();
                label.getScene().getWindow().hide();  
                Stage prStage =new Stage(); 
                loader.setLocation(getClass().getResource("accueilformateur.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                AccueilformateurController mc = loader.getController();
                mc.initData(idf);
                prStage.setScene(scene);
                prStage.setResizable(false);
                prStage.show();

            }
            if (type.equals("Enseignant")) {
               FXMLLoader loader = new FXMLLoader();
                label.getScene().getWindow().hide();  
                Stage prStage =new Stage(); 
                loader.setLocation(getClass().getResource("accueilformateur.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                AccueilformateurController mc = loader.getController();
                mc.initData(idf);
                prStage.setScene(scene);
                prStage.setResizable(false);
                prStage.show();

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
    private void Sinscrire(ActionEvent event) throws IOException {
                FXMLLoader loader = new FXMLLoader();
                label.getScene().getWindow().hide();  
                Stage prStage =new Stage(); 
                loader.setLocation(getClass().getResource("creercompte.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                prStage.setScene(scene);
                prStage.setResizable(false);
                prStage.show();
    }

}
