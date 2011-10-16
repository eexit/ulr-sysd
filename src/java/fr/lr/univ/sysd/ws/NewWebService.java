/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.lr.univ.sysd.ws;

import fr.lr.univ.sysd.bc.Generator;
import fr.lr.univ.sysd.gd.Stockage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
        this.st.depotDocument(name, data);
        return null;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "retourneDocument")
    public byte[] retourneDocument(@WebParam(name = "id") int id) throws FileNotFoundException, IOException {
        //TODO write your implementation code here:
        return this.st.retoutneDocument(id);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "rechercheDocument")
    public String rechercheDocument(@WebParam(name = "motsCle") String motsCle) {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "generePDF")
    public byte[] generePDF(@WebParam(name = "id") int id, @WebParam(name = "XSLfo") byte[] XSLfo) throws FileNotFoundException, IOException {
        //TODO write your implementation code here:
        Generator gen = new Generator("Foo Document", "Joris Berthelot"); 
        File xml = new File(this.st.getNameDocument(id));
        File xsl = new File("tmpXSLfile");
        FileOutputStream fos = new FileOutputStream(xsl); 
        fos.write(XSLfo);
        
        File pdf = new File("tmpPDFfile");
        gen.generatePdf(xml, xsl, pdf);
       
        System.out.println(pdf.getName() + " : " + pdf.length() / 1024 + " KB");
        
        FileInputStream fis = new FileInputStream(pdf);
        byte[] b_pdf = new byte[(int)pdf.length()];	
        fis.read(b_pdf);
        
        
        return b_pdf;
    }
}
