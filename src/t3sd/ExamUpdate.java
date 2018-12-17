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
public class ExamUpdate extends Thread {
    Socket socket;
    public ExamUpdate(Socket s)  
    { 
        this.socket = s; 
    } 

  
    @Override
    public void run() { 
        
        
    }    
    
    
}
