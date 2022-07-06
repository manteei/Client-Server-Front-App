package Grafic;

import Collection.LabWork;
import User.CommandLine;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Scanner;

public class MainGui implements Runnable{
        private GUI gui;
        private DefaultTableModel tableModel = new DefaultTableModel();
        private JTable jTable = new JTable(tableModel);
        private RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tableModel);
        private JFrame mainFrame = new JFrame("Labworks");
        private JLabel user = new JLabel("");

        public MainGui(GUI gui) {
            this.gui = gui;
        }

        public DefaultTableModel getTableModel() {
            return tableModel;
        }

        public JFrame getMainFrame() {
            return mainFrame;
        }

        public JLabel getUser() {
            return user;
        }

    public JTable getjTable() {
        return jTable;
    }

    public void createMainFrame() {
        Font fnt = new Font("шрифт", Font.PLAIN, 16);
        Font fnt1 = new Font("шрифт", Font.PLAIN, 12);
        Color col1 =new Color(248, 249, 250);
        Color col2 =new Color(173, 186, 241);
        Color col3 = new Color(108, 136, 226);
        Color col4 = new Color(255, 212, 91);
        mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        mainFrame.setSize(1900, 700);
        mainFrame.setLayout(new BorderLayout());
        mainFrame.getContentPane().setBackground(col2);
        JPanel headerBag = new JPanel();
        headerBag.setLayout(new GridBagLayout());
        headerBag.setBackground(col2);
        JPanel upperHeader = new JPanel();
        upperHeader.setLayout(new GridBagLayout());
        upperHeader.setBackground(col2);
        JButton exit = new JButton(gui.getBundle().getString("exit"));
        exit.setBackground(col1);
        JComboBox<String> languages = new JComboBox<>(new String[]{"Русский", "Suomalainen", "Украiнський", "Espanol (Nicaragua)"});
        user.setText(gui.getAuthorization().getLogin());

        upperHeader.add(languages, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0,
                GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        upperHeader.add(user, new GridBagConstraints(1, 0, 1, 1, 1.0, 1.0,
                GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(4, 0, 0, 0), 0, 0));
        upperHeader.add(exit, new GridBagConstraints(2, 0, 1, 1, 0.09, 1.0,
                GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        JPanel header = new JPanel();
        header.setLayout(new GridLayout(2, 0, 5, 5));
        JPanel lowerHeader = new JPanel();
        lowerHeader.setLayout(new GridLayout(2, 0, 5, 5));
        lowerHeader.setBackground(col2);
        String[] commands = {gui.getBundle().getString("help"), gui.getBundle().getString("info"), gui.getBundle().getString("show"), gui.getBundle().getString("add"),
                gui.getBundle().getString("update"), gui.getBundle().getString("remove_by_id"), gui.getBundle().getString("clear"),
                gui.getBundle().getString("add_if_max"), gui.getBundle().getString("add_if_min"), gui.getBundle().getString("remove_lower"),
                gui.getBundle().getString("min_by_minimal_point"), gui.getBundle().getString("filter_by_minimal_point"), gui.getBundle().getString("filter_greater_than_minimal_point"), gui.getBundle().getString("executescript")};
        String[] commandsAction = {"help", "info", "show", "add", "update", "remove_by_id", "clear", "add_if_max", "add_if_min", "remove_lower", "min_by_minimal_point",
                "filter_by_minimal_point", "filter_greater_than_minimal_point", "executescript"};

        for (int i = 0; i < commands.length; i++) {
            JButton button = new JButton(commands[i]);
            button.setBackground(col4);
            button.setActionCommand(commandsAction[i]);
            button.setFont(fnt1);
            lowerHeader.add(button);
            button.addActionListener(e -> {
                try {
                    switch (button.getActionCommand()) {
                        case "help":
                            gui.getResult().setText(gui.getBundle().getString("helpC"));
                            break;
                        case "info":
                            gui.getResult().setText(gui.getBundle().getString("info1")+gui.getClient().goCommand(button.getActionCommand()));
                            break;
                        case "clear":
                            gui.getClient().goCommand(button.getActionCommand());
                            gui.getResult().setText(gui.getBundle().getString("clearR"));
                            break;
                        case "show":
                        case "min_by_minimal_point":
                            gui.getResult().setText(gui.getClient().goCommand(button.getActionCommand()));
                            break;
                        case "add_if_max":
                        case "add_if_min":
                        case "add":
                        case "update":
                        case "remove_lower":
                            gui.getAddCommandFrame().createAddFrame(button.getActionCommand());
                            break;
                        case "remove_by_id":
                            gui.getRemoveCommandFrame().createRemoveFrame(button.getActionCommand());
                            break;
                        case "filter_by_minimal_point":
                        case "filter_greater_than_minimal_point":
                            gui.getFilterFrame().createFilterFrame(button.getActionCommand());
                            break;
                        case "executescript":
                            String user = gui.getAuthorization().getLogin();
                            try {
                                JFileChooser file = new JFileChooser();
                                JButton open = new JButton();
                                file.setFileSelectionMode(JFileChooser.FILES_ONLY);
                                if (file.showOpenDialog(open) == JFileChooser.APPROVE_OPTION) {
                                }
                                String answer = (gui.getClient().goScriptCommand(button.getActionCommand() + " " + file.getSelectedFile().getAbsolutePath(), user));
                                String ans[] = answer.split("@");
                                String result = "";
                                for (String key: ans){
                                    key = key.trim();
                                    if (gui.getBundle().containsKey(key)){
                                        result += gui.getBundle().getString(key) + "\n";
                                    }else {
                                        result += key + "\n";
                                    }
                                }
                                gui.getResult().setText(result);
                            } catch (NullPointerException ex) {
                                // Исключение не мешает логике исполнения программы
                            }
                            break;
                    }
                } catch (IndexOutOfBoundsException ex) {
                }
            });

        }
        header.add(upperHeader);
        header.add(lowerHeader);
        headerBag.add(header, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));
        mainFrame.add(headerBag, BorderLayout.NORTH);
        mainFrame.setVisible(true);
        String[] tableCol = new String[]{gui.getBundle().getString("username"), gui.getBundle().getString("name"), "id", "x", "y", gui.getBundle().getString("creationDate"), "Минимальный балл",
                gui.getBundle().getString("difficulty"), gui.getBundle().getString("authorname"), "Вес", gui.getBundle().getString("passportid"),
                gui.getBundle().getString("haircolor"), gui.getBundle().getString("nationality")};
        for (String s : tableCol) {
            tableModel.addColumn(s);
        }
        JPanel table = new JPanel();
        table.setLayout(new GridBagLayout());
        table.setFont(fnt);
        JTabbedPane jTabbedPane = new JTabbedPane();
        jTabbedPane.addTab(gui.getBundle().getString("table"), new JScrollPane(jTable));
        jTabbedPane.addTab(gui.getBundle().getString("visualisation"), new JScrollPane(gui.getDrawer()));
        jTable.setRowSorter(sorter);
        JPanel textArea = new JPanel();
        textArea.setLayout(new GridBagLayout());
        gui.getResult().setWrapStyleWord(true);
        gui.getResult().setEditable(false);

        table.add(jTabbedPane, new GridBagConstraints(1, 0, 1, 1, 1.0, 1.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 10, 10, 5), 0, 0));
        textArea.add(new JScrollPane(gui.getResult()), new GridBagConstraints(1, 0, 1, 2, 1.0, 0.97,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(20, 10, 0, 5), 0, 0));
        table.add(textArea, new GridBagConstraints(0, 0, 1, 1, 0.5, 1.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 5, 10, 10), 0, 0));
        table.setBackground(col2);
        table.setForeground(col1);

        textArea.setBackground(col2);
        mainFrame.add(table);
        mainFrame.setLocationRelativeTo(null);
        jTable.setEnabled(false);
        jTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked ( MouseEvent e ) {
                int row = jTable.rowAtPoint(new Point(e.getX(), e.getY()));
                int colomn = jTable.columnAtPoint(new Point(e.getX(), e.getY()));
                String us = String.valueOf(jTable.getValueAt(row, 0));
                if (!us.equals(gui.getAuthorization().getLogin())){
                    gui.getResult().setText(gui.getBundle().getString("editex4"));
                } else {
                    if (colomn == 0) {
                        gui.getResult().setText(gui.getBundle().getString("editex"));
                    } else if (colomn == 2) {
                        gui.getResult().setText(gui.getBundle().getString("editex2"));
                    } else if (colomn == 5) {
                        gui.getResult().setText(gui.getBundle().getString("editex3"));
                    } else {
                        gui.getEditorPane().createEditFrame(row, colomn);
                    }
                }

            }
        });

        exit.addActionListener(e -> {
            mainFrame.setVisible(false);
            gui.getClient().goCommand("exit");
            gui.getResult().setText("");
            System.exit(0);
        });


        languages.addActionListener(e -> {
            gui.choseLanguage(languages);
            exit.setText(gui.getBundle().getString("exit"));
            String[] commandsLanguage = {gui.getBundle().getString("help"), gui.getBundle().getString("info"), gui.getBundle().getString("show"), gui.getBundle().getString("add"),
                    gui.getBundle().getString("update"), gui.getBundle().getString("remove_by_id"), gui.getBundle().getString("clear")
                    , gui.getBundle().getString("add_if_max"), gui.getBundle().getString("add_if_min"), gui.getBundle().getString("remove_lower"),
                    gui.getBundle().getString("min_by_minimal_point"), gui.getBundle().getString("filter_by_minimal_point"), gui.getBundle().getString("filter_greater_than_minimal_point")};
            for (int i = 0; i < commandsLanguage.length; i++) {
                ((JButton) lowerHeader.getComponent(i)).setText(commandsLanguage[i]);
            }
            String[] tableLanguage = new String[]{gui.getBundle().getString("username"), gui.getBundle().getString("name"), "id", "x", "y", gui.getBundle().getString("creationDate"),gui.getBundle().getString("minimal_point"),
                    gui.getBundle().getString("difficulty"), gui.getBundle().getString("authorname"), gui.getBundle().getString("weight"), gui.getBundle().getString("passportid"),
                    gui.getBundle().getString("haircolor"), gui.getBundle().getString("nationality")};
            Enumeration<TableColumn> enumeration = jTable.getColumnModel().getColumns();
            for (int i = 0; i < tableLanguage.length; i++) {
                enumeration.nextElement().setHeaderValue(tableLanguage[i]);
            }
            jTabbedPane.setTitleAt(0, gui.getBundle().getString("table"));
            jTabbedPane.setTitleAt(1, gui.getBundle().getString("visualisation"));
            gui.getResult().setText("");
        });
        mainFrame.setVisible(true);
        Thread thread = new Thread(this);
        thread.setDaemon(true);
        thread.start();
    }

    public void run() {
        try {
                String answer;
                HashMap<String, Visualize> elementsServer = new HashMap<>();
                do {
                    String condition = gui.getClient().goCommand("show");
                    if (!condition.equals("Коллекция пуста!")) {
                        Scanner scanner = new Scanner(condition);
                        elementsServer.clear();
                        do {
                            String elements = scanner.nextLine();
                            String[] arguments = elements.split(", ");
                            tableModel.insertRow(0, arguments);
                            if (!gui.getDrawer().getColors().containsKey(arguments[0])) {
                                float[] rgb = gui.getDrawer().setColor();
                                gui.getDrawer().getColors().put(arguments[0], new Color(rgb[0], rgb[1], rgb[2]));
                            }
                            try {
                                Visualize visualize = new Visualize(arguments[0], arguments[1], arguments[2], arguments[3], arguments[4], arguments[5],
                                        arguments[6], arguments[7], arguments[8], arguments[9], arguments[10], arguments[11], arguments[12]);
                                elementsServer.put(arguments[2], visualize);
                            } catch (IndexOutOfBoundsException e) {

                            }
                        } while (scanner.hasNextLine());
                        gui.getDrawer().udateElement(elementsServer);
                        do {
                            answer = gui.getClient().goCommand("show");
                            Thread.sleep(1000);
                        } while (answer.equals(condition));
                        for (int i = gui.getMain().getTableModel().getRowCount() - 1; i > -1; i--) {
                            gui.getMain().getTableModel().removeRow(i);
                        }
                    } else {
                        elementsServer.clear();
                        gui.getDrawer().udateElement(elementsServer);
                        Thread.sleep(1000);
                    }
                }while (true);
        } catch (InterruptedException ex) {
            // Исключение не мешает логике исполнения программы
        }
    }

}
