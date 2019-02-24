package techevent.views;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import techevent.entities.Formateur;
import techevent.entities.Formation;
import techevent.services.ServiceFormation;
public class formateurformationcontroller implements Initializable  {
   @FXML
    private GoogleMapView mapid;
    private GoogleMap map;
    public Marker marker = null;
    @FXML
    private Label nompresident;
    @FXML
    private JFXButton b1;
    @FXML
    private JFXButton b14;
    @FXML
    private JFXButton btnmap;
    @FXML
    private JFXComboBox<String> prd;
    @FXML
    private JFXComboBox<String> datee;
    @FXML
    private JFXComboBox<String> prfr;
    @FXML
    private JFXTextArea descriptionid;
    @FXML
    private TableView<Formation> tableau;
    @FXML
    private TableColumn<Formation, String> id;
    @FXML
    private TableColumn<Formation, String> nom;
    @FXML
    private TableColumn<Formation, String> domaine;
    @FXML
    private TableColumn<Formation, String> datedebut;
    @FXML
    private TableColumn<Formation, String> datefin;
    @FXML
   private TableColumn<Formation, String> nbrheure;   
    @FXML
   private Label prix;
    @FXML
   private Label formateur;
    @FXML
   private Label nbrplace;
    @FXML
   private Label nbrinscri;
    @FXML
   private Label certifie;
   ObservableList<String> list = FXCollections.observableArrayList("demande de formation","formation accepter");
   ObservableList<String> list2 = FXCollections.observableArrayList("1jour","1semaine","1mois");
         @FXML
         void trievt(ActionEvent event) {
         List<Formation> lf = new ArrayList<Formation>();
         tableau.getItems().clear();
         String dateee=null;
         String formation="*";
         if(datee.getValue()=="1jour"){
         dateee = "1";
         }
         if(datee.getValue()=="1semaine"){
         dateee = "7";
         }
         if(datee.getValue()=="1mois"){
         dateee = "30";
         }
         if(datee.getValue()=="choisir date"){
         dateee = "1000";
         }
         if(prfr.getValue()=="demande de formation"){
         formation = "1";
         }
         if( prfr.getValue()=="formation accepter"){
         formation = "2";
         }
         ResultSet rs = null;
         ServiceFormation sf = new ServiceFormation();
         if(formation!="1"){
         rs = sf.filtrer3(dateee,1);
         b1.setVisible(false);
         b14.setLayoutY(634);
         b14.setPrefHeight(95);
         }
         else{
         rs = sf.filtrer2(dateee,1);  
         b1.setVisible(true);
         b14.setLayoutY(684);
         b14.setPrefHeight(42);
         b1.setText("accepter");
         }
         try{
            while (rs.next()) {
                Formation f = new Formation();
                f.setId(rs.getInt("id"));
                f.setNom(rs.getString("nom"));
                f.setDomaine(rs.getString("domaine"));
                System.out.println(rs.getString("domaine"));
                Date datedeb = rs.getDate("datedebut");
                Date datefin = rs.getDate("DATEDEFIN");
                f.setVolumehoraire(rs.getInt("VOLUMEHORAIRE"));
                f.setDatedebut((java.sql.Date) datedeb);
                f.setDatedefin((java.sql.Date) datefin);
                Date date = new Date();
                if (date.compareTo(datedeb) < 0) {
                f.setLocalisation("pas commencé");
                } else if (date.compareTo(datefin) > 0) {
                     f.setLocalisation("terminé");
                } else {
                     f.setLocalisation("en progress");
                }
                lf.add(f);
            }
        } catch (SQLException ex) {
            Logger.getLogger(presidentformationcontroller.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<Formation> listObAbn = FXCollections.observableArrayList(lf);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        domaine.setCellValueFactory(new PropertyValueFactory<>("domaine"));
        datedebut.setCellValueFactory(new PropertyValueFactory<>("datedebut"));
        datefin.setCellValueFactory(new PropertyValueFactory<>("datedefin"));
        nbrheure.setCellValueFactory(new PropertyValueFactory<>("volumehoraire")); 
        tableau.setItems(listObAbn);
     try {
         remplirTab();
     } catch (IOException ex) {
         Logger.getLogger(inscriformationcontroller.class.getName()).log(Level.SEVERE, null, ex);
     }
    }
    @FXML
    void mapevt(ActionEvent event) {
    }
         @FXML
    void consulterevt(ActionEvent event) {
    }
      @FXML
    void inscrievt(ActionEvent event) throws SQLException {
        ServiceFormation sf = new ServiceFormation();
        if ("accepter".equals(b1.getText())){
        if(tableau.getSelectionModel().isEmpty()==true){
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("choisirformation");
                    alert.showAndWait();
         
        }   else {
                           sf.confirmer(tableau.getSelectionModel().getSelectedItem().getId());
                           tableau.refresh();
                            }        }
       
    }
       protected void configureMap() {
        MapOptions mapOptions = new MapOptions();
        mapOptions.center(new LatLong(35.6558688, 9.3910852))
                .mapType(MapTypeIdEnum.ROADMAP)
                .zoom(8);
        map = mapid.createMap(mapOptions, false);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        b14.setLayoutY(634);
        b14.setPrefHeight(95);
        List<Formation> lf = new ArrayList<Formation>();  
        prfr.setItems(list);
        b1.setVisible(false);
        datee.setItems(list2);
        mapid.addMapInializedListener(() -> configureMap());
        ServiceFormation sf = new ServiceFormation();
        ResultSet rs = sf.affichertousFormation();
        try {
            while (rs.next()) {
                Formateur form = new Formateur();
                form.setId(rs.getInt("formateur_id"));
                Formation f = new Formation();
                f.setId(rs.getInt("id"));
                f.setNom(rs.getString("nom"));
                f.setDomaine(rs.getString("domaine"));
                f.setDescription(sf.formateurdeformation(rs.getInt("FORMATEUR_ID")));
                Date datedeb = rs.getDate("datedebut");
                Date datefin = rs.getDate("DATEDEFIN");
                f.setVolumehoraire(rs.getInt("VOLUMEHORAIRE"));
                f.setDatedebut((java.sql.Date) datedeb);
                f.setDatedefin((java.sql.Date) datefin);
                Date date = new Date();
                if (date.compareTo(datedeb) < 0) {
                    f.setLocalisation("pas commencé");
                } else if (date.compareTo(datefin) > 0) {
                    f.setLocalisation("terminé");
                } else {
                    f.setLocalisation("en progress");
                }
                lf.add(f);
            }
        } catch (SQLException ex) {
            Logger.getLogger(presidentformationcontroller.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<Formation> listObAbn = FXCollections.observableArrayList(lf);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        domaine.setCellValueFactory(new PropertyValueFactory<>("domaine"));
        datedebut.setCellValueFactory(new PropertyValueFactory<>("datedebut"));
        datefin.setCellValueFactory(new PropertyValueFactory<>("datedefin"));
        nbrheure.setCellValueFactory(new PropertyValueFactory<>("volumehoraire"));
        tableau.setItems(listObAbn);
     try {
         remplirTab();
     } catch (IOException ex) {
         Logger.getLogger(inscriformationcontroller.class.getName()).log(Level.SEVERE, null, ex);
     }
    }
     public void remplirTab() throws IOException {
        tableau.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                    ServiceFormation sf = new ServiceFormation();
                    double lon = 0;
                    double lat = 0;
                try {
                        ResultSet rs = sf.afficherformation(tableau.getSelectionModel().getSelectedItem().getId());
                        rs.next();
                        lon = rs.getDouble("lon");
                        lat = rs.getDouble("lat");
                        descriptionid.setText(rs.getString("description"));
                        descriptionid.setEditable(false);
                        prix.setText(String.valueOf(rs.getInt("prix")));
                        nbrplace.setText(String.valueOf(rs.getInt("capacite")));
                        nbrinscri.setText(String.valueOf(sf.nombredinscri(rs.getInt("id"))));
                        if(rs.getBoolean("CERTIFICATION")==true){
                              certifie.setText("oui");
                        }else{
                              certifie.setText("non");
                        }
                                        
                } catch (SQLException ex) {
                    Logger.getLogger(presidentformationcontroller.class.getName()).log(Level.SEVERE, null, ex);
                }
                  MapOptions mapOptions = new MapOptions();
        mapOptions.center(new LatLong(lat, lon))
                .mapType(MapTypeIdEnum.ROADMAP)
                .zoom(10);

        map = mapid.createMap(mapOptions, false);
                 MarkerOptions markerOptions = new MarkerOptions();
         markerOptions.position( new LatLong(lat, lon) )
                .visible(Boolean.TRUE)
                .title("My Marker");
         if (marker==null){
         marker = new Marker( markerOptions );
         map.addMarker(marker);
         }else{
             map.removeMarker(marker);
             marker = new Marker( markerOptions );
         map.addMarker(marker);
                 }
            }
        });
    }
}
