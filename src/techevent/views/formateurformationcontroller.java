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
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import techevent.entities.Formateur;
import techevent.entities.Formation;
import techevent.services.ServiceFormation;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class formateurformationcontroller implements Initializable {

    @FXML
    private GoogleMapView mapid;
    private GoogleMap map;
    public Marker marker = null;
    @FXML
    private Label nompresident;  
    @FXML
    private JFXButton ref;
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
    @FXML
    private JFXButton retour;
    @FXML
    private ImageView img;
    int idf;
    File file;
    String dateee;

    ObservableList<String> list = FXCollections.observableArrayList("demande de formation", "formation accepter");
    ObservableList<String> list2 = FXCollections.observableArrayList("1jour", "1semaine", "1mois", "tout");

    @FXML
    void retourevt(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        nbrplace.getScene().getWindow().hide();
        Stage prStage = new Stage();
        loader.setLocation(getClass().getResource("accueilformateur.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        AccueilformateurController mc = loader.getController();
        mc.initData(idf);
        prStage.setScene(scene);
        prStage.setResizable(false);
        prStage.show();
    }

    @FXML
    void trievt(ActionEvent event) {
        List<Formation> lf = new ArrayList<Formation>();
        tableau.getItems().clear();
        dateee = null;
        String formation = "*";
        if (datee.getValue() == "1jour") {
            dateee = String.valueOf(LocalDate.now().plusDays(1));
        }
        if (datee.getValue() == "1semaine") {
            dateee = String.valueOf(LocalDate.now().plusDays(7));
        }
        if (datee.getValue() == "1mois") {
            dateee = String.valueOf(LocalDate.now().plusDays(30));
        }
        if (datee.getSelectionModel().isEmpty()) {
            dateee = String.valueOf(LocalDate.now().plusDays(1000));
        }
        if (datee.getValue() == "tout") {
            dateee = String.valueOf(LocalDate.now().plusDays(1000));
        }
        if (prfr.getValue() == "demande de formation") {
            formation = "1";
        }
        if (prfr.getValue() == "formation accepter") {
            formation = "2";
        }
        if (prfr.getSelectionModel().isEmpty()) {
            formation = "1";
        }
        ResultSet rs = null;
        ServiceFormation sf = new ServiceFormation();
        if (formation == "1") {
            rs = sf.filtrer2(dateee, idf);
            b1.setVisible(true);
            b14.setLayoutY(684);
            b14.setPrefHeight(42);
            b1.setText("accepter");
            ref.setVisible(true);
        } else {
            rs = sf.filtrer3(dateee, idf);
            b1.setVisible(false);
            b14.setLayoutY(634);
            b14.setPrefHeight(95);
            ref.setVisible(false);
        }
        try {
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
        if ("accepter".equals(b1.getText())) {
            if (tableau.getSelectionModel().isEmpty() == true) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("choisirformation");
                alert.showAndWait();

            } else {
                ResultSet rsff = sf.formateurdeformation1(idf);
                Boolean samedate = false;
                Date a = tableau.getSelectionModel().getSelectedItem().getDatedebut();
                Date b = tableau.getSelectionModel().getSelectedItem().getDatedefin();
                while (rsff.next()) {
                    if (rsff.getBoolean("confirme") == true) {

                        if (rsff.getDate("datedebut").after(a) && rsff.getDate("datedefin").before(b)) {
                            samedate = true;
                        }
                        if (rsff.getDate("datedebut").before(a) && rsff.getDate("datedefin").after(a)) {
                            samedate = true;

                        }

                        if (rsff.getDate("datedebut").before(b) && rsff.getDate("datedefin").after(b)) {
                            samedate = true;
                        }

                    }
                }
                if (samedate == true) {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("vouz avez déja une formation dans la meme péroide!!!");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation Dialog");
                    alert.setHeaderText("Look, a Confirmation Dialog");
                    alert.setContentText("Are you ok with this?");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK) {
                        sf.confirmer(tableau.getSelectionModel().getSelectedItem().getId());
                        tableau.getItems().clear();
                        ResultSet rs = sf.filtrer2(dateee, idf);
                        List<Formation> lf = new ArrayList<Formation>();

                        try {
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

                    }
                }
            }
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
        b14.setLayoutY(634);
        b14.setPrefHeight(95);
        ref.setVisible(false);
        b1.setVisible(false);
        datee.setItems(list2);
        prfr.setItems(list);

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
                    if (rs.getBoolean("CERTIFICATION") == true) {
                        certifie.setText("oui");
                    } else {
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
    void initData(int idf,File file) {
        this.idf = idf;
   Image image = new Image(file.toURI().toString(),142,145,false,false);
        img.setImage(image);
        TrayNotification notifrejoindre= new TrayNotification();
         ServiceFormation sf = new ServiceFormation();
            int hh = 0;
            System.out.println(idf);
            try {
                hh = sf.nombredeformation(idf);
            } catch (SQLException ex) {
                Logger.getLogger(formateurformationcontroller.class.getName()).log(Level.SEVERE, null, ex);
            }
        
          
            notifrejoindre.setTray("information", "vouz avez "+String.valueOf(hh)+" nouveaux formation", NotificationType.INFORMATION);
            notifrejoindre.showAndDismiss(Duration.seconds(5));
    }
        @FXML
    void refuser(ActionEvent event) {
                ServiceFormation sf = new ServiceFormation();
             Alert alert = new Alert(AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation Dialog");
                    alert.setHeaderText("Confirmation Dialog");
                    alert.setContentText("vous êtes sûr que vous voulez refuser l'offre");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK) {
                        sf.supprimerFormation(tableau.getSelectionModel().getSelectedItem().getId());
                        tableau.getItems().clear();
                        ResultSet rs = sf.filtrer2(dateee, idf);
                        List<Formation> lf = new ArrayList<Formation>();

                        try {
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

    }
}
       @FXML
    void consulterorg(ActionEvent event) throws SQLException {
        ServiceFormation sf = new ServiceFormation();
        ResultSet rs = sf.afficherformation(tableau.getSelectionModel().getSelectedItem().getId());
        rs.next();
        ResultSet rs1 = sf.afficherorganisateur(rs.getInt("CLUB_ID"));
        while(rs1.next()){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("nom: "+rs1.getString("nom")+" prenom: "+rs1.getString("prenom"));
        alert.setContentText("numerotelephone: " +rs1.getLong("NUMEROTELEPHONE") +"email :"+ rs1.getString("email"));
        alert.showAndWait();
        }
    }     
}
