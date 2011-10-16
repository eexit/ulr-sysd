/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.lr.univ.sysd.gd;

import java.util.ArrayList;

/**
 *
 * @author choubi
 */


public class Document {
    
private int id;
private String nom;
private ArrayList<String> description;    





public Document(int p_id, String p_nom)
{
    id = p_id;
    nom = p_nom;
    description = new ArrayList<String>();
}




//---------



//getter
public int getId()
{
    return id;
}

public String getNom()
{
    return nom;
}

public ArrayList<String> getdescription()
{
    return this.description;
}



//setter
public int setId(int p_id)
{
    this.id = p_id;
    return 0;
}

public int setNom(String p_nom)
{
    this.nom = p_nom;
    return 0;
}

public int addDescription(String p_nom)
{
    this.description.add(nom);
    return 0;
}

public int setDescriptiot(ArrayList<String> p_description)
{
    this.description = p_description;
    return 0;
}


public String toString()
{
    return ("id= "+this.id+"; nom= "+this.nom +"; description= "+this.description+" || \n");
}


}