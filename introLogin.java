import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javax.swing.*;


import java.awt.event.*;

public class introLogin {
	SecondLogin sl = new SecondLogin();
	public void function() {
	JFrame f = new JFrame();

    try {
        f.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("logowanie.jpg")))));
    } catch (IOException e) {
        e.printStackTrace();
    }
    f.setSize(1800, 1049);
    f.setResizable(false);

    JButton klient = new JButton("");
    klient.setBounds(567, 645, 265, 93);
    klient.setOpaque(false);
    klient.setContentAreaFilled(false);
    klient.setBorderPainted(true);
    JButton pracownik = new JButton("");
    pracownik.setBounds(567+363, 645+1, 278, 90);
    pracownik.setOpaque(false);
    pracownik.setContentAreaFilled(false);
    pracownik.setBorderPainted(true);
    f.add(klient);
    f.add(pracownik);
    klient.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        	sl.run(1);
        	f.setVisible(false); //you can't see me!
        	f.dispose(); //Destroy the JFrame object
        	return;
        }
    });
    pracownik.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        	sl.run(2);
        	f.setVisible(false); //you can't see me!
        	f.dispose(); //Destroy the JFrame object
        	return;
        }
    });
    
    
    f.pack();
    f.setVisible(true);

}
}
