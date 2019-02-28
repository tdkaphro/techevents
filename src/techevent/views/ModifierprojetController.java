/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techevent.views;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import techevent.entities.Projet;
import techevent.services.ServiceProjet;
import techevent.views.ListeprojetenseignantController;

/**
 * FXML Controller class
 *
 * @author Wael
 */
public class ModifierprojetController implements Initializable {

    @FXML
    private AnchorPane projet;
    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextArea desription;
    @FXML
    private JFXComboBox<String> Domaine;
    @FXML
    private JFXButton importer;
    @FXML
    private JFXCheckBox checkspon;
    @FXML
    private Slider slider;
    @FXML
    private Label cbenseignant;
    @FXML
    private JFXListView<Projet> listeview;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceProjet sp = new ServiceProjet();
        try {
            ObservableList<String> oblist = FXCollections.observableArrayList(sp.getAllDomaine());
            Domaine.setItems(oblist);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("listeprojetenseignant.fxml"));
            Parent root = loader.load();
            ListeprojetenseignantController irc = loader.getController();

            ResultSet rs = sp.afficherProjetsparenseignant2(sp.getEnsidByProjetId(irc.id));
            nom.setText(rs.getString("nom"));
            desription.setText(rs.getString(2));
            Domaine.setValue(rs.getString(3));
            if (rs.getBoolean(4)) {
                checkspon.setSelected(true);
            } else {
                checkspon.setSelected(false);
            }
            slider.setValue(irc.prog);
        } catch (IOException ex) {
            Logger.getLogger(ModifierprojetController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ModifierprojetController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void importermedia(ActionEvent event) {
    }

    @FXML
    private void annuler(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("listeprojetenseignant.fxml"));
        Parent root=loader.load();
        nom.getScene().setRoot(root);
    }

    @FXML
    private void modifierprojetenseignant(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("listeprojetenseignant.fxml"));
        Parent root = loader.load();
        ListeprojetenseignantController irc = loader.getController();
        ServiceProjet sp = new ServiceProjet();
        Projet p = new Projet();
        p.setId(irc.id);
        System.out.println(irc.id);
        p.setNom(nom.getText());
        p.setDomaine(Domaine.getValue());
        p.setDescription(desription.getText());
        p.setEtat(checkspon.isSelected());
        p.setProgress((int) Math.round(slider.getValue()));
        sp.modifierprojet(p);
    }


}
