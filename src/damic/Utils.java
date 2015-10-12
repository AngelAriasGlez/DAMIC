/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package damic;

import java.awt.Dimension;
import java.io.IOException;
import java.io.InputStream;
import static java.lang.System.out;
import java.net.HttpURLConnection;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

/**
 *
 * @author aariasgonzalez
 */
public class Utils {

    public static InetAddress getEthInterfaceInformation() throws SocketException {
        Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
        for (NetworkInterface netint : Collections.list(nets)) {
            //out.printf("Display name: %s\n", netint.getDisplayName());
            //out.printf("Name: %s\n", netint.getName());
            if (!netint.isLoopback() && !netint.getName().contains("virbr")) {
                Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
                for (InetAddress inetAddress : Collections.list(inetAddresses)) {
                    if (inetAddress instanceof Inet4Address) {
                        out.printf("InetAddress: %s\n", inetAddress);
                        return inetAddress;
                    }
                }
            }

        }
        return null;
    }

    public static String getContentType(String urlString) throws IOException, UnknownHostException {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
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

    public static Dimension getImageSize(String url) {
        try {
            InputStream input = new URL(url).openStream();
            ImageInputStream in = ImageIO.createImageInputStream(input);
            final Iterator<ImageReader> readers = ImageIO.getImageReaders(in);
            if (readers.hasNext()) {
                ImageReader reader = readers.next();
                try {
                    reader.setInput(in);
                    return new Dimension(reader.getWidth(0), reader.getHeight(0));
                } finally {
                    reader.dispose();
                }
            }
        } catch (IOException e) {

        }
        return null;
    }


}
