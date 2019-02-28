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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import techevent.entities.Etudiant;
import techevent.entities.Formateur;
import techevent.services.ServiceEtudiant;
import techevent.services.ServiceFormateur;
import techevent.services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author theboy
 */
public class AccueiletudiantController implements Initializable {
    
    @FXML
    private JFXButton b1;
    @FXML
    private Label nom;
    @FXML
    private Label prenom;
    @FXML
    private Label datenaiss;
    @FXML
    private Label classe;
    @FXML
    private Label mail1;
    @FXML
    private Label telephone;
    @FXML
    private Label role;
    @FXML
    private Label datenaissance;
    int idf;
    File file;
    @FXML
    private ImageView img;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    @FXML
    private void EditProfile(ActionEvent event) {
    }
    
    private void mesevenements(ActionEvent event) throws IOException {
        
    }
    
    @FXML
    private void club(ActionEvent event) throws IOException {
        
    }
    
    @FXML
    private void formation(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        img.getScene().getWindow().hide();        
        Stage prStage = new Stage();        
        loader.setLocation(getClass().getResource("inscriformation.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        inscriformationcontroller mc = loader.getController();
        mc.initData(idf, file);
        prStage.setScene(scene);
        prStage.setResizable(false);
        prStage.show();
    }
    
    @FXML
    private void evenement(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        img.getScene().getWindow().hide();        
        Stage prStage = new Stage();        
        loader.setLocation(getClass().getResource("AcceuilEtudiantEven.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        AcceuilEtudiantEvenController mc = loader.getController();
        mc.initData(idf, file);
        prStage.setScene(scene);
        prStage.setResizable(false);
        prStage.show();
    }
    
    @FXML
    private void projet(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        img.getScene().getWindow().hide();        
        Stage prStage = new Stage();        
        loader.setLocation(getClass().getResource("listeprojetetudiant.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        ListeprojetetudiantController mc = loader.getController();
        mc.initData(idf, file);
        prStage.setScene(scene);
        prStage.setResizable(false);
        prStage.show();
    }
    
    void initData(int idf) {
        this.idf = idf;
        ServiceEtudiant sf = new ServiceEtudiant();
        Etudiant f = sf.afficherEtudiant(idf);
        nom.setText(f.getNom());
        prenom.setText(f.getPrenom());
        datenaiss.setText(String.valueOf(f.getDatedenaissance()));
        mail1.setText(f.getEmail());
        telephone.setText(String.valueOf(f.getNumerotelephone()));
        classe.setText(f.getClasse());
        role.setText("Etudiant");
        file = new File(f.getPicture());
        Image image = new Image(file.toURI().toString(), 142, 145, false, false);
        img.setImage(image);
    }

    void initData(int idf, File file) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
