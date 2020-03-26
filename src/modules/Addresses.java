package modules;

import sun.net.dns.ResolverConfiguration;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;

class Addresses {
    private static final String GOOGLE = "google.com";
    private static final String YANDEX = "yandex.ru";
    private static final String VK = "vk.com";
    private static String gatewayIp;
    private static String dnsIp;

    String getGOOGLE() {
        return GOOGLE;
    }

    String getYANDEX() {
        return YANDEX;
    }

    String getVK() {
        return VK;
    }

    String getGatewayIp() {
        setGateway(gatewayIp);
        return gatewayIp;
    }

    String getDnsIp() {
        setDnsIp();
        return dnsIp;
    }

    private void setDnsIp() {
        List nameServers = ResolverConfiguration.open().nameservers();
        nameServers.forEach((dns) -> dnsIp = dns.toString());
    }

    private void setGateway(String gateway) {
        try {
            Process result = Runtime.getRuntime().exec("netstat -rn");

            BufferedReader output = new BufferedReader(new InputStreamReader(result.getInputStream()));

            String line = output.readLine();
            while (line != null) {
                if (line.trim().startsWith("default") || line.trim().startsWith("0.0.0.0"))
                    break;
                line = output.readLine();
            }
            if (line == null) //gatewayIp not found;
                return;

            StringTokenizer st = new StringTokenizer(line);
            st.nextToken();
            st.nextToken();
            gateway = st.nextToken();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        gatewayIp = gateway;
    }
}
