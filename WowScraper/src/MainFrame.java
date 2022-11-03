import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Date;

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
                        numPlayers++;
                    }

                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }

                // Create table model for table1
                DefaultTableModel tableModel = new DefaultTableModel();

                // Create array of names to be used for columns
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

                // Create array for adding row to table
                Object rowData[] = new Object[7];

                for (int i=0; i<100; i++) {
                    rowData[0] = players[i].getRank();
                    rowData[1] = players[i].getRating();
                    rowData[2] = players[i].getName();
                    rowData[3] = players[i].getLevel();
                    rowData[4] = players[i].getSpec();
                    rowData[5] = players[i].getWowClass();
                    rowData[6] = players[i].getRealm();
                    tableModel.addRow(rowData);
                }

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

        saveLadderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //String connectionUrl = ""
            }
        } );
    }


    public static void main(String[] args) {

        // create main frame

        MainFrame frame = new MainFrame();
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
