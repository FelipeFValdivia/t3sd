package t3sd;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.FileNotFoundException;
import java.io.FileReader;
 
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


/**
 *
 * @author felipefvaldivia
 */
public class T3SD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(
        "personal.json")); 
        JSONObject jsonObject = (JSONObject) obj;
        println(jsonObject);
    }
    
}
