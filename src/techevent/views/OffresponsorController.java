/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techevent.views;

import com.jfoenix.controls.JFXButton;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import techevent.entities.Evenement;
import techevent.entities.Offre;
import techevent.entities.Sponsor;
import techevent.services.ServiceOffre;
import techevent.services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author Taboubi
 */
public class OffresponsorController implements Initializable {

    @FXML
    private JFXButton b1;
    @FXML
    private JFXButton b11;
    @FXML
    private JFXButton b12;
    @FXML
    private TableView<Offre> table;
    @FXML
    private TableColumn<Offre, String> nomeven;
    @FXML
    private TableColumn<Offre, Integer> idoffre;
    @FXML
    private TableColumn<Offre, Integer> ideven;
    @FXML
    private TableColumn<Offre, Integer> prix;
    @FXML
    private TableColumn<Offre, Integer> idspons;
    
     List<Offre> loo = new ArrayList<Offre>() ;
     
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceOffre so=new ServiceOffre();
        ServiceUser su=new ServiceUser();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/login.fxml"));
            Parent root = loader.load();
            LoginController irc = loader.getController();
            String mail=irc.mail();
            String mdp=irc.mdp();
            ResultSet rs=so.getDemandeSponsorisation(mail, mdp);
            while(rs.next()){
                Evenement e=new Evenement();
                e.setId(rs.getInt(3));
                Sponsor s=new Sponsor();
                s.setId(rs.getInt(4));
                Offre of=new Offre();
                of.setId(rs.getInt(1));
                of.setPrix(rs.getInt(2));
                of.setEvenement(e);
                of.setSponsor(s);
                loo.add(of);
            }
            ObservableList<Offre> obs = FXCollections.observableArrayList(loo);
            
            idoffre.setCellValueFactory(new PropertyValueFactory<>("id"));
            prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
            nomeven.setCellValueFactory(new PropertyValueFactory<>("prix"));
            ideven.setCellValueFactory(new PropertyValueFactory<>("prix"));
            idspons.setCellValueFactory(new PropertyValueFactory<>("prix"));
            table.setItems(obs);
            
        } catch (IOException ex) {
            Logger.getLogger(OffresponsorController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(OffresponsorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            
    }    

    @FXML
    private void AccepterOffre(ActionEvent event) {
    }

    @FXML
    private void RefuserOffre(ActionEvent event) {
    }

    @FXML
    private void ProposerOffre(ActionEvent event) {
    }
    
}
