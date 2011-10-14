/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.lr.univ.sysd.bc;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;

/**
 *
 * @author eexit
 */
public class Generator {
    
    /**
     * Default PDF resolution
     */
    final public static int DEFAULT_RESOLUTION = 300;
    
    /**
     * Default PDF creator
     */
    final public static String DEFAULT_CREATOR = "Apache FOP";
    
    /**
     * PDF title
     */
    public String title = "";
    
    /**
     * PDF author
     */
    public String author = "";
    
    /**
     * Custom PDF resolution
     */
    public int resolution = DEFAULT_RESOLUTION;
    
    /**
     * Custom PDF creator
     */
    public String creator = DEFAULT_CREATOR;
    
    /**
     * Class constructor
     * @param title
     * @param author 
     */
    public Generator(String title, String author) {
        this.title = title;
        this.author = author;
    }
    
    /**
     * Class constructor
     * @param title
     * @param author
     * @param resolution 
     */
    public Generator(String title, String author, int resolution) {
        this(title, author);
        this.resolution = resolution;
    }
    
    /**
     * Class constructor
     * @param title
     * @param author
     * @param resolution
     * @param creator 
     */
    public Generator(String title, String author, int resolution, String creator) {
        this(title, author, resolution);
        this.creator = creator;
    }

    /**
     * Generates a PDF file
     * @param xml
     * @param xsl
     * @param pdf 
     */
    public void generatePdf(File xml, File xsl, File pdf) {
        OutputStream output = null;
        try {
            // Gets the file content of XSL file
            Source xslt = new StreamSource(xsl);
            // Gets the file content of the XML file
            Source xmlSrc = new StreamSource(xml);
            // Opens the PDF file as writter buffer
            output = new FileOutputStream(pdf);
            output = new BufferedOutputStream(output);
            
            // 
            FopFactory fopFactory = FopFactory.newInstance();
            
            FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
            foUserAgent.setAuthor(this.author);
            foUserAgent.setCreator(this.creator);
            foUserAgent.setTargetResolution(this.resolution);
            foUserAgent.setTitle(this.title);
            
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, output);
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(xslt);
            
            Result result = new SAXResult(fop.getDefaultHandler());
            
            transformer.transform(xmlSrc, result);
            output.close();
        } catch (IOException ex) {
            Logger.getLogger(Generator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(Generator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FOPException ex) {
            Logger.getLogger(Generator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
