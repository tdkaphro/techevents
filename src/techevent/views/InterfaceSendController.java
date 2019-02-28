/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techevent.views;

import com.jfoenix.controls.JFXButton;
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
import techevent.utils.SendMail;


/**
 * FXML Controller class
 *
 * @author Hannachi
 */
public class InterfaceSendController implements Initializable {

    @FXML
    private JFXTextField adresseemail;
    @FXML
    private JFXButton boutonconnexion;
    @FXML
    private Button boutonretour;
    @FXML
    private JFXTextField motdepasse;
    
    private String presidentemail;
    private String sujet;
    private String continu;
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
    private void connexion(ActionEvent event) {
       String email=adresseemail.getText();
       String mp=motdepasse.getText();
       SendMail sm=new SendMail();
       sm.send(presidentemail, sujet, continu, email, mp);
    }

    @FXML
    private void retour(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceMesClubs.fxml"));
            Parent root;
            root = loader.load();
            InterfaceMesClubsController irc = loader.getController();
            irc.initData(idf, file);
            boutonretour.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(InterfaceMesClubsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void initdata1(String text) {
       presidentemail=text;
    }

    void initData2(String text) {
        sujet=text;
    }

    void initData3(String text) {
        continu=text;
    }

    void initData(int idf, File file) {
        this.idf=idf;
        this.file=file;
    }
}
