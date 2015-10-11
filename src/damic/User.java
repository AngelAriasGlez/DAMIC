/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package damic;

import java.net.InetAddress;

/**
 *
 * @author Angel
 */
public class User {
    private static final int ONLINE_TIMEOT = 3000;
    public static User SELF = new User(); 
    
    String mName = ""; 
    String mAddress = "";
    long mPrevOnline = 0;
    public User(){

    }
    public User(String name){
        mName = name;
    }
    public String getName(){
        return mName;
    }
    public void setName(String name){
        mName = name;
    }
    public void setAddress(String addr){
        mAddress = addr;
    }
    public String getAddress(){
        return mAddress;
    }  
    
    public void online(){
        mPrevOnline = System.currentTimeMillis();
    }
    public boolean isOnline(){
        return (System.currentTimeMillis()-mPrevOnline < ONLINE_TIMEOT);
    }
    
    @Override
    public String toString(){
        if(mName != null && mName.length() > 0){
            return mName;
        }
        return mAddress;
    }
}
