/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package damic.ui;

import damic.DAMIC;
import damic.Message;
import damic.User;
import emoti.Emoti;
import emoti.EmotiPanel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JLabel;
import javax.swing.JWindow;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

/**
 *
 * @author Angel
 */
public class MainWindow extends javax.swing.JFrame {
    JWindow emotiWindow;
    private static MainWindow mInstance = null;
    public static MainWindow getInstance(){
        if(mInstance == null){
            mInstance = new MainWindow();
        }
        return mInstance;
    }
    
    
    
    public MainWindow() {
        initComponents();
        setVisible(true);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png")));
        
        
        /*Action action = new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e){
               send();
            }
        };*/
        
        jTextPane1.setContentType( "text/html" );
        jTextPane1.setText("<html style=\"font:Serif;\">");
        jTextPane1.setEditable(false);
        jTextPane1.setCaretColor(jTextPane1.getBackground());
        
        jTextPane2.setContentType( "text/html" );
        jTextPane2.setCaretPosition(0);
        jTextPane2.grabFocus();
        
        emotiWindow = new JWindow();
        emotiWindow.add(new EmotiPanel());
        emotiWindow.setLocation(50, 300);
        emotiWindow.pack();
        

        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane2 = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("DAMIC");
        setResizable(false);

        jScrollPane1.setViewportView(jTextPane1);

        jButton1.setText("Send");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(jTextPane2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 721, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        send();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(emotiWindow.isVisible()){
            emotiWindow.setVisible(false);
        }else{
            emotiWindow.setVisible(true);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    public void appendLocalMessage(String msg){
        HTMLDocument doc = (HTMLDocument)jTextPane2.getDocument();
        try {
            doc.insertAfterEnd(doc.getCharacterElement(jTextPane2.getCaretPosition()),msg);
            jTextPane2.setCaretPosition(jTextPane2.getCaretPosition());
        } catch (BadLocationException | IOException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void send(){
        DAMIC.getInstance().send(new Message(jTextPane2.getText().replaceAll("\\<[^>]*>","")));
        
        jTextPane2.setText("");   
    }
    
    public void appendMessage(User usr, Message msg){
        
        String html = Emoti.replace(msg.toString());

        HTMLDocument doc = (HTMLDocument)jTextPane1.getDocument();
        HTMLEditorKit editorKit = (HTMLEditorKit)jTextPane1.getEditorKit();
        String text = "";
        User self = DAMIC.getInstance().getSelf();
        if(usr.getAddress().equals(self.getAddress())){
            text = "<div  style=\"text-align:right;background:#e0e0ee;padding:5px; margin-bottom:3px\">" + html + "</div>";
        }else{
            text = "<div  style=\"background:#e0eee0#e0e0ee;padding:5px; margin-bottom:3px\"><span style=\"color:#aaaaaa;\">" + usr.getName() + " </span> " + html + "</div>";
        }
        try {
            editorKit.insertHTML(doc, doc.getLength(), text, 0, 0, null);
        } catch (BadLocationException | IOException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        jTextPane1.setCaretPosition(doc.getLength());
        
        
        


        


    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JTextPane jTextPane2;
    // End of variables declaration//GEN-END:variables
}
