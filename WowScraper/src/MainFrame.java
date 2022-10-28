import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.swing.*;

import static java.lang.Integer.parseInt;

public class MainFrame extends JFrame {


    public MainFrame() {
        setSize(600,450);
        setVisible(true);
    }


    public static void main(String[] args) {

        // create main frame
        MainFrame frame = new MainFrame();


        final String url = "https://worldofwarcraft.com/en-us/game/pvp/leaderboards/3v3";

        Player[] players = new Player[100];
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



    }


    // create new player object and return
    static Player makePlayer(String rowString) {
        String[] split = rowString.split(" ");
        return new Player(parseInt(split[0]), parseInt(split[1]), split[2], parseInt(split[3]), split[4], split[5], split[6]);
    }


    static int averageRating(Player[] players) {

        int mean = 0;

        for (int i=0; i<players.length; i++) {
            mean+= players[i].getRating();
        }

        mean /= players.length;
        return mean;
    }
}
