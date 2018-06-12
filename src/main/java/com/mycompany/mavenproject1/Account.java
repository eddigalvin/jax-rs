package com.mycompany.mavenproject1;

//import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.xml.bind.annotation.XmlRootElement;
/*import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.ektorp.support.Views.Internal;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.jboss.aerogear.simplepush.server.datastore.Views;*/


@Entity
// @Table 
@XmlRootElement
  //@JsonIgnoreProperties(ignoreUnknown = true)
    //@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,   property = "id")
public class Account implements Serializable {

    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
     //@JsonView(value={Views.Internal.class})
    //@OneToOne(cascade = CascadeType.ALL)
    private int id;
    //private int custId;
    //@GeneratedValue(strategy = GenerationType.AUTO)
    //private int number;
    private String type;
    private double balance;
    
    public Account(){}
  
    @OneToMany(cascade = CascadeType.ALL)
    private Collection<Txn> txn = new ArrayList<Txn>();
    
     public Collection<Txn> getTxns() {
        return txn;
    }

    public void setTxns(Collection<Txn> txns) {
        this.txn = txns;
    }                                            /*       */
 /*      */ 
    
    @OneToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name="custId")
    private Customer customer = new Customer();

    public Customer getCust() {
        return customer;
    }

    public void setCust(Customer cust) {
        this.customer = cust;
    }                                                      

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
  /*   
    public int getCustId() {
        return custId;
    }

    public void setCustId(int custId) {
        this.custId = custId;
    }
                                                       
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }                                                   */

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
/*
    void setTxns(Collection<Txn> txnList) {
        throw new UnsupportedOperationException("Not supported yet.");
        //To change body of generated methods, choose Tools | Templates.
    }               */
    
}
