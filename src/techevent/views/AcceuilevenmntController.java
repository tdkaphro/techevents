
package techevent.views;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.lynden.gmapsfx.GoogleMapView;
import java.awt.Component;
import java.awt.Frame;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import techevent.entities.Club;
import techevent.entities.Evenement;
import techevent.entities.President;
import techevent.images.ServiceEvenement;

/**
 * FXML Controller class
 *
 * @author Ciro
 */
public class AcceuilevenmntController implements Initializable {

    @FXML
    private JFXButton ajoutEven;
    @FXML
    private JFXButton sponsorid;
    @FXML
    private Label nomclub;
    @FXML
    private JFXButton modifierEenement;
    @FXML
    private TableView<Evenement> tableau;
      @FXML
    private TableColumn<Evenement, String> id;
    @FXML
    private TableColumn<Evenement, String> nomidd;
    @FXML
    private TableColumn<Evenement, String> dateidd;
    @FXML
    private TableColumn<Evenement, String> payantidd;
    @FXML
    private TableColumn<Evenement, String> typeidd;
    @FXML
    private TableColumn<Evenement, String> étatfinanciéreid;
    @FXML
    private TableColumn<Evenement, Integer> prixid;
    @FXML
    private TableColumn<Evenement,Integer> capaciteid;
    @FXML
    private JFXButton ReporterEven;
    @FXML
    private Label desc;
    @FXML
    private JFXTextArea descidd;
    @FXML
    private TableColumn<Evenement, String> localid;
    @FXML
    private Label nombredesevenement;
    @FXML
    private Label nombresdesrespon;
    @FXML
    private Label nombredesmembres;
    @FXML
    private Label nomprésident;
    @FXML
    private JFXButton listparciipant;
    public static int x;
    public static int y;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List  <Evenement> lf = new ArrayList<Evenement>();
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
                e.setDescription(rs.getString("DESCRIPTION"));
               // e.setEtatdefinancement(rs.getString("PAYANT"));
               e.setDateorganisation(rs.getDate("dateorganisation"));
               // e.setCapacite(rs.getInt("CAPACITER"));
                e.setPrix1(String.valueOf(rs.getInt("PRIX")));
                e.setCapacite1(String.valueOf(rs.getInt("CAPACITER")));
                e.setFinance1(rs.getString("PAYANT"));
                e.setLocalisation(rs.getString("LOCALISATION"));
                lf.add(e);
              /* if (rs.getBoolean("Etatdefinancement") == true){
                    e.setLocalisation("oui");
                }else{
                    e.setLocalisation("non");
                }*/
                
               

                
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(AcceuilevenmntController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<Evenement> listObAbn = FXCollections.observableArrayList(lf);
        nomidd.setCellValueFactory(new PropertyValueFactory<>("nom"));
        typeidd.setCellValueFactory(new PropertyValueFactory<>("type"));
        dateidd.setCellValueFactory(new PropertyValueFactory<>("dateorganisation"));        
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        prixid.setCellValueFactory(new PropertyValueFactory<>("prix1"));        
        capaciteid.setCellValueFactory(new PropertyValueFactory<>("capacite1"));
        payantidd.setCellValueFactory(new PropertyValueFactory<>("finance1"));
        localid.setCellValueFactory(new PropertyValueFactory<>("localisation"));
        


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
    private void ajouterEven(ActionEvent event) throws IOException {
        desc.getScene().getWindow().hide();
        Stage prStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("AjouterEvenement.fxml"));
        Scene scene = new Scene(root);
        prStage.setScene(scene);
        prStage.setResizable(false);
        prStage.show();
    }

