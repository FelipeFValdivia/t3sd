/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package t3sd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author felipefvaldivia
 */
public class MaquinaHandler extends Thread {
    Socket socket;
    public MaquinaHandler(Socket socket) {
        this.socket = socket;
    }
    @Override
    public void run() { 
        System.out.println("estamos dentro");
    
        while(true){
            PrintWriter out;
            try {
                BufferedReader input =
                    new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String answer = input.readLine();

                
            } catch (IOException ex) {
                Logger.getLogger(ServerHandler.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    } 
}
