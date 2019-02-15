/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techevents.views;

import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Pipe;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.AccessibleAction;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Wael
 */
public class SeconnecterentantqueController implements Initializable {
@FXML private javafx.scene.control.Label label;
    @FXML
    private JFXButton b1;
    @FXML
    private JFXButton b11;
    @FXML
    private JFXButton b12;
    @FXML
    private JFXButton b13;
    @FXML
    private JFXButton b14;
@FXML 
  private ImageView etudiant;
        CheckBox chketud = new CheckBox();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void exit(MouseEvent event) {
                System.exit(0);

    }

    @FXML
    private void etudiant(MouseEvent event) {
ImageView img = new ImageView();
etudiant.visibleProperty().bind(chketud.selectedProperty());
final Timeline timeline = new Timeline();
timeline.setCycleCount(Timeline.INDEFINITE);
timeline.setAutoReverse(true);
final KeyValue kv = new KeyValue(etudiant.xProperty(),1);
final KeyFrame kf = new KeyFrame(Duration.millis(200), kv);
timeline.getKeyFrames().add(kf);
timeline.play();    
    }
    
}
