/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emoti;

import java.awt.Toolkit;
import javax.swing.ImageIcon;

/**
 *
 * @author Angel
 */
public class Emoti {
    
    public ImageIcon get(String name){
        return new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource(name+".png")));
    }
    
}
