package techevent.views;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import techevent.views.AccueilformateurController;

public class creercomptecontroller {


    @FXML
    private Label label;

    @FXML
    private JFXButton enseignant;

    @FXML
    private JFXButton formateur;

    @FXML
    private JFXButton sponsor;
    @FXML
    private JFXButton enseignant1;

    @FXML
    void enseignantinscri(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
                label.getScene().getWindow().hide();  
                Stage prStage =new Stage(); 
                loader.setLocation(getClass().getResource("inscriptionenseignant.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                prStage.setScene(scene);
                prStage.setResizable(false);
                prStage.show();
            
    }

   

    @FXML
    void formateurinscri(ActionEvent event) throws IOException {
                 FXMLLoader loader = new FXMLLoader();
                label.getScene().getWindow().hide();  
                Stage prStage =new Stage(); 
                loader.setLocation(getClass().getResource("inscriformateur.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                prStage.setScene(scene);
                prStage.setResizable(false);
                prStage.show();
    }

    @FXML
    void sponsorinscri(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
                label.getScene().getWindow().hide();  
                Stage prStage =new Stage(); 
                loader.setLocation(getClass().getResource("inscriptionsponsor.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                prStage.setScene(scene);
                prStage.setResizable(false);
                prStage.show();

    }

    @FXML
    private void etudiantinscri(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
                label.getScene().getWindow().hide();  
                Stage prStage =new Stage(); 
                loader.setLocation(getClass().getResource("inscriptionetudiant.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                prStage.setScene(scene);
                prStage.setResizable(false);
                prStage.show();
    }

}
