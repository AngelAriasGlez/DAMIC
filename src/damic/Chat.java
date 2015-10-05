/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package damic;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

/**
 *
 * @author Angel
 */
public class Chat {
    ArrayList<User> mUsers = new ArrayList();
    public Chat(User user){
        mUsers.add(user);
    }
    
    public String getName(){
        return mUsers.get(0).getName();
    }
    public Image getAvatar(){
        return Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png"));
    }
    
}
