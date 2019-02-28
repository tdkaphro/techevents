/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techevent.views;

import com.itextpdf.text.DocumentException;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.Marker;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sun.font.EAttribute;
import techevent.entities.Evenement;
import techevent.services.ServiceEvenement;

/**
 * FXML Controller class
 *
 * @author Ciro
 */
public class AcceuilEtudiantEvenController implements Initializable {

    @FXML
    private JFXButton evenementvenir;
    @FXML
    private JFXButton participer;
    @FXML
    private ImageView pdpid;
    @FXML
    private Label Description;
    @FXML
    private JFXTextArea descid;
    @FXML
    private Label nometudiant;
    @FXML
    private TableView<Evenement> tableau;
    @FXML
    private TableColumn<Evenement, String> nomidd;
    @FXML
    private TableColumn<Evenement, String> dateidd;
    @FXML
    private TableColumn<Evenement, String> payantidd;
    @FXML
    private TableColumn<Evenement, String> typeidd;

    @FXML
    private JFXButton tousevenem;
    @FXML
    private TableColumn<Evenement, String> clubid;
    @FXML
    private TableColumn<Evenement, String> id;

    ObservableList<String> listfiltrepayant = FXCollections.observableArrayList("Payant", "Non Payant");
    ObservableList<String> listfiltreType = FXCollections.observableArrayList("Physiques/Sportives", "Culturelles", "Gala", "Communautaires", "Autre..");

    @FXML
    private ComboBox<String> filtretype;
    @FXML
    private JFXButton optiondefiltrage;
    @FXML
    private ComboBox<String> filtrepayant;
    @FXML
    private TableColumn<Evenement, Integer> ettafinance;
    @FXML
    private TableColumn<Evenement, Integer> prixid;

    @FXML
    private TableColumn<Evenement, Integer> nbrparticipant;
    @FXML
    private TableColumn<Evenement, Integer> capacite;
    @FXML
    private Label local;
    @FXML
    private JFXButton imprime;
    @FXML
    private Label localid;
    @FXML
    private JFXButton r;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        filtrepayant.setItems(listfiltrepayant);
        filtretype.setItems(listfiltreType);
        filtretype.setDisable(true);
        filtrepayant.setDisable(true);

