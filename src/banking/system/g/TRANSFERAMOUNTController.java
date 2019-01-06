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
public class TRANSFERAMOUNTController implements Initializable {

    private TextField USERACCOUNT;
    @FXML
    private TextField RECIEVERACCOUNT;
    @FXML
    private TextField AMOUNT;
    @FXML
    private Button TRANSFERBUTTON;
   
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
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        // TODO
    }    

    @FXML
    private void TRANSFERBUTTON(ActionEvent event) {
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
               if (res.getString("ACCOUNTNUMBER").equals(RECIEVERACCOUNT.getText())) 
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
             String Query = "Select * from CUSTOMERDATA where ACCOUNTNUMBER='"+username+"'";
             res=st.executeQuery(Query);
             double useramount=res.getInt("AMOUNT");
             String pin=res.getString("PIN");
               
               String Query1 = "Select * from CUSTOMERDATA where ACCOUNTNUMBER='"+RECIEVERACCOUNT.getText()+"'";
               
                 res=st.executeQuery(Query1);
                 
               double recieveramount=res.getInt("AMOUNT");
               
               
              
               double transferamount = Double.parseDouble(AMOUNT.getText());
         
               if(transferamount<=0||useramount<transferamount||!PIN.getText().equals(pin))
               {
                   
                   Alert alert = new Alert(Alert.AlertType.INFORMATION);
                   alert.setTitle("Information Dialog");
                   alert.setHeaderText("Look, an Information Dialog");
                   alert.setContentText("Invalid value!");
                   alert.showAndWait();
               }
               else
               {
                   recieveramount=transferamount+recieveramount;
                   useramount=useramount-transferamount;
                   String Query2="UPDATE CUSTOMERDATA SET  AMOUNT='"+recieveramount+"' WHERE ACCOUNTNUMBER='"+RECIEVERACCOUNT.getText()+"'";
                   String Query3="UPDATE CUSTOMERDATA SET  AMOUNT='"+useramount+"' WHERE ACCOUNTNUMBER='"+username+"'";
                   st.executeUpdate(Query2);
                   st.executeUpdate(Query3);
                   Alert alert = new Alert(Alert.AlertType.INFORMATION);
                   alert.setTitle("Information Dialog");
                   alert.setHeaderText("Look, an Information Dialog");
                   alert.setContentText("TRANSFERED SUCCESSFULLY!");
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
                stage.setTitle("MAIN MENU");  
                stage.setScene(new Scene(root1));  
                USERController setConroller=fxmlLoader.getController();
                setConroller.myfunction(username);
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
