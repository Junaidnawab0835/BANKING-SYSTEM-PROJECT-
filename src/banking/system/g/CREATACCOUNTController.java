/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banking.system.g;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HANIF
 */
public class CREATACCOUNTController implements Initializable {
    @FXML
    private TextField FIRSTNAME;
    @FXML
    private Button SUBMIT;
    @FXML
    private AnchorPane DATEOFBIRTH;
    @FXML
    private TextField LASTNAME;
    @FXML
    private TextField CNIC;
    @FXML
    private TextField ADDRESS;
    
    Connection con;
    ResultSet res;
    Statement st;
   String newaccountnumber;
    @FXML
    private Button HOMEBUTTON;
    @FXML
    private DatePicker DATE;
    @FXML
    private TextField PIN;
    @FXML
    private Label label;
    
    int a=0;


    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //GENRATING RANDOM AND DISPLAYING IT
        Random rand = new Random();
        int newaccount=rand.nextInt(100000000);
        newaccountnumber=Integer.toString(newaccount);
        label.setText(newaccountnumber);
        
    }    

    @FXML
    private void SUBMIT(ActionEvent event) {
       
         try
               {
                   //TO CHECK CNIC MUST BE LESS THAN 13
                   
                   if(CNIC.getText().length()>13)
                   {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                   alert.setTitle("Information Dialog");
                   alert.setHeaderText("Look, an Information Dialog");
                   alert.setContentText("CNIC MUST BE 13 DIGITS|");
                   alert.showAndWait();
                  
                   }
                   if (!FIRSTNAME.getText().matches("[a-zA-Z]+")||!LASTNAME.getText().matches("[a-zA-Z]+"))
                   {
                      Alert alert = new Alert(Alert.AlertType.INFORMATION);
                   alert.setTitle("Information Dialog");
                   alert.setHeaderText("Look, an Information Dialog");
                   alert.setContentText("THEIR SHOULD BE NO INTEGERS IN NAMES!");
                   alert.showAndWait();
                  
                   }
                   if (!PIN.getText().matches("[0-9]*"))
                   {
                   Alert alert = new Alert(Alert.AlertType.INFORMATION);
                   alert.setTitle("Information Dialog");
                   alert.setHeaderText("Look, an Information Dialog");
                   alert.setContentText("THEIR SHOULD BE  INTEGERS IN PIN!");
                   alert.showAndWait(); 
                   
                   }
                   if (!CNIC.getText().matches("[0-9]*"))
                   {
                   Alert alert = new Alert(Alert.AlertType.INFORMATION);
                   alert.setTitle("Information Dialog");
                   alert.setHeaderText("Look, an Information Dialog");
                   alert.setContentText("THEIR SHOULD BE  INTEGERS IN CNIC!");
                   alert.showAndWait(); 
           
                   }
                   
                   if(CNIC.getText().length()<=13&&FIRSTNAME.getText().matches("[a-zA-Z]+")&&LASTNAME.getText().matches("[a-zA-Z]+")&&PIN.getText().matches("[0-9]*")&&CNIC.getText().matches("[0-9]*"))
                   {
                   Alert alert = new Alert(Alert.AlertType.INFORMATION);
                   alert.setTitle("Information Dialog");
                   alert.setHeaderText("Look, an Information Dialog");
                   alert.setContentText("CREATED ACCOUNT SUCCESFFULY!");
                   alert.showAndWait();
                 //CONNECTING TO DATABASE
                   Class.forName("org.sqlite.JDBC");
                   con = DriverManager.getConnection("jdbc:sqlite:BANK.sqlite");   
                   st=con.createStatement();
                   //CREATING TABEL IN DATABASE
                   String Query="create table if not exists CUSTOMERDATA ('FirstName' Text,'LastName' Text,'CNIC' Int,'Address' Text,'DATE' Text,'ACCOUNTNUMBER' Text,'PIN' Text)";
                   st.executeUpdate(Query);
                   Query="Insert into CUSTOMERDATA('FirstName','LastName','CNIC','Address',DATE,ACCOUNTNUMBER,PIN) values ('"+FIRSTNAME.getText()+"','"+LASTNAME.getText()+"','"+CNIC.getText()+"','"+ADDRESS.getText()+"','"+DATE.getValue().toString()+"','"+newaccountnumber+"','"+PIN.getText()+"')";
                   st.executeQuery(Query);
                 }
 
               }
               catch(Exception e)
               {
                   System.out.println(e);
               }
         finally{
             try{
                 st.close();
             }
             catch(Exception e){
                 
             }
                 
         }
                
	

    }

    @FXML
    private void HOMEBUTTON(ActionEvent event) {
        //GOING TO HOME
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
        
        
        
        
        
        
        
    

    

