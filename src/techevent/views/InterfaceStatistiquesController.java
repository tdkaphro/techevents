/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techevent.views;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import techevent.services.ServiceClub;

/**
 * FXML Controller class
 *
 * @author Hannachi
 */
public class InterfaceStatistiquesController implements Initializable {

    @FXML
    private PieChart statistique;
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
    private void retour(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceClubPresident.fxml"));
            Parent root;
            root = loader.load();
            InterfaceClubPresidentController irc = loader.getController();
            irc.initData(idf, file);
            boutonretour.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(InterfaceTraiterInvitationsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void initData(int idf, File file) throws SQLException {
    this.idf=idf;
    this.file=file;
        ServiceClub sc= new ServiceClub();
    ObservableList<PieChart.Data> x=FXCollections.observableArrayList(new PieChart.Data("Membres",sc.NombreDesMembres(idf)),
                new PieChart.Data("Responsables", sc.NombreDesResponsables(idf)),  
                new PieChart.Data("Invitations", sc.notificationInvitation(idf)), 
                new PieChart.Data("Formations",sc.NombreDesFormations(idf)), 
                new PieChart.Data("Evenements", sc.NombreDesEvenements(idf)), 
                new PieChart.Data("Projets", sc.NombreDesProjets(idf)));
         PieChart chart = new PieChart(x);
    statistique.setData(x);
    }
}