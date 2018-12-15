package t3sd;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
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
    public Doctor best_doc(List myDoctorList){
        
        return null;
        
    }
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        final String dir = System.getProperty("user.dir");
        System.out.println("current dir = " + dir);
        JSONParser parser = new JSONParser();
        Object obj;
        JSONObject single_json;
        String apellido, nombre;
        Long id, estudios, experiencia;
        try { 
            obj = parser.parse(new FileReader(
                    "personal.json"));
            JSONObject personal = (JSONObject) obj;
            
            List myDoctorList = new ArrayList();
            List myEnfermeroList = new ArrayList();
            List myParamedicoList = new ArrayList();

            JSONArray doctor = (JSONArray) personal.get("Doctor");
            Doctor best_doc;
            Long best_sum = 0L;
            for (int i = 0; i < doctor.size(); i++){
                single_json = (JSONObject) doctor.get(i);
                apellido = (String)single_json.get("apellido");
                nombre = (String)single_json.get("nombre");
                id = (Long)single_json.get("id");
                estudios = (Long)single_json.get("estudios");
                experiencia = (Long)single_json.get("experiencia");
                Doctor doc = new Doctor(id, nombre, apellido, estudios, experiencia);
                if (best_sum < estudios + experiencia){
                    best_doc = doc;
                    best_sum = estudios + experiencia;
                }
                myDoctorList.add(doc);
            }
            
            System.out.println(personal);
            JSONArray enfermero = (JSONArray) personal.get("enfermero");
            System.out.println(enfermero);
            for (int i = 0; i < enfermero.size(); i++){
                single_json = (JSONObject) enfermero.get(i);
                apellido = (String)single_json.get("apellido");
                nombre = (String)single_json.get("nombre");
                id = (Long)single_json.get("id");
                estudios = (Long)single_json.get("estudios");
                experiencia = (Long)single_json.get("experiencia");
                Enfermero enf = new Enfermero(id, nombre, apellido, estudios, experiencia);
                myEnfermeroList.add(enf);
            }
            
            JSONArray paramedico = (JSONArray) personal.get("Paramedico");
            for (int i = 0; i < paramedico.size(); i++){
                single_json = (JSONObject) paramedico.get(i);
                apellido = (String)single_json.get("apellido");
                nombre = (String)single_json.get("nombre");
                id = (Long)single_json.get("id");
                estudios = (Long)single_json.get("estudios");
                experiencia = (Long)single_json.get("experiencia");
                Paramedico par = new Paramedico(id, nombre, apellido, estudios, experiencia);
                myDoctorList.add(par);
            }
            
            
        ServerSocket listener = new ServerSocket(9090);
        try {
            while (true) {
                Socket socket = listener.accept();
                try {
                    PrintWriter out =
                        new PrintWriter(socket.getOutputStream(), true);
                    out.println(1L);
                    BufferedReader input =
                        new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    System.out.println(input.readLine());
                } finally {
                    socket.close();
                }
            }
        }
        finally {
            listener.close();
        }
            
        } catch (IOException | ParseException ex) {
            Logger.getLogger(T3SD.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static void println(JSONObject jsonObject) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
