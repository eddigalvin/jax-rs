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
//import javax.persistence.*;
/**
 *
 * @author manueltovaizquierdo
 
@NamedQueries({
	@NamedQuery(
	name = "findCustByName",
	query = "from customer where customer.name = :name"),


	@NamedQuery(
	name = "findCustByAddress",
	query = "from customer where customer.address = :address")
}
                                        */

@Entity 
public class Customer implements Serializable {
    
    @Id 
    @GeneratedValue//(strategy = GenerationType.AUTO) 
    private int custId;
    private String name;
    private String address;
   /* 
    @OneToOne(cascade = CascadeType.ALL)
    private Account account = new Account();

    public Account getAccount() {
        return account;
    }
                                                
    public void setAccount(Account account) {
        this.account = account;
    }                             */  /*    */

    public Customer() {
    }

    public Customer(int custId, String name, String address) {
        this.custId = custId;
        this.name = name;
        this.address = address;
    }


    public int getCustId() {
        return custId;
    }

    public void setCustId(int custId) {
        this.custId = custId;
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
