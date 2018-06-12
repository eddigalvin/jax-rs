/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 *
 * @author manueltovaizquierdo
 */
@Entity
public class Txn implements Serializable {
    
    @Id @GeneratedValue
    private int txnId;
    private int accFrom;
    private int accTo;
    private double amount;
    //private Date date;

    public Txn(/*int txnId,*/ int accFrom, int accTo, double amount/*, Date date*/) {
        this.txnId = txnId;
        this.accFrom = accFrom;
        this.accTo = accTo;
        this.amount = amount;
        //this.date = date;
    }

    public Txn() {
    }
    
     public int getTxnId() {
        return txnId;
    }

    public void setTxnId(int txnId) {
        this.txnId = txnId;
    }

    public int getAccFrom() {
        return accFrom;
    }

    public void setAccFrom(int accFrom) {
        this.accFrom = accFrom;
    }

    public int getAccTo() {
        return accTo;
    }

    public void setAccTo(int accTo) {
        this.accTo = accTo;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
/*
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }                                       */

   

   
    
    
    
}
