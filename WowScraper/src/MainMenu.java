import com.sun.tools.javac.Main;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {


    private JPanel mainMenuPanel;
    private JButton ladderButton;

    public MainMenu() {

        setContentPane(mainMenuPanel);
        setTitle("Arena Buddy");
        setSize(600, 450);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        ladderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                MainFrame ladderFrame = new MainFrame();

            }
        });

    }

    public static void main(String[] args){

        // create main frame

        MainMenu frame = new MainMenu();
    }




}
