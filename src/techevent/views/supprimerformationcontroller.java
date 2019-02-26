package techevent.views;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import techevent.services.ServiceFormation;


public class supprimerformationcontroller {

    @FXML
    private JFXButton oui;

    @FXML
    private JFXButton non;
    private int id1 ; 

    @FXML
    void retourevt(ActionEvent event) throws IOException {
        non.getScene().getWindow().hide();  
           FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("presidentformation.fxml"));
       	    Parent root = loader.load();
       	    Scene scene = new Scene(root);
       	    Stage prStage = new Stage();
       	    prStage.setScene(scene);
       	    prStage.setResizable(false);
       	    prStage.show();
    }
    @FXML
    void supprimerevt(ActionEvent event) throws IOException {
        ServiceFormation sf = new ServiceFormation();
        sf.supprimerFormation(id1);
        non.getScene().getWindow().hide();  
               FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("presidentformation.fxml"));
       	    Parent root = loader.load();
       	    Scene scene = new Scene(root);
       	    Stage prStage = new Stage();
       	    prStage.setScene(scene);
       	    prStage.setResizable(false);
       	    prStage.show();
    }

    void initData(int id) {
        id1=id;
    }
}
