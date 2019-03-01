/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techevent.views;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.sun.prism.impl.PrismTrace;
import java.io.File;
import java.io.IOException;
import techevent.entities.Projet;
import techevent.entities.Enseignant;
import techevent.entities.Club;
import techevent.entities.Sponsor;
import techevent.services.ServiceProjet;
import java.net.URL;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Wael
 */
public class CreerprojetController implements Initializable {

    private ProgressBar progress;

    @FXML
    private Slider slider;      
    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextArea desription;
    @FXML
    private JFXComboBox<String> Domaine;
    @FXML
    private JFXCheckBox checkspon;
    @FXML
    Label cbenseignant;
    @FXML
    private JFXButton importer;
    private VBox vbox;
    @FXML
    private JFXListView<String> listeview;
    @FXML
    private AnchorPane projet;
    @FXML
    private ImageView img;
   int idf1;
   File file;

    @FXML
    private void ajouterprojetenseignant(ActionEvent event) throws IOException {
      
                FXMLLoader loader = new FXMLLoader();
                slider.getScene().getWindow().hide();  
                Stage prStage =new Stage(); 
                loader.setLocation(getClass().getResource("listeprojetenseignant.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                ListeprojetenseignantController l = loader.getController();
                System.out.println(idf1);
                l.initData(idf1,file);
                prStage.setScene(scene);
                prStage.setResizable(false);
                prStage.show();
                
        }
        
        
    
  
 private static void showError( String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setContentText(message);

        alert.showAndWait();
    }
 private static void showSuccess( String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setContentText(message);

        alert.showAndWait();
    }
   /* @FXML
    private void importer(ActionEvent event) {
       FileChooser fileChooser = new FileChooser();
       File selectedFile = fileChooser.showOpenDialog(savedStage);
    }*/
ObservableList<String> list = FXCollections.observableArrayList("Informatique","Scientifique","Robotique","Technologique","Autre..");

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Domaine.setItems(list);
        slider.setValue(0);
    }    
 
    @FXML
    private void importermedia(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("All Files", "*.*")       
        
        );
 File selecFile = fc.showOpenDialog(null);
        if (selecFile != null){
             listeview.getItems().add(selecFile.getAbsolutePath());}
        
        else {
            System.out.println("fichier non valide");
        }
    }

    @FXML
    private void annuler(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("listeprojetenseignant.fxml"));
       this.projet.getChildren().setAll(pane);
    }

 void initData(int idf,File file) {
        idf1=idf;
        this.file=file;
        
     
 }
     

    
}
