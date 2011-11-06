/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.lr.univ.sysd.soap.client;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import org.doc.www.Namespace;

/**
 *
 * @author eexit
 */
public class Post {

    public static final String SOAP_ACTION = "";
    protected File xml;

    public Post(File xml) {
        if (!xml.exists()) {
            Logger.getLogger(Post.class.getName()).log(Level.SEVERE, "XML file not found!");
        }
        this.xml = xml;
    }

    public void exec() {
        DataHandler dh = new DataHandler(new FileDataSource(this.xml));
        ByteArrayOutputStream buffOs = new ByteArrayOutputStream();
        
        
        try {
            
            dh.writeTo(buffOs);
            byte[] bdata = buffOs.toByteArray();
            
            SOAPMessage message = MessageFactory.newInstance().createMessage();
            SOAPPart part = message.getSOAPPart();
            SOAPEnvelope envelope = part.getEnvelope();
            SOAPBody body = envelope.getBody();
            SOAPBodyElement depot = body.addBodyElement(envelope.createName("depotDocument"));
            
            SOAPElement name = depot.addChildElement("name");
            name.addTextNode(this.xml.getName());
            
            SOAPElement data = depot.addChildElement("data");
            data.addTextNode(new String(bdata));
            
            message.saveChanges();
            System.out.println("Request:");
            message.writeTo(System.out);
            
            SOAPConnection connection = SOAPConnectionFactory.newInstance().createConnection();
            SOAPMessage res = connection.call(message, new URL("http://localhost:8080/ulr-sysd/NewWebService"));
            
            System.out.println("\nResponse:");
            res.writeTo(System.out);
            
            connection.close();
            
        } catch (IOException ex) {
            Logger.getLogger(Post.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SOAPException ex) {
            Logger.getLogger(Post.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args)
    {
        Post post = new Post(new File("./../var/file.xml"));
        post.exec();
    }
}
