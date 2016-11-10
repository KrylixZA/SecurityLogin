package Logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NetworkTools {

    public static String getMacAddress() {
        InetAddress ip;
        StringBuilder sb = null;

        try {
            ip = InetAddress.getLocalHost();
            //for local ip ip.getHostAddress());
            NetworkInterface network = NetworkInterface.getByInetAddress(ip);

            byte[] mac = network.getHardwareAddress();

            sb = new StringBuilder();
            for (int i = 0; i < mac.length; i++) {
                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
            }
        } catch (UnknownHostException | SocketException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    public static String getExternalIP() {
        URL whatsmyip;
        String ip = "";
        try {
            whatsmyip = new URL("http://checkip.amazonaws.com");
            BufferedReader in = new BufferedReader(new InputStreamReader(whatsmyip.openStream()));
            ip = in.readLine();
        } catch (MalformedURLException ex) {
            Logger.getLogger(NetworkTools.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(NetworkTools.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return ip;
    }
}
