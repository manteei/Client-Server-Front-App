package Grafic;

import Collection.*;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

import Collection.Color;
import Reader.Checker;
import User.CommandLine;

public class EditAllFrame {
    private GUI gui;
    private JLabel validate = new JLabel();
    private CommandLine commandLine = new CommandLine();
    private Checker checker = new Checker(commandLine);
    private String result;

    public EditAllFrame(GUI gui) {
        this.gui = gui;
    }


    /**
     * Метод создает фрейм для ввода данных
     *
     * @param labWork
     */
    public void createEditAllFrame(LabWork labWork) {
        Font fnt = new Font("шрифт", Font.PLAIN, 16);
        Font fnt1 = new Font("шрифт", Font.PLAIN, 12);
        java.awt.Color color1 = new java.awt.Color(248, 249, 250);
        java.awt.Color color3 = new java.awt.Color(173, 186, 241);
        java.awt.Color color2 = new java.awt.Color(108, 136, 226);
        java.awt.Color color4 = new java.awt.Color(255, 212, 91);
        validate.setText("");
        int count = 0;
        JTextField idFiled = new JTextField();
        JFrame addFrame = new JFrame(gui.getBundle().getString("window2"));
        addFrame.getContentPane().setBackground(color3);
        addFrame.setLayout(new GridBagLayout());
        addFrame.setSize(700, 700);
        addFrame.setDefaultCloseOperation(addFrame.DISPOSE_ON_CLOSE);
        addFrame.setResizable(false);
        addFrame.setLocationRelativeTo(null);
        JTextField nameFiled = new JTextField(labWork.getName());
        JLabel resultName = new JLabel();
        JTextField xFiled = new JTextField(String.valueOf(labWork.getCoordinates().getX()));
        JTextField yFiled = new JTextField(String.valueOf(labWork.getCoordinates().getY()));
        JTextField minimalPointField = new JTextField(String.valueOf(labWork.getMinimalPoint()));
        ButtonGroup difficultyRadio = new ButtonGroup();
        JRadioButton dif1 = new JRadioButton("EASY");
        JRadioButton dif2 = new JRadioButton("HARD");
        JRadioButton dif3 = new JRadioButton("IMPOSSIBLE");
        JRadioButton dif4 = new JRadioButton("HOPELESS");
        dif1.setActionCommand("EASY");
        dif2.setActionCommand("HARD");
        dif3.setActionCommand("IMPOSSIBLE");
        dif4.setActionCommand("HOPELESS");
        dif1.setFont(fnt1);
        dif2.setFont(fnt1);
        dif3.setFont(fnt1);
        dif4.setFont(fnt1);
        dif1.setBackground(color3);
        dif2.setBackground(color3);
        dif3.setBackground(color3);
        dif4.setBackground(color3);
        difficultyRadio.add(dif1);
        difficultyRadio.add(dif2);
        difficultyRadio.add(dif3);
        difficultyRadio.add(dif4);
        JPanel dif = new JPanel();
        dif.setLayout(new GridLayout(4, 1, 5, 5));
        dif.add(dif1);
        dif.add(dif2);
        dif.add(dif3);
        dif.add(dif4);
        dif.setBackground(color3);
        String d = String.valueOf(labWork.getDifficulty());
        if (d.equals("EASY")) {
            dif1.setSelected(true);
        } else if (d.equals("HARD")){
            dif2.setSelected(true);
        } else if (d.equals("IMPOSSIBLE")){
            dif3.setSelected(true);
        } else if (d.equals("HOPELESS")){
            dif4.setSelected(true);
        }
        JTextField authorNameFiled = new JTextField(labWork.getAuthor().getName());
        JTextField weightFiled = new JTextField(String.valueOf(labWork.getAuthor().getWeight()));
        JTextField passportIDFiled = new JTextField(String.valueOf(labWork.getAuthor().getPassportID()));
        ButtonGroup colorRadio = new ButtonGroup();
        JRadioButton col1 = new JRadioButton("GREEN");
        JRadioButton col2 = new JRadioButton("YELLOW");
        JRadioButton col3 = new JRadioButton("WHITE");
        JRadioButton col4 = new JRadioButton("BROWN");
        col1.setActionCommand("GREEN");
        col2.setActionCommand("YELLOW");
        col3.setActionCommand("WHITE");
        col4.setActionCommand("BROWN");
        col1.setFont(fnt1);
        col2.setFont(fnt1);
        col3.setFont(fnt1);
        col4.setFont(fnt1);
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
        String c = String.valueOf(labWork.getAuthor().getHairColor());
        if (c.equals("GREEN")) {
            col1.setSelected(true);
        } else if (c.equals("YELLOW")){
            col2.setSelected(true);
        } else if (c.equals("WHITE")){
            col3.setSelected(true);
        } else if (c.equals("BROWN")){
            col4.setSelected(true);
        }
        ButtonGroup nationalityField = new ButtonGroup();
        JRadioButton n1 = new JRadioButton("GERMANY");
        JRadioButton n2 = new JRadioButton("SPAIN");
        JRadioButton n3 = new JRadioButton("VATICAN");
        JRadioButton n4 = new JRadioButton("ITALY");
        JRadioButton n5 = new JRadioButton("NORTH_KOREA");
        n1.setActionCommand("GERMANY");
        n2.setActionCommand("SPAIN");
        n3.setActionCommand("VATICAN");
        n4.setActionCommand("ITALY");
        n5.setActionCommand("NORTH_KOREA");
        n1.setFont(fnt1);
        n2.setFont(fnt1);
        n3.setFont(fnt1);
        n4.setFont(fnt1);
        n5.setFont(fnt1);
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
        String nat = String.valueOf(labWork.getAuthor().getNationality());
        if (nat.equals("GERMANY")) {
            n1.setSelected(true);
        } else if (nat.equals("SPAIN")){
            n2.setSelected(true);
        } else if (nat.equals("VATICAN")){
            n3.setSelected(true);
        } else if (nat.equals("ITALY")){
            n4.setSelected(true);
        }else if (nat.equals("NORTH_KOREA")){
            n5.setSelected(true);
        }
        JButton buttonAuth = new JButton(gui.getBundle().getString("submit4"));
        buttonAuth.setBackground(color4);
        createComponentForAdd(addFrame, gui.getBundle().getString("name"), nameFiled, count++);
        createComponentForAdd(addFrame, "x: ", xFiled, count++);
        createComponentForAdd(addFrame, "y: ", yFiled, count++);
        createComponentForAdd(addFrame, gui.getBundle().getString("minimalPoint"), minimalPointField, count++);
        createComponentForAdd(addFrame, gui.getBundle().getString("difficulty"), dif, count++);
        createComponentForAdd(addFrame, gui.getBundle().getString("authorName"), authorNameFiled, count++);
        createComponentForAdd(addFrame, gui.getBundle().getString("weight"), weightFiled, count++);
        createComponentForAdd(addFrame, gui.getBundle().getString("passportID"), passportIDFiled, count++);
        createComponentForAdd(addFrame, gui.getBundle().getString("hairColor"), col, count++);
        createComponentForAdd(addFrame, gui.getBundle().getString("nationality"), n, count++);
        createComponentForAdd(addFrame, "", validate, count++);
        addFrame.add(buttonAuth, new GridBagConstraints(0, count++, 1, 1, 1.0, 1.0,
                GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 50, 20, 0), 0, 0));


