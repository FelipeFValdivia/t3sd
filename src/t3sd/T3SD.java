package t3sd;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
 
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/**
 *
 * @author felipefvaldivia
 */
public class T3SD {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        final String dir = System.getProperty("user.dir");
        System.out.println("current dir = " + dir);
        JSONParser parser = new JSONParser();
        Object obj;
        try { 
            obj = parser.parse(new FileReader(
                    "personal.json"));
            JSONObject personal = (JSONObject) obj;
            JSONArray doctor = (JSONArray) personal.get("Doctor");
            for (int i = 0; i < doctor.size(); i++)
                System.out.println(doctor.get(i));

            
        } catch (IOException | ParseException ex) {
            Logger.getLogger(T3SD.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static void println(JSONObject jsonObject) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
