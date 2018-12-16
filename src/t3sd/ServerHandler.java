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
  
    // Constructor 
    public ServerHandler(Socket s, String myAddress)  
    { 
        this.socket = s; 
        this.myAddress = myAddress; 
    } 


  
    @Override
    public void run() { 
        
        System.out.println("estamos dentro");
        System.out.println(myAddress);
        while(true){
            PrintWriter out;
            try {
                out = new PrintWriter(socket.getOutputStream(), true);

                out.println(1L);
                BufferedReader input =
                    new BufferedReader(new InputStreamReader(socket.getInputStream()));
                System.out.println(input.readLine());
                
            } catch (IOException ex) {
                Logger.getLogger(ServerHandler.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }   
    
    
}
