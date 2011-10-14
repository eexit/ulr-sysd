/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package codemetier;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author choubi
 */
public class Stockage {

//chemin vers le dossier de stoclage    
private String path = "/home/choubi/Documents/BaseProjet"; //path par default

//clé autoincrémenté
private int ID;

public static Map map = new HashMap();

public Stockage()
{ 
    ID=0;
}

public Stockage(String p_path)
{ 
    ID=0;
    this.path = p_path;
}


public int depotDocument(String p_name , byte[] p_donnee) throws FileNotFoundException, IOException
{
    System.out.println("Stockage-> depotdocument... ");
    int id = createID();
    Document d = new Document(id,p_name,path);
    d.write(p_donnee);
    map.put(id, d);
    System.out.println("Stockage-> document= "+d.toString());
    return 0;
}

public ArrayList<String> rechercheDocument(ArrayList<String> motsCle)
{
    //TODO
    ArrayList<String> r = new ArrayList<String>();
    
    for(int i=0 ; i<map.size() ; i++)
    {
        for(int y=0 ; y< ((Document)map.get(y)).getdescription().size() ; y++)
        {
            if( motsCle.contains( ((Document)map.get(y)).getdescription().get(y)  ) )
            {
                r.add(  "id= "+((Document)map.get(y)).getId()+"  nom= "+  ((Document)map.get(y)).getNom()   );
            }
        }
    }
    return r;
}

public byte[] retoutneDocument(int p_id)
{
    return ((Document)map.get(p_id)).getDonnee();
}

public int serialise() throws IOException
{
    File f = new File(this.path+"/"+"desctiption.txt");
    FileWriter fr = new FileWriter(f);
    BufferedWriter bw = new BufferedWriter(fr);
    
    for(int i=0 ; i<this.map.size() ; i++)
    {
        int id = ((Document)this.map.get(i)).getId();
        String path = ((Document)this.map.get(i)).getPath();
        String nom = ((Document)this.map.get(i)).getNom();
        bw.write(id+"\n");
        bw.write(path+"\n");
        bw.write(nom+"\n");
       
    }
    bw.flush();
    bw.close();
    fr.close();
    
    return 0;
}

public int deserialise() throws FileNotFoundException, IOException
{
    File f = new File(this.path+"/"+"desctiption.txt");
    FileReader fr = new FileReader(f);
    BufferedReader br = new BufferedReader(fr);
    
    String line;
    while( (line = br.readLine()) !=null)
    {
        int id = Integer.valueOf(line);
        String path = br.readLine();
        String nom = br.readLine();
        Document d = new Document(id,nom,path);
        this.map.put(id, d);
    }
    
    
    
    
    return 0;
}

public int setPath(String p_path)
{
    this.path = p_path;
    return 0;
}

private int createID()
{
    return ID++;
}

public String toString()
{
    return map.toString();
}


}
