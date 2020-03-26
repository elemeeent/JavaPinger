package modules;


import java.io.IOException;
import java.net.InetAddress;

class PingChecker {

    String checkAddress(String address) throws IOException {
        InetAddress inetAddress = InetAddress.getByName(address);
        boolean reachable = inetAddress.isReachable(1000);
        if (!reachable) {
            return "Site " + address + " not reachable! Network error!"
                    +"\n\n\nYour internet connection not working!";
        }
        String hostAddress = inetAddress.getHostAddress();
        String hostName = inetAddress.getHostName();
        String canonicalHostName = inetAddress.getCanonicalHostName();
        return "Host \"" + address + "\" is reachable." +
                "\nHost address: " + hostAddress +
                "\nHost name: " + hostName +
                "\nHost address: " + canonicalHostName +
                "\n\n\nYour internet connection is OK!";
    }

    String checkGateway(String address) throws IOException {
        InetAddress inetAddress = InetAddress.getByName(address);
        boolean reachable = inetAddress.isReachable(1000);
        if (!reachable) {
            return "Site " + address + " not reachable! Network error!"
                    +"\n\n\nYour local internet connection not working!";
        }
        String hostAddress = inetAddress.getHostAddress();
        return "Host \"" + address + "\" is reachable." +
                "\nHost address: " + hostAddress+
                "\n\n\nYour local internet connection is OK!";
    }
}
