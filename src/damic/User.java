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
    InetAddress mRemoteAddress;
    public User(){

    }
    public User(String name){
        mName = name;
    }
    public String getName(){
        return mName;
    }
    public void setRemoteAddress(InetAddress addr){
        mName = addr.getHostAddress();
        mRemoteAddress = addr;
    }
}
