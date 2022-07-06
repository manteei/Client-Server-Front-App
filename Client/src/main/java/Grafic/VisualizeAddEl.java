package Grafic;

public class VisualizeAddEl implements Runnable {
    private Visualize visualize;
    private GUI gui;

    public VisualizeAddEl(Visualize visualize, GUI gui) {
        this.visualize = visualize;
        this.gui = gui;
    }

    /**
     * Метод создает анимацию при добавлени  элемента
     */
    @Override
    public void run() {
        try {
            while (visualize.getHad().getHeight() < Double.parseDouble(visualize.getMinimal_point()) * 1.25) {
                gui.getDrawer().repaint();
                Thread.sleep(25);
            }
            while (visualize.getHad().getHeight() > Double.parseDouble(visualize.getMinimal_point())) {
                gui.getDrawer().repaint();
                Thread.sleep(25);
            }
        } catch (InterruptedException e) {
            // Исключение не мешает логике исполнения программы
        }
    }
}
