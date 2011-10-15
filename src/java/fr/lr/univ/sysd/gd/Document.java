/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.lr.univ.sysd.gd;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author choubi
 */


public class Document {
    
private int id;
private String nom;
private ArrayList<String> description;    

private byte[] donnee;
private String path;


public Document()
{
    id = 0;
    nom = "";
    description = new ArrayList<String>();
    this.path = "";
}

public Document(int p_id, String p_nom)
{
    id = p_id;
    nom = p_nom;
    this.path = nom+id;
}

public Document(int p_id, String p_nom, String path)
{
    id = p_id;
    nom = p_nom;
    this.path = path+"/"+nom+id;
}

public Document(int p_id, String p_nom, ArrayList<String> p_description)
{
    id = p_id;
    nom = p_nom;
    description = p_description;
    this.path = nom+id;
}

//---------

public int write(byte[] p_donnee) throws FileNotFoundException, IOException
{
    File file = new File(this.path);
    FileOutputStream fos = new FileOutputStream(file); 
    FileWriter filewriter = new FileWriter(file);     
    fos.write(p_donnee);
    fos.close();
    
    return 0;
}




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

public byte[] getDonnee()
{
    return this.donnee;
}

public String getPath()
{
    return this.path;
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

public int setDescription(String p_nom)
{
    this.description.add(nom);
    return 0;
}

public int setDonnee(byte[] p_donnee)
{
    this.donnee= p_donnee;
    return 0;
}


public String toString()
{
    return ("id= "+this.id+"; nom= "+this.nom +"; path= "+this.path);
}


}