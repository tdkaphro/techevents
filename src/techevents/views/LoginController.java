/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techevents.views;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import static javafx.application.ConditionalFeature.FXML;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Wael
 */
public class LoginController implements Initializable {
@FXML private JFXTextField t1;
@FXML private JFXPasswordField t2;
@FXML private AnchorPane login;
@FXML private javafx.scene.control.Label label;
     @FXML
    private JFXButton b1;
    @FXML
    private JFXButton b11;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void inscription(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Seconnecterentantque.fxml"));
        login.getChildren().setAll(pane);
    }

    @FXML
    private void exit(MouseEvent event) {
        System.exit(0);
    }
    
}
