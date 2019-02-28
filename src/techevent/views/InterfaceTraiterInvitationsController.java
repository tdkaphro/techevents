/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techevent.views;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import techevent.entities.Etudiant;
import techevent.services.ServiceClub;

/**
 * FXML Controller class
 *
 * @author Hannachi
 */
public class InterfaceTraiterInvitationsController implements Initializable {
    @FXML
    private TableColumn<Etudiant, String> Email;
    @FXML
    private TableColumn<Etudiant, String> nom;
    @FXML
    private TableColumn<Etudiant, String> prenom;
    @FXML
    private TableColumn<Etudiant, String> classe;
    @FXML
    private TableColumn<Etudiant, String> téléphone;
    @FXML
    private Button boutonaccepter;
    @FXML
    private Button boutonrefuser;
    @FXML
    private Button boutonretour;
    @FXML
    private TableColumn<Etudiant, String> id;
    @FXML
    private TableView<Etudiant> tableinvitations;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            ServiceClub sc=new ServiceClub();
            List<Etudiant> list = sc.AfficherInvitation(5);
            ObservableList<Etudiant> obslist = FXCollections.observableArrayList(list);
            nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            classe.setCellValueFactory(new PropertyValueFactory<>("classe"));
            téléphone.setCellValueFactory(new PropertyValueFactory<>("numerotelephone"));
            Email.setCellValueFactory(new PropertyValueFactory<>("email"));
            id.setCellValueFactory(new PropertyValueFactory<>("id"));
            tableinvitations.setItems(obslist);
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceTraiterInvitationsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void accepter(ActionEvent event) throws SQLException{
    Etudiant e=tableinvitations.getSelectionModel().getSelectedItem();
        if(tableinvitations.getSelectionModel().getSelectedItem() == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Aucune invitation est selectionnée");
            alert.showAndWait();
            }
        else{
        ServiceClub sc=new ServiceClub();
        sc.AccepterInvitation(e.getId(), 5);
        Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
        alert2.setTitle("Succes");
        alert2.setHeaderText(null);
        alert2.setContentText(e.getNom()+" "+e.getPrenom()+" est devenu membre dans votre club");
        alert2.showAndWait();
        ServiceClub sc2=new ServiceClub();
        List<Etudiant> list = sc2.AfficherInvitation(5);
        ObservableList<Etudiant> obslist = FXCollections.observableArrayList(list);
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        classe.setCellValueFactory(new PropertyValueFactory<>("classe"));
        téléphone.setCellValueFactory(new PropertyValueFactory<>("numerotelephone"));
        Email.setCellValueFactory(new PropertyValueFactory<>("email"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableinvitations.setItems(obslist);
        }
    }

    @FXML
    private void refuser(ActionEvent event) throws SQLException{
    Etudiant e=tableinvitations.getSelectionModel().getSelectedItem();
    if(tableinvitations.getSelectionModel().getSelectedItem() == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Aucune invitation est selectionnée ");
            alert.showAndWait();
            }
    else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Vous étes sur de refuser l'invitation de "+e.getNom()+" "+e.getPrenom());
            alert.setContentText("Continuez ?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
            ServiceClub sc= new ServiceClub();
            sc.RefuserInvitation(e.getId(), 5);
            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setTitle("Succes");
            alert2.setHeaderText(null);
            alert2.setContentText("L'invitation est bien supprimée ");
            alert2.showAndWait();
            }
            else{}
    }
            ServiceClub sc=new ServiceClub();
            List<Etudiant> list = sc.AfficherInvitation(5);
            ObservableList<Etudiant> obslist = FXCollections.observableArrayList(list);
            nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            classe.setCellValueFactory(new PropertyValueFactory<>("classe"));
            téléphone.setCellValueFactory(new PropertyValueFactory<>("numerotelephone"));
            Email.setCellValueFactory(new PropertyValueFactory<>("email"));
            id.setCellValueFactory(new PropertyValueFactory<>("id"));
            tableinvitations.setItems(obslist);
    }

    @FXML
    private void retour(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceClubPresident.fxml"));
            Parent root;
            root = loader.load();
            InterfaceClubPresidentController irc = loader.getController();
            boutonretour.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(InterfaceTraiterInvitationsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
}
