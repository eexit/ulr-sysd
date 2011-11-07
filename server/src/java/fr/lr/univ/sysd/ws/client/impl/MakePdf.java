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
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author eexit
 */
public class MakePdf {
    /**
     * File ID
     */
    protected int id;
    
    /**
     * XSL file
     */
    protected File xsl;
    
    /**
     * File result output
     */
    protected File output;
    
    /**
     * Class constructor
     * @param id
     * @param xsl
     * @param output
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public MakePdf(int id, File xsl, File output) throws FileNotFoundException, IOException {
        if (!xsl.isFile()) {
            throw new FileNotFoundException("XML file not found!");
        }
        
        if (output.exists()) {
            output.delete();
        }
        
        output.createNewFile();
        this.output = output;
        this.xsl = xsl;
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
        
        FileInputStream fis = new FileInputStream(this.xsl);
        FileOutputStream fos = new FileOutputStream(this.output);
        
        byte[] xslData = new byte[(int) this.xsl.length()];
        fis.read(xslData);
        fis.close();
        
        byte[] xmlData = ws.generePDF(this.id, xslData);
        fos.write(xmlData);
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
            MakePdf makepdf = new MakePdf(0, new File("../var/transformer.xsl"), new File("test/example.pdf"));
            makepdf.exec();
        } catch (FileNotFoundException_Exception ex) {
            Logger.getLogger(MakePdf.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException_Exception ex) {
            Logger.getLogger(MakePdf.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MakePdf.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MakePdf.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
