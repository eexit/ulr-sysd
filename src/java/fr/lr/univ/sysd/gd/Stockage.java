/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.lr.univ.sysd.gd;

import org.doc.www.Namespace;

import fr.lr.univ.sysd.bc.Parser;
import fr.lr.univ.sysd.bc.SchemaValidator;
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

    private File root = null;
    private File data = null;
    private static int ID = 0;
    private static Map map = new HashMap();

    public Stockage(String p_path) {
        this.root = new File(p_path);
        this.data = new File(this.root, "data");
        this.root.mkdir();
        this.data.mkdir();
    }

//test ok
    public int depotDocument(String p_name, byte[] p_donnee) throws FileNotFoundException, IOException {
        int id = createID();
        Document d = new Document(id, p_name);
        this.writeDocument(d, p_donnee);

        Parser parser;
        File xml = new File(this.data, p_name);
        parser = new Parser(xml, "//doc:motcle", new Namespace());
        ArrayList<String> rTmp = parser.getResult();

        File xsd = new File(this.root, SchemaValidator.XSD_SCHEMA_FILENAME);
        SchemaValidator validator = new SchemaValidator(xsd);
        if (!validator.validate(xml)) {
            xml.delete();
            return -1;
        }

        d.setDescription(rTmp);
        this.map.put(id, d);
        return id;
    }

    public ArrayList<String> rechercheDocument(ArrayList<String> motsCle) {
        //TODO 
        ArrayList<String> r = new ArrayList<String>();


        for (int i = 0; i < this.map.size(); i++) {
            for (int y = 0; y < ((Document) map.get(i)).getDescription().size(); y++) {
                if (motsCle.contains(((Document) map.get(i)).getDescription().get(y))) {
                    r.add("id= " + ((Document) map.get(i)).getId() + "  nom= " + ((Document) map.get(i)).getNom());
                }
            }
        }


        return r;
    }

    public byte[] retourneDocument(int p_id) throws FileNotFoundException, IOException {
        //TODO test
        if (this.map.containsKey(p_id) == false) {
            return null;
        }

        String s;
        s = ((Document) this.map.get(p_id)).getNom();
        File f = new File(this.data, s);
        FileInputStream fis = new FileInputStream(f);
        byte[] b = new byte[(int) f.length()];
        fis.read(b);

        return b;
    }

//decrit la structure de stockage dans le fichier description.txt
    public int deserialise() throws IOException {
        File f = new File(this.root, "description.txt");
        FileWriter fr = new FileWriter(f);
        BufferedWriter bw = new BufferedWriter(fr);

        for (int i = 0; i < this.map.size(); i++) {
            int id = ((Document) this.map.get(i)).getId();
            String nom = ((Document) this.map.get(i)).getNom();
            bw.write(id + "\n");
            bw.write(nom + "\n");
        }
        bw.flush();
        bw.close();
        fr.close();

        return 0;
    }

    public int serialise() throws FileNotFoundException, IOException {
        File f = new File(this.root, "description.txt");
        f.createNewFile();
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);

        String line;

        String p_nom = null;
        int p_id;

        while ((line = br.readLine()) != null) {
            p_id = Integer.parseInt(line);
            if ((line = br.readLine()) != null) {
                p_nom = line;
            }
            Document d = new Document(p_id, p_nom);

            //get file description
            Parser parser;
            File xml = new File(this.data, p_nom);
            parser = new Parser(xml, "//doc:motcle", new Namespace());
            ArrayList<String> rTmp = parser.getResult();
            d.setDescription(rTmp);

            this.map.put(p_id, d);

        }

        return 0;
    }

//test ok
    private int writeDocument(Document p_doc, byte[] p_d) throws FileNotFoundException, IOException {
        File file = new File(this.data, p_doc.getNom());
        file.createNewFile();

        FileOutputStream fos = new FileOutputStream(file);
        FileWriter filewriter = new FileWriter(file);
        fos.write(p_d);
        fos.close();

        return 0;
    }
   
    /**
     * Gets a document name with his ID
     * @param p_id
     * @return 
     */
    public String getNameDocument(int p_id) {
        if (!this.map.containsKey(p_id)) {
            return null;
        }
        return ((Document) this.map.get(p_id)).getNom();
    }

    private int createID() {

        return ID++;
    }

    public String toString() {
        String s = "path= " + this.root + "\n";

        return s + map.toString();
    }
}