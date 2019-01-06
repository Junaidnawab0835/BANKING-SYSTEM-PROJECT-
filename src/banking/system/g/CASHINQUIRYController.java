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
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author HANIF
 */
public class CASHINQUIRYController implements Initializable {

    String username;
    Connection con;
    ResultSet res;
    Statement st;
    @FXML
    private Button homebutton;
    @FXML
    private Label firstname;
    @FXML
    private Label lastname;
    @FXML
    private Label accountnumber;
    @FXML
    private Label currentamount;
    @FXML
    private Button SHOW;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
  
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    //FUNCTION TO TAKE VALUE FROM ANOTHER FRAME
    }
      void myfunction(String name)
    {
        username=name;
        
    }

    @FXML
    private void homebutton(ActionEvent event) {
        //GOING TO HOME
          try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("USER.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                
                Stage stage = new Stage();
                stage.setTitle("USER MENU");  
                stage.setScene(new Scene(root1));
                USERController setConroller=fxmlLoader.getController();
                setConroller.myfunction(username);
                stage.show();
                ((Node)event.getSource()).getScene().getWindow().hide();
            } 
          catch(IOException e) {
          }
    }

    @FXML
    private void SHOW(ActionEvent event) {
                //TAKING DATA FROM DATABASE AND SHOWING IT
               try
               {
                 
                 Class.forName("org.sqlite.JDBC");
                 con = DriverManager.getConnection("jdbc:sqlite:BANK.sqlite");
                 st=con.createStatement();
                 String Query = "Select * from CUSTOMERDATA where ACCOUNTNUMBER='"+username+"'";
                 res=st.executeQuery(Query);
                 firstname.setText(res.getString("FirstName"));
                 lastname.setText(res.getString("LastName"));
                 accountnumber.setText(res.getString("ACCOUNTNUMBER"));
                 String name=Double.toString(res.getDouble("AMOUNT"));
                 currentamount.setText(name);
              
                 
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
    
    
 
    
 
    
}
