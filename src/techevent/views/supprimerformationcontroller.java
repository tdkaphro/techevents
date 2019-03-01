package techevent.views;

import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
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
 int idf; 
 File file ; 
    String a1;
    String a2; 
    String a3 ; 
    String a4 ; 
    @FXML
    void retourevt(ActionEvent event) throws IOException, SQLException {
                    non.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("presidentformation.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            presidentformationcontroller mc = loader.getController(); 
            mc.initData(idf,file,a1,a2,a3,a4);
            Stage prStage = new Stage();
            prStage.setScene(scene);
            prStage.setResizable(false);
            prStage.show();
    }
    @FXML
    void supprimerevt(ActionEvent event) throws IOException, SQLException {
        ServiceFormation sf = new ServiceFormation();
        sf.supprimerFormation(id1);
            non.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("presidentformation.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            presidentformationcontroller mc = loader.getController(); 
            mc.initData(idf,file,a1,a2,a3,a4);
            Stage prStage = new Stage();
            prStage.setScene(scene);
            prStage.setResizable(false);
            prStage.show();
    }

    void initData(int id,int idf, File file, String a1, String a2, String a3,String a4) {
        id1=id;
        this.idf=idf;
        this.file=file;
        this.a1=a1;
        this.a2=a2;
        this.a3=a3;
        this.a4=a4;
    }
}
