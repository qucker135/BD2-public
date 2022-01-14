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

public class MenuKlient {
	
    ImageIcon bBG = new ImageIcon("buttonBG.png");
	
    static JButton katalog;
    static JButton koszyk;
    static JButton historia;
    static JButton wyloguj;
    static JButton kontakt;
    
    static JButton barMenu;
    static JButton barKatalog;
    static JButton barKoszyk;
    static JButton barWyloguj;
    static JButton barKontakt;

    Menu menu;
    ListaKlient lista;
    //koszyk koszykObj = new koszyk(); //koszyk jest trzymany na ca�� sesj�
    koszyk koszykObj = null;

    
	public void function(String PESEL, koszyk maybeObject) {
		JFrame fMK = new JFrame();

	    try {
	    	
	        fMK.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("menuV2.jpg")))));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		fMK.setSize(751, 650);
		fMK.setResizable(false);
	    
	    if(null == maybeObject) {
	        koszykObj = new koszyk(); //koszyk jest trzymany na cala sesje
	    }else {
	    	koszykObj = maybeObject;
	    }
	    
	    
	    barMenu = new JButton("m");
	    barKatalog = new JButton("ka");
	    barKoszyk = new JButton("ko");
	    barWyloguj = new JButton("wy");
	    barKontakt = new JButton("kon");
	    
	    barMenu.setBounds(860, 35, 40, 10);
	    barKatalog.setBounds(935, 35, 40, 10);
	    barKoszyk.setBounds(990, 35, 40, 10);
	    barWyloguj.setBounds(1050, 35, 50, 10);
	    barKontakt.setBounds(1100, 35, 70, 10);

	    barMenu.setOpaque(false);
	    barMenu.setContentAreaFilled(false);
	    barMenu.setBorderPainted(false);
	    barKatalog.setOpaque(false);
	    barKatalog.setContentAreaFilled(false);
	    barKatalog.setBorderPainted(false);
	    barKoszyk.setOpaque(false);
	    barKoszyk.setContentAreaFilled(false);
	    barKoszyk.setBorderPainted(false);
	    barWyloguj.setOpaque(false);
	    barWyloguj.setContentAreaFilled(false);
	    barWyloguj.setBorderPainted(false);
	    barKontakt.setOpaque(false);
	    barKontakt.setContentAreaFilled(false);
	    barKontakt.setBorderPainted(false);
	    
	    
	    barKatalog.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
        		menu = new Menu();
        		menu.function(PESEL, koszykObj);
        		fMK.setVisible(false); //you can't see me!
        		fMK.dispose(); //Destroy the JFrame object
        		return;

	        }
	    });
	    barKoszyk.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	
	        	koszykObj.function(PESEL, koszykObj);
        		fMK.setVisible(false); //you can't see me!
        		fMK.dispose(); //Destroy the JFrame object
        		return;

	        }
	    });
	    barWyloguj.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            introLogin login = new introLogin();
	            login.function();
	            fMK.setVisible(false); //you can't see me!
        		fMK.dispose(); //Destroy the JFrame object
        		return;
	        }
	    });
	    barKontakt.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
        		JOptionPane.showMessageDialog(fMK, "+48 123 45 67");

	        }
	    });
	    
	    
	    
	    katalog = new JButton("KATALOG", bBG);
	    koszyk = new JButton("KOSZYK", bBG);
	    historia = new JButton("HISTORIA", bBG);
	    wyloguj = new JButton("WYLOGUJ", bBG);
	    kontakt = new JButton("KONTAKT", bBG);
	    
	    katalog.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
	    koszyk.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
	    historia.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
	    wyloguj.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
	    kontakt.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
	    
	    katalog.setBounds(470, 184, 240, 30);
	    koszyk.setBounds(470, 233, 240, 30);
	    historia.setBounds(470, 282, 240, 30);
	    wyloguj.setBounds(470, 331, 240, 30);
	    kontakt.setBounds(470, 380, 240, 30);
	    
	   // katalog.setOpaque(false);	   
	    katalog.setContentAreaFilled(false);
	    katalog.setBorderPainted(false);
	   // koszyk.setOpaque(false);
	    koszyk.setContentAreaFilled(false);
	    koszyk.setBorderPainted(false);
	   // historia.setOpaque(false);
	    historia.setContentAreaFilled(false);
	    historia.setBorderPainted(false);
	    //wyloguj.setOpaque(false);
	    wyloguj.setContentAreaFilled(false);
	    wyloguj.setBorderPainted(false);
	   // kontakt.setOpaque(false);
	    kontakt.setContentAreaFilled(false);
	    kontakt.setBorderPainted(false);
	    
	    katalog.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
        		menu = new Menu();
        		menu.function(PESEL, koszykObj);
        		fMK.setVisible(false); //you can't see me!
        		fMK.dispose(); //Destroy the JFrame object
        		return;
        		
	        }
	    });
	    koszyk.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	koszykObj.function(PESEL, koszykObj);
        		fMK.setVisible(false); //you can't see me!
        		fMK.dispose(); //Destroy the JFrame object
        		return;
	        }
	    });
	    historia.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
        		lista = new ListaKlient();
        		lista.function(PESEL, koszykObj);
	            fMK.setVisible(false); //you can't see me!
        		fMK.dispose(); //Destroy the JFrame object
        		return;
	        }
	    });
	    wyloguj.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            introLogin login = new introLogin();
	            login.function();
	            fMK.setVisible(false); //you can't see me!
        		fMK.dispose(); //Destroy the JFrame object
        		return;
	        }
	    });
	    kontakt.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
        		JOptionPane.showMessageDialog(fMK, "+48 123 45 67");

	        }
	    });

	    fMK.add(katalog);
	    fMK.add(koszyk);
	    fMK.add(historia);
	    fMK.add(wyloguj);
	    fMK.add(kontakt);
	    
	    fMK.add(barMenu);
	    fMK.add(barKatalog);
	    fMK.add(barKoszyk);
	    fMK.add(barWyloguj);
	    fMK.add(barKontakt);


	    fMK.pack();
	    fMK.setVisible(true);
	}
}
