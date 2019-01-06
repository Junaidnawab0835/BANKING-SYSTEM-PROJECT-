/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banking.system.g;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 *
 * @author HANIF
 */
public class DATA{
  String FirstName,LastName,CNIC,Address,DATE,ACCOUNTNUMBER,AMOUNT;

    

    public String getDATE() {
        return DATE;
    }

    public void setDATE(String DATE) {
        this.DATE = DATE;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public String getCNIC() {
        return CNIC;
    }

    public void setCNIC(String CNIC) {
        this.CNIC = CNIC;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getACCOUNTNUMBER() {
        return ACCOUNTNUMBER;
    }

    public void setACCOUNTNUMBER(String ACCOUNTNUMBER) {
        this.ACCOUNTNUMBER = ACCOUNTNUMBER;
    }

    public String getAMOUNT() {
        return AMOUNT;
    }

    public void setAMOUNT(String AMOUNT) {
        this.AMOUNT = AMOUNT;
    }

    public DATA(String FirstName, String LastName, String CNIC, String Address, String DATE,String ACCOUNTNUMBER, String AMOUNT) {
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.CNIC = CNIC;
        this.Address = Address;
        this.ACCOUNTNUMBER = ACCOUNTNUMBER;
        this.AMOUNT = AMOUNT;
        this.DATE=DATE;
    }

}
