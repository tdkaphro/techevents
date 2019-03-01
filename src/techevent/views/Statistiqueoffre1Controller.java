/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techevent.views;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import techevent.images.ServiceEvenement;
import techevent.services.ServiceOffre;
import techevent.services.ServiceUser;
import techevent.views.AccueilformateurController;
import techevent.views.LoginController;

/**
 * FXML Controller class
 *
 * @author Taboubi
 */
public class Statistiqueoffre1Controller implements Initializable {

    @FXML
    private BarChart<String, Integer> bc;
    @FXML
    private NumberAxis y;
    @FXML
    private CategoryAxis x;
    @FXML
    private JFXButton retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceUser su = new ServiceUser();
        ServiceEvenement se = new ServiceEvenement();
        ServiceOffre so = new ServiceOffre();
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root = loader.load();
            LoginController irc = loader.getController();
            String mail = irc.mail1;
            String mdp = irc.mdp1;
            ArrayList<String> l = so.getAllEvenementforSpons(su.getId(mail, mdp));
            XYChart.Series set = new XYChart.Series<>();
            for(String s: l){
                set.getData().add(new XYChart.Data(s, so.getPrixbyNomeven(s)));
            }
            bc.getData().addAll(set);

        } catch (IOException ex) {
            Logger.getLogger(AccueilformateurController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void Retour(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("offresponsor.fxml"));
            Parent root = loader.load();
            OffresponsorController irc = loader.getController();
            bc.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(OffresponsorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
