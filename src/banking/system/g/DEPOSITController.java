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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HANIF
 */
public class DEPOSITController implements Initializable {

    @FXML
    private TextField CNICDESPOSIT;
    @FXML
    private TextField AMOUNTDEPOSIT;
    @FXML
    private Button DEPOSIT;
    Connection con;
    ResultSet res;
    Statement st;
    String username;
    @FXML
    private Button HOME;
    @FXML
    private Label label;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
    }    

    @FXML
    private void DEPOSIT(ActionEvent event) {
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
               if (res.getString("CNIC").equals(CNICDESPOSIT.getText())) 
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
                 
               String Query = "Select * from CUSTOMERDATA where ACCOUNTNUMBER='"+username+"' AND CNIC='"+CNICDESPOSIT.getText()+"'";
               res=st.executeQuery(Query);
               double amount=res.getInt("AMOUNT");
               double dipositamount = Double.parseDouble(AMOUNTDEPOSIT.getText());
               
               //CHECKING IF AMOUNT IS LESS THAN OR EQUAL TO ZER0
              if(dipositamount<=0)
               {
                   Alert alert = new Alert(Alert.AlertType.INFORMATION);
                   alert.setTitle("Information Dialog");
                   alert.setHeaderText("Look, an Information Dialog");
                   alert.setContentText("Invalid value!");
                   alert.showAndWait();
               }
               else
               {
                   amount=amount+dipositamount;
                   String Query1="UPDATE CUSTOMERDATA SET  AMOUNT='"+amount+"' WHERE CNIC='"+CNICDESPOSIT.getText()+"' AND ACCOUNTNUMBER='"+username+"'";
                  System.out.println(amount);
                  st.executeUpdate(Query1);
                   Alert alert = new Alert(Alert.AlertType.INFORMATION);
                   alert.setTitle("Information Dialog");
                   alert.setHeaderText("Look, an Information Dialog");
                   alert.setContentText("DEPOSITED SUCCESSFULLY!");
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
    //DISPLAYING VALUE IN LABELS
    void myfunction(String username) {
        this.username=username;
        label.setText(this.username);
    }
    
}
