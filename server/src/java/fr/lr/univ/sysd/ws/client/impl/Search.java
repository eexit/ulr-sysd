/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.lr.univ.sysd.ws.client.impl;

import fr.lr.univ.sysd.ws.client.DocumentManager;
import fr.lr.univ.sysd.ws.client.DocumentManager_Service;

/**
 *
 * @author eexit
 */
public class Search {
    
    /**
     * Search keywords
     */
    protected String kw;
    
    /**
     * Class constructor
     * @param kw 
     */
    public Search(String kw) {

        /**
         * TODO string escaping/formatter
         */
        
        this.kw = kw;
    }
    
    /**
     * WebService executor
     * @return 
     */
    public String exec() {
        DocumentManager_Service _serv = new DocumentManager_Service();
        DocumentManager ws = _serv.getDocumentManagerPort();
        
        return ws.rechercheDocument(this.kw);
    }
    
    /**
     * Runner
     * @param args 
     */
    public static void main(String[] args) {
        
        /**
         * TODO implement method args management
         */
        
        Search search = new Search("kw1, kw2");
        System.out.println("Résultat de la recherche :" + search.exec());
    }
}
