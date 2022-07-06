package Grafic;

import javax.swing.*;
import java.awt.*;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class AuthorizationFrame {
    private GUI gui;
    private String login;
    private String password;
    private JFrame frame = new JFrame("Labworks");
    private String check;
    public AuthorizationFrame(GUI gui) {
        this.gui = gui;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public JFrame getFrame() {
        return frame;
    }

    public void createAuthorizationFrame() {
        Color col2 =new Color(173, 186, 241);
        Color col4 = new Color(255, 212, 91);
        frame.setLayout(new GridBagLayout());
        frame.setSize(350, 260);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(col2);
        Font fnt = new Font("шрифт", Font.PLAIN, 16);
        Font fnt1 = new Font("шрифт", Font.PLAIN, 12);

        JLabel loginLabel = new JLabel(gui.getBundle().getString("login"));
        JTextField loginFiled = new JTextField();
        JLabel passwordLabel = new JLabel(gui.getBundle().getString("password"));
        JTextField passwordField = new JTextField();
        JLabel result = new JLabel();
        JButton buttonAuth = new JButton(gui.getBundle().getString("enter"));
        JButton buttonReg = new JButton(gui.getBundle().getString("registration"));
        JComboBox<String> languages = new JComboBox<>(new String[]{"Русский", "Suomalainen", "Украiнський", "Espanol (Nicaragua)"});
        buttonAuth.setBackground(col4);
        buttonReg.setBackground(col4);
        languages.setBackground(col4);
        buttonReg.setFont(fnt);
        buttonAuth.setFont(fnt);
        passwordLabel.setFont(fnt1);
        loginLabel.setFont(fnt1);
        languages.setFont(fnt1);
        frame.add(languages, new GridBagConstraints(0, 0, 2, 1, 1.0, 1.0,
                GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(10, 200, 0, 10), 0, 0));
        frame.add(loginLabel, new GridBagConstraints(0, 1, 2, 1, 1.0, 0.1,
                GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 50, 0, 50), 0, 0));
        frame.add(loginFiled, new GridBagConstraints(0, 2, 2, 1, 1.0, 1.0,
                GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(0, 50, 0, 50), 0, 0));
        frame.add(passwordLabel, new GridBagConstraints(0, 3, 2, 1, 1.0, 0.1,
                GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 50, 0, 50), 0, 0));
        frame.add(passwordField, new GridBagConstraints(0, 4, 2, 1, 1.0, 10,
                GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(0, 50, 0, 50), 0, 0));
        frame.add(result, new GridBagConstraints(0, 5, 2, 1, 1.0, 1.0,
                GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(0, 50, 0, 50), 0, 0));
        frame.add(buttonAuth, new GridBagConstraints(0, 6, 1, 2, 1.0, 1.0,
                GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 50, 0, 0), 0, 0));
        frame.add(buttonReg, new GridBagConstraints(1, 6, 1, 4, 0.1, 1.0,
                GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 50), 0, 0));
        frame.setVisible(true);

        buttonAuth.addActionListener(e -> {
            login = loginFiled.getText();
            password = passwordField.getText();
            gui.getClient().getUser().setUsername(login);
            gui.getClient().getUser().setPassword(password);
            gui.getClient().getUser().setRegistration(false);
            gui.getClient().getUser().setAuthorization(true);
            String answer = gui.getBundle().getString(gui.getClient().authreg());
            if (answer.equals(gui.getBundle().getString("authorizationResult"))) {
                frame.setVisible(false);
                gui.getMain().createMainFrame();
                gui.getMain().getUser().setText(getLogin());
                gui.getMain().getMainFrame().setVisible(true);
            } else {
                result.setText("<html>" + answer + "</html>");
                loginFiled.setText("");
                passwordField.setText("");
            }
            gui.getClient().getUser().setRegistration(false);
            gui.getClient().getUser().setAuthorization(false);

        });
        buttonReg.addActionListener(e -> {
            login = loginFiled.getText();
            boolean l = validateUsername(login);
            if (l) {
                password = passwordField.getText();
                boolean p = validatePassword(password);
                if (p) {
                    gui.getClient().getUser().setUsername(login);
                    gui.getClient().getUser().setPassword(password);
                    gui.getClient().getUser().setRegistration(true);
                    gui.getClient().getUser().setAuthorization(false);
                    gui.getClient().authreg();
                    String answer = gui.getBundle().getString(gui.getClient().getAnswer());
                    result.setText("<html>" + answer + "</html>");
                    if (!answer.equals(gui.getBundle().getString("registrationResult"))) {
                        loginFiled.setText("");
                        passwordField.setText("");
                    }

                    gui.getClient().getUser().setRegistration(false);
                    gui.getClient().getUser().setAuthorization(false);
                }else {
                    String answer = gui.getBundle().getString(check);
                    result.setText("<html>" + answer + "</html>");
                }
            }else {
                String answer = gui.getBundle().getString(check);
                result.setText("<html>" + answer + "</html>");
            }
        });
        languages.addActionListener(e -> {
            gui.choseLanguage(languages);
            loginLabel.setText(gui.getBundle().getString("login"));
            passwordLabel.setText(gui.getBundle().getString("password"));
            buttonAuth.setText(gui.getBundle().getString("enter"));
            buttonReg.setText(gui.getBundle().getString("registration"));
            result.setText("");
        });
        frame.setVisible(true);
    }

    public boolean validateUsername(String username) {
        if ("".equals(username.split("\\s+")) || "".equals(username.trim())) {
            check = "exnulllogin";
            return false;
        }
        if (!username.matches("\\b[a-zA-Z][a-zA-Z0-9\\-._]{3,16}\\b")) {
            check = "exlogin";
            return false;
        }
        return true;
    }

    public boolean validatePassword(String password) {
        if ("".equals(password.split("\\s+")) || "".equals(password.trim())) {
            check = "exnullpassword";
            return false;
        }
        if (!password.matches("\\b[a-zA-Z][a-zA-Z0-9\\-._]{3,10}\\b")) {
            check = "expassword";
            return false;
        }
        return true;
    }
}
