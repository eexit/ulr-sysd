/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.lr.univ.sysd.server;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
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
public class Server {

    public static void main(String[] args) {

        File baseDir = new File(".");
        File outDir = new File(baseDir, "out");
        outDir.mkdirs();
        
        File xml = new File(baseDir, "xml/docReference.xml");
        File xsl = new File(baseDir, "../transform/transformer.xsl");
        File pdf = new File(baseDir, "../transform/docReference.pdf");
        
        

    }

    public void generatePdf(File xml, File xsl) {
        try {
            FopFactory fopFactory = FopFactory.newInstance();
            fopFactory.setSourceResolution(300);

            FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
            foUserAgent.setAuthor("ULR SYSD");
            foUserAgent.setCreator("Apache FOP");
            foUserAgent.setTargetResolution(300);
            foUserAgent.setTitle("My XML Title");

            OutputStream output = new FileOutputStream("lol");
            output = new BufferedOutputStream(output);


            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, output);

            TransformerFactory factory = TransformerFactory.newInstance();
            //Source xslt = new StreamSource(new File(xsl));
            Source xslt = new StreamSource(xsl);
            Transformer transformer = factory.newTransformer(xslt);
            
            Source xmlSrc = new StreamSource(xml);
            
            //Result result = SAXResult(fop.getDefaultHandler());
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FOPException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }




    }
}
