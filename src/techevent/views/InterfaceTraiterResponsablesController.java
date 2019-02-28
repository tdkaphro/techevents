/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techevent.views;

import java.io.File;
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
import techevent.entities.Responsable;
import techevent.services.ServiceClub;

/**
 * FXML Controller class
 *
 * @author Hannachi
 */
public class InterfaceTraiterResponsablesController implements Initializable {

    @FXML
    private TableView<Responsable> table;
    @FXML
    private TableColumn<Responsable, String> Email;
    @FXML
    private TableColumn<Responsable, String> nom;
    @FXML
    private TableColumn<Responsable, String> prenom;
    @FXML
    private TableColumn<Responsable, String> classe;
    @FXML
    private TableColumn<Responsable, String> telephone;
    @FXML
    private TableColumn<Responsable, String> Responsabilite;
    @FXML
    private TableColumn<Responsable, String> id;
    @FXML
    private Button boutonretour;
    @FXML
    private Button boutonmodifier;
    @FXML
    private Button boutonannuler;
    @FXML
    private Button boutonsupprimer;
    int idf;
    File file;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            ServiceClub sc=new ServiceClub();
            List<Responsable> list = sc.afficherResponsables(idf);
            ObservableList<Responsable> obslist = FXCollections.observableArrayList(list);
            nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            classe.setCellValueFactory(new PropertyValueFactory<>("classe"));
            telephone.setCellValueFactory(new PropertyValueFactory<>("numerotelephone"));
            Email.setCellValueFactory(new PropertyValueFactory<>("email"));
            Responsabilite.setCellValueFactory(new PropertyValueFactory<>("responsabilite"));
            id.setCellValueFactory(new PropertyValueFactory<>("id"));
            table.setItems(obslist);
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceTraiterResponsablesController.class.getName()).log(Level.SEVERE, null, ex);
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
    private void modifier(ActionEvent event) {
        if(table.getSelectionModel().getSelectedItem() == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Aucun résponsable est selectionné");
            alert.showAndWait();
            }
        
    }

    @FXML
    private void annuler(ActionEvent event) throws SQLException{
        Responsable r=table.getSelectionModel().getSelectedItem();
        if(table.getSelectionModel().getSelectedItem() == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Aucun résponsable est selectionné");
            alert.showAndWait();
            }
        else{
        ServiceClub sc=new ServiceClub();
        sc.annulerResponsabilite(idf, r.getId());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succes");
            alert.setHeaderText(null);
            alert.setContentText(r.getNom()+" "+r.getPrenom()+" est revenu un membre");
            alert.showAndWait();
        ServiceClub sc2=new ServiceClub();
            List<Responsable> list = sc2.afficherResponsables(idf);
            ObservableList<Responsable> obslist = FXCollections.observableArrayList(list);
            nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            classe.setCellValueFactory(new PropertyValueFactory<>("classe"));
            telephone.setCellValueFactory(new PropertyValueFactory<>("numerotelephone"));
            Email.setCellValueFactory(new PropertyValueFactory<>("email"));
            Responsabilite.setCellValueFactory(new PropertyValueFactory<>("responsabilite"));
            id.setCellValueFactory(new PropertyValueFactory<>("id"));
            table.setItems(obslist);
        }
    }
    @FXML
    private void supprimer(ActionEvent event) throws SQLException {
        Responsable r=table.getSelectionModel().getSelectedItem();
        if(table.getSelectionModel().getSelectedItem() == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Aucun résponsable est selectionné");
            alert.showAndWait();
            }
        else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Suppresion");
            alert.setHeaderText("Vous étes sur de supprimer "+r.getNom()+" "+r.getPrenom()+" définitivement de votre groupe ?");
            alert.setContentText("Continuez ?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
            ServiceClub sc=new ServiceClub();
            ServiceClub cs3=new ServiceClub();
            cs3.supprimerResponsable(idf, r.getId());
            List<Responsable> list = sc.afficherResponsables(idf);
            ObservableList<Responsable> obslist = FXCollections.observableArrayList(list);
            nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            classe.setCellValueFactory(new PropertyValueFactory<>("classe"));
            telephone.setCellValueFactory(new PropertyValueFactory<>("numerotelephone"));
            Email.setCellValueFactory(new PropertyValueFactory<>("email"));
            Responsabilite.setCellValueFactory(new PropertyValueFactory<>("responsabilite"));
            id.setCellValueFactory(new PropertyValueFactory<>("id"));
            table.setItems(obslist);
            }
            else {}
        }       
    }

    void initData(int idf, File file) {
        this.idf=idf;
        this.file=file;
    }
}
