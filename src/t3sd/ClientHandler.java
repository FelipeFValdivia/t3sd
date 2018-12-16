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


public class ClientHandler extends Thread  
{ 
    Socket s; 
    String myAddress;
    String address;
      
  
    // Constructor 

    ClientHandler(Socket s1, String myAddress, String address1) {
        this.s = s1;
        this.myAddress = myAddress;
        //this.address = address1;
        this.address = "localhost";
        
    }


  
    public void run()   { 
        
        System.out.println("estamos dentro");
        while(true){
            try {
                s = new Socket(address, 9090);
                PrintWriter out =
                    new PrintWriter(s.getOutputStream(), true);
                out.println(myAddress);
                BufferedReader input =
                    new BufferedReader(new InputStreamReader(s.getInputStream()));
                String answer = input.readLine();

            } catch (IOException ex) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
      

    } 
} 