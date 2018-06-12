package com.mycompany.mavenproject1;

import java.util.ArrayList;
import java.util.List;
//import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.ArrayList;;
import com.mycompany.mavenproject1.Txn;
/*
import java.util.Iterator;  
import java.util.List;  
  
//import org.hibernate.cfg.AnnotationConfiguration;  
import org.hibernate.*;
import org.hibernate.Session;
*/

@Path("/bank")
public class AccountService {
    
        private EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
        private EntityManager em = emf.createEntityManager();
        private EntityTransaction tx = em.getTransaction(); 
        
 /*       AnnotationConfiguration configuration=new AnnotationConfiguration();  
   configuration.configure("hibernate.cfg.xml");  
   SessionFactory sFactory=configuration.buildSessionFactory();  
    Session session=sFactory.openSession();  */

/*
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Account> getPersons() {
       return allEntries();
    }                                   */                                 
    
     public List<Account> allEntries() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Account> cq = cb.createQuery(Account.class);
        Root<Account> rootEntry = cq.from(Account.class);
        CriteriaQuery<Account> all = cq.select(rootEntry);
        TypedQuery<Account> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }
    
     public List<Customer> allCust() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);
        Root<Customer> rootEntry = cq.from(Customer.class);
        CriteriaQuery<Customer> all = cq.select(rootEntry);
        TypedQuery<Customer> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    } 
    
     public List<Customer> findCust(String name) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);
        Root<Customer> rootEntry = cq.from(Customer.class);
        CriteriaQuery<Customer> all = cq.select(rootEntry);
        TypedQuery<Customer> query = em.createQuery("SELECT c FROM Customer c WHERE c.name =:name", Customer.class);
        return query.setParameter("name",name).getResultList();
    } 
     
     public List<Account> findCustAccounts( String name) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Account> cq = cb.createQuery(Account.class);
        Root<Account> rootEntry = cq.from(Account.class);
        CriteriaQuery<Account> all = cq.select(rootEntry);
        TypedQuery<Account> query = em.createQuery("SELECT  a  \n" +
"FROM Account\n" +
"join Customer on customer.custId=account.id \n" +
"and customer.name =:name", Account.class);
        return query.setParameter("name",name).getResultList();
    } 
     
         /*   */
   /* @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/{id}")   
    public /*Account*/ //  CustAcc getAccount(@PathParam("id") int id) {  */
        public CustAcc getAccBal(int id){
        Account acc = em.find(Account.class, id);
        em.close();
        CustAcc custAcc=new CustAcc();
        custAcc.setName(acc.getCust().getName());
        custAcc.setAddress(acc.getCust().getAddress());
        custAcc.setType(acc.getType());
        custAcc.setBalance(acc.getBalance());
        return custAcc;
    }                                              
    
  
            public Account createAcc(CustAcc c){
              //Account test = em.find(Account.class, /*a*/c.getCust().getId());
            //Customer custTest=em.
            //if (test == null) {
              Customer c1 = new Customer();
            c1.setName(c.getName());
            c1.setAddress(c.getAddress());
            //List<Customer> custNameTest=checkCust(c1.getName());//em.//.find(Customer.class, c1);
            //String type = c.getType();
            
         //if((custTest==null)&&((type.equalsIgnoreCase("savings"))||(type.equalsIgnoreCase("current")))){
         Account a1 = new Account();
         a1.setCust(c1);
         a1.setType(c.getType());                      
            tx.begin();
            em.persist(a1);           
            tx.commit();
            em.close();                      
        return a1; 
   /*      }
         else{
         Account a1 = new Account();
         return a1;
         }                          */
    }
    
    @POST
    @Path("/accountlodgement")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Txn/*Customer*/ lodge(Txn txn1){
            //Create new txn from input 
            Txn txn=new Txn();
            txn.setAccTo(txn1.getAccTo());
            txn.setAccFrom(txn1.getAccFrom());
            txn.setAmount(txn1.getAmount());
            int id=txn1.getAccTo();
            //find account with Accto number passed in for money to be lodged
            Account acctxn = em.find(Account.class, id);
            //add the money to the account balance
            acctxn.setBalance(acctxn.getBalance()+txn1.getAmount());
            //add the transaction to the accounts transaction arraylist
            acctxn.getTxns().add(txn);
            //Persist modified AccountTo object to dbase...
             tx.begin();
            em.persist(acctxn);
            
            tx.commit();
            em.close();
       // }                       
        return txn;   
    }
    
    @POST
    @Path("/accounttransfer")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Txn/*Customer*/ transfer(Txn txn1){
            //Create new txn from input 
            Txn txn=new Txn();
            txn.setAccTo(txn1.getAccTo());
            txn.setAccFrom(txn1.getAccFrom());
            txn.setAmount(txn1.getAmount());
            
            Txn txnCopy=new Txn();
            txnCopy.setAccTo(txn1.getAccTo());
            txnCopy.setAccFrom(txn1.getAccFrom());
            txnCopy.setAmount(txn1.getAmount());
            
            
            int idFrom=txn1.getAccFrom();
            //find account with Accto number passed in for money to be lodged
            Account accFrom = em.find(Account.class, idFrom);
            //add the money to the account balance
            double bal=accFrom.getBalance()-txn1.getAmount();
            if(bal>0.0){
            accFrom.setBalance(bal);
            //add the transaction to the accounts transaction arraylist
            accFrom.getTxns().add(txn);
            //Persist modified AccountTo object to dbase...
            
            int idTo=txn1.getAccTo();
            //find account with Accto number passed in for money to be lodged
            Account accTfTo = em.find(Account.class, idTo);
            //add the money to the account balance
            accTfTo.setBalance(accTfTo.getBalance()+txn1.getAmount());
            //add the transaction to the accounts transaction arraylist
            accTfTo.getTxns().add(txnCopy);
            //Persist modified AccountTo object to dbase...
             tx.begin();
             
            em.persist(accFrom);
            em.persist(accTfTo);
            tx.commit();
            em.close();       /*  */
                               
        return txn;
           }
            else{
                txn.setAmount(0.0);
            }
         return txn;       
    }
    
   @POST
    @Path("/accountwithdrawal")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Txn/*Customer*/ withdraw(Txn txn1){
            //Create new txn from input 
            Txn txn=new Txn();
            txn.setAccTo(txn1.getAccTo());
            txn.setAccFrom(txn1.getAccFrom());
            txn.setAmount(txn1.getAmount());
            int id=txn1.getAccFrom();
            //find account with Accto number passed in for money to be lodged
            Account acctxn = em.find(Account.class, id);
            //add the money to the account balance
            double bal=acctxn.getBalance()-txn1.getAmount();
            if(bal>=0){
            acctxn.setBalance(bal);
            //add the transaction to the accounts transaction arraylist
            acctxn.getTxns().add(txn);
            //Persist modified AccountTo object to dbase...
             tx.begin();
            em.persist(acctxn);
            
            tx.commit();
            em.close();                             
        return txn; 
            }
        else{
              txn.setAmount(0.0);
                }
        return txn;
    }
    
        
    @DELETE
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void deleteItem(@PathParam("id") int id) {
        Account test = em.find(Account.class, id);
        if (test !=null) {
            tx.begin();
            em.remove(test);
            tx.commit();
            em.close();
        }
    }
}