    @FXML
    private void Sponsorisation(ActionEvent event) throws IOException {
         desc.getScene().getWindow().hide();
        Stage prStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("offreevenement.fxml"));
        Scene scene = new Scene(root);
        prStage.setScene(scene);
        prStage.setResizable(false);
        prStage.show();
    
        
    }

    @FXML
    private void modifieEvenement(ActionEvent event) throws IOException, SQLException {
        if (tableau.getSelectionModel().getSelectedItem()==null){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Avertissement");
            alert.setHeaderText("Erreur de modification événement ");
            alert.setContentText("Pour avoir la possibilité de modifier il faut selectionner l'evenement du tableau");
            alert.showAndWait();

        }
        else {
            FXMLLoader loader = new FXMLLoader();
                descidd.getScene().getWindow().hide();  
                Stage prStage =new Stage(); 
                loader.setLocation(getClass().getResource("ModifierEvenement.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                ModifierEvenementController mc = loader.getController();
                mc.initData(tableau.getSelectionModel().getSelectedItem().getId());
                prStage.setScene(scene);
                prStage.setResizable(false);
                prStage.show();
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
                        descidd.setText(rs.getString("DESCRIPTION"));
                        descidd.setEditable(false);
                        
                } catch (SQLException ex) {
                    Logger.getLogger(AcceuilevenmntController.class.getName()).log(Level.SEVERE, null, ex);
                }
                 
            }
        });
    }

    @FXML
    private void annulerEvenement(ActionEvent event) throws IOException {  
        
        if (tableau.getSelectionModel().getSelectedItem()==null){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Avertissement");
            alert.setHeaderText("Erreur de suppression de l'événement");
            alert.setContentText("Pour avoir la possibilité d'éffacer il faut selectionner l'evenement du tableau");
            alert.showAndWait();
          

        }
     
        else {
            Evenement e= new Evenement();
            int s=tableau.getSelectionModel().getSelectedItem().getId();
        ServiceEvenement se = new ServiceEvenement();
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Faites attention");
            alert.setHeaderText("Voulez vous supprimer votre Evenement");
            alert.setContentText("Comfirmer la suppression");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
            se.supprimerEvenement(s); // id etudiant connecté
            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setTitle("Succes");
            alert2.setHeaderText(null);
            alert2.setContentText("Votre évenement a été bien supprimé");
            alert2.showAndWait();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("acceuilevenmnt.fxml"));
            Parent root;
            root = loader.load();
            AcceuilevenmntController irc = loader.getController();
            ReporterEven.getScene().setRoot(root);
            } else {}
        
        
        
       /* Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Avertissement");
            alert.setHeaderText("");
            alert.setContentText("Evenement selectionnée est annulée avec succée");
            alert.showAndWait();*/
        
        
        }
        
    }

    @FXML
    private void ReporterEvenement(ActionEvent event) throws IOException, SQLException {
        
        if (tableau.getSelectionModel().getSelectedItem()==null){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Avertissement");
            alert.setHeaderText("Erreur de reporter cet événement ");
            alert.setContentText("Pour avoir la possibilité de reporter il faut selectionner l'événement du tableau");
            alert.showAndWait();

        }
       
        
        else {
            x=tableau.getSelectionModel().getSelectedItem().getId();
            
            FXMLLoader loader = new FXMLLoader();
                descidd.getScene().getWindow().hide();  
                Stage prStage =new Stage(); 
                loader.setLocation(getClass().getResource("ReporterEvenement.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                ReporterEvenementController mc = loader.getController();
                mc.initData1(tableau.getSelectionModel().getSelectedItem().getId());
                prStage.setScene(scene);
                prStage.setResizable(false);
                prStage.show();
            
        }
            
    }

    @FXML
    private void listedesparticipants(ActionEvent event) throws IOException {
          if (tableau.getSelectionModel().getSelectedItem()==null){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Avertissement");
            alert.setHeaderText("Erreur d'affichage de liste des participants");
            alert.setContentText("Il faut selectionner l'évenement où vous voulez afficher la list des ses participants");
            alert.showAndWait();

        }
          else {
              y=tableau.getSelectionModel().getSelectedItem().getId();
        
        
        desc.getScene().getWindow().hide();
        Stage prStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("ListeParticipants.fxml"));
        Scene scene = new Scene(root);
        prStage.setScene(scene);
        prStage.setResizable(false);
        prStage.show();
              
          }
        
        
    }
    
}
    
    
    

