/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.doc.www;

import java.util.Iterator;
import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;

/**
 *
 * @author eexit
 */
public class Namespace implements NamespaceContext {
    
    /**
     * Context namespace URI
     */
    final public static String URI = "http://www.doc.org";
    
    /**
     * Context namespace prefix
     */
    final public static String PREFIX = "doc";
    
    /**
     * Namespace URI getter
     * @param prefix
     * @return 
     */
    @Override
    public String getNamespaceURI(String prefix) {
        if (prefix.equals(PREFIX)) {
            return URI;
        }
        return XMLConstants.XML_NS_URI;
    }

    /**
     * Prefix getter
     * @param nsUri
     * @return 
     */
    @Override
    public String getPrefix(String nsUri) {
        if (nsUri.equals(URI)) {
            return PREFIX;
        }
        return XMLConstants.XML_NS_PREFIX;
    }

    @Override
    public Iterator getPrefixes(String string) {
        throw new UnsupportedOperationException("Not applicable.");
    }
}