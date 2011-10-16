/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.lr.univ.sysd.gd;

import java.util.ArrayList;

/**
 * Document class
 * 
 * @author choubi
 * @review eexit
 */
public class Document {
    /**
     * Document ID
     */
    protected int id;
    
    /**
     * Document name
     */
    protected String nom;
    
    /**
     * Document description
     */
    protected ArrayList<String> description;
    
    /**
     * Class constructor
     * @param p_id
     * @param p_nom 
     */
    public Document(int p_id, String p_nom) {
        this.id = p_id;
        this.nom = p_nom;
        this.description = new ArrayList<String>();
    }
    
    /**
     * Document ID getter
     * @return 
     */
    public int getId() {
        return id;
    }

    /**
     * Document name getter
     * @return 
     */
    public String getNom() {
        return nom;
    }
    
    /**
     * Document description getter
     * @return 
     */
    public ArrayList<String> getDescription() {
        return this.description;
    }

    /**
     * Document ID setter
     * @param p_id
     * @return 
     */
    public void setId(int p_id) {
        this.id = p_id;
    }
    
    /**
     * Document name setter
     * @param p_nom 
     */
    public void setNom(String p_nom) {
        this.nom = p_nom;
    }

    /**
     * Adds a description to the document
     * @param p_nom 
     */
    public void addDescription(String p_nom) {
        this.description.add(nom);
    }

    /**
     * Document description setter
     * @param p_description
     */
    public void setDescription(ArrayList<String> p_description) {
        this.description = p_description;
    }
    
    /**
     * Customized document output
     * @return 
     */
    public String toString() {
        return ("id= " + this.id + "; nom= " + this.nom + "; description= " + this.description + " || \n");
    }
}