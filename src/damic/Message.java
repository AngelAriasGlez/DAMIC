/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package damic;

/**
 *
 * @author Angel
 */
public class Message {
    String mData = null;
    
    public Message(String data){
        mData = data;
    }
    
    public String toString(){
        return mData;
    }
    
}
