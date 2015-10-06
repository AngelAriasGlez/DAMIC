/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package damic;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


/**
 *
 * @author Angel
 */
public class Broadcast {
    public static int PORT = 4445;
    
    public static String CMD_ANNOUNCE = "SACCEPT";

    
    ListenThread mLt = new ListenThread();
    AnnounceThread mAt = new AnnounceThread();
    public void start(){
        mLt.start();
        mAt.start();
    }

    class ListenThread extends Thread{
        DatagramSocket mSocket;
        BlockingQueue<User> mQueue = new LinkedBlockingQueue<>();
        public void run(){
            try {
                mSocket = new DatagramSocket(PORT);

                byte[] buff = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buff, buff.length);
                while(true){
                        mSocket.receive(packet);
                        String cmd = new String(packet.getData(), 0, packet.getLength());
                        if(cmd.equals(CMD_ANNOUNCE)){
                            User u = new User();
                            u.setRemoteAddress(packet.getAddress());
                            mQueue.add(u);
                    }
                }
            
            }catch (IOException e){
                    System.out.println(e.getMessage());
            }
        }
        
        
        public User pollUser(){
            return mQueue.poll();
        }
    }
    
    
    public User pollUser(){
        return mLt.pollUser();
    }
    
    class AnnounceThread extends Thread{
        DatagramSocket mSocket;
        public void run(){
            try {
                mSocket = new DatagramSocket();


                DatagramPacket packet = new DatagramPacket(CMD_ANNOUNCE.getBytes(), CMD_ANNOUNCE.length(), InetAddress.getByName("192.168.0.255"), PORT);
                while(true){
                    mSocket.send(packet);
                    try{
                        Thread.sleep(1000);
                    }catch(InterruptedException e){
                        System.out.println(e.getMessage());
                    }
                }
            
            }catch (IOException e){
                    System.out.println(e.getMessage());
            }            
        }
    
    }
}
