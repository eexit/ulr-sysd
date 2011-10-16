/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.lr.univ.sysd.gd;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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


private String path = "/home/choubi/Documents/BaseProjet"; //path par default
private int ID;
private Map map = new HashMap();


public Stockage(String p_path)
{ 
    ID=0;
    this.path = p_path;
}




//test ok
public int depotDocument(String p_name , byte[] p_donnee) throws FileNotFoundException, IOException
{
    int id = createID();
    Document d = new Document(id,p_name);
    this.writeDocument(d, p_donnee);
    this.map.put(id, d);
    return 0;
}



public ArrayList<String> rechercheDocument(ArrayList<String> motsCle)
{
    //TODO
    ArrayList<String> r = new ArrayList<String>();
    
    for(int i=0 ; i<this.map.size() ; i++)
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

public byte[] retoutneDocument(int p_id) throws FileNotFoundException, IOException
{
    //TODO test
    if(this.map.containsKey(p_id)==false)
    {
        return null;
    }   
    
    String s;
    s = ((Document)this.map.get(p_id)).getNom();
    File f = new File(this.path+"/"+s);
    FileInputStream fis = new FileInputStream(f);
    byte[] b = new byte[(int)f.length()];	
    fis.read(b);

    return b;
}

//decrit la structure de stockage dans le fichier description.txt
public int deserialise() throws IOException
{
    File f = new File(this.path+"/"+"desctiption.txt");
    FileWriter fr = new FileWriter(f);
    BufferedWriter bw = new BufferedWriter(fr);
    
    for(int i=0 ; i<this.map.size() ; i++)
    {
        int id = ((Document)this.map.get(i)).getId();
        String nom = ((Document)this.map.get(i)).getNom();
        bw.write(id+"\n");
        bw.write(nom+"\n");
    }
    bw.flush();
    bw.close();
    fr.close();
    
    return 0;
}

public int serialise() throws FileNotFoundException, IOException
{
    File f = new File(this.path+"/"+"desctiption.txt");
    f.createNewFile();
    FileReader fr = new FileReader(f);
    BufferedReader br = new BufferedReader(fr);
    
    String line;
    
    String p_nom = null;
    int p_id;
    
    while( (line = br.readLine()) !=null)
    {
        p_id = Integer.parseInt(line); 
        if((line = br.readLine()) !=null)
        {
            p_nom = line;
        }
        Document d = new Document(p_id,p_nom);
        this.map.put(p_id, d);
         
    }
    
    return 0;
}

//test ok
private int writeDocument(Document p_doc , byte[] p_d) throws FileNotFoundException, IOException
{
    File file = new File(this.path+"/"+p_doc.getNom());
    file.createNewFile();
    
    FileOutputStream fos = new FileOutputStream(file); 
    FileWriter filewriter = new FileWriter(file);    
    fos.write(p_d);
    fos.close();

    return 0;
}

private int createID()
{
    return ID++;
}






/*
public int addDocument(Document p_doc)
{
    this.map.put(p_doc.getId(), p_doc);
    return 0;
}
*/





/*
 * setter & getter
 */

public int setPath(String p_path)
{
    this.path = p_path;
    return 0;
}

public String getPath()
{
    return this.path;
}








public String toString()
{
    String s = "path= "+this.path+"\n";
        
    return s+map.toString();
}

}
