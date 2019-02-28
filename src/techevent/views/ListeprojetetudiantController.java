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
import java.util.Date;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;
import techevent.entities.Enseignant;
import techevent.entities.Etudiant;
import techevent.entities.Formateur;
import techevent.entities.Formation;
import techevent.entities.Projet;
import techevent.services.ServiceFormation;
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
    private TableColumn<Projet, String> etat;
    @FXML
    private TableColumn<Projet, String> media;
    @FXML
    private TableColumn<Projet, String> progress;
    @FXML
    private TableColumn<Projet, String> id;
    @FXML
    private TableColumn<Projet, String> domaine;
    @FXML
    private ImageView img;
    @FXML
    private MediaView mediaview;
    int idf ;
    @FXML
    private TableColumn<?, ?> progress3;
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
                p.setDomaine(rs.getString("domaine"));
                pf.add(p);
            }
        //    int x = sp.countenseign(1);
         //   int c = sp.nombresprojet(0);
          //  int b = sp.countmembre(1);
         //   String x2 = String.valueOf(x);
        //    String x3 = String.valueOf(c);
         //   String x4 = String.valueOf(b);
        //    nbrens.setText(x2);
       //     nbrpr.setText(x2);
       //     nbrmem.setText(x2);

        } catch (SQLException ex) {
            Logger.getLogger(ListeprojetenseignantController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<Projet> listObAbn = FXCollections.observableArrayList(pf);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomprojet.setCellValueFactory(new PropertyValueFactory<>("nom"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        etat.setCellValueFactory(new PropertyValueFactory<>("media"));
        progress.setCellValueFactory(new PropertyValueFactory<>("progress"));
        domaine.setCellValueFactory(new PropertyValueFactory<>("domaine"));

        tableau.setItems(listObAbn);
      
int note = sp.getMemidByProjetId(idf);

TrayNotification notifrejoindre = new TrayNotification();
String s = "invitation";
String s1 = "vous avez "+String.valueOf(note)+" nouvel invitation";
notifrejoindre.setTray(s, s1, NotificationType.INFORMATION);
notifrejoindre.showAndDismiss(Duration.seconds(5));
        try {
            remplirTab();
        } catch (IOException ex) {
            Logger.getLogger(ListeprojetetudiantController.class.getName()).log(Level.SEVERE, null, ex);
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
    private void listedesinvitations(ActionEvent event) throws SQLException, IOException {
        ServiceProjet sp = new ServiceProjet();
          List<Projet> lf = new ArrayList<Projet>();
          ResultSet rs = sp.afficherProjetparinvit(idf);
           while (rs.next()) {
               Projet p = new Projet();
               p.setNom(rs.getString("nom"));
               p.setDescription(rs.getString("description"));
               p.setDomaine(rs.getString("domaine"));
               p.setProgress(rs.getInt("progress"));
               if(rs.getBoolean("etat")==true){
               p.setMedia("sponsorisé");
               }else{
               p.setMedia("nonsponsorisé");
            
               }
                  p.setId(rs.getInt("id"));
               lf.add(p);
  
        }
        ObservableList<Projet> listObAbn = FXCollections.observableArrayList(lf);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomprojet.setCellValueFactory(new PropertyValueFactory<>("nom"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        etat.setCellValueFactory(new PropertyValueFactory<>("media"));
        progress.setCellValueFactory(new PropertyValueFactory<>("progress"));
        domaine.setCellValueFactory(new PropertyValueFactory<>("domaine"));
        tableau.setItems(listObAbn);
        remplirTab();
    }

    void initData(int idf, File file) {
        this.idf=idf;
     
        Image image = new Image(file.toURI().toString(),142,145,false,false);
        img.setImage(image);
     }
       @FXML
    void accepterevt(ActionEvent event) {
      ServiceProjet sp = new ServiceProjet();
      if(tableau.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("choisir le projet que vous voulez le joindre");
            alert.showAndWait();
      }else{
          sp.accepterinvitation(idf,tableau.getSelectionModel().getSelectedItem().getId());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("vous devez réussir ce test pour nous rejoundre");
            alert.showAndWait();
            Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
        //QUIZZZZ
        
        }}
     
      
    }
          @FXML
    void retourevt(ActionEvent event) throws IOException {
                FXMLLoader loader = new FXMLLoader();
                img.getScene().getWindow().hide();  
                Stage prStage =new Stage(); 
                loader.setLocation(getClass().getResource("accueiletudiant.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                AccueiletudiantController mc = loader.getController();
                mc.initData(idf);
                prStage.setScene(scene);
                prStage.setResizable(false);
                prStage.show();
    }
    }
                

