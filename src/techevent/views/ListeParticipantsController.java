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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import techevent.entities.Etudiant;
import techevent.entities.Evenement;
import techevent.services.ServiceEvenement;

/**
 * FXML Controller class
 *
 * @author Ciro
 */
public class ListeParticipantsController implements Initializable {

    @FXML
    private TableColumn<Etudiant, String> nomid;
    @FXML
    private TableColumn<Etudiant, String> prenomid;
    @FXML
    private TableColumn<Etudiant, String> classid;
    @FXML
    private TableColumn<Etudiant, String> adresseid;
    @FXML
    private TableColumn<Etudiant, Double> telephone;
    @FXML
    private JFXButton backid;
    @FXML
    private TableView<Etudiant> tab;
    /*prenom ; 
	private Date datedenaissance;
	private String email;
	private String motpasse;
	private long  numerotelephone*/

    /**
     * Initializes the controller class.
     */
     int idf; 
    File file ; 
    String a1;
    String a2; 
    String a3 ; 
    String a4 ;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
  
    }
        
    
    
       

    @FXML
    private void back(ActionEvent event) throws IOException {
      backid.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("acceuilevenmnt.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            AcceuilevenmntController mc = loader.getController();
            mc.initData(idf,file,a1,a2,a3,a4);
            Stage prStage = new Stage();
            prStage.setScene(scene);
            prStage.setResizable(false);
            prStage.show();
    }

    void initData(int id, int idf, File file, String a1, String a2, String a3, String a4) throws SQLException {
      this.idf=idf;
        this.file=file;
        this.a1=a1;
        this.a2=a2;
        this.a3=a3;
        this.a4=a4;
        try {
            ServiceEvenement se=new ServiceEvenement();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("acceuilevenmnt.fxml"));
            Parent root = loader.load();
            AcceuilevenmntController irc = loader.getController();
            ObservableList oblist=FXCollections.observableArrayList(se.getAllParticipantbyEvenId(id));
            nomid.setCellValueFactory(new PropertyValueFactory<>("nom"));
            prenomid.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            classid.setCellValueFactory(new PropertyValueFactory<>("classe"));
            adresseid.setCellValueFactory(new PropertyValueFactory<>("email"));
            telephone.setCellValueFactory(new PropertyValueFactory<>("numerotelephone"));
            tab.setItems(oblist);
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(ListeParticipantsController.class.getName()).log(Level.SEVERE, null, ex);
       
    
        
    }    }
    
}
