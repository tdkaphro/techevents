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
import java.time.LocalDate;
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

public class inscriformationcontroller implements Initializable  {
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
   ObservableList<String> list = FXCollections.observableArrayList("tout formation","mes formations");
   ObservableList<String> list1 = FXCollections.observableArrayList("web","reseau","mobile","genieciville","mécanique","electrique");
   ObservableList<String> list2 = FXCollections.observableArrayList("1jour","1semaine","1mois","tout");



      @FXML
    void trievt(ActionEvent event) throws SQLException {
            List<Formation> lf = new ArrayList<Formation>();

        tableau.getItems().clear();
        String dateee=null;
        String formation="*";
        String domainee;
        if(datee.getValue()=="1jour"){
        dateee= String.valueOf(LocalDate.now().plusDays(1));
        }
        if(datee.getValue()=="1semaine"){
        dateee= String.valueOf(LocalDate.now().plusDays(7));
        }
        if(datee.getValue()=="1mois"){
        dateee= String.valueOf(LocalDate.now().plusDays(30));
        }
        if(datee.getSelectionModel().isEmpty()){
        dateee= String.valueOf(LocalDate.now().plusDays(1000));
        }
        if(datee.getValue()=="tout"){
        dateee= String.valueOf(LocalDate.now().plusDays(1000));
        }
        if(prfr.getSelectionModel().isEmpty()){
        formation = "*";
        }
        if( prfr.getValue()=="mes formations"){
            formation = "1";
        }
        if(prd.getSelectionModel().isEmpty()){
            domainee = null;
        }else{
           domainee = prd.getValue();
        }
         ResultSet rs = null;
         ServiceFormation sf = new ServiceFormation();
        if(formation!="*"){
         rs = sf.filtrer(formation,dateee,domainee );
        }
        else{
                     rs = sf.filtrer1(dateee,domainee );     
        }
        b1.setText("");
        try {
            while (rs.next()) {
                Formateur form = new Formateur();
                form.setId(rs.getInt("formateur_id"));
                Formation f = new Formation();
                f.setId(rs.getInt("id"));
                f.setNom(rs.getString("nom"));
                f.setDomaine(rs.getString("domaine"));
                System.out.println(rs.getString("domaine"));
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
    @FXML
    void mapevt(ActionEvent event) {

    }
      @FXML
    void inscrievt(ActionEvent event) throws SQLException {
        ServiceFormation sf = new ServiceFormation();
        if (b1.getText()=="s'incrire"){
        if(sf.ajouterformationaunetudiant(1 ,tableau.getSelectionModel().getSelectedItem().getId())==true){
            b1.setText("se désinscrire");
        }else{
            Alert alert = new Alert(AlertType.INFORMATION);
alert.setTitle("Information Dialog");
alert.setHeaderText(null);
alert.setContentText("le formation est plein");

alert.showAndWait();
        }
        }else{
            sf.desincrire(1 ,tableau.getSelectionModel().getSelectedItem().getId());
            b1.setText("s'incrire");
        }
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
            List<Formation> lf = new ArrayList<Formation>();
         
        prfr.setItems(list);
        prd.setItems(list1);
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
                        formateur.setText(sf.formateurdeformation(rs.getInt("FORMATEUR_ID")));
                        nbrplace.setText(String.valueOf(rs.getInt("capacite")));
                        nbrinscri.setText(String.valueOf(sf.nombredinscri(rs.getInt("id"))));
                        if(rs.getBoolean("CERTIFICATION")==true){
                              certifie.setText("oui");
                        }else{
                              certifie.setText("non");
                        }
                        if(sf.inscri(1,tableau.getSelectionModel().getSelectedItem().getId())==true){
                            b1.setText("s'incrire");
                            
                        }else{
                            b1.setText("se désinscrire");
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
