package t3sd;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.Scanner;


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
        
        String address1, address2, address3, myAddress;
//        myAddress = "dist73.inf.santiago.usm.cl";
//        address1 = "dist74.inf.santiago.usm.cl";
//        address2 = "dist75.inf.santiago.usm.cl";
//        address3 = "dist76.inf.santiago.usm.cl";

        System.out.println("Ingresa tu direccion:");        
        Scanner sc = new Scanner(System.in);
        myAddress = sc.nextLine(); 

        System.out.println("Ingresa las otras tres direcciones:");
        
        address1 = sc.nextLine(); 
        address2 = sc.nextLine(); 
        address3 = sc.nextLine(); 

    	Scanner rawInput = new Scanner(System.in);
    	System.out.print("Presiona enter para comenzar");
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
            Map <Long, Doctor> docMap = null;
            Map <Long, Enfermero> enfMap = null;
            Map <Long, Paramedico> parMap = null;
            
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
                docMap.put(id, doc);
            }
            
            System.out.println(personal);
            JSONArray enfermero;
            enfermero = (JSONArray) personal.get("enfermero");
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
                enfMap.put(id, enf);
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
                myParamedicoList.add(par);
                parMap.put(id, par);
            }
   
                        
            ServerSocket listener = new ServerSocket(9090);
            Bully bully = new Bully(best_sum, -1L, "", myAddress, address1, address2, address3);
            //Se busca al primer coordinador
            Long sum1 = 0L;
            Long sum2 = 0L;
            Long sum3 = 0L;
            try {
                Socket socket = listener.accept();


                Thread t = new ServerHandler(socket, myAddress, best_sum, bully); 
                t.start();

                while(bully.get_address_best_sum(address1) == null ){
                    try {
                        Socket s1 = new Socket(address1, 9090);

                        BufferedReader input =
                            new BufferedReader(new InputStreamReader(s1.getInputStream()));
                        String answer = input.readLine();
                        sum1 = Long.parseLong(answer, 10);
                        System.out.println(sum1);
                        bully.set_address_best_sum(address1, sum1);
                    } catch (IOException ex) {
                        Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }                


                while(bully.get_address_best_sum(address2) == null ){
                    try {
                        Socket s2 = new Socket(address2, 9090);

                        BufferedReader input =
                            new BufferedReader(new InputStreamReader(s2.getInputStream()));
                        String answer = input.readLine();
                        sum2 = Long.parseLong(answer, 10);
                        System.out.println(sum2);
                        bully.set_address_best_sum(address2, sum2);
                    } catch (IOException ex) {
                        Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } 
                
                
                while(bully.get_address_best_sum(address3) == null ){
                    try {
                        Socket s3 = new Socket(address3, 9090);

                        BufferedReader input =
                            new BufferedReader(new InputStreamReader(s3.getInputStream()));
                        String answer = input.readLine();
                        sum3 = Long.parseLong(answer, 10);
                        System.out.println(sum3);
                        bully.set_address_best_sum(address3, sum3);
                    } catch (IOException ex) {
                        Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }                 
                
                
                if(best_sum >= sum1 && best_sum >= sum2 && best_sum >= sum3){
                    bully.set_leader(myAddress, best_sum);
                }else if(sum1 >= best_sum && sum1 >= sum2 && sum1 >= sum3){
                    bully.set_leader(address1, sum1);
                }else if(sum2 >= best_sum && sum2 > sum1 && sum2 >= sum3){
                    bully.set_leader(address2, sum2);
                }else{
                    bully.set_leader(address3, sum3);                    
                }
                
                
                
                obj = parser.parse(new FileReader(
                        "requerimientos.json"));
                JSONObject req = (JSONObject) obj;
                
                JSONArray requerimientos;
                requerimientos = (JSONArray) personal.get("requerimientos");
                String charge;
                Long charge_id;

                
                if(bully.get_leader_address() == null ? myAddress == null : bully.get_leader_address().equals(myAddress)){
                    ServerSocket coordinador = new ServerSocket(9091);
                    Socket socketCoordinador = coordinador.accept();

                    Thread coordinadorThread = new CoordinadorHandler(socketCoordinador, address1, address2, address3); 
                    coordinadorThread.start();
                }else{
                    ServerSocket maquina = new ServerSocket(9092);
                    Socket socketMaquina = maquina.accept();                    
                    Thread maquinaThread = new MaquinaHandler(socketMaquina); 
                }
                
                for (int i = 0; i < requerimientos.size(); i++){
                    single_json = (JSONObject) requerimientos.get(i);
                    charge = (String)single_json.get("cargo");
                    charge_id = (Long)single_json.get("id");
                    if(null == charge){
                        System.out.println("Existio un error al leer el cargo.");
                    }else switch (charge) {
                        case "doctor":
                            Doctor current_doc;
                            current_doc = docMap.get(charge_id);
                        case "enfermero":
                            Enfermero current_enf;
                            current_enf = enfMap.get(charge_id);
                        case "paramedico":
                            Paramedico current_par;
                            current_par = parMap.get(charge_id);
                        default:
                            System.out.println("Existio un error al leer el cargo.");
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
