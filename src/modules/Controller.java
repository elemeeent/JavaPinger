package modules;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

public class Controller {

    private static final String DEFAULT_MESSAGE = "Result Error!";

    private Addresses addresses = new Addresses();
    private PingChecker pingChecker = new PingChecker();
    private HttpChecker httpChecker = new HttpChecker();

    @FXML
    private TextField inputTextICMP;
    @FXML
    private TextField inputTextHTTP;
    @FXML
    private TextArea outputTextICMP;
    @FXML
    private TextArea outputTextHTTP;

    @FXML
    private void pingGoogle() {
        String result = DEFAULT_MESSAGE;
        try {
            result = pingChecker.checkAddress(addresses.getGOOGLE());
        } catch (IOException e) {
            e.printStackTrace();
        }
        printOutputICMP(result);
    }

    @FXML
    private void pingVk() {
        String result = DEFAULT_MESSAGE;
        try {
            result = pingChecker.checkAddress(addresses.getVK());
        } catch (IOException e) {
            e.printStackTrace();
        }
        printOutputICMP(result);
    }

    @FXML
    private void pingYandex() {
        String result = DEFAULT_MESSAGE;
        try {
            result = pingChecker.checkAddress(addresses.getYANDEX());
        } catch (IOException e) {
            e.printStackTrace();
        }
        printOutputICMP(result);
    }

    @FXML
    private void pingCurrent() {
        String value = inputTextICMP.getText();
        if (value.isEmpty()) {
            printOutputICMP("Please, add valid ip address or domain name");
        }

        String zeroTo255 = "([01]?[0-9]{1,2}|2[0-4][0-9]|25[0-5])";
        String IP_REGEXP = zeroTo255 + "\\." + zeroTo255 + "\\."
                + zeroTo255 + "\\." + zeroTo255;

        if (value.matches("^(?!:\\/\\/)([a-zA-Z0-9-_]+\\.)*[a-zA-Z0-9][a-zA-Z0-9-_]+\\.[a-zA-Z]{2,11}?$") ||
                value.matches(IP_REGEXP)) {
            printOutputICMP("Domain " + value + " is valid. Start Test");
            try {
                printOutputICMP(pingChecker.checkAddress(value));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            printOutputICMP("Please, add valid ip address or domain name");
        }
    }

    @FXML
    private void onEnterICMP() {
        pingCurrent();
    }

    @FXML
    private void onEnterHTTP() {
        httpCurrent();
    }

    @FXML
    private void pingDNS() {
        String result = DEFAULT_MESSAGE;
        try {
            result = pingChecker.checkAddress(addresses.getDnsIp());
        } catch (IOException e) {
            e.printStackTrace();
        }
        printOutputICMP(result);
    }

    @FXML
    private void pingGateway() {
        String result = DEFAULT_MESSAGE;
        try {
            result = pingChecker.checkGateway(addresses.getGatewayIp());
        } catch (IOException e) {
            e.printStackTrace();
        }
        printOutputICMP(result);
    }

    @FXML
    private void pingRouter() {
        String result = DEFAULT_MESSAGE;
        try {
            result = pingChecker.checkGateway(addresses.getGatewayIp());
        } catch (IOException e) {
            e.printStackTrace();
        }
        printOutputICMP(result);
    }

    @FXML
    private void httpGoogle() {
        String result = DEFAULT_MESSAGE;
        try {
            result = httpChecker.checkHttp(addresses.getGOOGLE());
        } catch (IOException e) {
            e.printStackTrace();
        }
        printOutputHTTP(result);
    }

    @FXML
    private void httpVk() {
        String result = DEFAULT_MESSAGE;
        try {
            result = httpChecker.checkHttp(addresses.getVK());
        } catch (IOException e) {
            e.printStackTrace();
        }
        printOutputHTTP(result);
    }

    @FXML
    private void httpYandex() {
        String result = DEFAULT_MESSAGE;
        try {
            result = httpChecker.checkHttp(addresses.getYANDEX());
        } catch (IOException e) {
            e.printStackTrace();
        }
        printOutputHTTP(result);
    }

    @FXML
    private void httpCurrent() {
        String value = inputTextHTTP.getText();
        if (value.isEmpty()) {
            printOutputHTTP("Please, add valid ip address or domain name");
        }

        String zeroTo255 = "([01]?[0-9]{1,2}|2[0-4][0-9]|25[0-5])";
        String IP_REGEXP = zeroTo255 + "\\." + zeroTo255 + "\\."
                + zeroTo255 + "\\." + zeroTo255;

        if (value.matches("^(?!:\\/\\/)([a-zA-Z0-9-_]+\\.)*[a-zA-Z0-9][a-zA-Z0-9-_]+\\.[a-zA-Z]{2,11}?$") ||
                value.matches(IP_REGEXP)||
                value.matches("^(http|https)(:\\/\\/)([a-zA-Z0-9-_]+\\.)*[a-zA-Z0-9][a-zA-Z0-9-_]+\\.[a-zA-Z]{2,11}?$")) {
            printOutputHTTP("Domain " + value + " is valid. Start Test");
            try {
                printOutputHTTP(httpChecker.checkHttpCustom(value));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            printOutputHTTP("Please, add valid ip address or domain name");
        }
    }

    // Add a public no-args constructor
    public Controller() {
    }

    @FXML
    private void initialize() {
    }

    @FXML
    private void printOutputICMP(String result) {
        outputTextICMP.setText(result);
    }

    @FXML
    private void printOutputHTTP(String result) {
        outputTextHTTP.setText(result);
    }
}
