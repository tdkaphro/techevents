/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techevents.views;

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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import techevent.entities.Evenement;
import techevent.entities.Offre;
import techevent.entities.Sponsor;
import javax.swing.table.DefaultTableModel;
import techevent.services.ServiceOffre;
import techevent.services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author Taboubi
 */
public class OffresponsorController implements Initializable {

    private JFXButton b1;
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
    @FXML
    private TableColumn<Offre, String> etat;

    @FXML
    private JFXButton accept;
    @FXML
    private JFXButton refuse;
    @FXML
    private JFXButton supprim;
    @FXML
    private JFXButton propose;
    @FXML
    private JFXButton modif;
    @FXML
    private JFXButton retour;
    public static Offre of=new Offre();
    @FXML
    private JFXButton suivre;
    @FXML
    private JFXButton gerer;
    @FXML
    private JFXButton modif1;
 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        accept.setDisable(true);
        refuse.setDisable(true);
        supprim.setDisable(true);
        modif.setDisable(true);

    }

    @FXML
    private void AccepterOffre(ActionEvent event) {
        if (table.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alerte");
            alert.setHeaderText(null);
            alert.setContentText("!!!  Selctionnez un evenement du tableau !!!");
            alert.showAndWait();
        }
        else if(!table.getSelectionModel().getSelectedItem().getEtat().equals("En Attente")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alerte");
            alert.setHeaderText(null);
            alert.setContentText("!!!  L offre est deja traite !!!");
            alert.showAndWait();
        }
        else {
            ServiceOffre so = new ServiceOffre();

            Offre f = table.getSelectionModel().getSelectedItem();
            so.AccepterOffre(f.getId());
            table.getItems().clear();
            table.refresh();
            this.GererOffre(event);
        }
    }

    @FXML
    private void RefuserOffre(ActionEvent event) {
        if (table.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alerte");
            alert.setHeaderText(null);
            alert.setContentText("!!!  Selctionnez un evenement du tableau !!!");
            alert.showAndWait();
        }
        if(!table.getSelectionModel().getSelectedItem().getEtat().equals("En Attente")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alerte");
            alert.setHeaderText(null);
            alert.setContentText("!!!  L offre est deja traite !!!");
            alert.showAndWait();
        }
        else {
            ServiceOffre so = new ServiceOffre();

            Offre f = table.getSelectionModel().getSelectedItem();
            so.RefuserOffre(f.getId());
            table.getItems().clear();
            table.refresh();
            this.GererOffre(event);
        }
    }

    @FXML
    private void ProposerOffre(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("offriroffre.fxml"));
            Parent root = loader.load();
            OffriroffreController irc = loader.getController();
            propose.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(OffresponsorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void SuivrePropositions(ActionEvent event) {
        accept.setDisable(true);
        refuse.setDisable(true);
        supprim.setDisable(false);
        modif.setDisable(false);
        table.getItems().clear();
        table.refresh();
        ServiceUser su = new ServiceUser();
        ServiceOffre so = new ServiceOffre();
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root = loader.load();
            LoginController irc = loader.getController();
        } catch (IOException ex) {
            Logger.getLogger(OffresponsorController.class.getName()).log(Level.SEVERE, null, ex);
        }

        String mail = LoginController.u1.getEmail();
        String mdp = LoginController.u1.getMotpasse();
        int id = su.getId(mail, mdp);

        List<Offre> plist = so.findAllOffreforSponseur(1);

        idoffre.setCellValueFactory(new PropertyValueFactory<>("id"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        nomeven.setCellValueFactory(new PropertyValueFactory<>("nomeven"));
        ideven.setCellValueFactory(new PropertyValueFactory<>("ideven"));
        idspons.setCellValueFactory(new PropertyValueFactory<>("idspon"));
        etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        ObservableList<Offre> oblist = FXCollections.observableArrayList(plist);
        table.setItems(oblist);

    }

    @FXML
    private void GererOffre(ActionEvent event) {
        accept.setDisable(false);
        refuse.setDisable(false);
        supprim.setDisable(false);
        modif.setDisable(true);
        table.getItems().clear();
        table.refresh();
        ServiceUser su = new ServiceUser();
        ServiceOffre so = new ServiceOffre();
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root = loader.load();
            LoginController irc = loader.getController();
        } catch (IOException ex) {
            Logger.getLogger(OffresponsorController.class.getName()).log(Level.SEVERE, null, ex);
        }

        String mail = LoginController.u1.getEmail();
        String mdp = LoginController.u1.getMotpasse();
        int id = su.getId(mail, mdp);

        List<Offre> plist = so.findAllDemandeforSponseur(1);

        idoffre.setCellValueFactory(new PropertyValueFactory<>("id"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        nomeven.setCellValueFactory(new PropertyValueFactory<>("nomeven"));
        ideven.setCellValueFactory(new PropertyValueFactory<>("ideven"));
        idspons.setCellValueFactory(new PropertyValueFactory<>("idspon"));
        etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        ObservableList<Offre> oblist = FXCollections.observableArrayList(plist);
        table.setItems(oblist);

    }

    @FXML
    private void SupprimerOffre(ActionEvent event) {
        if (table.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alerte");
            alert.setHeaderText(null);
            alert.setContentText("!!!  Selctionnez un evenement du tableau !!!");
            alert.showAndWait();
        } else {
            ServiceOffre so = new ServiceOffre();
            so.supprimerOffre(table.getSelectionModel().getSelectedItem().getId());
            table.getItems().removeAll(
                    table.getSelectionModel().getSelectedItems()
            );;
            table.refresh();
        }

    }

    @FXML
    private void ModifierOffre(ActionEvent event) {
        if (table.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alerte");
            alert.setHeaderText(null);
            alert.setContentText("!!!  Selctionnez un evenement du tableau !!!");
            alert.showAndWait();
        }
        if(!table.getSelectionModel().getSelectedItem().getEtat().equals("En Attente")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alerte");
            alert.setHeaderText(null);
            alert.setContentText("!!!  L offre est deja traite !!!");
            alert.showAndWait();
        }
        else {
            of.setPrix(table.getSelectionModel().getSelectedItem().getPrix());
            of.setId(table.getSelectionModel().getSelectedItem().getId());
            of.setNomeven(table.getSelectionModel().getSelectedItem().getNomeven());
            try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("modifieroffre1.fxml"));
            Parent root = loader.load();
            Modifieroffre1Controller irc = loader.getController();
            propose.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(OffresponsorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }

    @FXML
    private void Retour(ActionEvent event) {
    }

    @FXML
    private void Statistique(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("statistiqueoffre1.fxml"));
            Parent root = loader.load();
            Statistiqueoffre1Controller irc = loader.getController();
            accept.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(OffresponsorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   

}
