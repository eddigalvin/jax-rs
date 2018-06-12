/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import com.mycompany.mavenproject1.AccountService;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//import javax.ws.rs.core.Responsebuilder;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;

import java.util.Collection;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;




import com.mycompany.mavenproject1.Txn;


/**
 *
 * @author Eddie
 */
 @Path("/bank")
public class AccountResource {
    
     AccountService accountservice = new AccountService();
  /*  
        private EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
        private EntityManager em = emf.createEntityManager();
        private EntityTransaction tx = em.getTransaction(); 
        
        
    @GET
    @Path("{id}")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })   
    public /*Account*/      /*  Response getAccount(@PathParam("id") int id) {
        Account acc = em.find(Account.class, id);
        em.close();
        Double bal = (Double)acc.getBalance();
        
        return Response.status(200).entity(bal).build();
    }
    */
     
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/{id}")   
    public /*Account*/   CustAcc getAccountBalance(@PathParam("id") int id) {
        return accountservice.getAccBal(id);
    }
/*          */
    @GET
    @Path("/listaccounts")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Account> listAccounts() {
       return accountservice.allEntries();
    }                                       
    
    @GET
    @Path("/listcustomers")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Customer> listCustomers() {
       return accountservice.allCust();
    }                                       
    
    @GET
    @Path("/findcustomer/{name}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Customer> findCustomer(@PathParam("name") String name) {
       return accountservice.findCust(name);
    }
    
    @GET
    @Path("/findcustomeraccounts/{name}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Account> findCustAccs(@PathParam("name") String name) {
       return accountservice.findCustAccounts(name);
    }                                       

    @POST
    @Path("/createaccount")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Account createAccount(CustAcc c) {
       return accountservice.createAcc(c);
    }
    
    

 }