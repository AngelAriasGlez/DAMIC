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
    String mName = ""; 
    String mAddress = "";
    public User(){

    }
    public User(String name){
        mName = name;
    }
    public String getName(){
        if(mName.length() > 0){
            return mName;
        }
        return mAddress;
    }
    public void setName(String name){
        mName = name;
    }
    public void setAddress(String addr){
        mName = addr;
        mAddress = addr;
    }
     public String getAddress(){
        return mAddress;
    }  
}
