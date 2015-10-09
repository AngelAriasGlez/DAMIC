/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package damic;


import damic.ui.MainWindow;
import java.io.IOException;
import static java.lang.System.out;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

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
    
    User mUser;
    
    public DAMIC(){
        mUser = new User();
        try {
            InetAddress ia = getEthInterfaceInformation();
            if(ia != null)
                mUser.setAddress(ia.getHostAddress());
        } catch (SocketException ex) {
            Logger.getLogger(DAMIC.class.getName()).log(Level.SEVERE, null, ex);
        }

        new ListenThread().start();
        
        String username = JOptionPane.showInputDialog(
            MainWindow.getInstance(),
            "Username :" 
            );  // el icono sera un iterrogante
        mUser.setName(username);
    }
    
    public User getUser(){
        return mUser;
    }
    public void setUser(User usr){
        mUser = usr;
    }
    
    public static InetAddress getEthInterfaceInformation() throws SocketException {
        Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
        for (NetworkInterface netint : Collections.list(nets)){
            //out.printf("Display name: %s\n", netint.getDisplayName());
            //out.printf("Name: %s\n", netint.getName());
            if(!netint.isLoopback() && !netint.getName().contains("virbr")){
                Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
                for (InetAddress inetAddress : Collections.list(inetAddresses)) {
                    if(inetAddress instanceof Inet4Address){
                        out.printf("InetAddress: %s\n", inetAddress);
                        return inetAddress;
                    }
                }
            }
 
        }
        return null;
     }

    class ListenThread extends Thread{
        DatagramSocket mSocket;
        BlockingQueue<User> mQueue = new LinkedBlockingQueue<>();
        public void run(){
            try {
                mSocket = new DatagramSocket(PORT);

                
                while(true){
                        byte[] buff = new byte[1024*10];
                        DatagramPacket packet = new DatagramPacket(buff, buff.length);
                        mSocket.receive(packet);
                        String cmd = new String(packet.getData(), 0, 3);
                        String data = new String(packet.getData(), 4, packet.getLength());
                        if(cmd.equals(CMD_MESSAGE)){
                            String usrname = data.substring(data.indexOf("'")+1, data.lastIndexOf("'"));
                            String msg = data.substring(data.lastIndexOf("'")+2, data.length());        
                            
                            User u = new User();
                            u.setName(usrname);
                            u.setAddress(packet.getAddress().getCanonicalHostName());
                            MainWindow.getInstance().appendMessage(u, new Message(msg));
                        }
                }

            
            }catch (IOException e){
                    System.out.println(e.getMessage());
            }
            //mSocket.close();
        }

    }
    public void send(Message msg){
        try{
            DatagramSocket socket = new DatagramSocket();
            String msgstr = CMD_MESSAGE + " '" + mUser.getName() +"' " + msg.toString();
            DatagramPacket packet = new DatagramPacket(msgstr.getBytes(), msgstr.length(), InetAddress.getByName("255.255.255.255"), PORT);
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
        
        DAMIC.getInstance();
        MainWindow.getInstance();
    }

}
