package techevent.views;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.javascript.event.GMapMouseEvent;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.javascript.event.GMapMouseEvent;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import techevent.entities.Club;
import techevent.entities.Formateur;
import techevent.entities.Formation;
import techevent.services.ServiceClub;
import techevent.services.ServiceFormation;

public class presidentformationcontroller implements Initializable {

    @FXML
    private Label nomclub;

    @FXML
    private Label nompresident;

    @FXML
    private Label nbrmem;

    @FXML
    private Label nbres;

    @FXML
    private Label nbrfor;

    @FXML
    private JFXButton b1;

    @FXML
    private JFXButton b11;

    @FXML
    private JFXButton b12;

    @FXML
    private JFXButton b13;

    @FXML
    private JFXButton b14;
    @FXML
    private TableView<Formation> tableau;

    @FXML
    private TableColumn<Formation, String> nom;

    @FXML
    private TableColumn<Formation, String> domaine;

    @FXML
    private TableColumn<Formation, String> formateur;

    @FXML
    private TableColumn<Formation, String> inscri;

    @FXML
    private TableColumn<Formation, String> etat;
    @FXML
    private TableColumn<Formation, String> confirmer;
    @FXML
    private JFXButton btnmap;
    @FXML
    private JFXTextArea descriptionid;
    @FXML
    private TableColumn<Formation, String> id;
    @FXML
    private GoogleMapView mapid;
    @FXML
    private ImageView img;
    private GoogleMap map;
    public Marker marker = null;
    List<Formation> lf = new ArrayList<Formation>();
    
    int idf; 
    File file ; 
    String a1;
    String a2; 
    String a3 ; 
    String a4 ; 
    public void initData() throws SQLException {
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
       
    }

    @FXML
    void mapevt(ActionEvent event) {
        System.out.println("aa");
    }

    @FXML
    void ajouteevt(ActionEvent event) throws IOException {
           nomclub.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ajouterformation.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            ajouterformationcontroller mc = loader.getController(); 
            mc.initData(idf,file,a1,a2,a3,a4);
            Stage prStage = new Stage();
            prStage.setScene(scene);
            prStage.setResizable(false);
            prStage.show();
    }

