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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author eexit
 */
public class Get {
    /**
     * File ID
     */
    protected int id;
    
    /**
     * File result output
     */
    protected File output;
    
    /**
     * Class constructor
     * @param id
     * @param output
     * @throws IOException 
     */
    public Get(int id, File output) throws IOException {
        if (output.exists()) {
            output.delete();
        }
        
        output.createNewFile();
        this.output = output;
        this.id = id;
    }
    
    /**
     * WebService executor
     * @throws FileNotFoundException
     * @throws IOException
     * @throws FileNotFoundException_Exception
     * @throws IOException_Exception 
     */
    public void exec() throws FileNotFoundException, IOException, FileNotFoundException_Exception, IOException_Exception {
        DocumentManager_Service _serv = new DocumentManager_Service();
        DocumentManager ws = _serv.getDocumentManagerPort();
        
        FileOutputStream fos = new FileOutputStream(this.output);
        byte[] data = ws.retourneDocument(this.id);
        fos.write(data);
        fos.close();
    }
    
    /**
     * Runner
     * @param args 
     */
    public static void main(String[] args) {
        
        /**
         * TODO implement method args management
         */
        
        try {
            Get get = new Get(0, new File("test/example-retrieved.xml"));
            get.exec();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Get.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException_Exception ex) {
            Logger.getLogger(Get.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException_Exception ex) {
            Logger.getLogger(Get.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Get.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
