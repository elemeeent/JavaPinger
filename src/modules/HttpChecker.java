package modules;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;

class HttpChecker {

    String checkHttp(String address) throws IOException {
        InetAddress inetAddress = InetAddress.getByName(address);
        boolean reachable = inetAddress.isReachable(1000);
        if (!reachable) {
            return "Site " + address + " not reachable! Network error!"
                    + "\n\n\nYour internet connection not working!";
        }

        URL url;
        if (address.contains("http://") || address.contains("https://")) {
            url = new URL(address);
        } else {
            url = new URL("http://www." + address);
        }
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");
        con.setConnectTimeout(5000);
        con.setReadTimeout(5000);

        boolean redirect = false;

        // normally, 3xx is redirect
        int status = con.getResponseCode();
        if (status != HttpURLConnection.HTTP_OK) {
            if (status == HttpURLConnection.HTTP_MOVED_TEMP
                    || status == HttpURLConnection.HTTP_MOVED_PERM
                    || status == HttpURLConnection.HTTP_SEE_OTHER)
                redirect = true;
        }

        if (redirect) {
            // get redirect url from "location" header field
            String newUrl = con.getHeaderField("Location");

            // get the cookie if need, for login
            String cookies = con.getHeaderField("Set-Cookie");

            // open the new connection again
            con = (HttpURLConnection) new URL(newUrl).openConnection();
            con.setRequestProperty("Cookie", cookies);
            con.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
            con.addRequestProperty("User-Agent", "Mozilla");
            con.addRequestProperty("Referer", "google.com");
        }

        if (con.getResponseCode() >= 400) {
            return "Http Response for URL \"" + address + "\"\n- code is: " + con.getResponseCode() + "." +
                    "\n- message is: " + con.getResponseMessage() +
                    "\n\n\nYour internet connection is OK, but Url not reachable!";
        }

        return "Http Response for URL \"" + address + "\"\n- code is: " + con.getResponseCode() + "." +
                "\n- message is: " + con.getResponseMessage() + "\n\n\nYour internet connection is OK!";
    }

    String checkHttpCustom(String address) throws IOException {
        URL url;
        if (address.contains("http://") || address.contains("https://")) {
            url = new URL(address);
        } else {
            url = new URL("https://www." + address);
        }

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");
        con.setConnectTimeout(5000);
        con.setReadTimeout(5000);

        boolean redirect = false;

        // normally, 3xx is redirect
        int status = con.getResponseCode();
        if (status != HttpURLConnection.HTTP_OK) {
            if (status == HttpURLConnection.HTTP_MOVED_TEMP
                    || status == HttpURLConnection.HTTP_MOVED_PERM
                    || status == HttpURLConnection.HTTP_SEE_OTHER)
                redirect = true;
        }

        if (redirect) {
            // get redirect url from "location" header field
            String newUrl = con.getHeaderField("Location");

            // get the cookie if need, for login
            String cookies = con.getHeaderField("Set-Cookie");

            // open the new connection again
            con = (HttpURLConnection) new URL(newUrl).openConnection();
            con.setRequestProperty("Cookie", cookies);
            con.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
            con.addRequestProperty("User-Agent", "Mozilla");
        }

        if (con.getResponseCode() >= 400) {
            return "Http Response for URL \"" + address + "\"\n- code is: " + con.getResponseCode() + "." +
                    "\n- message is: " + con.getResponseMessage() +
                    "\n\n\nYour internet connection is OK, but Url not reachable!";
        }

        return "Http Response for URL \"" + address + "\"\n- code is: " + con.getResponseCode() + "." +
                "\n- message is: " + con.getResponseMessage() + "\n\n\nYour internet connection is OK!";
    }
}
