/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techevent.views;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import techevent.entities.Evenement;
import techevent.services.ServiceEvenement;

/**
 * FXML Controller class
 *
 * @author Ciro
 */
public class ReporterEvenementController implements Initializable {

    @FXML
    private JFXButton ReporterEven;
    @FXML
    private JFXDatePicker nouveaudate;
    @FXML
    private Label Nomevenmt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void Retour(ActionEvent event) throws IOException {
        Nomevenmt.getScene().getWindow().hide();
        Stage prStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("acceuilevenmnt.fxml"));
        Scene scene = new Scene(root);
        prStage.setScene(scene);
        prStage.setResizable(false);
        prStage.show();
    }

    @FXML
    private void ajouter(MouseEvent event) {
    }

    @FXML
    private void ReporterEvenement(ActionEvent event) throws SQLException, IOException {

        ServiceEvenement se = new ServiceEvenement();
        Evenement e = new Evenement();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Faites attention");
        alert.setHeaderText("Voulez vous reporter votre Evenement");
        alert.setContentText("Comfirmer raporté");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("acceuilevenmnt.fxml"));
            Parent root = loader.load();
            AcceuilevenmntController irc = loader.getController();
            ResultSet rs = se.afficherEvenementparEtudiant(irc.x);
            rs.next();

            if (nouveaudate.getValue().toEpochDay() - rs.getDate(4).toLocalDate().toEpochDay() > 7) {

                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setTitle("ERROR");
                alert2.setHeaderText(null);
                alert2.setContentText("Votre événement n'a été pas reporté,car il ne faut pas dépassé 7 jours de différence");
                alert2.showAndWait();
                System.out.println( LocalDate.now().toEpochDay() );}
                
                else if( nouveaudate.getValue().toEpochDay() - LocalDate.now().toEpochDay() < 0){
                     
                     
                 Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
               alert3.setTitle("ERROR");
                alert3.setHeaderText(null);
                alert3.setContentText("Votre événement n'a été pas reporté,car il ne faut pas donnée une date inférieur à la ancienne date ");
                alert3.showAndWait();
                        
                        
                        }    

             else {
                e.setDateorganisation(Date.valueOf(nouveaudate.getValue()));
                se.modifierEvenement2(e, 1);
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setTitle("Succes");
                alert2.setHeaderText(null);
                alert2.setContentText("Votre événement reporté avec succées, tous les membres participants seront informés");
                alert2.showAndWait();

            }

        } else {

        }

        Nomevenmt.getScene().getWindow().hide();
        Stage prStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("acceuilevenmnt.fxml"));
        Scene scene = new Scene(root);
        prStage.setScene(scene);
        prStage.setResizable(false);
        prStage.show();
        Nomevenmt.getScene().getWindow().hide();
    }

    @FXML
    private void nouveaudate(ActionEvent event) {
    }

    void initData1(int id) throws SQLException {
        ServiceEvenement se = new ServiceEvenement();
        ResultSet rs = se.afficherEvenementparEtudiant(id);
        rs.next();
        nouveaudate.setValue(rs.getDate("DATEORGANISATION").toLocalDate());
        Nomevenmt.setText(rs.getString("nom"));

    }

}
