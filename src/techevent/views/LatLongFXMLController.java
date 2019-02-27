package techevent.views;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.javascript.event.GMapMouseEvent;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
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

public class LatLongFXMLController implements Initializable {

     @FXML
    private Button  locate;

    @FXML
    private GoogleMapView googleMapView;

    private GoogleMap map;

    private DecimalFormat formatter = new DecimalFormat("###.00000");
    public Marker marker = null;
    private GeocodingService geocodingService;
    String nom1; String prix1; String capaciter1; String volume1; LocalDate datedebut1; LocalDate datedefin1; String domaine1; String description1; boolean certified1;String formateur1;
    double lon =0; 
    double lat =0;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        googleMapView.addMapInializedListener(() -> configureMap());
        
        
    }

    protected void configureMap() {
        MapOptions mapOptions = new MapOptions();

        mapOptions.center(new LatLong(35.6558688,9.3910852))
                .mapType(MapTypeIdEnum.ROADMAP)
                .zoom(8);
       
        map = googleMapView.createMap(mapOptions, false);
        map.addMouseEventHandler(UIEventType.click, (GMapMouseEvent event) -> {
            LatLong latLong = event.getLatLong();
            
         MarkerOptions markerOptions = new MarkerOptions();
         markerOptions.position( new LatLong(latLong.getLatitude(), latLong.getLongitude()) )
                .visible(Boolean.TRUE)
                .title("My Marker");
        lat =   latLong.getLatitude();
        lon =   latLong.getLongitude();
        System.out.println(lat);
                System.out.println(lon);


         if (marker==null){
         marker = new Marker( markerOptions );
         map.addMarker(marker);
         }else{
             map.removeMarker(marker);
             marker = new Marker( markerOptions );
         map.addMarker(marker);
                 }
        
  

        });
          
    }
    @FXML
     void locateevt(ActionEvent event) throws IOException, SQLException {
    locate.getScene().getWindow().hide();
   FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ajouterformation.fxml"));
       	    Parent root = loader.load();
       	    Scene scene = new Scene(root);
       	    ajouterformationcontroller mc = loader.getController();
       	    mc.initData(lat,lon,nom1,prix1,capaciter1,volume1,datedebut1,datedefin1,domaine1,certified1,formateur1);
       	    Stage prStage = new Stage();
       	    prStage.setScene(scene);
       	    prStage.setResizable(false);
       	    prStage.show();
    }

    void initData(String nom, String prix, String capaciter, String volume, LocalDate datedebut, LocalDate datedefin, String domaine, String description, boolean certified,String formateur) {
        nom1=nom;
        prix1 =prix;
        capaciter1 = capaciter;
        volume1= volume ;
        datedebut1 = datedebut;
        datedefin1 = datedefin;
        domaine1 = domaine;
        certified1 = certified;
        formateur1 = formateur;
        description1 = description;
        
    }

 

   
     
}
