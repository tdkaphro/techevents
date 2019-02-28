/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techevent.views;

import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;
import techevent.entities.Enseignant;
import techevent.entities.Etudiant;
import techevent.entities.Projet;
import techevent.services.ServiceProjet;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Wael
 */
public class ListeprojetetudiantController implements Initializable {

    @FXML
    private AnchorPane creer;
    @FXML
    private Label nbrens;
    @FXML
    private Label nbrmem;
    @FXML
    private Label nbrpr;
    @FXML
    private TableView<Projet> tableau;
    @FXML
    private TableColumn<Projet, String> nomprojet;
    @FXML
    private TableColumn<Projet, String> description;
    @FXML
    private TableColumn<Projet, Boolean> etat;
    @FXML
    private TableColumn<Projet, String> media;
    @FXML
    private TableColumn<Projet, Integer> progress;
    @FXML
    private TableColumn<Projet, Integer> progress1;
    @FXML
    private TableColumn<Projet, Integer> progress2;
    @FXML
    private TableColumn<Projet, Integer> progress3;
    @FXML
    private MediaView mediaview;
      public static int id;
    public static int prog;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
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
boolean note = sp.getMemidByProjetId(4);
if (note==false){
TrayNotification notifrejoindre = new TrayNotification();
String s = "invitation";
String s1 = "vous avez 1 nouvel invitation";
notifrejoindre.setTray(s, s1, NotificationType.INFORMATION);
notifrejoindre.showAndDismiss(Duration.seconds(5));

}
    }    

     public void remplirTab() throws IOException {
        tableau.setOnMousePressed(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                
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
                /*ResultSet rs = sp.afficherProjetparinvit(sp.getEnsidByProjetId(tableau.getSelectionModel().getSelectedItem().getId()));
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
                 
            }
        });**/
        
    }
                });
                }
    @FXML
    private void listedesinvitations(ActionEvent event) throws SQLException {
        ServiceProjet sp = new ServiceProjet();
        ArrayList<Projet> rs = sp.afficherProjetparinvit(1);
        ObservableList<Projet> listObAbn = FXCollections.observableArrayList(rs);
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
    }
                

