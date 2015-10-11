/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package damic;


import damic.ui.MainWindow;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

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
    
    ListenThread mListener;
    
    public static int PORT = 4445;
    
    public static String CMD_MESSAGE = "MSG";
    public static String CMD_USERNAME = "NME";
    
    User mUser;
    
    ArrayList<User>  mOnlineUsers = new ArrayList();
    
    public DAMIC(){
        mUser = new User();
        try {
            InetAddress ia = Utils.getEthInterfaceInformation();
            if(ia != null)
                mUser.setAddress(ia.getHostAddress());
        } catch (SocketException ex) {
            Logger.getLogger(DAMIC.class.getName()).log(Level.SEVERE, null, ex);
        }

        mListener = new ListenThread();
        mListener.start();
        
        Timer timer; 
        timer = new Timer (100, new ActionListener () { 
            @Override
            public void actionPerformed(ActionEvent e){ 
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        process();
                    }
                });
            }
        }); 
        timer.start();
        
        Timer timer2; 
        timer2 = new Timer (2000, new ActionListener () { 
            @Override
            public void actionPerformed(ActionEvent e){ 
                broadcastUsername();
            }
        }); 
        timer2.start();
        
        

    }
    
    public User getUser(){
        return mUser;
    }
    public void setUser(User usr){
        mUser = usr;
    }
    
    public User getUserByIp(String ip){
        for(User u : mOnlineUsers){
            if(u.getAddress().equals(ip)){
                return u;
            }
        }
        User u = new User();
        u.setAddress(ip);
        mOnlineUsers.add(u);
        return u;
    }
    public void removeOfflineUsers(){
        for(User u : mOnlineUsers){
            if(!u.isOnline()){
                mOnlineUsers.remove(u);
            }
        }
    }
    
    
    void process(){
        //removeOfflineUsers();
        
        InputData id = mListener.pollData();
        if(id == null){
            return;
        }
            User remoteuser = getUserByIp(id.ip);
            remoteuser.online();
            if(id.cmd.equals(CMD_MESSAGE)){
                MainWindow.getInstance().appendMessage(remoteuser, new Message(id.data));
            }else if(id.cmd.equals(CMD_USERNAME)){
                int fo = id.data.indexOf("'")+1;
                String usrname = id.data.substring(fo, id.data.indexOf("'", fo));
                remoteuser.setName(usrname);
            }
            MainWindow.getInstance().setOnlineUsers(mOnlineUsers);
    }
    
    
    class InputData{
        String cmd;
        String data;
        String ip;
        InputData(String cmd, String data, String ip){
            this.cmd = cmd;
            this.data = data;
            this.ip = ip;
        }
    }
    class ListenThread extends Thread{
        DatagramSocket mSocket;
        BlockingQueue<InputData> mQueue = new LinkedBlockingQueue<>();
        public void run(){
            try {
                mSocket = new DatagramSocket(PORT);
                
                while(true){
                        byte[] buff = new byte[1024*10];
                        DatagramPacket packet = new DatagramPacket(buff, buff.length);
                        mSocket.receive(packet);
                        String cmd = new String(packet.getData(), 0, 3);
                        String data = new String(packet.getData(), 4, packet.getLength());
                        String ip = packet.getAddress().getHostAddress();
                        mQueue.add(new InputData(cmd, data, ip));
                }

            
            }catch (IOException e){
                    System.out.println(e.getMessage());
            }
            //mSocket.close();
        }
        public InputData pollData(){
            return mQueue.poll();
        }

    }
    public void broadcastMessage(Message msg){
            broadcast(CMD_MESSAGE + " " + msg.toString());
    }
    public void broadcastUsername(){
        if(!mUser.getName().isEmpty())
            broadcast(CMD_USERNAME + " '" + mUser.getName() + "'");
    }
    
     public void broadcast(String str){
        try(DatagramSocket socket = new DatagramSocket()) {
            DatagramPacket packet = new DatagramPacket(str.getBytes(), str.length(), InetAddress.getByName("255.255.255.255"), PORT);
            socket.send(packet);
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
