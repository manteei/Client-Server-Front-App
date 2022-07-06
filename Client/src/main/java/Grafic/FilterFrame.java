package Grafic;

import javax.swing.*;
import java.awt.*;

public class FilterFrame {
    private GUI gui;
    private JLabel validate = new JLabel();

    public FilterFrame(GUI gui) {
        this.gui = gui;
    }


    /**
     * Метод создает фрейм для фильтрации элементов
     *
     * @param command
     */
    public void createFilterFrame(String command) {
        Font fnt = new Font("шрифт", Font.PLAIN, 12);
        java.awt.Color color3 =new java.awt.Color(173, 186, 241);
        java.awt.Color color4 = new java.awt.Color(255, 212, 91);
        validate.setText("");
        String arg1 = "";
        JFrame removeFrame = new JFrame(command);
        removeFrame.setLayout(new GridBagLayout());
        removeFrame.setSize(300, 200);
        removeFrame.setDefaultCloseOperation(removeFrame.DISPOSE_ON_CLOSE);
        removeFrame.setResizable(false);
        removeFrame.setLocationRelativeTo(null);
        removeFrame.getContentPane().setBackground(color3);
        arg1 = gui.getBundle().getString("minimalPoint");

        JTextField arg2 = new JTextField();
        JButton buttonAuth = new JButton(gui.getBundle().getString("submit3"));
        buttonAuth.setBackground(color4);
        buttonAuth.setFont(fnt);


        removeFrame.add(new JLabel(arg1), new GridBagConstraints(0, 0, 1, 1, 1.0, 0.1,
                GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(10, 30, 0, 0), 0, 0));
        removeFrame.add(arg2, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0,
                GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(10, 30, 0, 30), 0, 0));
        removeFrame.add(validate, new GridBagConstraints(0, 2, 1, 1, 1.0, 1.0,
                GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(10, 30, 0, 0), 0, 0));
        removeFrame.add(buttonAuth, new GridBagConstraints(0, 3, 1, 1, 1.0, 1.0,
                GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(10, 30, 0, 0), 0, 0));

        buttonAuth.addActionListener(e -> {
            try {
                String result = gui.getClient().goCommand(command + " " + arg2.getText());
                if ("filterex1".equals(result) | "updateres3".equals(result)) {
                    validate.setText("<html>" + gui.getBundle().getString(result) + "</html>");
                } else {
                    gui.getResult().setText(result);
                    removeFrame.dispose();
                }
            } catch (NumberFormatException ex) {
                validate.setText(gui.getBundle().getString("updateres3"));
            }
        });
        removeFrame.setVisible(true);
    }
}