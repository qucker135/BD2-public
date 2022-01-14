import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import java.awt.image.*;

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
import java.sql.ResultSet;
import java.sql.SQLException;


public class SecondLogin {
	
    static JTextField login;
    static JPasswordField passwd;
    
    static JButton zaloguj;
    
    MenuKlient MK;
    MenuPracownik MP;
    
    
    //private int idKlienta=-1;
    private int idPracownika=-1;
    
    private String loginData;
    private String passwdData;
    
	public void run(int whoIsThere){
		JFrame fSL = new JFrame();
	    fSL.setSize(1200, 847);
	    fSL.setResizable(false);
	    try {
	    	
	        fSL.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("logowanie1.jpg")))));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    login = new JTextField();
	    login.setBounds(402, 330, 410, 51); //392, 270, 420, 65

	    //login.setOpaque(false);
	    passwd = new JPasswordField();
	    passwd.setBounds(402, 330+79, 410, 50); //392, 350, 420, 65
		passwd.setEchoChar('*');
	    
	    zaloguj = new JButton("");
	    zaloguj.setBounds(402, 330+196, 410, 60);
	    //zaloguj.setOpaque(false);
	    zaloguj.setContentAreaFilled(false);
	    zaloguj.setBorderPainted(true);
	    
	    zaloguj.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	loginData = login.getText();
				passwdData = String.valueOf(passwd.getPassword());
				String sha256hex = org.apache.commons.codec.digest.DigestUtils.sha256Hex(passwdData);
	        	/*
	        	 * Tutaj trzeba doda� kod, kt�ry b�dzie sprawdza� passy z baz� danych
	        	 * pracownik, albo klient po zmiennej whoIsThere
	        	 * Nale�y te� zapewni�, aby znalezione id klient / pracownik zosta�o przypisane do odpowiedniej zmiennej
	        	 * 
	        	 */
				//ResultSet rsHashHasla = DbConnector.executeSelectQuery("SELECT IDhasla FROM HasheHasel WHERE hashHasla=\""+sha256hex+"\";");

				boolean czyPoprawnyLogin = false;

				if(whoIsThere == 1){ //logowanie Klienta
					try{
						ResultSet resultSet = DbConnector.executeSelectQuery("SELECT HasheHaselIDhasla FROM Klient WHERE PESEL=\""+loginData+"\";");
						int resultSetSize = 0;
						String bfrIDHashHasla = "";
						while(resultSet.next()){
							resultSetSize++;
							bfrIDHashHasla = resultSet.getString("HasheHaselIDhasla");
						}
						if(resultSetSize>0){
							//znaleziono usera
							try{
								ResultSet resultSet2 = DbConnector.executeSelectQuery("SELECT hashHasla FROM HasheHasel WHERE IDhasla="+bfrIDHashHasla+";");

								int resultSet2Size = 0;
								String bfrHashHasla = "";
								while(resultSet2.next()){
									resultSet2Size++;
									bfrHashHasla = resultSet2.getString("hashHasla");
								}
								if(resultSet2Size>0 && bfrHashHasla.equals(sha256hex)){
									//zalogowano
									czyPoprawnyLogin = true;
								}
								else{
									JOptionPane.showMessageDialog(fSL, "Nieprawidłowe dane logowania");
								}
							}catch(SQLException e4){
								e4.printStackTrace();
							}

						}
						else {
							//nie znaleziono usera
							JOptionPane.showMessageDialog(fSL, "Nieprawidłowe dane logowania");
						}
					}catch (SQLException e3){
						e3.printStackTrace();
					}
				}
				if(whoIsThere == 2){ //logowanie Pracownika
					try{
						ResultSet resultSet = DbConnector.executeSelectQuery("SELECT HasheHaselIDhasla FROM Pracownik WHERE IDpracownika=\""+loginData+"\";");
						int resultSetSize = 0;
						String bfrIDHashHasla = "";
						while(resultSet.next()){
							resultSetSize++;
							bfrIDHashHasla = resultSet.getString("HasheHaselIDhasla");
						}
						if(resultSetSize>0){
							//znaleziono usera
							try{
								ResultSet resultSet2 = DbConnector.executeSelectQuery("SELECT hashHasla FROM HasheHasel WHERE IDhasla="+bfrIDHashHasla+";");

								int resultSet2Size = 0;
								String bfrHashHasla = "";
								while(resultSet2.next()){
									resultSet2Size++;
									bfrHashHasla = resultSet2.getString("hashHasla");
								}
								if(resultSet2Size>0 && bfrHashHasla.equals(sha256hex)){
									//zalogowano
									czyPoprawnyLogin = true;
								}
								else{
									JOptionPane.showMessageDialog(fSL, "Nieprawidłowe dane logowania");
								}
							}catch(SQLException e4){
								e4.printStackTrace();
							}

						}
						else {
							//nie znaleziono usera
							JOptionPane.showMessageDialog(fSL, "Nieprawidłowe dane logowania");
						}
					}catch (SQLException e3){
						e3.printStackTrace();
					}
				}

	        	if(czyPoprawnyLogin) {
					if (whoIsThere == 1) {
						MK = new MenuKlient();

						MK.function(loginData, null);
						fSL.setVisible(false); //you can't see me!
						fSL.dispose(); //Destroy the JFrame object
						return;
					}
					if (whoIsThere == 2) {
						MP = new MenuPracownik();

						idPracownika = Integer.parseInt(loginData);

						MP.function(idPracownika);
						fSL.setVisible(false); //you can't see me!
						fSL.dispose(); //Destroy the JFrame object
						return;
					}
				}
	        }
	    });
	    
	    fSL.add(zaloguj);
	    fSL.add(login);
	    fSL.add(passwd);

	    fSL.pack();
	    fSL.setVisible(true);
	}
}
