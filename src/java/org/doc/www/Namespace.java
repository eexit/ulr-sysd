/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.doc.www;

import java.util.ArrayList;
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

    @Override
    public String getNamespaceURI(String string) {
        return URI;
    }

    @Override
    public String getPrefix(String string) {
        return PREFIX;
    }

    @Override
    public Iterator getPrefixes(String string) {
        ArrayList prefixes = new ArrayList();
        prefixes.add(PREFIX);
        return prefixes.iterator();
    }
    
}
