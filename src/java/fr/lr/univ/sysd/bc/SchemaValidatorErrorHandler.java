/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.lr.univ.sysd.bc;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXParseException;

/**
 *
 * @author eexit
 */
public class SchemaValidatorErrorHandler implements ErrorHandler {
    
    /**
     * Handles SAXParser warning exceptions
     * @param saxpe 
     */
    @Override
    public void warning(SAXParseException saxpe) {
        Logger.getLogger(SchemaValidatorErrorHandler.class.getName()).log(Level.WARNING, null, saxpe);
    }
    
    /**
     * Handles SAXParser error exceptions
     * @param saxpe 
     */
    @Override
    public void error(SAXParseException saxpe) {
        Logger.getLogger(SchemaValidatorErrorHandler.class.getName()).log(Level.SEVERE, null, saxpe);
    }
    
    /**
     * Handles SAXParser fatal error exceptions
     * @param saxpe 
     */
    @Override
    public void fatalError(SAXParseException saxpe) {
        Logger.getLogger(SchemaValidatorErrorHandler.class.getName()).log(Level.SEVERE, null, saxpe);
    }
}
