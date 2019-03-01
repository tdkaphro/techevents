/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techevent.views;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Hannachi
 */
public class InterfaceEmailController implements Initializable {

    @FXML
    private JFXTextField presidentemail;
    @FXML
    private JFXTextField sujet;
    @FXML
    private JFXTextArea continu;
    @FXML
    private JFXButton boutonsend;
    @FXML
    private Button boutonretour;
    int idf;
    File file;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    

    @FXML
    private void send(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceSend.fxml"));
            Parent root;
            root = loader.load();
            InterfaceSendController irc = loader.getController();
            InterfaceSendController irc2 = loader.getController();
            irc.initdata1(presidentemail.getText(),sujet.getText(),continu.getText());
            irc2.initData(idf,file);
            boutonsend.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(InterfaceSendController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void retour(ActionEvent event) {
          try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceMesClubs.fxml"));
            Parent root;
            root = loader.load();
            InterfaceMesClubsController irc = loader.getController();
            irc.initData(idf,file);
            boutonretour.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(InterfaceMesClubsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void initData2(String s) {
       presidentemail.setText(s);
    }



    void initData(int idf, File file) {
       this.idf=idf; 
       this.file=file;
    }
    
}
