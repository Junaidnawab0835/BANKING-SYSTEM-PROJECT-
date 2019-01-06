/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banking.system.g;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HANIF
 */
public class SALESREPORTController implements Initializable {

    @FXML
    private BarChart<DATA, String> bar;
    @FXML
    private NumberAxis yAxis;
    @FXML
    private CategoryAxis xAxis;
    Connection con;
    ResultSet res;
    Statement st;
    double amount=0.0f;
    int number=0;
    @FXML
    private Button HOME;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            try {
                con = DriverManager.getConnection("jdbc:sqlite:BANK.sqlite");
            } catch (SQLException ex) {
                Logger.getLogger(CUSTOMERController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                st=con.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(SALESREPORTController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            String Query = "Select * from CUSTOMERDATA";
            res=st.executeQuery(Query);
            //TAKING TOTAL CASH VALUE FROM DATABASE
            while(res.next())
            {
               
               amount=amount+res.getDouble("AMOUNT");
               number++;
            }
            //DISPLAYING VALUES IN GRAPH
            XYChart.Series dataSeries1 = new XYChart.Series();
            XYChart.Series dataSeries2 = new XYChart.Series();
            dataSeries1.setName("2018");
            
            dataSeries1.getData().add(new XYChart.Data("CUSTOMERS", number));
            dataSeries1.getData().add(new XYChart.Data("CASH"  ,amount ));
            
            bar.getData().add(dataSeries1);
            dataSeries2.setName("2017");
            
            dataSeries2.getData().add(new XYChart.Data("CUSTOMERS", 1000000));
            dataSeries2.getData().add(new XYChart.Data("CASH"  ,10000000 ));
            
            bar.getData().add(dataSeries2);
            // TODO
        } catch (SQLException ex) {
                        Logger.getLogger(SALESREPORTController.class.getName()).log(Level.SEVERE, null, ex);
                    }
    }    

    @FXML
    private void HOME(ActionEvent event) {
         try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML2.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                
                Stage stage = new Stage();
                stage.setTitle("MAIN MENU");  
                stage.setScene(new Scene(root1));  
                stage.show();
                ((Node)event.getSource()).getScene().getWindow().hide();
                
       
       
       
                
                
        } catch(IOException e) {
          }
    }
    
}
