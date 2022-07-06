package Grafic;

import java.util.HashMap;

public class VisualizeDeleteEl implements Runnable {
    private Visualize visualize;
    private GUI gui;
    private HashMap<String, Visualize> elementsClient;
    private String arg;

    public VisualizeDeleteEl(Visualize visualize, GUI gui, HashMap<String, Visualize> elementsClient, String arg) {
        this.visualize = visualize;
        this.gui = gui;
        this.elementsClient = elementsClient;
        this.arg = arg;
    }

    /**
     * Метод создает анимацию при удалении элемента
     */
    @Override
    public void run() {
        elementsClient.remove(arg);
        gui.getDrawer().repaint();
    }
}
