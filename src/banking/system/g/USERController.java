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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HANIF
 */
public class USERController implements Initializable {

    @FXML
    private Button WITHDRAW;
    @FXML
    private Button CASHINQUIRY;
    @FXML
    private Button DEPOSITCASH;
    @FXML
    private Button TRANFERAMOUNT;
    @FXML
    private Button signout;
String username;
    @FXML
    private Button CHANGEPIN;
    @FXML
    private Button PAYBILL;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    void myfunction(String name)
    {
        this.username=name;
    }
        

    @FXML
    private void WITHDRAWCASH(ActionEvent event) {
          try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("WITHDRAW.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                
                Stage stage = new Stage();
                stage.setTitle("WITHDRAW");  
                WITHDRAWController setConroller=fxmlLoader.getController();
                setConroller.myfunction(username);
                stage.setScene(new Scene(root1));  
                stage.show();
                ((Node)event.getSource()).getScene().getWindow().hide();
                
       
       
       
                
                
        } catch(IOException e) {
          }
        
    }

    @FXML
    private void CASHINQUIRIY(ActionEvent event) {
          try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CASHINQUIRY.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                CASHINQUIRYController setConroller=fxmlLoader.getController();
                setConroller.myfunction(username);
                Stage stage = new Stage();
                stage.setTitle("CASH INQUIRY");  
                stage.setScene(new Scene(root1));  
                stage.show();
                ((Node)event.getSource()).getScene().getWindow().hide();
                
       
       
       
                
                
        } catch(IOException e) {
          }
        
    }

    @FXML
    private void DEPOSITCASH(ActionEvent event) {
            try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DEPOSIT.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                
                Stage stage = new Stage();
                stage.setTitle("MAIN MENU"); 
                DEPOSITController setConroller=fxmlLoader.getController();
                setConroller.myfunction(username);
                stage.setScene(new Scene(root1));  
                stage.show();
                ((Node)event.getSource()).getScene().getWindow().hide();
                
       
       
       
                
                
        } catch(IOException e) {
          }
        
        
    }

    @FXML
    private void TRANSFERAMOUNT(ActionEvent event) {
                 try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TRANSFERAMOUNT.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                
                Stage stage = new Stage();
                stage.setTitle("TRANSFER MENU"); 
                TRANSFERAMOUNTController setConroller=fxmlLoader.getController();
                setConroller.myfunction(username);
                stage.setScene(new Scene(root1));  
                stage.show();
                ((Node)event.getSource()).getScene().getWindow().hide();
                
       
       
       
                
                
        } catch(IOException e) {
          }
        
    }


    @FXML
    private void signout(ActionEvent event) {
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
    private void CHANGEPIN(ActionEvent event) {
          try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CHANGEPIN.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                
                Stage stage = new Stage();
                stage.setTitle("CHANGEPIN MENU"); 
                CHANGEPINController setConroller=fxmlLoader.getController();
                setConroller.myfunction(username);
                stage.setScene(new Scene(root1));  
                stage.show();
                ((Node)event.getSource()).getScene().getWindow().hide();
                
       
       
       
                
                
        } catch(IOException e) {
          }
    }

    @FXML
    private void PAYBILL(ActionEvent event) {
        try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PAYBILL.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                
                Stage stage = new Stage();
                stage.setTitle("PAYBILL MENU"); 
                PAYBILLController setConroller=fxmlLoader.getController();
                setConroller.myfunction(username);
                stage.setScene(new Scene(root1));  
                stage.show();
                ((Node)event.getSource()).getScene().getWindow().hide();
                
       
       
       
                
                
        } catch(IOException e) {
          }
        
    }
    
}
