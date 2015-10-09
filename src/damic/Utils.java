/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package damic;

import java.io.IOException;
import static java.lang.System.out;
import java.net.HttpURLConnection;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.util.Collections;
import java.util.Enumeration;

/**
 *
 * @author aariasgonzalez
 */
public class Utils {
       public static InetAddress getEthInterfaceInformation() throws SocketException {
        Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
        for (NetworkInterface netint : Collections.list(nets)){
            //out.printf("Display name: %s\n", netint.getDisplayName());
            //out.printf("Name: %s\n", netint.getName());
            if(!netint.isLoopback() && !netint.getName().contains("virbr")){
                Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
                for (InetAddress inetAddress : Collections.list(inetAddresses)) {
                    if(inetAddress instanceof Inet4Address){
                        out.printf("InetAddress: %s\n", inetAddress);
                        return inetAddress;
                    }
                }
            }
 
        }
        return null;
     }
       
    public static String getContentType(String urlString) throws IOException{
    URL url = new URL(urlString);
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.setRequestMethod("HEAD");
    if (isRedirect(connection.getResponseCode())) {
        String newUrl = connection.getHeaderField("Location"); // get redirect url from "location" header field
            //logger.warn("Original request URL: '{}' redirected to: '{}'", urlString, newUrl);
            return getContentType(newUrl);
        }
        String contentType = connection.getContentType();
        return contentType;
}

/**
 * Check status code for redirects
 * 
 * @param statusCode
 * @return true if matched redirect group
 */
protected static boolean isRedirect(int statusCode) {
    if (statusCode != HttpURLConnection.HTTP_OK) {
        if (statusCode == HttpURLConnection.HTTP_MOVED_TEMP
            || statusCode == HttpURLConnection.HTTP_MOVED_PERM
                || statusCode == HttpURLConnection.HTTP_SEE_OTHER) {
            return true;
        }
    }
    return false;
}  
       
}
