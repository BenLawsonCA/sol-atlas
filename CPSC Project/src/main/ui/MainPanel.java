package ui;

import model.HttpCelestialDataLoader;
import model.Celestial;
import model.CelestialDataLoader;
import persistance.SaveGame;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.JOptionPane.showMessageDialog;

// Main graphical interface for project. Includes all action listeners.
public class MainPanel {

    private JPanel panel1;
    private JButton exitButton;
    private JButton saveButton;
    private JButton searchForCelestialButton;
    private JTextField searchInputField;
    private JList celestialList;
    private JTextArea dataTable;
    private JLabel imageLabel;
    private JButton removeButton;
    private DefaultListModel planetList;
    private List<String> defaultPlanetList;
    private HttpCelestialDataLoader planetaryBodiesList = null;

    public MainPanel() {
        startUp();

        searchForCelestialButton.addActionListener(new SearchForCelestialController());

        celestialList.addListSelectionListener(new CelestialListController());

        exitButton.addActionListener(new ExitButtonController());

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SaveGame.savePlanetList(planetaryBodiesList.getSolarSystem(), "./data/planets.txt");
                showMessageDialog(null, "Your celestial list has been saved!");
            }
        });
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeFromPlanetList((String) planetList.getElementAt(celestialList.getSelectedIndex()));
            }
        });
    }

    //Modifies: this
    //Effects: Runs several essential tasks for program startup
    private void startUp() {
        planetaryBodiesList = new HttpCelestialDataLoader();
        loadPlanetList(planetaryBodiesList);
        displayGif();
        celestialList.setSelectedIndex(0);
        displayData(planetaryBodiesList.getSolarSystem().getSolarSystem().get(0));

    }

    //Effects: Creates the main panel and associations
    protected static void runProgram() {
        JFrame mainPanel = new JFrame("SolAtlas");
        mainPanel.setContentPane(new MainPanel().panel1);
        mainPanel.setMinimumSize(new Dimension(1000, 800)); //"./data/jupiterIcon.png"
        Image barIcon = Toolkit.getDefaultToolkit().getImage("./data/jupiterIcon.png");
        mainPanel.setIconImage(barIcon);
        mainPanel.pack();
        mainPanel.setVisible(true);
        mainPanel.addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
                int confirmed = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to exit?", "Exit Dialog Box",
                        JOptionPane.YES_NO_OPTION);
                if (confirmed == JOptionPane.YES_OPTION) {
                    mainPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                } else if (confirmed == JOptionPane.NO_OPTION) {
                    mainPanel.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });
    }

    //Modifies: this
    //Effects: Displays data in text field for specified Celestial
    private void displayData(Celestial p) {
        dataTable.setText("");
        dataTable.append(p.displayCelestialData());
    }

    //Modifies: this
    //Effects: Adds a list of planets to JList object.
    private void loadPlanetList(CelestialDataLoader planetDL) {
        planetList = new DefaultListModel();
        for (Celestial p : planetDL.getSolarSystem().getSolarSystem()) {
            planetList.addElement(p.getPlanetName());
        }
        celestialList.setModel(planetList);
        selectedValue(planetList.getSize());
    }

    //Modifies: this
    //Effects: Adds a Celestial to planetList
    private void addToPlanetList(Celestial p) {
        planetList.addElement(p.getPlanetName());
        selectedValue(planetList.getSize());
    }

    //Modifies: this
    //Effects: Removes a Celestial with provided name from celestialList
    private void removeFromPlanetList(String p) {
        int currentSelection = celestialList.getSelectedIndex();
        defaultPlanetList = defaultList();
        if (defaultPlanetList.contains(p)) {
            showMessageDialog(null, "Planets cannot be removed from the list!");
        } else {
            selectedValue(currentSelection - 1);
            planetList.removeElement(p);
            planetaryBodiesList.getSolarSystem().removePlanetaryBody(p);
        }

    }


    private void displayGif() {
        ImageIcon image = new ImageIcon("./data/solarSystemGif2.gif");
        imageLabel.setIcon(image);
    }

    private class SearchForCelestialController implements java.awt.event.ActionListener {

        //Modifies: this
        //Effects: Creates a search query for API call. Will inform user of success/fail
        public void actionPerformed(ActionEvent e) {
            String planetaryApiData = "";
            Celestial celestialResult;
            String input;

            input = searchInputField.getText();                //grabs the text from the input field
            input.replaceAll("\\s+", "");     // gets rid of whitespace in input

            planetaryApiData = planetaryBodiesList.getPlanetaryData(input);
            if (planetaryApiData.equals("fail")) {
                dataTable.setText("");            //clears the text box
                showMessageDialog(null, "Unable to find celestial "
                        + "matching provided name. Please try an alternative.");     //Tells user about the failure
            } else {
                planetaryBodiesList.getSolarSystem().addPlanetaryBody(celestialResult =
                        new Celestial(planetaryApiData));
                dataTable.setText("");            //clears the text box
                dataTable.append("Success! We found data for the celestial body "
                        + celestialResult.getPlanetName() + "\n\n");      //Tells user about the failure
                addToPlanetList(celestialResult);
            }
        }
    }

    private class CelestialListController implements ListSelectionListener {

       //Effects: Checks what selection is made in JList and displays proper celestial data
        public void valueChanged(ListSelectionEvent e) {
            String currentSelection = celestialList.getSelectedValue().toString();
            for (Celestial p : planetaryBodiesList.getSolarSystem().getSolarSystem()) {
                if (p.getPlanetName() == currentSelection) {
                    displayData(p);
                }
            }
        }

    }

    //Effects: Checks is the celestialList is longer than a provided value
    private void selectedValue(int value) {
        if (value > 8) {
            celestialList.setSelectedIndex(value - 1);
        } else {
            celestialList.setSelectedIndex(0);
        }
    }

    //Modifies: this
    //Effects: Creates a default list of planets to be loaded
    private List<String> defaultList() {
        defaultPlanetList = new ArrayList<>();
        defaultPlanetList.add("Mercury");
        defaultPlanetList.add("Venus");
        defaultPlanetList.add("Earth");
        defaultPlanetList.add("Mars");
        defaultPlanetList.add("Jupiter");
        defaultPlanetList.add("Saturn");
        defaultPlanetList.add("Uranus");
        defaultPlanetList.add("Neptune");
        return defaultPlanetList;
    }


    private class ExitButtonController implements ActionListener {
        //Effects: Checks for user input to ask if they want to save their data before exiting.
        public void actionPerformed(ActionEvent e) {
            int confirmed = JOptionPane.showConfirmDialog(null,
                    "Would you like to save before exiting?", "Exit Dialog Box",
                    JOptionPane.YES_NO_OPTION);
            if (confirmed == JOptionPane.YES_OPTION) {
                SaveGame.savePlanetList(planetaryBodiesList.getSolarSystem(), "./data/planets.txt");
                System.exit(0);
            } else {
                SaveGame.loadDefault("./data/planets.txt");
                System.exit(0);
            }
        }
    }
}
