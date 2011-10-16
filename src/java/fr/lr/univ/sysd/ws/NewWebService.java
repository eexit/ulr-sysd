/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.lr.univ.sysd.ws;

import fr.lr.univ.sysd.gd.Stockage;
import java.io.File;
import java.io.FileInputStream;
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

    private File f ;
    private Stockage st ;
    
    
    @PostConstruct
    private void deserialise() throws FileNotFoundException, IOException
    {
        String path=new File("").getAbsolutePath(); 
        f = new File(path+"/"+"BaseProjet");   
        if(this.f.exists()==false)
        {
            this.f.mkdir(); 
        }
        st = new Stockage("BaseProjet");
        this.st.deserialise();
    }
    
    @PreDestroy
    private void serialise() throws IOException
    {
        this.st.serialise();
    }
    
    
    /** This is a sample web service operation */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "status")
    public String status() {
        //TODO write your implementation code here:
        return this.st.toString();
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "depotDocument")
    public String depotDocument(@WebParam(name = "name") String name, @WebParam(name = "data") byte[] data) throws FileNotFoundException, IOException {
        //TODO write your implementation code here:
        File f = new File("/home/choubi/Documents/cv.odt");
        FileInputStream fis = new FileInputStream(f);
        byte[] b = new byte[(int)f.length()];	
        fis.read(b);
        
        this.st.depotDocument(name, b);
        return null;
    }
}
