package OrgClient;


import Grafic.GUI;

import java.io.IOException;

public class MainClient {
    public static void main(String[] args) throws IOException {
        GUI gui = new GUI();
        gui.getAuthorization().createAuthorizationFrame();
    }
}
