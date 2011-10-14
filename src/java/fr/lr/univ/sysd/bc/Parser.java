/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.lr.univ.sysd.bc;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author eexit
 */
public class Parser {

    /**
     * Default XPath query
     */
    protected String query = "/";
    /**
     * XML file to query
     */
    protected File xml = null;
    /**
     * Namespace context
     */
    protected NamespaceContext nsContext = null;
    /**
     * Node list result from XPath query
     */
    protected NodeList nodeSet = null;

    /**
     * Class constructor
     * @param xml 
     */
    public Parser(File xml) {
        this.xml = xml;
    }

    /**
     * Class constructor
     * @param xml
     * @param query 
     */
    public Parser(File xml, String query) {
        this(xml);
        this.query(query);
    }

    /**
     * Class constructor
     * @param xml
     * @param query
     * @param nsContext 
     */
    public Parser(File xml, String query, NamespaceContext nsContext) {
        this(xml);
        this.setNamespaceContext(nsContext);
        this.query(query);
    }

    public void setNamespaceContext(NamespaceContext nsContext) {
        this.nsContext = nsContext;
    }

    /**
     * XPath query getter
     * @return 
     */
    public String getQuery() {
        return this.query;
    }

    /**
     * XPath query invoker
     */
    public void query() {
        this.query(this.query);
    }

    /**
     * XPath query invoker with custom query
     * @param query 
     */
    public void query(String query) {
        try {
            this.query = query;
            // Creates a new DOM Document
            DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
            domFactory.setNamespaceAware(true);

            // Opens the XML file as DOM Document
            DocumentBuilder xmlBuilder = domFactory.newDocumentBuilder();
            Document xmlF = xmlBuilder.parse(this.xml);

            // Creates a new XPath instance
            XPathFactory factory = XPathFactory.newInstance();
            XPath xpath = factory.newXPath();

            // Sets the namespace if any
            if (null != this.nsContext) {
                xpath.setNamespaceContext(this.nsContext);
            }

            // Evaluates the XPath query
            XPathExpression xp = xpath.compile(this.query);
            this.nodeSet = (NodeList) xp.evaluate(xmlF, XPathConstants.NODESET);

        } catch (SAXException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (XPathExpressionException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test if there are results and gives how many
     * @return 
     */
    public int hasResults() {
        if (null == this.nodeSet) {
            return 0;
        }
        return this.nodeSet.getLength();
    }

    /**
     * Results getter
     * @return 
     */
    public ArrayList<String> getResult() {
        if (null == this.nodeSet) {
            return null;
        }
        
        ArrayList<String> result = new ArrayList<String>();
        for (int i = 0; i < this.nodeSet.getLength(); i++) {
            Node node = this.nodeSet.item(i);
            result.add(node.getTextContent());
        }
        return result;
    }
}