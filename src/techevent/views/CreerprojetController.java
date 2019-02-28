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
    private void ajouterprojetenseignant(ActionEvent event) throws IOException {
        if (!nom.getText().equals("") && !desription.getText().equals("")){
     Projet projet = new Projet();
     projet.setNom(nom.getText());
     projet.setDescription(desription.getText());
     List<String> a;
     a = listeview.getItems();
     projet.setMedia(a.get(0));
     projet.setProgress((int) Math.round(slider.getValue()));
     projet.setDomaine((String) Domaine.getValue());
     Enseignant en = new Enseignant();
     en.setId(1);
     projet.setEnseignant(en);
     if(!checkspon.isSelected()){
         projet.setEtat(false);
     }else{
         projet.setEtat(true);
     }
    Club cl = new Club();
    cl.setId(1);
    Sponsor s = new Sponsor();
    s.setId(1);
    
    ServiceProjet sp = new ServiceProjet();
   sp.ajouterprojetpourenseignant(1, projet);
    Group root = new Group();
 ScrollBar sc = new ScrollBar();
 sc.setMin(0);
 sc.setOrientation(Orientation.VERTICAL);
 //set other properties
  vbox = new VBox();
 vbox.getChildren();
 //add childrens to Vbox and properties
 root.getChildren().addAll(vbox,sc);
         
  sc.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                Number old_val, Number new_val) {
                    vbox.setLayoutY(-new_val.doubleValue());

            }
        });
      AnchorPane pane = FXMLLoader.load(getClass().getResource("listeprojetenseignant.fxml"));
       this.projet.getChildren().setAll(pane);
            showSuccess("Projet a été créer");
        }
        else{
            showError("Vérifier les champs");
        }
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


     

    
}
