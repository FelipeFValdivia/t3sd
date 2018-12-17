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
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.NoRouteToHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
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

    @SuppressWarnings("empty-statement")
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        
        String address1, address2, address3, myAddress, miMaquina;
//        myAddress = "dist73.inf.santiago.usm.cl";
//        address1 = "dist74.inf.santiago.usm.cl";
//        address2 = "dist75.inf.santiago.usm.cl";
//        address3 = "dist76.inf.santiago.usm.cl";

        System.out.println("Ingresa el numero de tu maquina (73,74,75,76)");        
        Scanner sc = new Scanner(System.in);
        miMaquina = sc.nextLine(); 


        JSONParser parser = new JSONParser();
        Object obj;
        JSONObject single_json;
        String apellido, nombre;
        Long id, estudios, experiencia;
        String nombreArchivoPersonal;
        String ip1 = "10.6.40.213";
        String ip2 = "10.6.40.214";
        String ip3 = "10.6.40.215";
        String ip4 = "10.6.40.216";
        //Se determina en que maquina estoy para leer mi archivo correspondiente de trabajadores
        switch (miMaquina) {
            case "73":
                myAddress = ip1;
                address1 = ip2;
                address2 = ip3;
                address3 = ip4;
                nombreArchivoPersonal = "personal.json";
                break;
            case "74":
                myAddress = ip2;
                address1 = ip1;
                address2 = ip3;
                address3 = ip4;                
                nombreArchivoPersonal = "personal2.json";
                break;
            case "75":
                myAddress = ip3;
                address1 = ip1;
                address2 = ip2;
                address3 = ip4;                
                nombreArchivoPersonal = "personal3.json";
                break;
            case "76":
                myAddress = ip4;
                address1 = ip1;
                address2 = ip2;
                address3 = ip3;                
                nombreArchivoPersonal = "personal4.json";
                break;
            default:
                myAddress = ip1;
                address1 = ip2;
                address2 = ip3;
                address3 = ip4;                
                nombreArchivoPersonal = "personal.json";
                break;
        }
        
        try { 
            //Leo mi respectivo archivo de trabajadores
            obj = parser.parse(new FileReader(
                    nombreArchivoPersonal));
            JSONObject personal = (JSONObject) obj;
            
            List myDoctorList = new ArrayList();
            List myEnfermeroList = new ArrayList();
            List myParamedicoList = new ArrayList();
            Map<Long, Doctor> docMap = new HashMap<>();
            
            Map<Long, Enfermero> enfMap = new HashMap<>();
            Map <Long, Paramedico> parMap = new HashMap<>();
            
            JSONArray doctor = (JSONArray) personal.get("Doctor");
            Doctor best_doc;
            Long best_sum = 0L;
            //Llevo a memoria mis doctores
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
            
            //Llevo a memoria a mis enfermeros
            JSONArray enfermero;
            enfermero = (JSONArray) personal.get("enfermero");
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
            
            //Llevo a memoria a mis enfermeros
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
   
            InetAddress myInetAdd = InetAddress.getByName(myAddress);           
            ServerSocket listener = new ServerSocket(9090, 10, myInetAdd);
            Bully bully = new Bully(best_sum, -1L, "", myAddress, address1, address2, address3);
            //Se busca al primer coordinador
            Long sum1 = 0L;
            Long sum2 = 0L;
            Long sum3 = 0L;
            

            //Se crea una thread para poder escuchar los mensajes de los otros servidores, y poder determinar quien es el coordinador
            Thread t = new ServerHandler(listener, myAddress, best_sum, bully); 
            t.start();
            
            //Se pregunta al servidor 1 cual es su mejor doctor
            while(bully.get_address_best_sum(address1) == null ){
                InetAddress InetAdd = InetAddress.getByName(address1);           
                try{
                    System.out.println("Extrayendo información desde " + address1);
                    Socket s1 = new Socket(InetAdd, 9090);
                    BufferedReader input =
                            new BufferedReader(new InputStreamReader(s1.getInputStream()));
                    String answer = input.readLine();
                    sum1 = Long.parseLong(answer, 10);
                    System.out.println(sum1);

                    bully.set_address_best_sum(address1, sum1);
                    System.out.println(bully.get_address_best_sum(address1));                    
                }catch (NoRouteToHostException e) 
                {
                    System.err.println("Problem at ip: " + address1);
                }catch (ConnectException e){
                    System.err.println("Problem at ip: " + e);
                }

                
            }   
            //Se pregunta al servidor 2 cual es su mejor doctor
            while(bully.get_address_best_sum(address2) == null ){
                InetAddress InetAdd = InetAddress.getByName(address2);           
                try{
                    System.out.println("Extrayendo información desde " + address2);
                    Socket s2 = new Socket(InetAdd, 9090);

                    BufferedReader input =
                        new BufferedReader(new InputStreamReader(s2.getInputStream()));
                    String answer = input.readLine();
                    sum2 = Long.parseLong(answer, 10);
                    System.out.println(sum2);
                    bully.set_address_best_sum(address2, sum2);                    
                }catch (NoRouteToHostException e) 
                {
                    System.err.println("Problem at ip: " + address1);
                }catch (ConnectException e){
                    System.err.println("Problem at ip: " + e);
                }



            } 

            //Se pregunta al servidor 3 cual es su mejor doctor
            while(bully.get_address_best_sum(address3) == null ){
                
                
                InetAddress InetAdd = InetAddress.getByName(address3);           
                try{
                    System.out.println("Extrayendo información desde " + address3);
                    Socket s3 = new Socket(InetAdd, 9090);

                    BufferedReader input =
                        new BufferedReader(new InputStreamReader(s3.getInputStream()));
                    String answer = input.readLine();
                    sum3 = Long.parseLong(answer, 10);
                    System.out.println(sum3);
                    bully.set_address_best_sum(address3, sum3);
                }
                catch (NoRouteToHostException e) 
                {
                    System.err.println("Problem at ip: " + address3);
                } catch (ConnectException e){
                    System.err.println("Problem at ip: " + e);
                }


            }                 

            //Se determina cual es el coordinador por sus doctores
            if(best_sum >= sum1 && best_sum >= sum2 && best_sum >= sum3){
                bully.set_leader(myAddress, best_sum);
            }else if(sum1 >= best_sum && sum1 >= sum2 && sum1 >= sum3){
                bully.set_leader(address1, sum1);
            }else if(sum2 >= best_sum && sum2 > sum1 && sum2 >= sum3){
                bully.set_leader(address2, sum2);
            }else{
                bully.set_leader(address3, sum3);                    
            }
            System.out.println("el coordinador es " + bully.get_leader_address());
            obj = parser.parse(new FileReader(
                    "requerimientos.json"));
            JSONObject req = (JSONObject) obj;

            JSONArray requerimientos;
            requerimientos = (JSONArray) personal.get("requerimientos");
            String charge;
            Long charge_id;

            //Si este es el coordinador, se empieza a escuchar en un thread para que los otros servidores se comuniquen con el
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



            
        

        } catch (IOException | ParseException ex) {
            Logger.getLogger(T3SD.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static void println(JSONObject jsonObject) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
