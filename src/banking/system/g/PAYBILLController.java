/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banking.system.g;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
public class PAYBILLController implements Initializable {

    String username;
    @FXML
    private TextField ROLLNO;
    @FXML
    private TextField CNIC;
    @FXML
    private Label label;
    @FXML
    private Button PAY;
    Connection con;
    ResultSet res;
    Statement st;
    @FXML
    private Button HOME;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
    }    

    void myfunction(String username) {
        this.username=username;
        label.setText(username);
    }

    @FXML
    private void PAY(ActionEvent event) {
        
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
               if (res.getString("CNIC").equals(CNIC.getText())) 
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
                 
               String Query = "Select * from CUSTOMERDATA where ACCOUNTNUMBER='"+username+"' AND CNIC='"+CNIC.getText()+"'";
               res=st.executeQuery(Query);
               double amount=res.getDouble("AMOUNT");
               String recievername="95499176";
               String Query2 = "Select * from CUSTOMERDATA where ACCOUNTNUMBER='"+recievername+"'";
               res=st.executeQuery(Query2);
               double recieveramount=res.getDouble("AMOUNT");
               double withdrawamount = 40000.0f;
              
              
               //CHECKING IF AMOUNT IS LESS THAN OR EQUALS TO 0 FOR LESS THAN USER AMOUNT
               if(amount<withdrawamount)
               {
                   Alert alert = new Alert(Alert.AlertType.INFORMATION);
                   alert.setTitle("Information Dialog");
                   alert.setHeaderText("Look, an Information Dialog");
                   alert.setContentText("Insufficient Amount or Invalid value!");
                   alert.showAndWait();
               }
               else
               {
                   //CHANGES IN DATABASE
                  amount=amount-withdrawamount; 
                  recieveramount=recieveramount+withdrawamount;
                  System.out.println(recieveramount);
                  String Query1="UPDATE CUSTOMERDATA SET  AMOUNT='"+amount+"' WHERE CNIC='"+CNIC.getText()+"' AND ACCOUNTNUMBER='"+username+"'";
                  System.out.println(amount);
                  st.executeUpdate(Query1);
                  String Query3="UPDATE CUSTOMERDATA SET  AMOUNT='"+recieveramount+"' WHERE ACCOUNTNUMBER='"+recievername+"'";
                  st.executeUpdate(Query3);
                    try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AFTERPAYCHALANFXML.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                AFTERPAYCHALANFXMLController setConroller=fxmlLoader.getController();
                setConroller.myfunction(username,ROLLNO.getText(),CNIC.getText());
                Stage stage = new Stage();
                stage.setTitle("CASH INQUIRY");  
                stage.setScene(new Scene(root1));  
                stage.show();   
               } 
               catch(IOException e) {
               }
          
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
         //FILE READING
         	String fileName="challan.csv";
		
		FileWriter fileWriter = null;
		BufferedWriter bw=null;	
		try {
                    File file = new File(fileName);
			fileWriter = new FileWriter(fileName,true);
                        bw = new BufferedWriter(fileWriter);
			//Write the CSV file header
			if (!file.exists()) {
				file.createNewFile();
			}
			
			
                        
                       DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	                LocalDateTime now = LocalDateTime.now();
                               
				bw.write(username);
                                bw.write(",");
                                bw.write(CNIC.getText());
                                bw.write(",");
                                bw.write(ROLLNO.getText());
                                bw.write(",");
                                bw.write(dtf.format(now));
                                bw.write(",");
                                bw.write("\n");

			
			System.out.println("CSV file was created successfully !!!");
			
		} catch (Exception e) {
			System.out.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
		} finally {
			
			try {
				if (bw != null)
					bw.close();

				if (fileWriter != null)
					fileWriter.close();


			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
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
