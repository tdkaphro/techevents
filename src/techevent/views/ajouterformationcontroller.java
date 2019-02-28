package techevent.views;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import techevent.entities.Club;
import techevent.entities.Formateur;
import techevent.entities.Formation;
import techevent.services.ServiceFormation;
import java.util.*;
public class ajouterformationcontroller implements Initializable {

    
    @FXML
    private JFXTextField nomid;

    @FXML
    private JFXTextField prixid;

    @FXML
    private JFXTextField capaciterid;

    @FXML
    private JFXTextField volumeid;

    @FXML
    private JFXDatePicker datedeb;

    @FXML
    private JFXDatePicker datefin;

    @FXML
    private JFXButton choisir;

    @FXML
    private JFXTextField domaineid;

    @FXML
    private JFXTextArea descriptionid;

    @FXML
    private JFXButton ajouter;
    @FXML
    private JFXCheckBox certifieid;
    
    @FXML
    private JFXComboBox<String> cbdomaine;

    @FXML
    private JFXComboBox<String> cbformateur;
      @FXML
    private Label nbrchar;

    String nom1; String prix1; String capaciter1; String volume1; LocalDate datedebut1; LocalDate datedefin1; String domaine1; String description1; boolean certified1;String formateur1;

    
            public double lat1=0;
            public double lon1=0;
            
              
       ObservableList<String> list = FXCollections.observableArrayList("web","reseau","mobile","genieciville","mécanique","electrique");

     
    @FXML
    void ajouterevt(ActionEvent event) throws IOException {
	java.util.Date date = new java.util.Date();
        int aa = 0;
        if(nomid.getText()==null||prixid.getText()==null||capaciterid.getText()==null||volumeid.getText()==null||datedeb.getValue()==null||datefin.getValue()==null||cbformateur.getSelectionModel().isEmpty()||descriptionid.getText()==null){
       Alert alert = new Alert(Alert.AlertType.INFORMATION);
                             alert.setTitle("Information Dialog");
                             alert.setHeaderText(null);
                             alert.setContentText("remplir tous les champ!!!");
                             alert.showAndWait();
                             aa=1;
        }
            if(!prixid.getText().matches("[0-9]+")||!capaciterid.getText().matches("[0-9]+")||!volumeid.getText().matches("[0-9]+")){
                   Alert alert = new Alert(Alert.AlertType.INFORMATION);
                             alert.setTitle("Information Dialog");
                             alert.setHeaderText(null);
                             alert.setContentText("remplir les champs des numero avec des numero!!!");
                             alert.showAndWait();
                          aa=1;

                }
            
            if(Date.valueOf(datedeb.getValue()).after(Date.valueOf(datefin.getValue()))||Date.valueOf(datedeb.getValue()).before(date)){
                   Alert alert = new Alert(Alert.AlertType.INFORMATION);
                             alert.setTitle("Information Dialog");
                             alert.setHeaderText(null);
                             alert.setContentText("remplir les date correctement");
                             alert.showAndWait();
                             aa=1;

            } if(aa==0){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("formation ajoutee avec succes!!");
                alert.showAndWait();
                 Formation a = new Formation();
    a.setNom(nomid.getText());
    a.setPrix(Double.parseDouble(prixid.getText()));
    a.setCapacite(Integer.parseInt(capaciterid.getText()));
    a.setVolumehoraire(Integer.parseInt(volumeid.getText()));
    a.setDatedebut(Date.valueOf(datedeb.getValue()));
    a.setDatedefin(Date.valueOf(datefin.getValue()));
    a.setDomaine(cbdomaine.getValue());
    a.setDescription(descriptionid.getText());
    if(certifieid.isSelected()){
        a.setCertification(true);
    }else{
        a.setCertification(false);
    }
    char h =cbformateur.getValue().charAt(0);
    int e = Integer.parseInt(String.valueOf(h));
    Formateur fo = new Formateur();
    fo.setId(e);
    ServiceFormation sf = new ServiceFormation();
    a.setLon(lon1);
    a.setLat(lat1);
    sf.ajouterformationdeclub(a, 2, e);
    ajouter.getScene().getWindow().hide();  
    Stage prStage =new Stage(); 
    Parent root = FXMLLoader.load(getClass().getResource("presidentformation.fxml"));
    Scene scene = new Scene(root);
    prStage.setScene(scene);
    prStage.setResizable(false);
    prStage.show();
            }
   
    }      
    @FXML
    void choisirevt(ActionEvent event) throws IOException {
    String nom = nomid.getText();
    String prix = prixid.getText();
    String capaciter = capaciterid.getText();
    String volume = volumeid.getText();
    LocalDate datedebut = datedeb.getValue();
    LocalDate datedefin = datefin.getValue();
    String domaine = cbdomaine.getValue();
    String description = descriptionid.getText();
    String formateur = cbformateur.getValue();
    System.out.println(nom);
    boolean certified =false;
    if(certifieid.isSelected()){
        certified = true;
    }else{
        certified = false;
    }    
    FXMLLoader loader = new FXMLLoader();
    ajouter.getScene().getWindow().hide();  
    Stage prStage =new Stage(); 
    loader.setLocation(getClass().getResource("Scene.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    LatLongFXMLController mc = loader.getController();
    mc.initData(nom,prix,capaciter,volume,datedebut,datedefin,domaine,description,certified,formateur);
    prStage.setScene(scene);
    prStage.setResizable(false);
    prStage.show();
        
    }
 void initData(double lat, double lon, String nom, String prix, String capaciter, String volume, LocalDate datedebut, LocalDate datedefin, String domaine, boolean certified, String formateur) throws SQLException {
        lat1 = lat; 
        lon1 = lon;
        nom1=nom;
        prix1 = prix;
        capaciter1 = capaciter;
        volume1= volume ;
        datedebut1 = datedebut;
        datedefin1 = datedefin;
        domaine1 = domaine;
        certified1 = certified;
        formateur1 = formateur;
          nomid.setText(nom1);
    prixid.setText(prix1);
    capaciterid.setText(capaciter1);
    volumeid.setText(volume1);
    datedeb.setValue(datedebut1);
    datefin.setValue(datedefin1);
    cbdomaine.setValue(domaine1);
    descriptionid.setText(description1);
     ServiceFormation sf = new ServiceFormation();
        ObservableList<String> listf = FXCollections.observableArrayList();
        ResultSet rs = sf.formateurpardomaine(domaine1);
        while (rs.next()){
            listf.add(rs.getInt("id")+"."+rs.getString("nom")+" "+rs.getString("prenom"));
        }
        cbformateur.setItems(listf);
       
    
    cbformateur.setValue(formateur1);
    if(certified1 == true){
        certifieid.setSelected(true);
    }
        
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    cbdomaine.setItems(list);
 
    }
      @FXML
    void nombrecharactere(KeyEvent event) {
        nbrchar.setText(String.valueOf(255-descriptionid.getText().length()-1)+ "characteres");
    }
    @FXML
    void changerliste(ActionEvent event) throws SQLException {
        ServiceFormation sf = new ServiceFormation();
        ObservableList<String> listf = FXCollections.observableArrayList();
        ResultSet rs = sf.formateurpardomaine(cbdomaine.getValue());
        while (rs.next()){
            listf.add(rs.getInt("id")+"."+rs.getString("nom")+" "+rs.getString("prenom"));
        }
        cbformateur.setItems(listf);
       
    }

 
}
