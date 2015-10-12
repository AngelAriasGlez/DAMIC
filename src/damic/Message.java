/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package damic;



import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Angel
 */
public class Message {
    String mData = null;
    String mTime = "";

    
    public Message(String data){
        mData = data;
        Date date = new Date();
        mTime = new SimpleDateFormat("HH:mm").format(date); // 9:00

    }
    
    public String toString(){
        return mData;
    }
    
    public String getTime(){
        return mTime;
    }
    
}
