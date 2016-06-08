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
    private String mData = null;
    private String mTime = "";

    public Message(){
    
    }

    public Message(String data){
        mData = data;
        Date date = new Date();
        mTime = new SimpleDateFormat("HH:mm").format(date); // 9:00

    }

    public void setData(String data) {
        this.mData = data;
    }

    public void setTime(String time) {
        this.mTime = time;
    }
    public String getData() {
        return mData;
    }
    
    public String toString(){
        return mData;
    }
    
    public String getTime(){
        return mTime;
    }
    
}
