/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication5;

import com.sun.jna.platform.win32.Advapi32Util;
import com.sun.jna.platform.win32.WinReg;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import sun.tools.jar.CommandLine;
import java.io.ByteArrayOutputStream;
import javax.swing.JTextArea;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;
/**
 *
 * @author inv.Desarrollo2
 */
public class Files extends Thread{
String cadena="",temp="",error=null;
String[] result;



public String Readxml(JFrame jframe,final String[] items,String tags) {

  
            File file=new File("host.xml");
            
            if(file.exists()){
                BufferedReader br = null;
                try {
                    br = new BufferedReader(new FileReader("./host.xml"));
                    if(br.readLine()!=null){
                        
                        DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
                        DocumentBuilder documentBuilder=dbf.newDocumentBuilder();
                        Document document=documentBuilder.parse(file);
                        document.normalize();
                        final NodeList nodeList=document.getElementsByTagName(tags);
                        
                        Thread thread=new Thread(new Runnable() {
                            
                            @Override
                            public void run() {
                                for(int i=0;i<nodeList.getLength();i++){
                                    Node node = nodeList.item(i);
                                    if(node.getNodeType()==Node.ELEMENT_NODE){
                                        NodeList nodeList1=node.getChildNodes();
                                        for(int j=1;j<nodeList1.getLength();j++){
                                            Node node1=nodeList1.item(j);
                                            items[i]=node1.getTextContent().replaceAll("\\s+","");
                                            temp=items[i].toString();
                                            cadena+=temp+";";
                                        }
                                    }
                                    else{
                                        break;
                                    }
                                }
                            }
                        });
                        thread.start();
                        thread.join();
                        
                    }   } catch (FileNotFoundException ex) {
                    Logger.getLogger(Files.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Files.class.getName()).log(Level.SEVERE, null, ex);
                } catch (        ParserConfigurationException | SAXException | InterruptedException ex) {
                    Logger.getLogger(Files.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        br.close();
                    } catch (IOException ex) {
                        Logger.getLogger(Files.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
}
            else{
            JOptionPane.showMessageDialog(jframe,"no existe archivo host.xml o esta vacio", null, 0);
            System.exit(0);
            }
   return cadena;
}
     
public String getReg(String path,String key){

 String productName = Advapi32Util.registryGetStringValue(
            WinReg.HKEY_LOCAL_MACHINE, path, key);

//        // Read an int (& 0xFFFFFFFFL for large unsigned int)
//        int timeout = Advapi32Util.registryGetIntValue(
//            WinReg.HKEY_LOCAL_MACHINE, "SOFTWARE\\Microsoft\\Windows NT\\CurrentVersion\\Windows", "ShutdownWarningDialogTimeout");
//        System.out.printf("Shutdown Warning Dialog Timeout: %d (%d as unsigned long)\n", timeout, timeout & 0xFFFFFFFFL);
//
//        // Create a key and write a string
//        Advapi32Util.registryCreateKey(WinReg.HKEY_CURRENT_USER, "SOFTWARE\\StackOverflow");
//        Advapi32Util.registrySetStringValue(WinReg.HKEY_CURRENT_USER, "SOFTWARE\\StackOverflow", "url", "http://stackoverflow.com/a/6287763/277307");
//
//        // Delete a key
//        Advapi32Util.registryDeleteKey(WinReg.HKEY_CURRENT_USER, "SOFTWARE\\StackOverflow");
return productName;
}
 
public void execCmd(JTextArea jTextArea,String command) {
   
    try {
                Runtime rt = Runtime.getRuntime();
                //Process pr = rt.exec("cmd /c dir");
                Process pr = rt.exec(command);
 
                BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
 
                String line=null,temp = "";
                
                while((line=input.readLine()) != null) {
                    temp+=line+"\n";
                    jTextArea.setText(temp);
                }
 
                int exitVal = pr.waitFor();
                //System.out.println("Exited with error code "+exitVal);
 
            } catch(Exception e) {
                System.out.println(e.toString());
                e.printStackTrace();
            }
        }
}
