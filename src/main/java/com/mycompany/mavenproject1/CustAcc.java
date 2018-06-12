/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.CascadeType;
//import javax.persistence.JsonProperty;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;
/**
 *
 * @author manueltovaizquierdo
 */

@XmlRootElement
public class CustAcc implements Serializable {
    
   
    private String name;
    private String address;
    private String type;
    private double balance;
   /* 
    @OneToOne(cascade = CascadeType.ALL)
    private Account account = new Account();

    public Account getAccount() {
        return account;
    }
                                                
    public void setAccount(Account account) {
        this.account = account;
    }                             */  /*    */

    public CustAcc(){}
    
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }


    public CustAcc(String name, String address,String type) {
        
        this.name = name;
        this.address = address;
        this.type=type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


   

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
     public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}

