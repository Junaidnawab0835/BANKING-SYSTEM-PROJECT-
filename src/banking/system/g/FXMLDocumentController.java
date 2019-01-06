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
import java.time.ZoneOffset;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author HANIF
 */
public class FXMLDocumentController implements Initializable {
    
    
    @FXML
    private Button signup;
    @FXML
    private PasswordField password;
    @FXML
    private TextField username;
    @FXML
    private Label checklogin;
    @FXML
    private Label checklogin2;
    public String name;
    @FXML
    private CheckBox USER;
    Connection con;
    ResultSet res;
    Statement st;
    String accountnumber;
    String PIN;
    
    private static final String FILE_HEADER = "USERNAME,PASSWORD,DATE";
    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        // TODO
    }    

    @FXML
        private void signup(ActionEvent event) throws IOException {
       
    
            
             try
               {
                   
                 Class.forName("org.sqlite.JDBC");
                 con = DriverManager.getConnection("jdbc:sqlite:BANK.sqlite");
                    
                 st=con.createStatement();
             
               String Query = "Select * from CUSTOMERDATA where ACCOUNTNUMBER='"+username.getText()+"'";
               res=st.executeQuery(Query);
               PIN=res.getString("PIN");
               System.out.println(PIN);
               accountnumber=res.getString("ACCOUNTNUMBER");
               System.out.println(accountnumber);
               
               
               }
             catch(Exception e)
             {
                 
             }
             finally
             {
                 try
                 {
                     st.close();
                 }
                 catch(Exception e)
                 {
                     System.out.println("ERROR WHILE CLOSING DATABASE");
                 }
             }
            
            //RECORD OF LOGIN IS STORED IN A FILE
            
            	String fileName="Records.csv";
		
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
                               
				bw.write(username.getText());
                                bw.write(COMMA_DELIMITER);
                                bw.write(password.getText());
                                bw.write(COMMA_DELIMITER);
                                bw.write(dtf.format(now));
                                bw.write(COMMA_DELIMITER);
                                bw.write(NEW_LINE_SEPARATOR);
                                
				
				
		

			
			
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
                
            
            
            
            
            
            
            
            
        
       if(!USER.isSelected())//CHECK BOX
       {

        if(username.getText().equals("ADMIN"))
        {
            checklogin.setStyle("-fx-text-fill: green");
            checklogin.setText("Correct Username");   
        }
        else
        {
            checklogin.setStyle("-fx-text-fill: red");
            checklogin.setText("Incorrect Username");
        }
        if(!password.getText().equals("ADMIN")) 
        {
            checklogin2.setStyle("-fx-text-fill: red");
            checklogin2.setText("Incorrect Password");   
        }
        else
        {
            checklogin2.setStyle("-fx-text-fill: green");
            checklogin2.setText("Correct Password");
        }
       if(username.getText().equals("ADMIN")&&password.getText().equals("ADMIN"))
       {
         try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML2.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                FXML2Controller setConroller=fxmlLoader.getController();
                setConroller.myfunction(username.getText());
                Stage stage = new Stage();
                stage.setTitle("MAIN MENU");  
                stage.setScene(new Scene(root1));  
                stage.show();
                ((Node)event.getSource()).getScene().getWindow().hide();
                
       
       
       
                
                
        } catch(IOException e) {
          }
        
        
         
       }
       }
      else//CHECK BOX
       {
           
        if(username.getText().equals(accountnumber)) 
        {
            checklogin.setStyle("-fx-text-fill: red");
            checklogin.setText("Incorrect Username");   
        }
        else
        {
            checklogin.setStyle("-fx-text-fill: green");
            checklogin.setText("Correct Username");
        }
        if(!password.getText().equals(PIN)) 
        {
            checklogin2.setStyle("-fx-text-fill: red");
            checklogin2.setText("Incorrect Password");   
        }
        else
        {
            checklogin2.setStyle("-fx-text-fill: green");
            checklogin2.setText("Correct Password");
        }
       if(username.getText().equals(accountnumber)&&password.getText().equals(PIN))
       {
         try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("USER.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                USERController setConroller=fxmlLoader.getController();
                setConroller.myfunction(username.getText());
                Stage stage = new Stage();
                stage.setTitle("USER MENU");  
                stage.setScene(new Scene(root1));  
                stage.show();
                ((Node)event.getSource()).getScene().getWindow().hide();
                
       
       
       
                
                
        } catch(IOException e) {
          }
         
   
        
     
         
       }
           
           
       }
       
    } 

    @FXML
    private void USER(ActionEvent event) {
        
                
    }
         
}
