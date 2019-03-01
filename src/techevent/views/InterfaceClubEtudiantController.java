/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techevent.views;

import com.jfoenix.controls.JFXComboBox;
import java.io.File;
import java.io.IOException;
import java.net.URL;
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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import static jdk.nashorn.internal.runtime.Debug.id;
import techevent.entities.Club;
import techevent.services.ServiceClub;

/**
 * FXML Controller class
 *
 * @author Hannachi
 */
public class InterfaceClubEtudiantController implements Initializable {

    @FXML
    private TableView<Club> tableclub;
    @FXML
    private TableColumn<Club, String> colonneid;
    @FXML
    private TableColumn<Club, String> colonnenom;
    @FXML
    private TableColumn<Club, String> colonnedomaine;
    @FXML
    private TableColumn<Club, String> colonnefraisinscription;
    
    
    @FXML
    private Label nombresdesclub;
    @FXML
    private Button boutonmonclub;
    @FXML
    private Button boutonsinformerclub;
    @FXML
    private Button boutonrejoindreclub;
    @FXML
    private Button boutoncréerclub;
    
    @FXML
    private ImageView utilisateurphoto;
    
    int idf;
    File file;
    
    @FXML
    private JFXComboBox<String> comboboxdomaine;
    ObservableList<String> domaine = FXCollections.observableArrayList(
            "Tous",
            "Informatique",
            "Réseau",
            "Robotique",
            "Aéronautique",
            "Chimie",
            "Biologie"
    );
    
