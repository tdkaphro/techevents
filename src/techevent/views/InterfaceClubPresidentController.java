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
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import javax.naming.spi.DirStateFactory;
import techevent.services.ServiceClub;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Hannachi
 */
public class InterfaceClubPresidentController implements Initializable {

    @FXML
    private Button boutonmodifierclub;
    @FXML
    private Button boutonsupprimerclub;
    @FXML
    private Button boutonretour;
    @FXML
    private Button boutonorganiserevenement;
    @FXML
    private Button boutonajouterformation;
    @FXML
    private Label nombresmembre;
    @FXML
    private Label nombreevenement;
    @FXML
    private Label nombreformation;
    @FXML
    private Label nombreprojet;
    @FXML
    private Label nomclub;
    @FXML
    private Button boutontraitermembre;
    @FXML
    private JFXButton boutonresponsables;
    @FXML
    private JFXButton boutoninvitation;
    @FXML
    private Label nombresdesresponsable;
    @FXML
    private ImageView utilisateurphoto;
    
    int idf;
    File file;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
        try {
            // TODO
            ServiceClub sc = new ServiceClub();
            ServiceClub sc2 = new ServiceClub();
            ServiceClub sc3= new ServiceClub();
            ServiceClub sc4= new ServiceClub();
            ServiceClub sc5= new ServiceClub();
            int nombremembre= sc2.NombreDesMembres(idf);
            int nombreresp=sc3.NombreDesResponsables(idf);
            int nombreeven=sc.NombreDesEvenements(idf);
            int nombrefor=sc.NombreDesFormations(idf);
            int nombreprojets=sc.NombreDesProjets(idf);
            ResultSet rs=sc2.afficherClubParPresident(idf);
            nomclub.setText(rs.getString(6));
            nombresmembre.setText(Integer.toString(nombremembre));
            nombresdesresponsable.setText(Integer.toString(nombreresp));
            nombreevenement.setText(Integer.toString(nombreeven));
            nombreformation.setText(Integer.toString(nombrefor));
            nombreprojet.setText(Integer.toString(nombreprojets));
            int not=sc3.notificationInvitation(idf);
            if(not>0){
            TrayNotification notifrejoindre= new TrayNotification();
            String s="Invitation";
            String s2="Vous avez "+not+" nouvelles invitations";
            notifrejoindre.setTray(s, s2, NotificationType.INFORMATION);
            notifrejoindre.showAndDismiss(Duration.seconds(5));
            }
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceClubPresidentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void modifierclub(ActionEvent event) {
        try{            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceModifierClub.fxml"));
            Parent root;
            root = loader.load();
            InterfaceModifierClubController irc = loader.getController();
            irc.initData(idf,file);
            boutonmodifierclub.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(InterfaceClubEtudiantController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void supprimerclub(ActionEvent event) throws SQLException{
        try {
            ServiceClub cl = new ServiceClub();
            ServiceClub cl2= new ServiceClub();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Faites attention");
            alert.setHeaderText("Voulez vous supprimer votre club");
            alert.setContentText("Continez ? ");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
            cl2.SupprimerClub(idf); // id etudiant connecté
            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setTitle("Succes");
            alert2.setHeaderText(null);
            alert2.setContentText("Votre club a été bien supprimé");
            alert2.showAndWait();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceClubEtudiant.fxml"));
            Parent root;
            root = loader.load();
            InterfaceClubEtudiantController irc = loader.getController();
            irc.initData(idf, file);
            boutonsupprimerclub.getScene().setRoot(root);
            } else {}    
        } catch (IOException ex) {
            Logger.getLogger(InterfaceClubPresidentController.class.getName()).log(Level.SEVERE, null, ex);
        }     
    }

    @FXML
    private void retour(ActionEvent event) {           
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceClubEtudiant.fxml"));
            Parent root;
            root = loader.load();
            InterfaceClubEtudiantController irc = loader.getController();
            irc.initData(idf,file);
            boutonretour.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(InterfaceCréerClubController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  

    @FXML
    private void organiserevenement(ActionEvent event) {
               
    }

    @FXML
    private void ajouterformation(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("presidentformation.fxml"));
            Parent root;
            root = loader.load();
            presidentformationcontroller irc = loader.getController();
            irc.initData(idf, file, nombreformation.getText(), nombresdesresponsable.getText(), nombresmembre.getText(),nombreevenement);
            boutonretour.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(InterfaceCréerClubController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void traitermembre(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceTraiterMembres.fxml"));
            Parent root;
            root = loader.load();
            InterfaceTraiterMembresController irc = loader.getController();
            irc.initData(idf,file);
            boutontraitermembre.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(InterfaceTraiterMembresController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void traiterresponsables(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceTraiterResponsables.fxml"));
            Parent root;
            root = loader.load();
            InterfaceTraiterResponsablesController irc = loader.getController();
            irc.initData(idf,file);
            boutonresponsables.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(InterfaceTraiterResponsablesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void traiterinvitations(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceTraiterInvitations.fxml"));
            Parent root;
            root = loader.load();
            InterfaceTraiterInvitationsController irc = loader.getController();
            irc.initData(idf,file);
            boutoninvitation.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(InterfaceTraiterInvitationsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void initData(int idf, File file) {
        this.idf=idf;
        this.file=file;
        Image image = new Image(file.toURI().toString());
       utilisateurphoto.setImage(image);
    }
}