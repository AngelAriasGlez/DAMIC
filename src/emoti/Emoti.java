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
        final static String[] LIST = new String[]{
        "1F601","1F602","1F603","1F604","1F605","1F606","1F609","1F60A","1F60B",
        "1F60C","1F60D","1F60F","1F612","1F613","1F614","1F616","1F618","1F61A",
        "1F61C","1F61D","1F61E","1F620","1F621","1F622","1F623","1F624","1F625",
        "1F628","1F629","1F62A","1F62B","1F62D","1F630","1F631","1F632","1F633",
        "1F635","1F637","1F648","1F649","1F64A","1F64C","1F64F","1F4A3","1F4A4",
        "1F4A9","1F4AA","1F50A","1F600","1F607","1F608","1F60E","1F610","1F611",
        "1F615","1F617","1F619","1F61B","1F61F","1F626","1F627","1F62C","1F62E",
        "1F62F","1F634","1F636"};
    
    public static ImageIcon get(String name){

        return new ImageIcon(Toolkit.getDefaultToolkit().getImage(Emoti.class.getResource(name+".png")));
    }
    public static String replace(String str){
        for(int i=0;i<Emoti.LIST.length;i++){
            String s = Emoti.LIST[i].toLowerCase();
            str = str.replace(s, "<img src=\""+ Emoti.class.getResource(s+".png").toString()+"\"/>");
        }
        return str;
    }
    
}
