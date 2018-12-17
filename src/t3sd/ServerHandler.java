/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package t3sd;

import java.io.*; 
import java.text.*; 
import java.util.*; 
import java.net.*; 
import java.util.logging.Level;
import java.util.logging.Logger;
  

/**
 *
 * @author felipefvaldivia
 */
public class ServerHandler extends Thread  {
    Socket socket;
    String myAddress;
    Long best_sum;
    Bully bully;
   
    // Constructor 
    public ServerHandler(Socket s, String myAddress, Long best_sum, Bully bully)  
    { 
        
        this.socket = s; 
        this.myAddress = myAddress;
        this.best_sum = best_sum;
        this.bully = bully;
    } 


  
    @Override
    public void run() { 
        Map <String, Long> my_best = null;
        Long put = my_best.put(myAddress, best_sum);
        System.out.println("estamos dentro");
        System.out.println(myAddress);
        String sumString = best_sum.toString();
        while(true){
            PrintWriter out;
            try {
                out = new PrintWriter(socket.getOutputStream(), true);

                out.println(sumString);

                
            } catch (IOException ex) {
                Logger.getLogger(ServerHandler.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }   
    
    
}
