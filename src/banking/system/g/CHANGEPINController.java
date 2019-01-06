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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HANIF
 */
public class CHANGEPINController implements Initializable {
    String username;
    @FXML
    private TextField OLDERPIN;
    @FXML
    private TextField NEWPIN;
    @FXML
    private TextField CONFIRMPIN;
    @FXML
    private Button CHANGE;
    Connection con;
    ResultSet res;
    Statement st;
    @FXML
    private Button HOME;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     //FUNCTION USED TO MOVE USERNAME VALUE
    void myfunction(String username) {
        this.username=username;
    }

    @FXML
    private void CHANGE(ActionEvent event) {
         try
               {
                   
                 Class.forName("org.sqlite.JDBC");
                 con = DriverManager.getConnection("jdbc:sqlite:BANK.sqlite");
                    
                 st=con.createStatement();
             
               String Query = "Select * from CUSTOMERDATA where ACCOUNTNUMBER='"+username+"'";
               res=st.executeQuery(Query);
               String PIN1=res.getString("PIN");
               //CHECK0
                if (!NEWPIN.getText().matches("[0-9]*"))
                   {
                   Alert alert = new Alert(Alert.AlertType.INFORMATION);
                   alert.setTitle("Information Dialog");
                   alert.setHeaderText("Look, an Information Dialog");
                   alert.setContentText("THEIR SHOULD BE  INTEGERS IN PIN!");
                   alert.showAndWait(); 
                   
                   }
               //CHECK1
               if(!OLDERPIN.getText().equals(PIN1))
               {
                   Alert alert = new Alert(Alert.AlertType.INFORMATION);
                   alert.setTitle("Information Dialog");
                   alert.setHeaderText("Look, an Information Dialog");
                   alert.setContentText("WRONG PIN");
                   alert.showAndWait();
               }
               //CHECK2
               if(!NEWPIN.getText().equals(CONFIRMPIN.getText()))
               {
                   Alert alert = new Alert(Alert.AlertType.INFORMATION);
                   alert.setTitle("Information Dialog");
                   alert.setHeaderText("Look, an Information Dialog");
                   alert.setContentText("CONFIRM PIN IS NOT EQUALS TO NEW PIN!");
                   alert.showAndWait();
               }
               if(NEWPIN.getText().matches("[0-9]*")&&OLDERPIN.getText().equals(PIN1)&&NEWPIN.getText().equals(CONFIRMPIN.getText()))
               { 
                   //TAKING VALUES FROM DATABASE AND CHANGING IT
                  String Query1="UPDATE CUSTOMERDATA SET  PIN='"+NEWPIN.getText()+"' WHERE ACCOUNTNUMBER='"+username+"'";
                  st.executeUpdate(Query1);
                   Alert alert = new Alert(Alert.AlertType.INFORMATION);
                   alert.setTitle("Information Dialog");
                   alert.setHeaderText("Look, an Information Dialog");
                   alert.setContentText("PIN CHANGED SUCCESSFULLY!");
                   alert.showAndWait();
                  
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
    private void HOME(ActionEvent event) {
        //GOING TO HOME
         try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("USER.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                
                Stage stage = new Stage();
                stage.setTitle("USER MENU");
                USERController setConroller=fxmlLoader.getController();
                setConroller.myfunction(username);
                stage.setScene(new Scene(root1));  
                stage.show();
                ((Node)event.getSource()).getScene().getWindow().hide();                
        } catch(IOException e) {
          }
    }
    
}
