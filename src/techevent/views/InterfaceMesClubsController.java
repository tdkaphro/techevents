/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techevent.views;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import techevent.entities.Club;
import techevent.entities.Etudiant;
import techevent.services.ServiceClub;

/**
 * FXML Controller class
 *
 * @author Hannachi
 */
public class InterfaceMesClubsController implements Initializable {

    @FXML
    private TableView<Club> tableclub;
    @FXML
    private TableColumn<Club, String> colonneid;
    @FXML
    private TableColumn<Club, String> colonnenom;
    @FXML
    private TableColumn<Club, String> colonnedomaine;
    @FXML
    private Button boutonscontacter;
    @FXML
    private Button boutonsquiter;
    @FXML
    private Button boutonretour;
    @FXML
    private Label mesclub;
    @FXML
    private ImageView utilisateurphoto;
    int idf;
    File file;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
    }    

    @FXML
    private void contacter(ActionEvent event) throws SQLException {
        if(tableclub.getSelectionModel().getSelectedItem() == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Aucun club est selectionné");
            alert.showAndWait();
        }
        else{
            try {
            Club c=tableclub.getSelectionModel().getSelectedItem();
            ServiceClub sc = new ServiceClub();
            int id=c.getId();
            ResultSet  rs=sc.afficherPresident(id);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceEmail.fxml"));
            Parent root;
            root = loader.load();
            InterfaceEmailController irc2 = loader.getController();     
            InterfaceEmailController irc = loader.getController();
            String s=rs.getString(4);
            irc.initData(idf,file);
            irc2.initData2(rs.getString(4));
            boutonscontacter.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(InterfaceEmailController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }

    @FXML
    private void quitter(ActionEvent event) throws SQLException{
        Club c=tableclub.getSelectionModel().getSelectedItem();
        if(tableclub.getSelectionModel().getSelectedItem() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Aucun club est selectionné");
            alert.showAndWait();
        }
        else{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Attention");
            alert.setHeaderText("Vouler vouz quitter "+c.getNom());
            alert.setContentText("Continuez ?");
            Optional<ButtonType> result = alert.showAndWait(); 
            if (result.get() == ButtonType.OK){
            ServiceClub sc=new ServiceClub();
            ServiceClub sc2= new ServiceClub();
            sc.quitterclub(idf, c.getId());
            ServiceClub sc3 = new ServiceClub();
            int n=sc3.mesclub(idf);
            mesclub.setText(Integer.toString(n));
            List<Club> list = sc2.clubspersonel(idf);
            ObservableList<Club> obslist = FXCollections.observableArrayList(list);
            colonneid.setCellValueFactory(new PropertyValueFactory<>("id"));
            colonnenom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            colonnedomaine.setCellValueFactory(new PropertyValueFactory<>("domaineduclub"));
            tableclub.setItems(obslist);
            }
        }
    }

    @FXML
    private void retour(ActionEvent event) throws SQLException {
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

    void initData(int idf, File file) {
       this.idf=idf; 
       this.file=file;
       Image image = new Image(file.toURI().toString(),142,145,false,false);
       utilisateurphoto.setImage(image);
           try {
            // TODO
            ServiceClub sc = new ServiceClub();
            ServiceClub sc2 = new ServiceClub();
            int n=sc.mesclub(idf);
            mesclub.setText(Integer.toString(n));
            List<Club> list = sc2.clubspersonel(idf);
            ObservableList<Club> obslist = FXCollections.observableArrayList(list);
            colonneid.setCellValueFactory(new PropertyValueFactory<>("id"));
            colonnenom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            colonnedomaine.setCellValueFactory(new PropertyValueFactory<>("domaineduclub"));
            tableclub.setItems(obslist);
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceMesClubsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
