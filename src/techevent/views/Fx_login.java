/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techevent.views;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import techevent.services.ServiceProjet;
  
/**
 *
 * @author Wael
 */
public class Fx_login extends Application {
   
 
    @Override
     public void start(Stage primaryStage) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("accueiletudiant.fxml"));
        Scene scene = new Scene(root);
         primaryStage.initStyle(StageStyle.UNDECORATED);
         primaryStage.setScene(scene);
        primaryStage.show();
    }
 public static void main(String[] args) throws SQLException {
        launch(args);
        //ServiceProjet sp=new ServiceProjet();
        //System.out.println(sp.afficherProjetparinvit(1));
 }
}