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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.management.Query;
import static javax.swing.text.html.HTML.Tag.ADDRESS;

/**
 * FXML Controller class
 *
 * @author HANIF
 */
public class WITHDRAWController implements Initializable {

    @FXML
    private TextField RECIEVERCNIC;
    @FXML
    private TextField AMOUNT;
    @FXML
    private Button WITHDRAW;
    Connection con;
    ResultSet res;
    Statement st;
    String username;
    @FXML
    private Button HOME;
    @FXML
    private TextField PIN;
    @FXML
    private Label label;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void WITHDRAW(ActionEvent event) {
         try
               {
                   
                 Class.forName("org.sqlite.JDBC");
                 con = DriverManager.getConnection("jdbc:sqlite:BANK.sqlite");   
                 st=con.createStatement();
              String Query0 = "Select * from CUSTOMERDATA";
               res=st.executeQuery(Query0);
               boolean a=false;
               while(res.next())
               {
               if (res.getString("CNIC").equals(RECIEVERCNIC.getText())) 
               {
               a=true;
               }
               }
                      
                   if(a==false)
                   {
                   Alert alert = new Alert(Alert.AlertType.INFORMATION);
                   alert.setTitle("Information Dialog");
                   alert.setHeaderText("Look, an Information Dialog");
                   alert.setContentText("NO RECORD FOUND!");
                   alert.showAndWait();
                   }
                   
                 
                 
                if(a==true)
                {
                 
               String Query = "Select * from CUSTOMERDATA where ACCOUNTNUMBER='"+username+"' AND CNIC='"+RECIEVERCNIC.getText()+"'";
               res=st.executeQuery(Query);
               double amount=res.getInt("AMOUNT");
               String PIN1=res.getString("PIN");
               double withdrawamount = Double.parseDouble(AMOUNT.getText());
               //CHECKING FOR PIN
               if(!PIN.getText().equals(PIN1))
               {
                   Alert alert = new Alert(AlertType.INFORMATION);
                   alert.setTitle("Information Dialog");
                   alert.setHeaderText("Look, an Information Dialog");
                   alert.setContentText("WRONG PIN");
                   alert.showAndWait();
               }
               //CHECKING IF AMOUNT IS LESS THAN OR EQUALS TO 0 FOR LESS THAN USER AMOUNT
               else if(withdrawamount<=0||amount<withdrawamount)
               {
                   Alert alert = new Alert(AlertType.INFORMATION);
                   alert.setTitle("Information Dialog");
                   alert.setHeaderText("Look, an Information Dialog");
                   alert.setContentText("Insufficient Amount or Invalid value!");
                   alert.showAndWait();
               }
               else
               {
                   //CHANGES IN DATABASE
                  amount=amount-withdrawamount; 
                  String Query1="UPDATE CUSTOMERDATA SET  AMOUNT='"+amount+"' WHERE CNIC='"+RECIEVERCNIC.getText()+"' AND ACCOUNTNUMBER='"+username+"'";
                  System.out.println(amount);
                  st.executeUpdate(Query1);
                  Alert alert = new Alert(AlertType.INFORMATION);
                   alert.setTitle("Information Dialog");
                   alert.setHeaderText("Look, an Information Dialog");
                   alert.setContentText("AMOUNT WITHDRAW SUCCESSFULLY!");
                   alert.showAndWait();
                  
               }
             
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

    void myfunction(String username) {
        this.username=username;
        label.setText(this.username);
    }
    
}
