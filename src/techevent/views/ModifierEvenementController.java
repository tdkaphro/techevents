/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techevent.views;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import techevent.entities.Evenement;
import techevent.images.ServiceEvenement;

/**
 * FXML Controller class
 *
 * @author Ciro
 */
public class ModifierEvenementController implements Initializable {

    @FXML
    private JFXTextField nomid;
    @FXML
    private JFXTextArea descriptionid;
    @FXML
    private JFXComboBox<String> typeid;    
    @FXML
    private JFXTextField autretypeid;
    @FXML
    private Label descid;
    @FXML
    private JFXButton retour;
    @FXML
    private JFXButton miseajour;
    @FXML
    private JFXComboBox<String> financeid;
    @FXML
    private JFXTextField capacite;
    @FXML
    private Label sponsorisélab1;
   
    @FXML
    private JFXSlider prixid;

    /**
     * Initializes the controller class.
     */
    // List des domaines
    ObservableList<String> list = FXCollections.observableArrayList("Physiques/Sportives", "Culturelles", "Gala", "Communautaires", "Autre..");
    // liste etat finnance
    ObservableList<String> listf = FXCollections.observableArrayList("Payant", "Non Payant");
    @FXML
    private JFXTextField local;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        typeid.setItems(list);
        financeid.setItems(listf);
        
    }    

    @FXML
    private void keyevent(KeyEvent event) {
        descid.setText(String.valueOf(255-descriptionid.getText().length()-1)+ "characteres");
        if (descriptionid.getText().length()>255){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Avertissement");
            alert.setHeaderText("Erreur de Description");
            alert.setContentText("Vous ne pouvez pas depasser 255 caractéres");
            alert.showAndWait();            
        }
    }


    @FXML
    private void passerautre(ActionEvent event) {
        if (typeid.getValue().equals("Autre.."))
        autretypeid.setDisable(false);
    }

    
    

    @FXML
    private void retourmenu(ActionEvent event) throws IOException {
        sponsorisélab1.getScene().getWindow().hide();
        Stage prStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("acceuilevenmnt.fxml"));
        Scene scene = new Scene(root);
        prStage.setScene(scene);
        prStage.setResizable(false);
        prStage.show();
    }

  

    @FXML
    private void miseajour(ActionEvent event) throws IOException {
        
        ServiceEvenement se = new ServiceEvenement();
        
        Evenement e = new Evenement();
        
        e.setNom(nomid.getText());
        e.setDescription(descriptionid.getText());
       // e.setDateorganisation(Date.valueOf(dateid.getValue()));
        e.setType(typeid.getValue());
        e.setCapacite(Integer.parseInt(capacite.getText()));
        e.setPrix(prixid.getValue());
        e.setEtatdefinancement(financeid.getValue());
        e.setLocalisation(local.getText());

        if (financeid.getValue().equals("Payant")){
            e.setPrix(prixid.getValue());}   
        
        if (e.getType().equals("Autre..")) { 
            e.setSoustypeautre(autretypeid.getText());
         e.setType(autretypeid.getText());
            
        }
        
        se.modifierEvenement(e,30);
        
        
            
        descid.getScene().getWindow().hide();
        Stage prStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("acceuilevenmnt.fxml"));
        Scene scene = new Scene(root);
        prStage.setScene(scene);
        prStage.setResizable(false);
        prStage.show();
        
             descid.getScene().getWindow().hide();
             
       
        
        
            
            
        
        
    }

    

    void initData(int id) throws SQLException {
        ServiceEvenement se = new ServiceEvenement();
        ResultSet rs = se.afficherEvenementparEtudiant(id);
        rs.next();
        //dateid.setValue(rs.getDate("DATEORGANISATION").toLocalDate());
        nomid.setText(rs.getString("NOM"));
        descriptionid.setText(rs.getString("DESCRIPTION"));
        capacite.setText(rs.getString("CAPACITER"));
        if(rs.getString("type").equals("Autre..")){
            typeid.setValue("Autre.."); 
            autretypeid.setText(rs.getString("soustype"));
        }
        else{
            typeid.setValue(rs.getString("TYPE")); 
            autretypeid.setText("");
        }
        
        prixid.setValue(rs.getInt("PRIX"));
        financeid.setValue(rs.getString("PAYANT")); 
        local.setText(rs.getString("LOCALISATION"));
        
      
        
    }


   
  
    
}
