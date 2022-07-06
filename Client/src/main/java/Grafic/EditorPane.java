package Grafic;

import Collection.*;
import Reader.Checker;
import User.CommandLine;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.time.LocalDate;


public class EditorPane {
    private GUI gui;
    private JLabel validate = new JLabel();
    private CommandLine commandLine;
    private Checker checker = new Checker(commandLine);
    private boolean flag;
    String text = "";
    public EditorPane(GUI gui) {
        this.gui = gui;
    }

    public boolean getFlag() {
        return flag;
    }

    /**
     * Метод создает фрейм для редактирования элементов
     *
     * @param colomn
     */
    public String createEditFrame(int row, int colomn) {
        Font fnt = new Font("шрифт", Font.PLAIN, 12);
        java.awt.Color color3 = new java.awt.Color(173, 186, 241);
        java.awt.Color color4 = new java.awt.Color(255, 212, 91);
        JFrame edit = new JFrame("Edit");
        edit.setLayout(new GridBagLayout());
        edit.setSize(350, 190);
        edit.setDefaultCloseOperation(edit.DISPOSE_ON_CLOSE);
        edit.setResizable(false);
        edit.setLocationRelativeTo(null);
        edit.getContentPane().setBackground(color3);
        edit.add(validate);
        String arg1 = "";
        if (colomn ==1){
             arg1 =gui.getBundle().getString("name");
        }else if (colomn ==3){
             arg1 = "x: ";
        }else if (colomn ==4){
             arg1 = "y: ";
        }else if (colomn ==5){
             arg1 = gui.getBundle().getString("creationDate");
        }else if (colomn ==6){
             arg1 = gui.getBundle().getString("minimalPoint");
        }else if (colomn ==7){
             arg1 = gui.getBundle().getString("difficulty");
        }else if (colomn ==8){
             arg1 = gui.getBundle().getString("authorName");
        }else if (colomn ==9){
             arg1 = gui.getBundle().getString("weight");
        }else if (colomn ==10){
             arg1 = gui.getBundle().getString("passportID");
        }else if (colomn ==11){
             arg1 = gui.getBundle().getString("haircolor");
        }else if (colomn ==12){
             arg1 = gui.getBundle().getString("nationality");
        }
        validate.setText("");

        String username = (String) gui.getMain().getjTable().getModel().getValueAt(row, 0);
        String name = (String) gui.getMain().getjTable().getModel().getValueAt(row, 1);
        String id = (String) gui.getMain().getjTable().getModel().getValueAt(row, 2);
        Long x = Long.valueOf(String.valueOf(gui.getMain().getjTable().getModel().getValueAt(row, 3)));
        Long y = Long.valueOf(String.valueOf(gui.getMain().getjTable().getModel().getValueAt(row, 4)));
        LocalDate date = (LocalDate.parse(String.valueOf(gui.getMain().getjTable().getModel().getValueAt(row, 5))));
        Double minimalPoint = Double.valueOf(String.valueOf(gui.getMain().getjTable().getModel().getValueAt(row, 6)));
        Difficulty difficulty = (Difficulty.valueOf(String.valueOf(gui.getMain().getjTable().getModel().getValueAt(row, 7))));
        String authorName = (String) gui.getMain().getjTable().getModel().getValueAt(row, 8);
        Double weight = Double.valueOf(String.valueOf(gui.getMain().getjTable().getModel().getValueAt(row, 9)));
        String passportID = (String) gui.getMain().getjTable().getModel().getValueAt(row, 10);
        Collection.Color hairColor = Collection.Color.valueOf(String.valueOf(gui.getMain().getjTable().getModel().getValueAt(row, 11)));
        Country nationality = Country.valueOf(String.valueOf(gui.getMain().getjTable().getModel().getValueAt(row, 12)));
        Coordinates coordinates = new Coordinates(x, y);
        Person person = new Person(authorName,weight, passportID, hairColor, nationality);
        LabWork labWork = new LabWork(name,coordinates, date, minimalPoint, difficulty, person, username);

        if (colomn == 7){
            ButtonGroup difficultyRadio = new ButtonGroup();
            JRadioButton dif1 = new JRadioButton("EASY");
            JRadioButton dif2 = new JRadioButton("HARD");
            JRadioButton dif3 = new JRadioButton("IMPOSSIBLE");
            JRadioButton dif4 = new JRadioButton("HOPELESS");
            dif1.setActionCommand("EASY");
            dif2.setActionCommand("HARD");
            dif3.setActionCommand("IMPOSSIBLE");
            dif4.setActionCommand("HOPELESS");
            dif1.setFont(fnt);
            dif2.setFont(fnt);
            dif3.setFont(fnt);
            dif4.setFont(fnt);
            dif1.setBackground(color3);
            dif2.setBackground(color3);
            dif3.setBackground(color3);
            dif4.setBackground(color3);
            difficultyRadio.add(dif1);
            difficultyRadio.add(dif2);
            difficultyRadio.add(dif3);
            difficultyRadio.add(dif4);
            JPanel dif = new JPanel();
            dif.setLayout(new GridLayout(2, 1, 5, 5));
            dif.add(dif1);
            dif.add(dif2);
            dif.add(dif3);
            dif.add(dif4);
            dif.setBackground(color3);
            edit.add(dif);
            JButton buttonAuth = new JButton(gui.getBundle().getString("submit2"));
            edit.add(new JLabel(arg1), new GridBagConstraints(0, 0, 1, 1, 1.0, 0.1,
                    GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(10, 30, 0, 0), 0, 0));
            edit.add(dif, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0,
                    GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(10, 30, 0, 30), 0, 0));
            edit.add(validate, new GridBagConstraints(0, 2, 1, 1, 1.0, 1.0,
                    GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(10, 30, 0, 0), 0, 0));
            edit.add(buttonAuth, new GridBagConstraints(0, 3, 1, 1, 1.0, 1.0,
                    GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(10, 30, 0, 0), 0, 0));
            buttonAuth.setBackground(color4);
            buttonAuth.addActionListener(e -> {
                try {
                    String text = difficultyRadio.getSelection().getActionCommand();
                    gui.getMain().getjTable().setValueAt(text,row,colomn);
                    labWork.setDifficulty(Difficulty.valueOf(text));
                    gui.getClient().goCommandFrame("update" + " " + id, labWork);
                } catch (NullPointerException ex) {
                    validate.setText("<html>" + gui.getBundle().getString("diffex") + "</html>");
                }
            });
            edit.setVisible(true);
        }else if (colomn == 11){
            ButtonGroup colorRadio = new ButtonGroup();
            JRadioButton col1 = new JRadioButton("GREEN");
            JRadioButton col2 = new JRadioButton("YELLOW");
            JRadioButton col3 = new JRadioButton("WHITE");
            JRadioButton col4 = new JRadioButton("BROWN");
            col1.setActionCommand("GREEN");
            col2.setActionCommand("YELLOW");
            col3.setActionCommand("WHITE");
            col4.setActionCommand("BROWN");
            col1.setFont(fnt);
            col2.setFont(fnt);
            col3.setFont(fnt);
            col4.setFont(fnt);
            col1.setBackground(color3);
            col2.setBackground(color3);
            col3.setBackground(color3);
            col4.setBackground(color3);
            colorRadio.add(col1);
            colorRadio.add(col2);
            colorRadio.add(col3);
            colorRadio.add(col4);
            JPanel col = new JPanel();
            col.setLayout(new GridLayout(2, 1, 5, 5));
            col.add(col1);
            col.add(col2);
            col.add(col3);
            col.add(col4);
            col.setBackground(color3);
            edit.add(col);
            JButton buttonAuth = new JButton(gui.getBundle().getString("submit2"));
            edit.add(new JLabel(arg1), new GridBagConstraints(0, 0, 1, 1, 1.0, 0.1,
                    GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(10, 30, 0, 0), 0, 0));
            edit.add(col, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0,
                    GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(10, 30, 0, 30), 0, 0));
            edit.add(validate, new GridBagConstraints(0, 2, 1, 1, 1.0, 1.0,
                    GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(10, 30, 0, 0), 0, 0));
            edit.add(buttonAuth, new GridBagConstraints(0, 3, 1, 1, 1.0, 1.0,
                    GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(10, 30, 0, 0), 0, 0));
            buttonAuth.setBackground(color4);
            buttonAuth.addActionListener(e -> {
                try {
                    String text = colorRadio.getSelection().getActionCommand();
                    gui.getMain().getjTable().setValueAt(text,row,colomn);
                    labWork.getAuthor().setHairColor((Collection.Color.valueOf(text)));
                    gui.getClient().goCommandFrame("update" + " " + id, labWork);
                } catch (NullPointerException ex) {
                    validate.setText("<html>" + gui.getBundle().getString("colex") + "</html>");
                }
            });
            edit.setVisible(true);
        }else if (colomn == 12 ){
            edit.setSize(440, 190);
            ButtonGroup nationalityField = new ButtonGroup();
            JRadioButton n1 = new JRadioButton("GERMANY");
            JRadioButton n2 = new JRadioButton("SPAIN");
            JRadioButton n3 = new JRadioButton("NORTH_KOREA");
            JRadioButton n4 = new JRadioButton("ITALY");
            JRadioButton n5 = new JRadioButton("VATICAN");
            n1.setActionCommand("GERMANY");
            n2.setActionCommand("SPAIN");
            n3.setActionCommand("NORTH_KOREA");
            n4.setActionCommand("ITALY");
            n5.setActionCommand("VATICAN");
            n1.setFont(fnt);
            n2.setFont(fnt);
            n3.setFont(fnt);
            n4.setFont(fnt);
            n5.setFont(fnt);
            n1.setBackground(color3);
            n2.setBackground(color3);
            n3.setBackground(color3);
            n4.setBackground(color3);
            n5.setBackground(color3);
            nationalityField.add(n1);
            nationalityField.add(n2);
            nationalityField.add(n3);
            nationalityField.add(n4);
            nationalityField.add(n5);
            JPanel n = new JPanel();
            n.setLayout(new GridLayout(2, 1, 5, 5));
            n.add(n1);
            n.add(n2);
            n.add(n3);
            n.add(n4);
            n.add(n5);
            n.setBackground(color3);
            edit.add(n);
            JButton buttonAuth = new JButton(gui.getBundle().getString("submit2"));
            edit.add(new JLabel(arg1), new GridBagConstraints(0, 0, 1, 1, 1.0, 0.1,
                    GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(10, 30, 0, 0), 0, 0));
            edit.add(n, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0,
                    GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(10, 30, 0, 30), 0, 0));
            edit.add(validate, new GridBagConstraints(0, 2, 1, 1, 1.0, 1.0,
                    GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(10, 30, 0, 0), 0, 0));
            edit.add(buttonAuth, new GridBagConstraints(0, 3, 1, 1, 1.0, 1.0,
                    GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(10, 30, 0, 0), 0, 0));
            buttonAuth.setBackground(color4);
            buttonAuth.addActionListener(e -> {
                try {
                    String text = nationalityField.getSelection().getActionCommand();
                    gui.getMain().getjTable().setValueAt(text,row,colomn);
                    labWork.getAuthor().setNationality(Country.valueOf(text));
                    gui.getClient().goCommandFrame("update" + " " + id, labWork);
                } catch (NullPointerException ex) {
                    validate.setText("<html>" + gui.getBundle().getString("countryex") + "</html>");
                }
            });
            edit.setVisible(true);
        }else {
            JTextField arg2 = new JTextField();
            JButton buttonAuth = new JButton(gui.getBundle().getString("submit2"));
            buttonAuth.setBackground(color4);
            buttonAuth.setFont(fnt);

            edit.add(new JLabel(arg1), new GridBagConstraints(0, 0, 1, 1, 1.0, 0.1,
                    GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(10, 30, 0, 0), 0, 0));
            edit.add(arg2, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0,
                    GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(10, 30, 0, 30), 0, 0));
            edit.add(validate, new GridBagConstraints(0, 2, 1, 1, 1.0, 1.0,
                    GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(10, 30, 0, 0), 0, 0));
            edit.add(buttonAuth, new GridBagConstraints(0, 3, 1, 1, 1.0, 1.0,
                    GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(10, 30, 0, 0), 0, 0));
            buttonAuth.addActionListener(e -> {
                try {
                    String result = "";
                    if (colomn == 1){
                        result =  checker.chekName(arg2.getText());
                    }else if (colomn == 3){
                        result =  checker.chekX(arg2.getText());
                    }else if (colomn == 4){
                        result = checker.chekY(arg2.getText());
                    } else if (colomn == 6){
                        result = checker.checkMinimalPoint(arg2.getText());
                    }else if (colomn == 8){
                        result = checker.checkAuName(arg2.getText());
                    }else if (colomn == 9){
                        result = checker.checkWeight(arg2.getText());
                    }else if (colomn == 10){
                        result = checker.checkPassportId(arg2.getText());
                    }
                    if (result.equals("")) {
                        edit.dispose();
                        text = arg2.getText();
                        gui.getMain().getjTable().setValueAt(text,row,colomn);
                        if (colomn == 1){
                            labWork.setName(text);
                        }else if (colomn == 3){
                            labWork.getCoordinates().setX(Long.parseLong(text));
                        }else if (colomn == 4) {
                            labWork.getCoordinates().setY(Long.parseLong(text));
                        }else if (colomn == 6){
                            labWork.setMinimalPoint(Double.parseDouble(text));
                        }else if (colomn == 8){
                            labWork.getAuthor().setName(text);
                        }else if (colomn == 9){
                            labWork.getAuthor().setWeight(Double.parseDouble(text));
                        }else if (colomn == 10){
                            labWork.getAuthor().setPassportID(text);
                        }
                        String answer = gui.getClient().goCommandFrame("update" + " " + id, labWork);
                        validate.setText("<html>" + gui.getBundle().getString(answer) + "</html>");
                    } else {
                        validate.setText("<html>" + gui.getBundle().getString(result) + "</html>");
                        text = "";
                    }
                } catch (NumberFormatException ex) {
                    validate.setText(gui.getBundle().getString(""));
                }
            });
        }
        edit.setVisible(true);
        return text;
    }
}