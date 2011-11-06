/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.lr.univ.sysd.ws.client.impl;

import fr.lr.univ.sysd.ws.client.DocumentManager;
import fr.lr.univ.sysd.ws.client.DocumentManager_Service;
import fr.lr.univ.sysd.ws.client.FileNotFoundException_Exception;
import fr.lr.univ.sysd.ws.client.IOException_Exception;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author eexit
 */
public class Post {
    /**
     * XML file
     */
    protected File xml;
    
    /**
     * Class constructor
     * @param xml
     * @throws FileNotFoundException 
     */
    public Post(File xml) throws FileNotFoundException {
        if (!xml.exists()) {
            throw new FileNotFoundException("XML file not found!");
        }
        
        this.xml = xml;
    }
    
    /**
     * WebService executor
     * @return
     * @throws FileNotFoundException_Exception
     * @throws IOException_Exception
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public int exec() throws FileNotFoundException_Exception, IOException_Exception, FileNotFoundException, IOException {
        DocumentManager_Service _serv = new DocumentManager_Service();
        DocumentManager ws = _serv.getDocumentManagerPort();

        FileInputStream fis = new FileInputStream(this.xml);
        byte[] data = new byte[(int) this.xml.length()];
        fis.read(data);
        
        return ws.depotDocument(this.xml.getName(), data);
    }
    
    /**
     * Runner
     * @param args 
     */
    public static void main(String[] args) {
        try {
            Post post = new Post(new File("./../var/example.xml"));
            System.out.println("Document enregistré sous l'ID #" + post.exec());
            post.exec();
        } catch (IOException ex) {
            Logger.getLogger(Post.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException_Exception ex) {
            Logger.getLogger(Post.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException_Exception ex) {
            Logger.getLogger(Post.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
