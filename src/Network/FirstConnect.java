package Network;

import java.io.IOException;
import java.net.URL;

public class FirstConnect {
    public static void main(String[] args) throws IOException {
        ConnectURI koneksisaya = new ConnectURI();
        URL myAddress= koneksisaya.builduRl("https://harber.mimoapps.xyz/api/getaccess.php");
        String response = koneksisaya.getResponseFromHttpUrl(myAddress);
        System.out.println(response);
    }
}