    @FXML
    private Button boutonmesclub;
    @FXML
    private Button boutonretour;
   
      
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboboxdomaine.setItems(domaine);
        
    }

    @FXML
    private void monclub(ActionEvent event) throws SQLException{
            ServiceClub cs = new ServiceClub();
            int monclubid=cs.getMonClubId(idf); // id d'étudiant connecté
            
            ServiceClub cs2 = new ServiceClub();    
            if(monclubid==0)
            {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Vous n'étes pas encore un président d'un club");
            alert.showAndWait();
            }
            else {
                if(!cs.etatduclub(idf)) { // id d'étudiant connecté
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Merci de patienter");
            alert.setHeaderText("Votre demande n'est pas encore traitée");
            alert.setContentText("Voulez vous l'annuler ?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
            ServiceClub cs3=new ServiceClub();
            cs3.SupprimerClub(idf); // id d'étudiant connecté
            } else {}
                   }
                    else{
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceClubPresident.fxml"));
                            Parent root;
                            root = loader.load();
                            InterfaceClubPresidentController irc = loader.getController();
                            irc.initData(idf,file);
                            boutoncréerclub.getScene().setRoot(root);
                        } catch (IOException ex) {
                            Logger.getLogger(InterfaceClubEtudiantController.class.getName()).log(Level.SEVERE, null, ex);
                        }
            }
                    }
    }
   

    @FXML
    private void sinformerclub(ActionEvent event) throws SQLException {
            if(tableclub.getSelectionModel().getSelectedItem() == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Aucun club est selectionné");
            alert.showAndWait();
            }
            else{
            Club cl=tableclub.getSelectionModel().getSelectedItem();
            ServiceClub sc2= new ServiceClub();
            String s=" ";
            ResultSet rs2=sc2.afficherPresident(cl.getId());
            s=rs2.getString(6)+" "+rs2.getString(8)+" et son email "+rs2.getString(4);
            ServiceClub sc=new ServiceClub();
            int clubid=cl.getId();
            ResultSet rs=sc.afficherClub(clubid);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("INFORMATIONS");
            alert.setHeaderText(rs.getString(6));
            alert.setContentText("C'est un club fondé en "+rs.getString(3)+" specifié au domaine "+rs.getString(4)+"\n"
            +"Son président :"+s);
            alert.showAndWait();
            }
        }
    

    @FXML
    private void creerclub(ActionEvent event) throws SQLException {
        try {
            ServiceClub ps=new ServiceClub();
            int test=ps.getMonClubId(idf); // id d'étudiant connecté
            if(test!=0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Vous avez déja crée un club");
            alert.showAndWait();
            }
            else{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceCréerClub.fxml"));
            Parent root;
            root = loader.load();
            InterfaceCréerClubController irc = loader.getController();
            irc.initData(idf,file);
            boutoncréerclub.getScene().setRoot(root);
            Scene scene = new Scene(root);
            Stage prStage = new Stage();
            prStage.setScene(scene);
            prStage.setResizable(false);
            prStage.show();
            }
        } catch (IOException ex) {
            Logger.getLogger(InterfaceClubEtudiantController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void affichage(ActionEvent event) {
         try {
            ServiceClub ps = new ServiceClub();
            String s;
            s=comboboxdomaine.getValue();
            if(s.equals("Tous")){
            List<Club> list = ps.trierClub(idf); // id d'étudiant connecté
            ObservableList<Club> obslist = FXCollections.observableArrayList(list);
            colonneid.setCellValueFactory(new PropertyValueFactory<>("id"));
            colonnenom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            colonnedomaine.setCellValueFactory(new PropertyValueFactory<>("domaineduclub"));
            colonnefraisinscription.setCellValueFactory(new PropertyValueFactory<>("fraisinscription"));
            tableclub.setItems(obslist);
            }
            else{
            List<Club> list2 = ps.trierClubParDomaine(idf,s); // id d'étudiant connecté
            ObservableList<Club> obslist2 = FXCollections.observableArrayList(list2);
            colonneid.setCellValueFactory(new PropertyValueFactory<>("id"));
            colonnenom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            colonnedomaine.setCellValueFactory(new PropertyValueFactory<>("domaineduclub"));
            colonnefraisinscription.setCellValueFactory(new PropertyValueFactory<>("fraisinscription"));
            tableclub.setItems(obslist2);
            }
            tableclub.refresh();
            
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceClubEtudiantController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void rejoindreclub(ActionEvent event) throws SQLException{
        Club cl=tableclub.getSelectionModel().getSelectedItem();
            ServiceClub sc=new ServiceClub();
            ServiceClub sc3= new ServiceClub();
            ServiceClub sc2=new ServiceClub(); 
        if(tableclub.getSelectionModel().getSelectedItem() == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Aucun club est selectionné");
            alert.showAndWait();
            }
        else if(sc3.verifierMembre(idf, cl.getId()))
            {
            Alert alert5 = new Alert(Alert.AlertType.INFORMATION);
            alert5.setTitle("Merci");
            alert5.setHeaderText(null);
            alert5.setContentText("Vous étes déja membre  ");
            alert5.showAndWait();}
        else if(sc2.verifierInvitation(idf, cl.getId())==1){ // id d'étudiant connecté
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Merci de patienter");
            alert.setHeaderText("Votre demande de rejoindre "+cl.getNom()+" n'est pas encore traitée");
            alert.setContentText("Voulez vous l'annuler ?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
            ServiceClub cs3=new ServiceClub();
            cs3.AnnulerInvitation(idf ,cl.getId()); // id d'étudiant connecté
            } 
        }
            else{
            sc.DemandeDeRejoindre(idf , cl.getId()); // id d'étudiant connecté
            Alert alert6 = new Alert(Alert.AlertType.INFORMATION);
            alert6.setTitle("Merci");
            alert6.setHeaderText(null);
            alert6.setContentText("Votre demande de rejoindre "+cl.getNom()+" a été enregistrée ");
            alert6.showAndWait();
            }
        }
    
    @FXML
    private void mesclub(ActionEvent event) throws SQLException {
        ServiceClub sc= new ServiceClub();
        int n=sc.mesclub(idf);
        if(n==0)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Vous n'étes pas un membre dans un aucun club");
            alert.showAndWait();
        }
        else{
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceMesClubs.fxml"));
                Parent root;
                root = loader.load();
                InterfaceMesClubsController irc = loader.getController();
                irc.initData(idf,file);
                boutonmesclub.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(InterfaceMesClubsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    @FXML
    private void retour(ActionEvent event) {
        try {
         
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Accueiletudiant.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
             AccueiletudiantController irc = loader.getController();
            irc.initData(idf);;
            Stage prStage = new Stage();
            prStage.setScene(scene);
            prStage.setResizable(false);
            prStage.show();
        } catch (IOException ex) {
            Logger.getLogger(AccueiletudiantController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void initData(int idf, File file) throws SQLException{
       this.idf=idf; 
       this.file=file;
       Image image = new Image(file.toURI().toString());
       utilisateurphoto.setImage(image);
       ServiceClub ps=new ServiceClub();
       List<Club> list = ps.trierClub(idf); // id d'étudiant connecté
       ObservableList<Club> obslist = FXCollections.observableArrayList(list);
       colonneid.setCellValueFactory(new PropertyValueFactory<>("id"));
       colonnenom.setCellValueFactory(new PropertyValueFactory<>("nom"));
       colonnedomaine.setCellValueFactory(new PropertyValueFactory<>("domaineduclub"));
       colonnefraisinscription.setCellValueFactory(new PropertyValueFactory<>("fraisinscription"));
       tableclub.setItems(obslist);
    }
}
