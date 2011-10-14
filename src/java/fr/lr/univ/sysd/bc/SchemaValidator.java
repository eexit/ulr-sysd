/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.lr.univ.sysd.bc;

import java.io.File;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

/**
 *
 * @author eexit
 */
public class SchemaValidator {
    
    /**
     * Default W3C validation schema
     */
    final public static String W3C_XML_SCHEMA_URI = "http://www.w3.org/2001/XMLSchema";
    
    /**
     * Default schema name
     */
    final public static String XSD_SCHEMA_FILENAME = "schema.xsd";
    
    /**
     * Schema path
     */
    public File schemaPath = new File(XSD_SCHEMA_FILENAME);
    
    /**
     * Class constructor
     */
    public SchemaValidator() {
        
    }
    
    /**
     * Class constructor with custom schema path
     * @param schemaPath 
     */
    public SchemaValidator(File schemaPath) {
        if (!schemaPath.exists()) {
            Logger.getLogger(SchemaValidator.class.getName()).log(Level.SEVERE, "Schema file not found!");
            return;
        }
        this.schemaPath = schemaPath;
    }
    
    /**
     * Validation process
     * @param xml
     * @return 
     */
    public boolean validate(File xml) {
        try {
            Source xmlData = new StreamSource(xml);
            SchemaValidatorErrorHandler errorHandler = new SchemaValidatorErrorHandler();
            SchemaFactory factory = SchemaFactory.newInstance(W3C_XML_SCHEMA_URI);
            Schema schema = factory.newSchema(schemaPath);
            Validator validator = schema.newValidator();
            validator.setErrorHandler(errorHandler);
            validator.validate(xmlData);
            return true;
        } catch (SAXException ex) {
            Logger.getLogger(SchemaValidator.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (IOException ex) {
            Logger.getLogger(SchemaValidator.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}