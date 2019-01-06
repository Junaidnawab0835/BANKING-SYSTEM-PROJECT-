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
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Random;
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
public class CONSOLIDATEController implements Initializable {

    @FXML
    private TextField FIRSTACCOUNT;
    @FXML
    private TextField SECOUNDACCOUNT;
    @FXML
    private TextField FIRSTPIN;
    @FXML
    private TextField SECOUNDPIN;
    @FXML
    private Button COONSOLIDATE;
    @FXML
    private Button HOME;
    Connection con;
    ResultSet res;
    Statement st;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void CONSOLIDATE(ActionEvent event) {
        try
               {//start of try
                   
               //TAKING CURRENT DATE AND TIME
               DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	       LocalDateTime now = LocalDateTime.now();    
               Random rand = new Random(); 
               Class.forName("org.sqlite.JDBC");
               con = DriverManager.getConnection("jdbc:sqlite:BANK.sqlite");
                    
               st=con.createStatement();
               String Query0 = "Select * from CUSTOMERDATA";
               res=st.executeQuery(Query0);
               boolean a=false;
               boolean b=false;
               while(res.next()){
               if (res.getString("ACCOUNTNUMBER").equals(FIRSTACCOUNT.getText())) {
                   
               a=true;
               }
               if (res.getString("ACCOUNTNUMBER").equals(SECOUNDACCOUNT.getText())) {
                   
               b=true;
               }
               
               
               
               }
                      
                   if(a==false && b==false)
                   {
                   Alert alert = new Alert(Alert.AlertType.INFORMATION);
                   alert.setTitle("Information Dialog");
                   alert.setHeaderText("Look, an Information Dialog");
                   alert.setContentText("NO RECORD FOUND!");
                   alert.showAndWait();
                   }
                   if(a==true && b==true)
                   {
               
               //GENRATING RAMDOM NUMBER
               int newaccount=rand.nextInt(100000000);
               String newaccountnumber=Integer.toString(newaccount);
               System.out.println(newaccountnumber);
               String Query = "Select * from CUSTOMERDATA where ACCOUNTNUMBER='"+FIRSTACCOUNT.getText()+"'";
               res=st.executeQuery(Query);
               double useramount=res.getInt("AMOUNT");
               String userpin=res.getString("PIN");
               String usercnic=res.getString("CNIC");
               String Query1 = "Select * from CUSTOMERDATA where ACCOUNTNUMBER='"+SECOUNDACCOUNT.getText()+"'";
               res=st.executeQuery(Query1);
               //STORING VALUES OD DATABASE IN VARIABLES
               double recieveramount=res.getInt("AMOUNT");
               String recieverpin=res.getString("PIN");
               String recievercnic=res.getString("CNIC");
                //CHECKING PIN 
               if(userpin.equals(FIRSTPIN.getText())&&recieverpin.equals(SECOUNDPIN.getText()))
               {
               String Query3="DELETE FROM CUSTOMERDATA WHERE ACCOUNTNUMBER='"+FIRSTACCOUNT.getText()+"'";
               st.executeUpdate(Query3);    
               double newamount=useramount+recieveramount;
              String Query4 = "UPDATE CUSTOMERDATA SET ACCOUNTNUMBER = '"+newaccountnumber+"' , "
                + "AMOUNT = '"+newamount+"'"
                 
                + "WHERE ACCOUNTNUMBER = '"+SECOUNDACCOUNT.getText()+"'";
               //CREAT POP-UP 
               st.executeUpdate(Query4);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                   alert.setTitle("Information Dialog");
                   alert.setHeaderText("Look, an Information Dialog");
                   alert.setContentText("CONSOLIDATE SUCCESSFULLY!");
                   alert.showAndWait();
               }
               else
               {
                   Alert alert = new Alert(Alert.AlertType.INFORMATION);
                   alert.setTitle("Information Dialog");
                   alert.setHeaderText("Look, an Information Dialog");
                   alert.setContentText("WRONG PINS ENTERED!");
                   alert.showAndWait(); 
               }
               
                  
                   
               
                   }
               }//END OF TRY
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
        }//END OF FINALLY BLOCK
    }

    @FXML
    private void HOME(ActionEvent event) {
        //GOING TO HOME PANEL
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
