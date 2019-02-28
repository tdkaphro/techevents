/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techevent.views;

import com.jfoenix.controls.JFXButton;
import com.sun.javafx.application.ParametersImpl;
import java.awt.Button;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Callback;
import techevent.entities.Enseignant;
import techevent.entities.Projet;
import techevent.services.ServiceProjet;

/**
 * FXML Controller class
 *
 * @author Wael
 */
public class ListeprojetenseignantController implements Initializable {

    @FXML
    private JFXButton b1;
    @FXML
    private Label nbrens;
    @FXML
    private Label nbrmem;
    @FXML
    private Label nbrpr;
    @FXML
    private AnchorPane creer;
    @FXML
    private TableColumn<Projet, String> nomprojet;
    @FXML
    private TableColumn<Projet, String> description;
    @FXML
    private TableColumn<Projet, Boolean> etat;
    @FXML
    private TableColumn<Projet, Integer> progress;
    @FXML
    private TableColumn<Projet, String> media;

    @FXML
    private TableView<Projet> tableau;
    @FXML
    private MediaView mediaview;
    @FXML
    private JFXButton btnmodifier;

    @FXML
    private TableColumn<Projet, Integer> progress1;
    @FXML
    private TableColumn<Projet, Integer> progress2;
    @FXML
    private TableColumn<Projet, Integer> progress3;
    @FXML
    private JFXButton supprimer;
    @FXML
    private JFXButton ajoutermembr;
    public static int id;
    public static int prog;
    @FXML
    private JFXButton even;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL location, ResourceBundle rb) {
        btnmodifier.setVisible(false);
        supprimer.setVisible(false);
        ajoutermembr.setVisible(false);
        even.setVisible(false);
        Enseignant en = new Enseignant();
        ServiceProjet sp = new ServiceProjet();
        List<Projet> pf = new ArrayList<Projet>();
        ResultSet rs = sp.affichertousProjets();

        try {

            while (rs.next()) {

                Projet p = new Projet();
                p.setId(rs.getInt("id"));
                p.setNom(rs.getString("nom"));
                p.setDescription(rs.getString("Description"));
                if (rs.getBoolean("etat") == true) {
                    p.setMedia("sponsorisé");
                } else {
                    p.setMedia("non sponsorisé");
                }
                p.setProgress(rs.getInt("progress"));
                pf.add(p);
            }
            int x = sp.countenseign(1);
            int c = sp.nombresprojet(0);
            int b = sp.countmembre(1);
            String x2 = String.valueOf(x);
            String x3 = String.valueOf(c);
            String x4 = String.valueOf(b);
            nbrens.setText(x2);
            nbrpr.setText(x2);
            nbrmem.setText(x2);

        } catch (SQLException ex) {
            Logger.getLogger(ListeprojetenseignantController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<Projet> listObAbn = FXCollections.observableArrayList(pf);
        nomprojet.setCellValueFactory(new PropertyValueFactory<>("nom"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        etat.setCellValueFactory(new PropertyValueFactory<>("media"));
        progress.setCellValueFactory(new PropertyValueFactory<>("progress"));
        progress1.setCellValueFactory(new PropertyValueFactory<>("id"));

        tableau.setItems(listObAbn);
        try {
            remplirTab();
        } catch (IOException ex) {
            Logger.getLogger(ListeprojetenseignantController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void remplirTab() throws IOException {
        tableau.setOnMousePressed(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                btnmodifier.setVisible(false);
                supprimer.setVisible(false);
                ajoutermembr.setVisible(false);
                even.setVisible(false);
                ServiceProjet sp = new ServiceProjet();
                Projet p = new Projet();
                ResultSet rs = sp.affichertousProjets();
                try {
                    rs.next();
                } catch (SQLException ex) {
                    Logger.getLogger(ListeprojetenseignantController.class.getName()).log(Level.SEVERE, null, ex);
                }
                String Media_url = "webservice.MP4";

//String Media_url = rs.getString("Media");
                System.out.println(this.getClass().getResource(Media_url).toExternalForm());
                MediaPlayer mediaPlayer = new MediaPlayer(new Media(this.getClass().getResource(Media_url).toExternalForm()));
                mediaPlayer.setAutoPlay(true);
                mediaview.setMediaPlayer(mediaPlayer);
                Media myvedio = null;
                try {
                    myvedio = new Media(new File(rs.getString("Media"))
                            .toURI().toURL().toExternalForm());
                } catch (SQLException ex) {
                    Logger.getLogger(ListeprojetenseignantController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(ListeprojetenseignantController.class.getName()).log(Level.SEVERE, null, ex);
                }
                //System.out.println(myvedio.getDuration());
                
                
            }
        });
    }

    public void remplirMesprojetsTab() throws IOException {
        tableau.setOnMousePressed(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                ServiceProjet sp = new ServiceProjet();
                Projet p = new Projet();
                ResultSet rs = sp.afficherProjetsparenseignant2(sp.getEnsidByProjetId(tableau.getSelectionModel().getSelectedItem().getId()));
                try {
                    rs.next();
                } catch (SQLException ex) {
                    Logger.getLogger(ListeprojetenseignantController.class.getName()).log(Level.SEVERE, null, ex);
                }

                String Media_url = "webservice.MP4";

//String Media_url = rs.getString("Media");
                //System.out.println(this.getClass().getResource(Media_url).toExternalForm());
                MediaPlayer mediaPlayer = new MediaPlayer(new Media(this.getClass().getResource(Media_url).toExternalForm()));
                mediaPlayer.setAutoPlay(true);
                mediaview.setMediaPlayer(mediaPlayer);
                Media myvedio = null;
                try {
                    myvedio = new Media(new File(rs.getString("Media"))
                            .toURI().toURL().toExternalForm());
                } catch (SQLException ex) {
                    Logger.getLogger(ListeprojetenseignantController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(ListeprojetenseignantController.class.getName()).log(Level.SEVERE, null, ex);
                }
//            System.out.println(myvedio.getDuration());
                btnmodifier.setVisible(true);
                supprimer.setVisible(true);
                ajoutermembr.setVisible(true);
                if(tableau.getSelectionModel().getSelectedItem().getProgress()==100){
                    even.setVisible(true);
                }
            }
        });
        
    }

    @FXML
    private void modifierprojet(ActionEvent event) throws IOException {
        prog=tableau.getSelectionModel().getSelectedItem().getProgress();
        id=tableau.getSelectionModel().getSelectedItem().getId();
        FXMLLoader loader=new FXMLLoader(getClass().getResource("modifierprojet.fxml"));
        Parent root=loader.load();
        supprimer.getScene().setRoot(root);
    }

    @FXML
    private void mesprojets() {
        Enseignant en = new Enseignant();
        ServiceProjet sp = new ServiceProjet();
        ResultSet rs = sp.afficherProjetsparenseignant(1);
        List<Projet> pf = new ArrayList<Projet>();
        try {

            while (rs.next()) {

                Projet p = new Projet();
                p.setId(rs.getInt("id"));
                p.setNom(rs.getString("nom"));
                p.setDescription(rs.getString("Description"));
                if (rs.getBoolean("etat") == true) {
                    p.setMedia("sponsorisé");
                } else {
                    p.setMedia("non sponsorisé");
                }
                p.setProgress(rs.getInt("progress"));

                pf.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ListeprojetenseignantController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<Projet> listObAbn = FXCollections.observableArrayList(pf);
        nomprojet.setCellValueFactory(new PropertyValueFactory<>("nom"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        etat.setCellValueFactory(new PropertyValueFactory<>("media"));
        progress.setCellValueFactory(new PropertyValueFactory<>("progress"));

        tableau.setItems(listObAbn);
        try {
            remplirMesprojetsTab();
        } catch (IOException ex) {
            Logger.getLogger(ListeprojetenseignantController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void touslesprojets(MouseEvent event) {
        Enseignant en = new Enseignant();
        ServiceProjet sp = new ServiceProjet();
        ResultSet rs = sp.affichertousProjets();
        List<Projet> pf = new ArrayList<Projet>();
        try {

            while (rs.next()) {

                Projet p = new Projet();
                p.setId(rs.getInt("id"));
                p.setNom(rs.getString("nom"));
                p.setDescription(rs.getString("Description"));
                if (rs.getBoolean("etat") == true) {
                    p.setMedia("sponsorisé");
                } else {
                    p.setMedia("non sponsorisé");
                }
                p.setProgress(rs.getInt("progress"));

                pf.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ListeprojetenseignantController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<Projet> listObAbn = FXCollections.observableArrayList(pf);
        nomprojet.setCellValueFactory(new PropertyValueFactory<>("nom"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        etat.setCellValueFactory(new PropertyValueFactory<>("media"));
        progress.setCellValueFactory(new PropertyValueFactory<>("progress"));
        tableau.setItems(listObAbn);
        try {
            remplirTab();
        } catch (IOException ex) {
            Logger.getLogger(ListeprojetenseignantController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void creerprojet(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("creerprojet.fxml"));
        this.creer.getChildren().setAll(pane);
    }

    @FXML
    private void supprimerprojet(ActionEvent event) throws IOException {
        showConfirmation("Voulez vous vrament supprimer ce projet?");

    }

    private void showConfirmation(String message) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(message);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Projet p = new Projet();

            ServiceProjet sp = new ServiceProjet();
            sp.supprimerprojet(tableau.getSelectionModel().getSelectedItem().getId());

            tableau.refresh();
            mesprojets();
        } else {
            // ... user chose CANCEL or closed the dialog
        }
    }

    @FXML
    private void ajoutermembre(ActionEvent event) throws IOException {
        id=tableau.getSelectionModel().getSelectedItem().getId();
        FXMLLoader loader=new FXMLLoader(getClass().getResource("Ajoutermembre.fxml"));
        Parent root=loader.load();
        supprimer.getScene().setRoot(root);
        
    }
       @FXML
    void lancerevt(ActionEvent event) throws IOException {
               AnchorPane pane = FXMLLoader.load(getClass().getResource("AjouterEvenement.fxml"));
        this.creer.getChildren().setAll(pane);
    }

}
