/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package damic;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 *
 * @author Angel
 */
public class Broadcast {

    class QuoteServerThread extends Thread{
        
        public void run() {
            try {
                DatagramSocket socket = new DatagramSocket(4445);

                while(true){
                    byte[] buf = new byte[256];
                    DatagramPacket packet = new DatagramPacket(buf, buf.length);
                    socket.receive(packet);
                }
            
            }catch (IOException e){
                    
                    
            }

        }
    }
    
}
