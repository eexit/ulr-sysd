/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.lr.univ.sysd.bc;

import java.io.File;
import org.doc.www.Namespace;

/**
 *
 * @author eexit
 */
public class Tester {
    
    public static void validatorTest(File xml, File xsd) {
        SchemaValidator validator = new SchemaValidator(xsd);
        if (validator.validate(xml)) {
            System.out.println("[ OK ] " + xml.getName() + " is valid!");
        } else {
            System.err.println("[FAIL] " + xml.getName() + " is valid!");
        }
    }
    
    public static void parserTest(File xml) {
        Parser parser = new Parser(xml, "//doc:motcle", new Namespace());
        System.out.println(parser.hasResults() + " result(s) : " + parser.getResult());
    }
    
    public static void generatorTest(File xml, File xsl, File pdf) {
        Generator gen = new Generator("Foo Document", "Joris Berthelot");
        gen.generatePdf(xml, xsl, pdf);
        System.out.println(pdf.getName() + " : " + pdf.length() / 1024 + " KB");
    }
    
    public static void main(String[] args) {
        File baseDir = new File("../var");
        File outDir = new File("test");
        outDir.mkdirs();
        
        File xml = new File(baseDir, "example.xml");
        File xsd = new File(baseDir, SchemaValidator.XSD_SCHEMA_FILENAME);
        File xsl = new File(baseDir, "transformer.xsl");
        File pdf = new File(outDir, "example.pdf");
        
        System.out.println("--- Validator call ---");
        validatorTest(xml, xsd);
        
        System.out.println("--- Parser call ---");
        parserTest(xml);
        
        System.out.println("--- Generator call ---");
        generatorTest(xml, xsl, pdf);
    }
}
