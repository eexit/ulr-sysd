/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.lr.univ.sysd.ws;

import fr.lr.univ.sysd.gd.Stockage;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author choubi
 */
@WebService(serviceName = "NewWebService")
public class NewWebService {

    private Stockage st = new Stockage("");
    
    
    @PostConstruct
    private void deserialiser() throws FileNotFoundException, IOException
    {
        this.st.deserialise();
    }
    
    @PreDestroy
    private void serialyser() throws FileNotFoundException, IOException
    {
        this.st.deserialise();
    }
    
    
    
    /** This is a sample web service operation */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "depotDocument")
    public String depotDocument(@WebParam(name = "name") String name, @WebParam(name = "data") byte[] data) {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "retoutneDocument")
    public byte[] retoutneDocument(@WebParam(name = "id") int id) {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "renerePDF")
    public String renerePDF(@WebParam(name = "id") int id, @WebParam(name = "XSLfo") byte[] XSLfo) {
        //TODO write your implementation code here:
        return null;
    }
}
