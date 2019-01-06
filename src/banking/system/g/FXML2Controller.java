/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banking.system.g;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HANIF
 */
public class FXML2Controller implements Initializable {

    @FXML
    private Label label;
    @FXML
    private Button CREATACCOUNT;
    @FXML
    private Button SALESREPORT;
    @FXML
    private Button CUSTOMER;
    @FXML
    private Button SIGNOUT;
    @FXML
    private Button CONSOLIDATEACCOUNT;
    
    
    
   
   
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       
        
    }
    void myfunction(String name)
    {
        label.setText("WELCOME :"+name);
    }
       
    
        
        // TODO

    @FXML
    private void CREATACCOUNT(ActionEvent event) {
            try {
        FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("CREATACCOUNT.fxml"));
                Parent root2 = (Parent) fxmlLoader1.load();
                Stage stage = new Stage();
                stage.setTitle("CREAT ACCOUNT");  
                stage.setScene(new Scene(root2));  
                stage.show();
                ((Node)event.getSource()).getScene().getWindow().hide();
        
        } catch(IOException e) {
          }
        
        
    }

    @FXML
    private void CUSTOMER(ActionEvent event) {
            try {
        FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("CUSTOMER.fxml"));
                Parent root3 = (Parent) fxmlLoader2.load();
                Stage stage = new Stage();
                stage.setTitle("CUSTOMERS");  
                stage.setScene(new Scene(root3));  
                stage.show();
                ((Node)event.getSource()).getScene().getWindow().hide();
        
        } catch(IOException e) {
            System.out.println("ERROR IN OPENING CUSTOMER");
          }
        
    }

    @FXML
    private void SIGNOUT(ActionEvent event) {
           try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                
                Stage stage = new Stage();
                stage.setTitle("LOGIN MENU");  
                stage.setScene(new Scene(root1));  
                stage.show();
                ((Node)event.getSource()).getScene().getWindow().hide();
                
       
       
       
                
                
        } catch(IOException e) {
          }
    }

    @FXML
    private void SALESREPORT(ActionEvent event) {
              try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SALESREPORT.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                
                Stage stage = new Stage();
                stage.setTitle("SALESREPORT");  
                stage.setScene(new Scene(root1));  
                stage.show();
                ((Node)event.getSource()).getScene().getWindow().hide();
                
       
       
       
                
                
        } catch(IOException e) {
          }
        
    }

    @FXML
    private void CONSOLIDATEACCOUNT(ActionEvent event) {
        
              try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CONSOLIDATE.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                
                Stage stage = new Stage();
                stage.setTitle("CONSOLIDATEACCOUNT");  
                stage.setScene(new Scene(root1));  
                stage.show();
                ((Node)event.getSource()).getScene().getWindow().hide();
                
       
       
       
                
                
        } catch(IOException e) {
          }
        
        
        
    }

   

    
    }    
    

