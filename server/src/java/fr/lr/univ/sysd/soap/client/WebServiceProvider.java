/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.lr.univ.sysd.soap.client;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

/**
 *
 * @author eexit
 */
public class WebServiceProvider {
    
    public static final String ENDPOINT = "http://localhost:8080/ulr-sysd/NewWebService?wsdl";
    
    public static final String NAMESPACE = "";
    
    protected HttpURLConnection connection;
    
    public WebServiceProvider(String serviceName)
    {
        this(serviceName, WebServiceProvider.ENDPOINT, WebServiceProvider.NAMESPACE);
    }
    
    public WebServiceProvider(String serviceName, String endpoint)
    {
        this(serviceName, endpoint, WebServiceProvider.NAMESPACE);
    }
    
    public WebServiceProvider(String serviceName, String endpoint, String namespace)
    {
        try {
            URL url = new URL(endpoint);
            QName serviceQN = new QName(namespace, serviceName);
            Service service = Service.create(url, serviceQN);
        } catch (MalformedURLException ex) {
            Logger.getLogger(WebServiceProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public HttpURLConnection getConnection()
    {
        return this.connection;
    }
}
