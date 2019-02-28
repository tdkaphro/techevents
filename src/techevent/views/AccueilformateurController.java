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
import java.sql.Date;
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
import javafx.stage.Stage;
import techevent.entities.Formateur;
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
    private JFXButton deco;
    @FXML
    private JFXButton formation;
    @FXML
    private ImageView img;
   
            int idf;
    @FXML
    void MesFormations(ActionEvent event) throws IOException {
                FXMLLoader loader = new FXMLLoader();
                img.getScene().getWindow().hide();  
                Stage prStage =new Stage(); 
                loader.setLocation(getClass().getResource("formateurformation.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                formateurformationcontroller mc = loader.getController();
                mc.initData(idf);
                prStage.setScene(scene);
                prStage.setResizable(false);
                prStage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
              
    }

    void initData(int idf) {
        this.idf=idf;
        ServiceFormateur sf = new ServiceFormateur();
        Formateur f = sf.afficherFormateur(idf);
        nom.setText(f.getNom());
        prenom.setText(f.getPrenom());
        datenaiss.setText(String.valueOf(f.getDatedenaissance()));
        mail1.setText(f.getEmail());
        telephone.setText(String.valueOf(f.getNumerotelephone()));
        File file ;
        file = new File(f.getPicture());
        Image image = new Image(file.toURI().toString(),142,145,false,false);
        img.setImage(image);
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

 
    

   


}
