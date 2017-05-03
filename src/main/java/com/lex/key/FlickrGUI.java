package com.lex.key;

/**
 * Created by alexishennings on 5/3/17.
 */




import com.lex.FlickrIMG;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

    /**
     * Created by admin on 4/27/17.
     */
    public class FlickrGUI extends JFrame implements FlickrIMG.FlickrListener{


        private JPanel mainPanel;
        private JLabel imageLabel;
        private JTextField searchText;
        private JButton searchButton;

        public FlickrGUI() {
            setContentPane(mainPanel);

            searchButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String query = searchText.getText();
                    if (query.isEmpty()) {
                        JOptionPane.showMessageDialog(FlickrGUI.this, "Enter search text");
                        return;
                    }
                    imageLabel.setText("Searching...");
                    new FlickrIMG().get320ImageURL(query, FlickrGUI.this);
                }
            });

            getRootPane().setDefaultButton(searchButton);

            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            setPreferredSize(new Dimension(400,400));
            pack();
            setVisible(true);
        }

        @Override
        public void imageSaved(String filename) {

            // Update imageLabel with an image fetched from the URL

            System.out.println("fetched " + filename);

            if (filename == null) {
                imageLabel.setText("Sorry, no images found");
                return;
            }

            ImageIcon icon = new ImageIcon(filename);   // This fails silently if file doesn't exist. No image shown.
            imageLabel.setText("");
            icon.getImage().flush();   // Any previous image is cached, so need to get rid of if
            imageLabel.setIcon(icon);
            pack();
        }
    }