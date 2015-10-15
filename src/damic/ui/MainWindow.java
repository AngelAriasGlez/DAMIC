/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package damic.ui;


import damic.DAMIC;
import damic.Message;
import damic.User;
import damic.Utils;
import emoti.Emoti;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.io.IOException;
import java.net.SocketException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListCellRenderer;
import javax.swing.SwingUtilities;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.BadLocationException;

import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;


/**
 *
 * @author Angel
 */
public class MainWindow extends javax.swing.JFrame implements WindowFocusListener {

    int mUnreadMsg = 0;
    
    boolean mNeedScroll = false;


    public MainWindow() {
        initComponents();
        
        System.setProperty("http.agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
        
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png")));

        /*Action action = new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e){
               send();
            }
        };*/
        jTextPane1.setContentType("text/html");

        

        StyleSheet styleSheet = ((HTMLEditorKit) jTextPane1.getEditorKit()).getStyleSheet();
        styleSheet.addRule("body {color:#222222;font-family:Helvetica; margin: 4px;background-image: '"+getClass().getResource("bg.png")+"'}");
        styleSheet.addRule("p {margin:0;padding:0;}");


        

        //jTextPane1.setText("<html style=\"font:'"+getClass().getResource("hn.otf")+"';\">");
        jTextPane1.setEditable(false);
        jTextPane1.setCaretColor(jTextPane1.getBackground());
        jTextPane1.addHyperlinkListener(new HyperlinkListener() {
            public void hyperlinkUpdate(HyperlinkEvent e) {
                if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                    if (Desktop.isDesktopSupported()) {
                        try {
                            Desktop.getDesktop().browse(e.getURL().toURI());
                        } catch (IOException | URISyntaxException ex) {
                            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        });



        jTextPane2.setContentType("text/html");
        StyleSheet styleSheet2 = ((HTMLEditorKit) jTextPane2.getEditorKit()).getStyleSheet();
        styleSheet2.addRule("p {margin:0;padding:0;}");
        
        jTextPane2.setText("<html><body><p id=\"mn\"></p></body></html>");
        jTextPane2.setCaretPosition(0);
        jTextPane2.grabFocus();

        for (int i = 0; i < Emoti.LIST.length; i++) {
            String name = Emoti.LIST[i].toLowerCase();
            final JLabel lab = new JLabel();

            lab.setToolTipText(name);
            Image img = Emoti.get(name);
            if (name.startsWith("0m")) {
                img = img.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
                lab.setPreferredSize(new java.awt.Dimension(75, 75));
            }

            lab.setCursor(new Cursor(Cursor.HAND_CURSOR));

            lab.setIcon(new ImageIcon(img));
            lab.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    String tl = lab.getToolTipText();
                    appendLocalMessage(Emoti.replace(tl));
                }
            });
            //lab.setText(Emoti.LIST[i].toLowerCase());
            jPanel3.add(lab);

        }

        addWindowFocusListener(this);
        
        
        jList1.setCellRenderer(new UserCellRenderer());

        
        jScrollPane1.setVisible(false);

        pack();
        setVisible(true);
        
        jScrollPane4.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
    @Override
    public void adjustmentValueChanged(AdjustmentEvent ae) {
        if(mNeedScroll)
        jScrollPane4.getVerticalScrollBar().setValue(jScrollPane4.getVerticalScrollBar().getMaximum());
        mNeedScroll = false;
    }
});
        

    }
    
    
    public void updateUsername(){
        jLabel1.setText(User.SELF.toString());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextPane2 = new javax.swing.JTextPane();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("DAMIC");
        setPreferredSize(new java.awt.Dimension(1150, 800));

        jScrollPane4.setViewportView(jTextPane1);

        jScrollPane5.setViewportView(jTextPane2);

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton2.setText("Send");
        jButton2.setToolTipText("");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jScrollPane1.setRequestFocusEnabled(false);

        jPanel3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel3.setPreferredSize(new java.awt.Dimension(170, 1000));
        jScrollPane1.setViewportView(jPanel3);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/damic/ui/happy.png"))); // NOI18N
        jButton1.setToolTipText("");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4)
            .addComponent(jScrollPane1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jList1.setBackground(new java.awt.Color(250, 250, 250));
        jList1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jScrollPane2.setViewportView(jList1);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(34, 34, 34));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 572, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 32, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
        );

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/damic/ui/icon_big.png"))); // NOI18N
        jLabel2.setToolTipText("");

        jMenuBar1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jMenu2.setText("File");

        jMenuItem1.setText("Exit");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Edit");

        jMenuItem2.setText("Username");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem2);

        jMenuBar1.add(jMenu3);

        jMenu1.setText("About");
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        send();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        showUsernameInputDialog();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    void showUsernameInputDialog(){
        String username = JOptionPane.showInputDialog(
                this,
                "Username :"
        );  // el icono sera un iterrogante
        User u = User.SELF;
        u.setName(username);

        jLabel1.setText(u.toString());
    
    }
    
    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked

        try {
            JOptionPane.showMessageDialog(this, "Your IP is " + Utils.getEthInterfaceInformation().getHostAddress() + "\n" + Utils.getEthInterfaceInformation().getCanonicalHostName() + "\n\n Angel Arias Gonzalez - 2015");
        } catch (SocketException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jMenu1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jScrollPane1.setVisible(!jScrollPane1.isVisible());                // TODO add your handling code here:
        this.validate();
    }//GEN-LAST:event_jButton1ActionPerformed

    public void setOnlineUsers(ArrayList<User> users){
        DefaultListModel listModel = new DefaultListModel();
        listModel.clear();
        int i =0 ;
        for(User u : users){
            listModel.add(i++, u);  
        }
        jList1.setModel(listModel);
    }
    
    
    public void appendLocalMessage(String msg) {
        HTMLDocument doc = (HTMLDocument) jTextPane2.getDocument();
        try {
            doc.insertBeforeEnd(doc.getElement("mn"), msg);
            jTextPane2.grabFocus();
            //jTextPane2.setCaretPosition(doc.getLength());
        } catch (BadLocationException | IOException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void send() {
        String text = jTextPane2.getText();
        text = text.replaceAll("<img[^>]*src=[\\\"']([^\"]*)\\/([^\\.\\\"]*)\\.?[^\"]*[\\\"'][^>]*>", "$2");
       
        text = text.replaceAll("\n", "");
        text = text.replaceAll("</p>", "\n");
        int index = text.lastIndexOf("\n");
        if (index > -1)
        text =  text.substring(0, index) + "" + text.substring(index+1, text.length());


        text = text.replaceAll("\\<[^>]*>", "");
        if (text.replaceAll(" ", "").isEmpty()) {
            return;
        }
        DAMIC.broadcastMessage(new Message(text));

        jTextPane2.setText("<html><body><p id=\"mn\"></p></body></html>");
    }

    public void appendMessage(User usr, Message msg) {
        if (!this.isFocused()) {
            mUnreadMsg++;
        }
        if (mUnreadMsg > 0) {
            this.setTitle("DAMIC (" + mUnreadMsg + ")");
        } else {
            this.setTitle("DAMIC");
        }

        //Toolkit.getDefaultToolkit().beep();
        if (msg.toString().contains("1f50a")) {
            Toolkit.getDefaultToolkit().beep();
            System.out.print("\007");
            System.out.flush();
        }
        String html = msg.toString();
        html = html.replaceAll("\n", "<br/>");
        html = Emoti.replace(html);
        
        
        
        Pattern p = Pattern.compile("(\\A|\\s)((http|https|ftp|mailto):\\S+)(\\s|\\z)");
        Matcher m = p.matcher(html);
        while (m.find()) {
            String url = m.group(0);
            try {

                String contentType = Utils.getContentType(url);
                if (contentType.contains("image")) {
                    Dimension imgsize = Utils.getImageSize(url);
                    String hsize = "";
                    if(imgsize != null && imgsize.width > 600){
                            imgsize.height = (int)((float)imgsize.height / (float)imgsize.width * 600.0f);
                            imgsize.width = 600;
                            hsize = hsize.concat("width=\""+imgsize.width+"\" height=\""+imgsize.height+"\"");
                    } 
                    
                    html = html.substring(0, m.start()) + "<a href=\"" + url + "\">" + url.substring(0, (url.length()>80?80:url.length())) + (url.length()>80?"...":"") + "</a> <br/><br/> <img "+hsize+" src=\"" + url + "\">" + html.substring(m.end(), html.length());
                } else {
                    html = html.substring(0, m.start()) + "<a href=\"" + url + "\">" + url + "</a>" + html.substring(m.end(), html.length());
                }
            } catch (IOException ex) {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        HTMLDocument doc = (HTMLDocument) jTextPane1.getDocument();

        String text = "";
        if (usr == User.SELF) {
            text = "<table style=\"background:#e7e7ee;margin:0 15 5 15;padding:5px;\" width=\"100%\"><tr><td  style=\"text-align:right;color:#aaaaaa;\" width=\"50\" valign=\"top\">"+msg.getTime()+"</td><td style=\"text-align:right;\">" + html + "</td></tr></table>";
            //text = "<p  style=\"text-align:right;background:#e0e0ee;padding:5px; margin:0px 10px 5px 10px\">" + html + " <span style=\"color:#aaaaaa;\"><i>"+msg.getTime()+"</i></span> </p>";
        } else {
            text = "<table style=\"background:#e5eeee;margin:0 15 5 15;padding:5px;\" width=\"100%\"><tr><td style=\"\">" + html + "</td><td  valign=\"top\" style=\"text-align:right;color:#aaaaaa;\" width=\"150\"><b>"+usr.toString(15)+"</b><br/>"+msg.getTime()+"</td></tr></table>";
            //text = "<p  style=\"background:#e0eee0#e0e0ee;padding:5px; margin:0px 10px 5px 10px\"><span style=\"color:#aaaaaa;\"><i>" + usr.toString() + "</i></span> " + html + "</p>";
        }
        try {
            doc.insertBeforeEnd(doc.getDefaultRootElement().getElement(0), text);
        } catch (BadLocationException | IOException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        //jTextPane1.setCaretPosition(doc.getLength());
        mNeedScroll = true;



    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JList jList1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JTextPane jTextPane2;
    // End of variables declaration//GEN-END:variables

    @Override
    public void windowGainedFocus(WindowEvent e) {
        mUnreadMsg = 0;
        setTitle("DAMIC");
    }

    @Override
    public void windowLostFocus(WindowEvent e) {

    }
    
    
    
    
    class UserCellRenderer extends JLabel implements ListCellRenderer {


    public UserCellRenderer() {


    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value,
      int index, boolean isSelected, boolean cellHasFocus) {
        if(value instanceof String){
        
        }
        User entry = (User) value;
        

        
        setText("<html><body><div style=\"padding:5 5 0 5;\"><p width=\"136px\" style=\"background:#e5eeee;padding:13 15 5 15; color:#222222;font-size:14;\"><img align=\"middle\" src=\""+getClass().getResource("online.png")+"\"/>"+entry.toString(15)+"</p></div></body></html>");
    return this;
  }
}

    
}
