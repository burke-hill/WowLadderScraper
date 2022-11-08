import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Vector;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Date;
import java.util.Properties;

import static java.lang.Integer.parseInt;

public class MainFrame extends JFrame {


    private JPanel mainPanel;
    private JTable table1;
    private JButton loadButton;
    private JLabel testLabel;
    private JButton mainMenuButton;
    private JButton saveLadderButton;
    private JLabel TestLabel;
    private JButton avgButton;
    public static Player[] players = new Player[100];

    public MainFrame() {
        setContentPane(mainPanel);
        setTitle("Arena Buddy");
        setSize(600,450);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);


        /*avgButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TestLabel.setText(String.valueOf(averageRating(players)));
            }
        });*/

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                final String url = "https://worldofwarcraft.com/en-us/game/pvp/leaderboards/3v3";

                String[] names = new String[100];
                DefaultTableModel tableModel = new DefaultTableModel();
                Object rowData[] = new Object[7];
                String[] columnNames = new String[7];

                columnNames[0] = "Rank";
                columnNames[1] = "Rating";
                columnNames[2] = "Name";
                columnNames[3] = "Level";
                columnNames[4] = "Spec";
                columnNames[5] = "Class";
                columnNames[6] = "Realm";


                // Add columns
                for (int i=0; i<7; i++) {
                    tableModel.addColumn(columnNames[i]);
                }

                int numPlayers = 0;
                try {
                    // get raw html document
                    final Document document = Jsoup.connect(url).get();
                    // int to skip random data I dont want
                    int counter = 0;
                    for (Element row : document.select(

                            // get necessary data from wow arena ladder
                            "div.SortTable-body " +
                                    "div.SortTable-row ")) {
                        // skip random data I dont want
                        if (counter < 4) {
                            counter ++;
                            continue;
                        }
                        // parse function test
                        /*String[] test = getName(row.text());
                        names[numNames] = test[2];
                        numNames++;*/

                        //make new player in players array
                        players[numPlayers] = makePlayer(row.text());
                        rowData[0] = players[numPlayers].getRank();
                        rowData[1] = players[numPlayers].getRating();
                        rowData[2] = players[numPlayers].getName();
                        rowData[3] = players[numPlayers].getLevel();
                        rowData[4] = players[numPlayers].getSpec();
                        rowData[5] = players[numPlayers].getWowClass();
                        rowData[6] = players[numPlayers].getRealm();
                        tableModel.addRow(rowData);
                        numPlayers++;
                    }
                    // testing branch hehe

                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }

                // Create table model for table1


                // Create array of names to be used for columnn

                // Create array for adding row to table



                testLabel.setText("Data Loaded");
                table1.setModel(tableModel);
            }
        });

        mainMenuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                MainMenu frame = new MainMenu();
            }
        } );


    }




    // create new player object and return
    static Player makePlayer(String rowString) {
        String[] split = rowString.split(" ");
        return new Player(parseInt(split[0]), parseInt(split[1]), split[2], parseInt(split[3]), split[4], split[5], split[6]);
    }




    public int averageRating(Player[] players) {

        int mean = 0;

        for (int i=0; i<players.length; i++) {
            mean+= players[i].getRating();
        }

        mean /= players.length;
        return mean;
    }
}
