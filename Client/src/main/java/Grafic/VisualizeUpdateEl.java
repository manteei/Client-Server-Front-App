package Grafic;

import java.util.HashMap;

public class VisualizeUpdateEl implements Runnable {
    private Visualize visualize;
    private Visualize visualizeUpdate;
    private GUI gui;
    private HashMap<String, Visualize> elementsClient;
    private String arg;

    public VisualizeUpdateEl(Visualize visualize, Visualize visualizeUpdate, GUI gui, HashMap<String, Visualize> elementsClient, String arg) {
        this.visualize = visualize;
        this.visualizeUpdate = visualizeUpdate;
        this.gui = gui;
        this.elementsClient = elementsClient;
        this.arg = arg;
    }

    /**
     * Метод создает анимацию при удалении элемента
     */
    @Override
    public void run() {
        try {
            elementsClient.remove(arg);
            gui.getDrawer().repaint();
            elementsClient.put(arg, visualizeUpdate);
            while (visualizeUpdate.getHad().getHeight() < Double.parseDouble(visualizeUpdate.getMinimal_point()) * 1.25) {
                gui.getDrawer().repaint();
                Thread.sleep(25);
            }
            while (visualizeUpdate.getHad().getHeight() > Double.parseDouble(visualizeUpdate.getMinimal_point())) {
                gui.getDrawer().repaint();
                Thread.sleep(25);
            }
        } catch (InterruptedException e) {
            // Исключение не мешает логике исполнения программы
        }
    }
}
