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
    Bully bully;
    String address4 = "dist73.inf.santiago.usm.cl";
    String address1 = "dist74.inf.santiago.usm.cl";
    String address2 = "dist75.inf.santiago.usm.cl";
    String address3 = "dist76.inf.santiago.usm.cl";       
  

    ClientHandler(Socket s1, String myAddress, String address, Bully bully) {
        this.s = s1;
        this.myAddress = myAddress;
        this.address = address;
        this.bully = bully;
    }


  
    public void run()   { 
        

      

    } 
} 