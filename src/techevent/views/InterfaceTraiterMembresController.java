/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techevent.views;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.Provider;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import techevent.entities.Club;
import techevent.entities.Etudiant;
import techevent.services.ServiceClub;
import techevent.services.ServiceEtudiant;

/**
 * FXML Controller class
 *
 * @author Hannachi
 */
public class InterfaceTraiterMembresController implements Initializable {
    @FXML
    private TableColumn<Etudiant, String> Email;
    @FXML
    private TableView<Etudiant> tablemembres;
    @FXML
    private TableColumn<Etudiant, String> nom;
    @FXML
    private TableColumn<Etudiant, String> prenom;
    @FXML
    private TableColumn<Etudiant, String> classe;
    @FXML
    private TableColumn<Etudiant, String> téléphone;
    @FXML
    private Button boutonretour;
    @FXML
    private Button boutonsupprimer;
    @FXML
    private Button boutonrésponsabilité;
    @FXML
    private Button boutonajouter;
    @FXML
    private TableColumn<Etudiant, String> id;
    @FXML
    private JFXTextField textfieldchercher;
    int idf;
    File file;
   
    ObservableList<String> responsabilité = FXCollections.observableArrayList(
            "Vice President",
            "Trésorier",
            "Rh",
            "Sponsoring",
            "Media",
            "Recherche et developpement",
            "Matériels"
    );
   
    @FXML
    private ComboBox<String> comboboxrésponsbilité;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            comboboxrésponsbilité.setItems(responsabilité);
            ServiceClub sc=new ServiceClub();
            List<Etudiant> list = sc.AfficherMembres(idf);
            ObservableList<Etudiant> obslist = FXCollections.observableArrayList(list);
            nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            classe.setCellValueFactory(new PropertyValueFactory<>("classe"));
            téléphone.setCellValueFactory(new PropertyValueFactory<>("numerotelephone"));
            Email.setCellValueFactory(new PropertyValueFactory<>("email"));
            id.setCellValueFactory(new PropertyValueFactory<>("id"));
            tablemembres.setItems(obslist);
            textfieldchercher.textProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                try {
                    filterEmail((String) oldValue, (String) newValue);
                } catch (SQLException ex) {
                    Logger.getLogger(InterfaceTraiterMembresController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceTraiterMembresController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void retour(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceClubPresident.fxml"));
            Parent root;
            root = loader.load();
            InterfaceClubPresidentController irc = loader.getController();
            irc.initData(idf, file);
            boutonretour.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(InterfaceTraiterInvitationsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void supprimermembre(ActionEvent event) throws SQLException{
        Etudiant e=tablemembres.getSelectionModel().getSelectedItem();
        if(tablemembres.getSelectionModel().getSelectedItem() == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Aucun membre est selectionné");
            alert.showAndWait();
            }
        else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Suppresion");
            alert.setHeaderText("Vous étes sur de supprimer "+e.getNom()+" "+e.getPrenom());
            alert.setContentText("Voulez vous l'annulée?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
            ServiceClub sc=new ServiceClub();
            ServiceClub cs3=new ServiceClub();
            cs3.supprimerMembre(idf , e.getId());
            List<Etudiant> list = sc.AfficherMembres(5);
            ObservableList<Etudiant> obslist = FXCollections.observableArrayList(list);
            nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            classe.setCellValueFactory(new PropertyValueFactory<>("classe"));
            téléphone.setCellValueFactory(new PropertyValueFactory<>("numerotelephone"));
            Email.setCellValueFactory(new PropertyValueFactory<>("email"));
            id.setCellValueFactory(new PropertyValueFactory<>("id"));
            tablemembres.setItems(obslist);
            }
        }
    }

    @FXML
    private void donnerrésponsabilité(ActionEvent event) throws SQLException{
        Etudiant e=tablemembres.getSelectionModel().getSelectedItem();
        ServiceClub sc2=new ServiceClub();
        if((tablemembres.getSelectionModel().getSelectedItem() == null)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Merci de séléctionner un membre ");
            alert.showAndWait();
        }
        else{
            ServiceClub sc = new ServiceClub();
            sc.affecterResponsabilité(idf, e.getId(),comboboxrésponsbilité.getValue());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succes");
            alert.setHeaderText(null);
            alert.setContentText(e.getNom()+" "+e.getPrenom()+" est devenu "+comboboxrésponsbilité.getValue());
            alert.showAndWait();
            ServiceClub sc3=new ServiceClub();
            List<Etudiant> list = sc3.AfficherMembres(idf);
            ObservableList<Etudiant> obslist = FXCollections.observableArrayList(list);
            nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            classe.setCellValueFactory(new PropertyValueFactory<>("classe"));
            téléphone.setCellValueFactory(new PropertyValueFactory<>("numerotelephone"));
            Email.setCellValueFactory(new PropertyValueFactory<>("email"));
            id.setCellValueFactory(new PropertyValueFactory<>("id"));
            tablemembres.setItems(obslist);
        }
    }
    

    @FXML
    private void ajoutermembre(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceTraiterInvitations.fxml"));
            Parent root;
            root = loader.load();
            InterfaceTraiterInvitationsController irc = loader.getController();
            boutonajouter.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(InterfaceTraiterInvitationsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   

    
    void filterEmail(String oldValue, String newValue) throws SQLException{
        ServiceClub es = new ServiceClub();
        ObservableList<Etudiant> filteredList = FXCollections.observableArrayList();
        if (textfieldchercher.getText() == null || (newValue.length() < oldValue.length()) || newValue == null) {
            ObservableList<Etudiant> listSalle= FXCollections.observableArrayList(es.AfficherMembres(5));
            tablemembres.setItems(listSalle) ;
        } else {

            newValue = newValue.toUpperCase();

            for (Etudiant e : tablemembres.getItems()) {

                String filtertitre= e.getEmail();

                if (filtertitre.toUpperCase().contains(newValue)) {
                    filteredList.add(e);
                }
            }
            tablemembres.setItems(filteredList);
        }
    }

    void initData(int idf, File file) {
        this.idf=idf;
        this.file=file;
    }
}
