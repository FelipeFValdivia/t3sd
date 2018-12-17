/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package t3sd;

import java.io.*; 
import java.net.*; 
import java.util.logging.Level;
import java.util.logging.Logger;
  

/**
 *
 * @author felipefvaldivia
 */
public class ServerHandler extends Thread  {
    ServerSocket listener;
    String myAddress;
    Long best_sum;
    Bully bully;
   
    // Constructor 
    public ServerHandler(ServerSocket s, String myAddress, Long best_sum, Bully bully)  
    { 
        
        this.listener = s; 
        this.myAddress = myAddress;
        this.best_sum = best_sum;
        this.bully = bully;
    } 


  
    @Override
    public void run() {
        Socket socket;        
        try {
            socket = listener.accept();
            System.out.println(myAddress);
            String sumString; 
            sumString = best_sum.toString();
            while(true){
                PrintWriter out;
                try {
                    out = new PrintWriter(socket.getOutputStream(), true);

                    out.println(sumString);


                } catch (IOException ex) {
                    Logger.getLogger(ServerHandler.class.getName()).log(Level.SEVERE, null, ex);
                }

            }            
        } catch (IOException ex) {
            Logger.getLogger(ServerHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }   
    
    
}
