import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class tagPuller extends JFrame {

        public tagPuller(String s) {

            setTitle("View Document Tags");
            setSize(700, 600);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(null);

            // fonts
            Font fonty_mcfonterson = new Font("Times New Roman", Font.BOLD, 56);
            Font fonty_jr = new Font("Times New Roman", Font.PLAIN, 18);
            Font fonty_III = new Font("Times New Roman", Font.PLAIN, 14);

            // Title Label
            JLabel jabel = new JLabel("View Document Tags", JLabel.CENTER);
            jabel.setBounds(40, 0, 600, 200);
            jabel.setFont(fonty_mcfonterson);

            // Text Area and Scroll Pane for current roll
            JTextArea jerria = new JTextArea();
            jerria.setFont(fonty_III);
            JScrollPane jane = new JScrollPane(jerria);
            jane.setBounds(135, 175, 400, 200);

            JButton quit_butt = new JButton("Quit");
            quit_butt.setBounds(500, 415, 100, 100);
            quit_butt.setFont(fonty_jr);
            quit_butt.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });

            JButton openFile = new JButton("Search File");
            openFile.setBounds(50, 415, 100, 100);
            openFile.setFont(fonty_jr);
            openFile.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    File workingDirectory = new File(System.getProperty("user.dir"));
                    File jfcFile = new File(workingDirectory.getPath() + "\\src\\PersonTestData.txt");
                    JFileChooser jfc = new JFileChooser(jfcFile, FileSystemView.getFileSystemView());
                    jfc.showOpenDialog(null);

                    String file_contents = null;

                    try {
                        file_contents = new String(Files.readAllBytes(Paths.get(jfc.getSelectedFile().getAbsolutePath())));
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                    String[] content = file_contents.split("\\b" + jerria.getText() + "\\b");
                    int c2 = content.length-1;
                    jerria.setText("Found "+ c2 +" times.");
                }
            });

            add(jabel);
            add(jane);
            add(openFile);
            add(quit_butt);

            setVisible(true);

        }}