        buttonAuth.addActionListener(e -> {
            String difficulty;
            String color;
            String country;
            try {
                difficulty = difficultyRadio.getSelection().getActionCommand();
            } catch (NullPointerException ex) {
                difficulty = null;
            }
            try {
                color = colorRadio.getSelection().getActionCommand();
            } catch (NullPointerException ex) {
                color = null;
            }
            try {
                country = nationalityField.getSelection().getActionCommand();
            } catch (NullPointerException ex) {
                country = null;
            }
            String name = nameFiled.getText();
            String x = xFiled.getText();
            String y = yFiled.getText();
            String minimalPoint = minimalPointField.getText();
            String difficultyF = difficulty;
            String auName =  authorNameFiled.getText();
            String weight = weightFiled.getText();
            String passportId = passportIDFiled.getText();
            String colorF = color;
            String countryF = country;

            String nameA = checker.chekName(name);
            String xA = checker.chekX(x);
            String yA = checker.chekX(y);
            String minA = checker.checkMinimalPoint(minimalPoint);
            String diffA = checker.checkDifficulty(difficultyF);
            String auNameA = checker.checkAuName(auName);
            String weightA = checker.checkWeight(weight);
            String passportA = checker.checkPassportId(passportId);
            String colorA = checker.checkColor(colorF);
            String countryA = checker.checkNationality(countryF);
            if (nameA.equals("") && xA.equals("") && yA.equals("") && minA.equals("") && diffA.equals("") && auNameA.equals("") && weightA.equals("") && passportA.equals("") && colorA.equals("") && countryA.equals("")) {
                Coordinates coordinates = new Coordinates(Long.valueOf(x), Long.valueOf(y));
                Person person = new Person(auName, Double.parseDouble(weight), passportId, Color.valueOf(colorF), Country.valueOf(countryF));
                LabWork labWork1 = new LabWork(name, coordinates, LocalDate.now(), Double.parseDouble(minimalPoint), Difficulty.valueOf(difficultyF), person, labWork.getUsername());
                String res = gui.getClient().goCommandFrame(("update " + labWork.getId()), labWork1);
                result = gui.getBundle().getString(res);
            }else {
                if (!nameA.equals("")){
                    result += gui.getBundle().getString(nameA);
                }
                if (!xA.equals("")){
                    result += gui.getBundle().getString(xA);
                }
                if (!yA.equals("")){
                    result += gui.getBundle().getString(yA);
                }
                if (!minA.equals("")){
                    result += gui.getBundle().getString(minA);
                }
                if (!diffA.equals("")){
                    result += gui.getBundle().getString(diffA);
                }
                if (!auNameA.equals("")){
                    result += gui.getBundle().getString(auNameA);
                }
                if (!weightA.equals("")){
                    result += gui.getBundle().getString(weightA);
                }
                if (!passportA.equals("")){
                    result += gui.getBundle().getString(passportA);
                }
                if (!colorA.equals("")){
                    result += gui.getBundle().getString(colorA);
                }
                if (!countryA.equals("")){
                    result += gui.getBundle().getString(countryA);
                }
            }
            gui.getResult().setText(result + "\n");
            addFrame.setVisible(false);
        });

        addFrame.setVisible(true);
    }

    /**
     * Метод добавляет элементы на фрейм
     *
     * @param addFrame
     * @param arg1
     * @param arg2
     * @param position
     */
    private void createComponentForAdd(JFrame addFrame, String arg1, JComponent arg2, int position) {
        addFrame.add(new JLabel(arg1), new GridBagConstraints(0, position, 1, 1, 1.0, 1.0,
                GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 40, 0, 0), 0, 0));
        addFrame.add(arg2, new GridBagConstraints(1, position, 1, 1, 1.0, 1.0,
                GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 10, 0, 70), 0, 0));
    }
}
