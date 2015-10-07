/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package damic;


import damic.ui.MainWindow;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import javax.swing.SwingUtilities;

/**
 *
 * @author Angel
 */
public class DAMIC{
    private static DAMIC mInstance = null;
    public static DAMIC getInstance(){
        if(mInstance == null){
            mInstance = new DAMIC();
        }
        return mInstance;
    }
    
    
    public static int PORT = 4445;
    
    public static String CMD_MESSAGE = "MSG";
    
    public DAMIC(){
        new ListenThread().start();
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
                        String cmd = new String(packet.getData(), 0, 3);
                        String data = new String(packet.getData(), 4, packet.getLength());
                        if(cmd.equals(CMD_MESSAGE)){
                            MainWindow.getInstance().appendMessage(new User(packet.getAddress().getHostAddress()), new Message(data));
                        }
                }
            
            }catch (IOException e){
                    System.out.println(e.getMessage());
            }
            mSocket.close();
        }

    }
    public void send(Message msg){
        try{
            DatagramSocket socket = new DatagramSocket();
            String msgstr = CMD_MESSAGE + "  " + msg.toString();
            DatagramPacket packet = new DatagramPacket(msgstr.getBytes(), msgstr.length(), InetAddress.getByName("192.168.0.255"), PORT);
            socket.send(packet);
            socket.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public static void main(String args[]) {
       
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        MainWindow.getInstance();
        DAMIC.getInstance();
    }

}
