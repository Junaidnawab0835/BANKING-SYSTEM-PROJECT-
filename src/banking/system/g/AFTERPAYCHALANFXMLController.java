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
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author HANIF
 */
public class AFTERPAYCHALANFXMLController implements Initializable {

    @FXML
    private Label rollno;
    @FXML
    private Label useraccountnumber;
    @FXML
    private Label cnic;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    void myfunction(String username, String text, String text0) {
        rollno.setText(text);
        useraccountnumber.setText(username);
        cnic.setText(text0);
    }
    
}
