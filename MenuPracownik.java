import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

//import org.apache.commons.codec.digest;

public class MenuPracownik {

    ImageIcon bBG = new ImageIcon("buttonBG.png");
	
    static JButton katalog;
    static JButton noweKonto;
    static JButton lista;
    static JButton mojeKonto;
    static JButton wyloguj;

    Menu menu;
	ListaKlient listaObj;
    

	public void function(int idPracownika) {
		JFrame fMP = new JFrame();

	    try {
	    	
	        fMP.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("menuV2.jpg")))));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    
		fMP.setSize(751, 650);
		fMP.setResizable(false);
	    
	    katalog = new JButton("KATALOG", bBG);
	    noweKonto = new JButton("NOWE KONTO", bBG);
	    lista = new JButton("LISTA", bBG);
	    mojeKonto = new JButton("MOJE KONTO", bBG);
	    wyloguj = new JButton("WYLOGUJ", bBG);

	    katalog.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
	    noweKonto.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
	    lista.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
	    mojeKonto.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
	    wyloguj.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
	    
	    
	    katalog.setBounds(470, 184, 240, 30);
	    noweKonto.setBounds(470, 233, 240, 30);
	    lista.setBounds(470, 282, 240, 30);
	    mojeKonto.setBounds(470, 331, 240, 30);
	    wyloguj.setBounds(470, 380, 240, 30);
	    
	    katalog.setContentAreaFilled(false);
	    katalog.setBorderPainted(false);
	    noweKonto.setContentAreaFilled(false);
	    noweKonto.setBorderPainted(false);
	    lista.setContentAreaFilled(false);
	    lista.setBorderPainted(false);
	    mojeKonto.setContentAreaFilled(false);
	    mojeKonto.setBorderPainted(false);
	    wyloguj.setContentAreaFilled(false);
	    wyloguj.setBorderPainted(false);

	    
	    katalog.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
        		KatalogPracownik katalog = new KatalogPracownik();
        		katalog.function(idPracownika);
        		fMP.setVisible(false); //you can't see me!
        		fMP.dispose(); //Destroy the JFrame object
        		return;
	        }
	    });
	    noweKonto.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            
	            JTextField peselInput = new JTextField(11);

				JTextField adresMiasto = new JTextField(8); //50
				JTextField adresKod = new JTextField(6); //6
				JTextField adresUlica = new JTextField(8); //50
				JTextField adresBudynek = new JTextField(2); //10
				JTextField adresLokal = new JTextField(2); //10

	            JTextField emailInput = new JTextField(8); //49
	            JTextField telefonInput = new JTextField(9); //9
	            JTextField hasloInput = new JTextField(8); //20
	            JTextField imieInput = new JTextField(8); //15
	            JTextField imie2Input = new JTextField(8); //15
	            JTextField nazwiskoInput = new JTextField(8); //25

	            JPanel myPanel = new JPanel();
	            myPanel.add(new JLabel("PESEL:"));
	            myPanel.add(peselInput);
	            myPanel.add(Box.createVerticalStrut(15)); // a spacer

				myPanel.add(Box.createVerticalStrut(15)); // a spacer
				myPanel.add(new JLabel("Miasto:"));
				myPanel.add(adresMiasto);
				myPanel.add(Box.createVerticalStrut(15)); // a spacer
				myPanel.add(new JLabel("Kod Pocztowy:"));
				myPanel.add(adresKod);
				myPanel.add(Box.createVerticalStrut(15)); // a spacer
				myPanel.add(new JLabel("Ulica:"));
				myPanel.add(adresUlica);
				myPanel.add(Box.createVerticalStrut(15)); // a spacer
				myPanel.add(new JLabel("Brama:"));
				myPanel.add(adresBudynek);
				myPanel.add(Box.createVerticalStrut(15)); // a spacer
				myPanel.add(new JLabel("Lokal:"));
				myPanel.add(adresLokal);

	            myPanel.add(new JLabel("e-mail:"));
	            myPanel.add(emailInput);
	            myPanel.add(Box.createVerticalStrut(15)); // a spacer
	            myPanel.add(new JLabel("telefon:"));
	            myPanel.add(telefonInput);
	            myPanel.add(Box.createVerticalStrut(15)); // a spacer
	            myPanel.add(new JLabel("hasło:"));
	            myPanel.add(hasloInput);
	            myPanel.add(Box.createVerticalStrut(15)); // a spacer
	            myPanel.add(new JLabel("imię:"));
	            myPanel.add(imieInput);
	            myPanel.add(Box.createVerticalStrut(15)); // a spacer
	            myPanel.add(new JLabel("drugie imi�:"));
	            myPanel.add(imie2Input);
	            myPanel.add(Box.createVerticalStrut(15)); // a spacer
	            myPanel.add(new JLabel("nazwisko:"));
	            myPanel.add(nazwiskoInput);
	            myPanel.add(Box.createVerticalStrut(15)); // a spacer

	            int result = JOptionPane.showConfirmDialog(null, myPanel, 
	                     "Please Enter X and Y Values", JOptionPane.OK_CANCEL_OPTION);
	            if (result == JOptionPane.OK_OPTION) {
					try{
						DbConnector.executeQuery("INSERT INTO Email VALUES (DEFAULT, \""+emailInput.getText()+"\");");
						DbConnector.executeQuery("INSERT INTO nrTelefonu VALUES (DEFAULT, \""+telefonInput.getText()+"\");");
						DbConnector.executeQuery("INSERT INTO Adres VALUES (DEFAULT, \""+adresMiasto.getText()+"\", \""+adresKod.getText()+"\", \""+adresUlica.getText()+"\", \""+adresBudynek.getText()+"\", \""+adresLokal.getText()+"\");");
						String sha256hex = org.apache.commons.codec.digest.DigestUtils.sha256Hex(hasloInput.getText());

						DbConnector.executeQuery("INSERT INTO HasheHasel VALUES (DEFAULT, \""+sha256hex+"\");");

						ResultSet resultSet1 = DbConnector.executeSelectQuery("SELECT IDmail FROM Email ORDER BY IDmail DESC LIMIT 1;");
						ResultSet resultSet2 = DbConnector.executeSelectQuery("SELECT IDnumeru FROM nrTelefonu ORDER BY IDnumeru DESC LIMIT 1;");
						ResultSet resultSet3 = DbConnector.executeSelectQuery("SELECT IDhasla FROM HasheHasel ORDER BY IDhasla DESC LIMIT 1;");
						ResultSet resultSet4 = DbConnector.executeSelectQuery("SELECT IDadresu FROM Adres ORDER BY IDadresu DESC LIMIT 1;");

						resultSet1.next();
						resultSet2.next();
						resultSet3.next();
						resultSet4.next();

						String IDmail = resultSet1.getString("IDmail");
						String IDnumeru = resultSet2.getString("IDnumeru");
						String IDhasla = resultSet3.getString("IDhasla");
						String IDAdresu = resultSet4.getString("IDadresu");

						DbConnector.executeQuery("INSERT INTO Klient VALUES (\""+peselInput.getText()+"\", "+IDAdresu+", "+IDnumeru+", "+IDmail+", "+IDhasla+", \""+imieInput.getText()+"\", "+(imie2Input.getText().equals("")?"NULL":("\""+imie2Input.getText()+"\""))+", \""+nazwiskoInput.getText()+"\");");

					}catch(SQLException e2){
						e2.printStackTrace();
					}

	            }
	        }
	    });
	    lista.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
        		ListaPracownik l = new ListaPracownik();
        		l.function(idPracownika);
	            fMP.setVisible(false); //you can't see me!
        		fMP.dispose(); //Destroy the JFrame object
        		return;
	        }
	    });
	   mojeKonto.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
				String imie = "SampleName";
				String nazwisko = "SampleSurname";
				String imie2 = "SampleSecondName";

				try{
					ResultSet resultSet = DbConnector.executeSelectQuery("SELECT Imie, Imie2, Nazwisko FROM Pracownik WHERE IDpracownika="+idPracownika+";");
					while(resultSet.next()) {
						imie = resultSet.getString("Imie");
						nazwisko = resultSet.getString("Nazwisko");
						imie2 = resultSet.getString("Imie2");
						if(resultSet.wasNull()){
							imie2 = "";
						}
					}
				}catch(SQLException e2){
					e2.printStackTrace();
				}
	        	
	        	String toSee = "ID Pracownika "+idPracownika  + " Imię " + imie + " Drugie Imię " + imie2 + " Nazwisko " + nazwisko;
        		JOptionPane.showMessageDialog(fMP, toSee);

	        }
	    });
	    wyloguj.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            introLogin login = new introLogin();
	            login.function();
	            fMP.setVisible(false); //you can't see me!
        		fMP.dispose(); //Destroy the JFrame object
        		return;
	        }
	    });

	    fMP.add(katalog);
	    fMP.add(noweKonto);
	    fMP.add(lista);
	    fMP.add(wyloguj);
	    fMP.add(mojeKonto);


	    fMP.pack();
	    fMP.setVisible(true);
	}
}
