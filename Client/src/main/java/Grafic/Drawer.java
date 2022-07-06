package Grafic;

import Collection.*;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.GeneralPath;
import java.time.LocalDate;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Drawer extends JPanel {
    private HashMap<String, Visualize> elementsClient = new HashMap();
    private HashMap<String, Color> colors = new HashMap<>();
    private Random random = new Random();
    private GUI gui;


    public HashMap<String, Color> getColors() {
        return colors;
    }

    public HashMap<String, Visualize> getElementsClient() {
        return elementsClient;
    }

    public Drawer(GUI gui) {
        java.awt.Color color3 = new java.awt.Color(173, 186, 241);
        java.awt.Color color4 = new java.awt.Color(255, 212, 91);
        this.gui = gui;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                for (Map.Entry<String, Visualize> element : elementsClient.entrySet()) {
                    if (element.getValue().getHad().contains(e.getX(), e.getY())) {
                        gui.getResult().setText("id: " + element.getValue().getId() + "\n"
                                + gui.getBundle().getString("username") + ": " + element.getValue().getUsername() + "\n"
                                + gui.getBundle().getString("name") + ": " + element.getValue().getName() + "\n"
                                + "x: " + element.getValue().getX() + "\n"
                                + "y: " + element.getValue().getY() + "\n"
                                + gui.getBundle().getString("creationDate") + ": " + element.getValue().getCreationDate() + "\n"
                                + gui.getBundle().getString("minimal_point") + ": " + element.getValue().getMinimal_point() + "\n"
                                + gui.getBundle().getString("difficulty") + ": " + element.getValue().getDifficulty() + "\n"
                                + gui.getBundle().getString("authorname") + ": " + element.getValue().getAuthorname() + "\n"
                                + gui.getBundle().getString("weight") + ": " + element.getValue().getWeight() + "\n"
                                + gui.getBundle().getString("passportid") + ": " + element.getValue().getPassportid() + "\n"
                                + gui.getBundle().getString("hairColor") + ": " + element.getValue().getHaircolor() + "\n"
                                + gui.getBundle().getString("nationality") + ": " + element.getValue().getNationality() + "\n"
                        );

                        Coordinates coordinates = new Coordinates(Long.parseLong(element.getValue().getX()), Long.parseLong(element.getValue().getY()));
                        Person person = new Person(element.getValue().getAuthorname(), Double.parseDouble(element.getValue().getWeight()), element.getValue().getPassportid(),
                                Collection.Color.valueOf(element.getValue().getHaircolor()), Country.valueOf(element.getValue().getNationality()));
                        LabWork labWork = new LabWork(Integer.parseInt(element.getValue().getId()),element.getValue().getName(), coordinates, LocalDate.parse(element.getValue().getCreationDate()),
                                Double.parseDouble(element.getValue().getMinimal_point()), Difficulty.valueOf(element.getValue().getDifficulty()), person, element.getValue().getUsername());

                        String TITLE_confirm = gui.getBundle().getString("window1");
                        UIManager UI=new UIManager();
                        UI.put("OptionPane.background",new ColorUIResource(173, 186, 241));
                        UI.put("Panel.background",new ColorUIResource(173, 186, 241));
                        UIManager.put("OptionPane.buttonBackground", new ColorUIResource(255, 212, 91));
                        if (element.getValue().getUsername().equals(gui.getAuthorization().getLogin())) {
                            int result = JOptionPane.showConfirmDialog(gui.getMain().getjTable(), gui.getBundle().getString("editel"), TITLE_confirm, JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                            if (result == 0) {
                                gui.getEditAllFrame().createEditAllFrame(labWork);
                            }
                        }

                    }
                }
            }
        });
    }

    public void udateElement(HashMap<String, Visualize> elementsServer) {
            for (Map.Entry<String, Visualize> elementServer : elementsServer.entrySet()) {
                if (!elementsClient.containsKey(elementServer.getKey())) {
                    elementsClient.put(elementServer.getKey(), elementServer.getValue());
                    new Thread(new VisualizeAddEl(elementServer.getValue(), gui)).start();
                }
            }
            for (Map.Entry<String, Visualize> elementClient : elementsClient.entrySet()) {
                if (!elementsServer.containsKey(elementClient.getKey())) {
                    new Thread(new VisualizeDeleteEl(elementClient.getValue(), gui, elementsClient, elementClient.getKey())).start();
                }
            }
            for (Map.Entry<String, Visualize> elementServer : elementsServer.entrySet()) {
                if (!elementsClient.get(elementServer.getKey()).getName().equals(elementServer.getValue().getName()) ||
                        !elementsClient.get(elementServer.getKey()).getX().equals(elementServer.getValue().getX()) ||
                        !elementsClient.get(elementServer.getKey()).getY().equals(elementServer.getValue().getY()) ||
                        !elementsClient.get(elementServer.getKey()).getCreationDate().equals(elementServer.getValue().getCreationDate()) ||
                        !elementsClient.get(elementServer.getKey()).getMinimal_point().equals(elementServer.getValue().getMinimal_point()) ||
                        !elementsClient.get(elementServer.getKey()).getDifficulty().equals(elementServer.getValue().getDifficulty()) ||
                        !elementsClient.get(elementServer.getKey()).getAuthorname().equals(elementServer.getValue().getAuthorname()) ||
                        !elementsClient.get(elementServer.getKey()).getWeight().equals(elementServer.getValue().getWeight()) ||
                        !elementsClient.get(elementServer.getKey()).getPassportid().equals(elementServer.getValue().getPassportid()) ||
                        !elementsClient.get(elementServer.getKey()).getHaircolor().equals(elementServer.getValue().getHaircolor()) ||
                        !elementsClient.get(elementServer.getKey()).getNationality().equals(elementServer.getValue().getNationality())) {
                    new Thread(new VisualizeUpdateEl(elementsClient.get(elementServer.getKey()), elementServer.getValue(), gui, elementsClient, elementServer.getKey())).start();
                }
            }
            repaint();
        }


    /**
     * Метод генерирует цвет элементам
     *
     * @return
     */
    public float[] setColor() {
        float r = random.nextFloat();
        float g = random.nextFloat();
        float b = random.nextFloat();
        return new float[]{r, g, b};
    }

    @Override
    public void paint(Graphics g) {
        try {
            super.paintComponent(g);
            this.setBackground(Color.WHITE);
            Graphics2D g2 = (Graphics2D) g;
            for (Map.Entry<String, Visualize> element : elementsClient.entrySet()) {
                g2.setBackground(Color.WHITE);
                element.getValue().drawHat(g2, colors.get(element.getValue().getUsername()));
            }
        } catch (ConcurrentModificationException e) {
            // Исключение не мешает логике исполнения программы
        }
    }


}