    @FXML
    void modifevt(ActionEvent event) throws IOException, SQLException {

        if (tableau.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("selectioner le formation que vous voulez modifier!!!");
            alert.showAndWait();
        } else if (tableau.getSelectionModel().getSelectedItem().isConfirme() == true) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("vouz pouvez modifier une formation déja accepter par le formateur!!!");
            alert.showAndWait();
        } else {
            FXMLLoader loader = new FXMLLoader();
            tableau.getScene().getWindow().hide();
            Stage prStage = new Stage();
            loader.setLocation(getClass().getResource("modifierformation.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            ServiceFormation sf = new ServiceFormation();
            ResultSet rs = sf.afficherformation(tableau.getSelectionModel().getSelectedItem().getId());
            rs.next();
            modifierformationcontroller mc = loader.getController();
            int id = rs.getInt("id");
            String nom = rs.getString("nom");
            double prix = rs.getDouble("prix");
            String description = rs.getString("description");
            int capaciter = rs.getInt("CAPACITE");
            int voulmehoraire = rs.getInt("VOLUMEHORAIRE");
            java.sql.Date datedebut = rs.getDate("datedebut");
            java.sql.Date datefinn = rs.getDate("datedefin");
            Double lat = rs.getDouble("lat");
            Double lon = rs.getDouble("lon");
            Boolean certifie = rs.getBoolean("CERTIFICATION");
            String domaine = rs.getString("domaine");
            ResultSet rsf = sf.formateurdeformation2(rs.getInt("FORMATEUR_ID"));
            rsf.next();
            int idformateur = rsf.getInt("id");
            String nomformateur = rsf.getString("nom");
            String prenomformateur = rsf.getString("prenom");
            mc.initData(id, nom, prix, description, capaciter, voulmehoraire, datedebut, datefinn, lat, lon, certifie, domaine, idformateur, nomformateur, prenomformateur);
            prStage.setScene(scene);
            prStage.setResizable(false);
            prStage.show();
        }
    }

    @FXML
    void suppevt(ActionEvent event) throws IOException {
        if (tableau.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("selectioner le formation que vous voulez supprimer!!!");
            alert.showAndWait();
        } else {
            tableau.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("supprimerformation.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            supprimerformationcontroller mc = loader.getController();
            mc.initData(tableau.getSelectionModel().getSelectedItem().getId());
            Stage prStage = new Stage();
            prStage.setScene(scene);
            prStage.setResizable(false);
            prStage.show();
        }
    }

    private static void notifier(String pTitle, String pMessage) {
        Platform.runLater(() -> {
            Stage owner = new Stage(StageStyle.TRANSPARENT);
            StackPane root = new StackPane();
            root.setStyle("-fx-background-color: TRANSPARENT");
            Scene scene = new Scene(root, 1, 1);
            scene.setFill(Color.TRANSPARENT);
            owner.setScene(scene);
            owner.setWidth(1);
            owner.setHeight(1);
            owner.toBack();
            owner.show();
            Notifications a = Notifications.create()
                    .title("aa")
                    .text("ahmed")
                    .graphic(null)
                    .hideAfter(Duration.seconds(1000))
                    .position(Pos.BOTTOM_RIGHT)
                    .onAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            owner.hide();
                        }
                    });
            a.darkStyle();
            a.showInformation();

        }
        );
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

                } catch (SQLException ex) {
                    Logger.getLogger(presidentformationcontroller.class.getName()).log(Level.SEVERE, null, ex);
                }
                MapOptions mapOptions = new MapOptions();

                mapOptions.center(new LatLong(lat, lon))
                        .mapType(MapTypeIdEnum.ROADMAP)
                        .zoom(10);
                map = mapid.createMap(mapOptions, false);
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(new LatLong(lat, lon))
                        .visible(Boolean.TRUE)
                        .title("My Marker");
                if (marker == null) {
                    marker = new Marker(markerOptions);
                    map.addMarker(marker);
                } else {
                    map.removeMarker(marker);
                    marker = new Marker(markerOptions);
                    map.addMarker(marker);
                }
            }
        });
    }

    @FXML
    void formateurcon(ActionEvent event) throws SQLException {
 if (tableau.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("selectioner le formation que vous voulez consulter son formateur!!!");
            alert.showAndWait();
        } else {
        ServiceFormation sf = new ServiceFormation();
        ResultSet rs = sf.formateurdeformation3(tableau.getSelectionModel().getSelectedItem().getId());
        System.out.println(tableau.getSelectionModel().getSelectedItem().getId());
        while(rs.next()){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("nom: "+rs.getString("nom")+" prenom: "+rs.getString("prenom"));
        alert.setContentText("numerotelephone: " +rs.getLong("NUMEROTELEPHONE") +"email :"+ rs.getString("email"));
        alert.showAndWait();
        }
 }
    }
    
    @FXML
    void listepar(ActionEvent event) throws IOException, SQLException {
         if (tableau.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("selectioner le formation que vous voulez voir ces participant!!!");
            alert.showAndWait();
                } else {
            tableau.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("partiformation.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            partiformationcontroller mc = loader.getController();
            mc.initData(tableau.getSelectionModel().getSelectedItem().getId());
            Stage prStage = new Stage();
            prStage.setScene(scene);
            prStage.setResizable(false);
            prStage.show();
    }
    }

    void initData(int idf, File file, String a1, String a2, String a3,String a4) {
        this.idf=idf;
        this.file=file;
        this.a1=a1;
        this.a2=a2;
        this.a3=a3;
        this.a4=a4;
        System.out.println(idf);
        nomclub.setText(a1);
        nbrmem.setText(a3);
        nbres.setText(a2);
        nbrfor.setText(a4);
         Image image = new Image(file.toURI().toString(),142,145,false,false);
        img.setImage(image);
         try {
            mapid.addMapInializedListener(() -> configureMap());
            ServiceFormation sf = new ServiceFormation();
            ServiceClub sc = new ServiceClub();
            System.out.println(idf);
            System.out.println(sc.getMonClubId(idf));
            ResultSet rs = sf.formationparclub(sc.getMonClubId(idf));
            try {
                while (rs.next()) {
                    Formateur form = new Formateur();
                    form.setId(rs.getInt("formateur_id"));
                    Formation f = new Formation();
                    f.setId(rs.getInt("id"));
                    f.setNom(rs.getString("nom"));
                    f.setDomaine(rs.getString("domaine"));
                    f.setC("null");
                    if (rs.getBoolean("confirme") == true) {
                        f.setC("oui");
                    } else {
                        f.setC("non");
                    }
                    f.setDescription(sf.formateurdeformation(rs.getInt("FORMATEUR_ID")));
                    f.setVolumehoraire(sf.nombredinscri(rs.getInt("id")));
                    Date datedeb = rs.getDate("datedebut");
                    Date datefin = rs.getDate("DATEDEFIN");
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
            nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            domaine.setCellValueFactory(new PropertyValueFactory<>("domaine"));
            formateur.setCellValueFactory(new PropertyValueFactory<>("description"));
            inscri.setCellValueFactory(new PropertyValueFactory<>("volumehoraire"));
            etat.setCellValueFactory(new PropertyValueFactory<>("localisation"));
            confirmer.setCellValueFactory(new PropertyValueFactory<>("c"));
            tableau.setItems(listObAbn);
            try {
                remplirTab();
                
            } catch (IOException ex) {
                Logger.getLogger(presidentformationcontroller.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(presidentformationcontroller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
}