        List<Evenement> lf = new ArrayList<Evenement>();
        ServiceEvenement ev = new ServiceEvenement();
        ResultSet rs = null;
        try {
            rs = ev.afficherEvenementparClub(1);
        } catch (SQLException ex) {
            Logger.getLogger(AcceuilevenmntController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            while (rs.next()) {
                Evenement e = new Evenement();

                e.setId(rs.getInt("id"));
                e.setNom(rs.getString("nom"));
                e.setType(rs.getString("type"));
                e.setSoustypeautre(ev.affichersponsorevenement(rs.getInt("id")));
                e.setDescription(rs.getString("DESCRIPTION"));
                e.setLocalisation(rs.getString("LOCALISATION"));
                e.setFinance1(rs.getString("PAYANT"));
                e.setPrix1(String.valueOf(rs.getInt("PRIX")));
                e.setCapacite(rs.getInt("CAPACITER"));
                e.setNbrparticipant(ev.nombredeparticipant(e.getId()));
                e.setDateorganisation(rs.getDate("DATEORGANISATION"));

                lf.add(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AcceuilevenmntController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<Evenement> listObAbn = FXCollections.observableArrayList(lf);
        nomidd.setCellValueFactory(new PropertyValueFactory<>("nom"));
        typeidd.setCellValueFactory(new PropertyValueFactory<>("type"));
        dateidd.setCellValueFactory(new PropertyValueFactory<>("dateorganisation"));
        ettafinance.setCellValueFactory(new PropertyValueFactory<>("finance1"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        prixid.setCellValueFactory(new PropertyValueFactory<>("prix1"));
        capacite.setCellValueFactory(new PropertyValueFactory<>("capacite"));
        nbrparticipant.setCellValueFactory(new PropertyValueFactory<>("nbrparticipant"));

        tableau.setItems(listObAbn);
        try {
            remplirTab();
        } catch (IOException ex) {
            Logger.getLogger(AcceuilevenmntController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void ajouter(MouseEvent event) {
    }

    @FXML
    private void Evenementàvenir(ActionEvent event) throws SQLException {

        ServiceEvenement ev = new ServiceEvenement();

        ArrayList<Evenement> l = ev.AffihcerEvenavenir(1);

        ObservableList<Evenement> listObAbn = FXCollections.observableArrayList(l);
        nomidd.setCellValueFactory(new PropertyValueFactory<>("nom"));
        typeidd.setCellValueFactory(new PropertyValueFactory<>("type"));
        dateidd.setCellValueFactory(new PropertyValueFactory<>("dateorganisation"));
        ettafinance.setCellValueFactory(new PropertyValueFactory<>("finance1"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        prixid.setCellValueFactory(new PropertyValueFactory<>("prix1"));
        capacite.setCellValueFactory(new PropertyValueFactory<>("capacite"));
        nbrparticipant.setCellValueFactory(new PropertyValueFactory<>("nbrparticipant"));

        tableau.setItems(listObAbn);
        try {
            remplirTab();
        } catch (IOException ex) {
            Logger.getLogger(AcceuilevenmntController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void participerEvenement(ActionEvent event) throws SQLException, IOException {

        if (tableau.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Avertissement");
            alert.setHeaderText("Erreur de particiation de l'évenement");
            alert.setContentText("Pour avoir la possibilité de participer il faut selectionner l'evenement du tableau");
            alert.showAndWait();

        } else {

            ServiceEvenement se = new ServiceEvenement();
            ServiceEvenement se2 = new ServiceEvenement();

            if (se2.verifierparticipant(1, tableau.getSelectionModel().getSelectedItem().getId())) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("ERROR");
                alert.setHeaderText("Vous êtes déja participé cet évenement");
                alert.setContentText("Annuler votre participation");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    se.supprimeEtudiantrEvenement(1, tableau.getSelectionModel().getSelectedItem().getId());
                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setTitle("Succes");
                    alert2.setHeaderText(null);
                    alert2.setContentText("Votre participation a été bien supprimé");
                    alert2.showAndWait();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("AcceuilEtudiantEven.fxml"));
                    Parent root;
                    root = loader.load();
                    Description.getScene().setRoot(root);
                } else {
                }
            } else {
                se.ajouterEtudiantaEvenement(3, tableau.getSelectionModel().getSelectedItem().getId());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Participation");
                alert.setHeaderText("");
                alert.setContentText("Votre demande de participation est accepté");
                alert.showAndWait();
            }

        }
    }

    public void remplirTab() throws IOException {
        tableau.setOnMousePressed(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                ServiceEvenement se = new ServiceEvenement();

                try {
                    ResultSet rs = se.AfficherparEvenement(tableau.getSelectionModel().getSelectedItem().getId());
                    rs.next();
                    descid.setText(rs.getString("DESCRIPTION"));
                    descid.setEditable(false);
                    localid.setText(rs.getString("Localisation"));

                } catch (SQLException ex) {
                    Logger.getLogger(AcceuilevenmntController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
    }

    @FXML
    private void tousevenementback(ActionEvent event) {
        List<Evenement> lf = new ArrayList<Evenement>();
        ServiceEvenement ev = new ServiceEvenement();
        ResultSet rs = null;
        try {
            rs = ev.afficherEvenementparClub(1);
        } catch (SQLException ex) {
            Logger.getLogger(AcceuilevenmntController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            while (rs.next()) {
                Evenement e = new Evenement();

                e.setId(rs.getInt("id"));
                e.setNom(rs.getString("nom"));
                e.setType(rs.getString("type"));
                e.setSoustypeautre(ev.affichersponsorevenement(rs.getInt("id")));
                e.setDescription(rs.getString("DESCRIPTION"));
                e.setLocalisation(rs.getString("LOCALISATION"));
                e.setFinance1(rs.getString("PAYANT"));
                e.setPrix1(String.valueOf(rs.getInt("PRIX")));
                e.setCapacite(rs.getInt("CAPACITER"));
                e.setDateorganisation(rs.getDate("DATEORGANISATION"));

                lf.add(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AcceuilevenmntController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<Evenement> listObAbn = FXCollections.observableArrayList(lf);
        nomidd.setCellValueFactory(new PropertyValueFactory<>("nom"));
        typeidd.setCellValueFactory(new PropertyValueFactory<>("type"));
        dateidd.setCellValueFactory(new PropertyValueFactory<>("dateorganisation"));
        ettafinance.setCellValueFactory(new PropertyValueFactory<>("finance1"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        prixid.setCellValueFactory(new PropertyValueFactory<>("prix1"));
        capacite.setCellValueFactory(new PropertyValueFactory<>("capacite"));
        nbrparticipant.setCellValueFactory(new PropertyValueFactory<>("nbrparticipant"));

        tableau.setItems(listObAbn);
        try {
            remplirTab();
        } catch (IOException ex) {
            Logger.getLogger(AcceuilevenmntController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void optionsdefiltrage(ActionEvent event) {
        filtretype.setDisable(false);
        filtrepayant.setDisable(false);

    }

    @FXML
    private void filtre(ActionEvent event) throws SQLException, IOException {

        List<Evenement> le = new ArrayList<Evenement>();

        String etatdefinancement;
        int id;
        String type;

        if (filtretype.getSelectionModel().isEmpty()) {
            type = "Gala";
        } else {
            type = filtretype.getValue();
        }

        if (filtrepayant.getSelectionModel().isEmpty()) {
            etatdefinancement = "Non Payant";
        } else {
            etatdefinancement = filtrepayant.getValue();
        }
        ResultSet rs = null;
        ServiceEvenement se = new ServiceEvenement();

        rs = se.filtrer(etatdefinancement, type);
        try {
            while (rs.next()) {
                Evenement e = new Evenement();
                e.setId(rs.getInt("id"));
                e.setNom(rs.getString("nom"));
                e.setType(rs.getString("type"));
                // e.setSoustypeautre(ev.affichersponsorevenement(rs.getInt("id")));
                e.setDescription(rs.getString("DESCRIPTION"));
                e.setFinance1(rs.getString("PAYANT"));
                e.setPrix1(String.valueOf(rs.getInt("PRIX")));
                e.setCapacite(rs.getInt("CAPACITER"));
                e.setDateorganisation(rs.getDate("DATEORGANISATION"));
                le.add(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AcceuilEtudiantEvenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<Evenement> listObAbn = FXCollections.observableArrayList(le);
        nomidd.setCellValueFactory(new PropertyValueFactory<>("nom"));
        typeidd.setCellValueFactory(new PropertyValueFactory<>("type"));
//        sponsoridd.setCellValueFactory(new PropertyValueFactory<>("soustypeautre"));
        dateidd.setCellValueFactory(new PropertyValueFactory<>("dateorganisation"));
        ettafinance.setCellValueFactory(new PropertyValueFactory<>("finance1"));
        // id.setCellValueFactory(new PropertyValueFactory<>("id"));
        prixid.setCellValueFactory(new PropertyValueFactory<>("prix1"));

        tableau.setItems(listObAbn);

        remplirTab();

    }

    @FXML
    private void imprimer(ActionEvent event) throws SQLException, DocumentException, IOException {
        if (tableau.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Avertissement");
            alert.setHeaderText("Erreur d'imprission de l'évenement");
            alert.setContentText("Pour avoir la possibilité d'imprimer votre participation il faut selectionner l'evenement du tableau");
            alert.showAndWait();

        } else {
            ServiceEvenement se = new ServiceEvenement();
            if (!se.verifierparticipant(1, tableau.getSelectionModel().getSelectedItem().getId())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("ERROR");
                alert.setHeaderText("Erreur d'imprission de l'évenement");
                alert.setContentText("Vous n'êtes pas participant dans cet événement pour avoir la possibilité d'imprimer la participation");
                alert.showAndWait();

            }

            se.participationdpdf(tableau.getSelectionModel().getSelectedItem().getId());
        }

    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        r.getScene().getWindow().hide();
        Stage prStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("accueiletudiant.fxml"));
        Scene scene = new Scene(root);
        prStage.setScene(scene);
        prStage.setResizable(false);
        prStage.show();
    }

}
