/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techevent.views;

import com.jfoenix.controls.JFXButton;
import java.io.File;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import techevent.entities.Enseignant;
import techevent.entities.Etudiant;
import techevent.services.ServiceEnseignant;
import techevent.services.ServiceEtudiant;

/**
 * FXML Controller class
 *
 * @author Taboubi
 */
public class AccueilenseignantController implements Initializable {

    @FXML
    private ImageView btnlogout;
    @FXML
    private JFXButton btnclub;
    @FXML
    private JFXButton btnformation;
    @FXML
    private JFXButton btnevenement;
    @FXML
    private JFXButton btnprojet;
    @FXML
    private JFXButton b1;
    @FXML
    private Label role;
    @FXML
    private Label nom;
    @FXML
    private Label prenom;
    @FXML
    private Label datenaiss;
    @FXML
    private Label departement;
    @FXML
    private Label telephone;
    @FXML
    private Label mail1;
    int idf;
    @FXML
    private ImageView img;
File file ;
    @FXML
    private AnchorPane creer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
    }    

    @FXML
    private void MesClubs(ActionEvent event) {
    }

    @FXML
    private void MesFormations(ActionEvent event) {
    }

    @FXML
    private void MesEvenements(ActionEvent event) {
    }
void initData(int idf) {
        this.idf=idf;
        ServiceEnseignant en = new ServiceEnseignant();
        Enseignant e = en.afficherEnseignant(idf);
        nom.setText(e.getNom());
        prenom.setText(e.getPrenom());
        datenaiss.setText(String.valueOf(e.getDatedenaissance()));
        mail1.setText(e.getEmail());
        telephone.setText(String.valueOf(e.getNumerotelephone()));
        departement.setText(e.getClasse());
        role.setText("enseignant");
        file = new File(e.getPicture());
        Image image = new Image(file.toURI().toString(),142,145,false,false);
        img.setImage(image);
                        }
    @FXML
    private void MesProjets(ActionEvent event) throws IOException {
      FXMLLoader loader = new FXMLLoader();
                img.getScene().getWindow().hide();  
                Stage prStage =new Stage(); 
                loader.setLocation(getClass().getResource("listeprojetenseignant.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                ListeprojetenseignantController en = loader.getController();
                en.initData(idf,file);
                prStage.setScene(scene);
                prStage.setResizable(false);
                prStage.show();
    }

    

    @FXML
    private void ModifierProfil(ActionEvent event) {
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("login.fxml"));
        this.creer.getChildren().setAll(pane);
    }
    @FXML
    void decoevt(ActionEvent event) throws IOException {
                FXMLLoader loader = new FXMLLoader();
                img.getScene().getWindow().hide();  
                Stage prStage =new Stage(); 
                loader.setLocation(getClass().getResource("login.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                prStage.setScene(scene);
                prStage.setResizable(false);
                prStage.show();
    }

    void initData(int idf, File file) {
this.idf = idf;
this.file = file;
    }

}
