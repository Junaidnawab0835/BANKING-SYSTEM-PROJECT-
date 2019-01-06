/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banking.system.g;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import static java.util.Collections.list;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HANIF
 */
public class CUSTOMERController implements Initializable {
    	private static final String COMMA_DELIMITER = ",";
    @FXML
    private TableColumn<DATA, String> C1;
    @FXML
    private TableColumn<DATA, String> C2;
    @FXML
    private TableColumn<?, ?> C3;
    @FXML
    private TableColumn<?, ?> C4;
    @FXML
    private TableColumn<?, ?> C5;
    @FXML
    private TableColumn<?, ?> C6;
    @FXML
    private TableColumn<?, ?> C7;
    @FXML
    private TableView<DATA> TABLxEVIEW;
    @FXML
    private Button DELETEBUTTON;
    @FXML
    private TextField DELETENTERY;
    Connection con;
    ResultSet res;
    Statement st;
    @FXML
    private Button EDIT;
    @FXML
    private ChoiceBox<String> EDITBOX;
    @FXML
    private TextField EDITTEXT;
    
    

   
    @FXML
    private TextField EDITTEXT2;
    @FXML
    private Button HOME;
    @FXML
    private Button UPDATE;
     
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //CHOICEBOX
        ObservableList<String> availableChoices = FXCollections.observableArrayList("FirstName","LastName","CNIC","Address");
        EDITBOX.setItems(availableChoices);
        
                try {
                    //TAKING VALUE FROM CLASS DATA
                    ObservableList<DATA> ob=FXCollections.observableArrayList();
                    try {
                        Class.forName("org.sqlite.JDBC");
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(CUSTOMERController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        con = DriverManager.getConnection("jdbc:sqlite:BANK.sqlite");
                    } catch (SQLException ex) {
                        Logger.getLogger(CUSTOMERController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    st=con.createStatement();
                    
                    String Query = "Select * from CUSTOMERDATA";
                    res=st.executeQuery(Query);
                    while(res.next())
                    {
                    //DISPLAYING VALUES IN TABLEVIEW
                    ob.add(new DATA(res.getString("FirstName"),res.getString("LastName"),res.getString("CNIC"),res.getString("Address"),res.getString("DATE"),res.getString("ACCOUNTNUMBER"),res.getString("AMOUNT")));
                    }
                    C1.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
                    C2.setCellValueFactory(new PropertyValueFactory<>("LastName"));
                    C3.setCellValueFactory(new PropertyValueFactory<>("CNIC"));
                    C4.setCellValueFactory(new PropertyValueFactory<>("Address"));
                    C5.setCellValueFactory(new PropertyValueFactory<>("ACCOUNTNUMBER"));
                    C6.setCellValueFactory(new PropertyValueFactory<>("AMOUNT"));
                    C7.setCellValueFactory(new PropertyValueFactory<>("DATE"));
                    TABLxEVIEW.setItems(ob); 
                    
                } catch (SQLException ex) {
                    Logger.getLogger(CUSTOMERController.class.getName()).log(Level.SEVERE, null, ex);
                }

     
     
    }  

    @FXML
    private void DELETBUTTON(ActionEvent event) {
        try
               {
                   
                 Class.forName("org.sqlite.JDBC");
                 con = DriverManager.getConnection("jdbc:sqlite:BANK.sqlite");
                    
                 st=con.createStatement();
               //QUERY FOR DELETING DATA
                String Query="DELETE FROM CUSTOMERDATA WHERE ACCOUNTNUMBER='"+DELETENTERY.getText()+"'";
                 st.executeUpdate(Query);
                 
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
    private void EDIT(ActionEvent event) {
    
       try
               {
                   
                 Class.forName("org.sqlite.JDBC");
                 con = DriverManager.getConnection("jdbc:sqlite:BANK.sqlite");
                    
                 st=con.createStatement();
               //QUERY FOR EDITING DATA
                String Query="UPDATE CUSTOMERDATA SET  '"+EDITBOX.getValue()+"'='"+EDITTEXT.getText()+"' WHERE ACCOUNTNUMBER='"+EDITTEXT2.getText()+"'";
                System.out.println(EDITBOX.getValue());
                 st.executeUpdate(Query);
                 
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

    @FXML
    private void UPDATE(ActionEvent event) {
          try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CUSTOMER.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                
                Stage stage = new Stage();
                stage.setTitle("CUSTOMER MENU");  
                stage.setScene(new Scene(root1));  
                stage.show();
                ((Node)event.getSource()).getScene().getWindow().hide();
                
       
       
       
                
                
        } catch(IOException e) {
          }
        
    }
 
    
    }
