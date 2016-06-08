/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package damic;

import com.angel.db.Field;
import com.angel.db.Record;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author aariasgonzalez
 */
public class DatabaseModel{
    private String mName = "messages";
    
    public String getName(){
        return mName;
    }
    public void setName(String name){
        mName = name;
    }
    
    public ArrayList<Field> getStructure(){
        ArrayList<Field> fields = new ArrayList();
        fields.add(new Field("id", 1, true, true, true));
        fields.add(new Field("user", 0, false, false, false));
        fields.add(new Field("addr", 0, false, false, false));
        fields.add(new Field("time", 0, false, false, false));
        fields.add(new Field("msg", 0, false, false, false));
        return fields;
    }
    
    public ArrayList<Record> getDefaults() throws SQLException, FileNotFoundException, IOException{

        return new ArrayList();
        
    
    
    }
    
}
