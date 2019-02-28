/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techevent.views;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
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
            InterfaceSendController irc3 = loader.getController();
            InterfaceSendController irc4 = loader.getController();
            irc.initdata(presidentemail.getText());
            irc.initData2(sujet.getText());
            irc.initData3(continu.getText());
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
            boutonretour.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(InterfaceMesClubsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void initData(String s) {
       presidentemail.setText(s);
    }
    
}
