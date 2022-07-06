package Grafic;

import OrgClient.Client;
import OrgClient.ComandReader;
import User.User;

import javax.swing.*;
import java.util.*;

public class GUI {
    private ResourceBundle bundle = ResourceBundle.getBundle("resources");
    private MainGui main = new MainGui(this);
    private JTextArea result = new JTextArea();
    private Client client = new Client();
    private Drawer drawer = new Drawer(this);
    private ComandReader comandReader;
    private AddCommandFrame addCommandFrame = new AddCommandFrame(this);
    private RemoveCommandFrame removeCommandFrame = new RemoveCommandFrame(this);
    private FilterFrame filterFrame = new FilterFrame(this);
    private EditorPane editorPane = new EditorPane(this);
    private EditAllFrame editAllFrame = new EditAllFrame(this);
    AuthorizationFrame authorization = new AuthorizationFrame(this);


    public ResourceBundle getBundle() {
        return bundle;
    }
    public AddCommandFrame getAddCommandFrame() {
        return addCommandFrame;
    }
    public RemoveCommandFrame getRemoveCommandFrame(){
        return removeCommandFrame;
    }
    public JTextArea getResult() {
        return result;
    }
    public AuthorizationFrame getAuthorization() {
        return authorization;
    }
    public Client getClient(){
        return client;
    }
    public MainGui getMain(){
        return main;
    }
    public FilterFrame getFilterFrame() {
        return filterFrame;
    }

    public EditorPane getEditorPane() {
        return editorPane;
    }

    public Drawer getDrawer() {
        return drawer;
    }

    public EditAllFrame getEditAllFrame() {
        return editAllFrame;
    }

    public ComandReader getComandReader(){
        return comandReader;
    }

    /**
     * Язык
     */
    public void choseLanguage(JComboBox<String> languages) {
        String language = (String) languages.getSelectedItem();
        switch (language) {
            case "Русский":
                bundle = ResourceBundle.getBundle("resources");
                break;
            case "Suomalainen":
                bundle = ResourceBundle.getBundle("resources", new Locale("su"));
                break;
            case "Украiнський":
                bundle = ResourceBundle.getBundle("resources", new Locale("uk"));
                break;
            case "Espanol (Nicaragua)":
                bundle = ResourceBundle.getBundle("resources", new Locale("sp"));
                break;
        }
    }
}
