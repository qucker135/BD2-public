import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import static java.lang.Integer.parseInt;


//historia zam�wie� klienta


public class ListaKlient {
	
	JTable table;
	
	JTextField nrZam;
	
	JButton back;
	
	JComboBox paragony;
	
    ImageIcon bBG = new ImageIcon("buttonBG.png");
	
	int amountOfData=10;
	int amountOfPar=10;
	
	String prod;
	int il;
	int cena;
	String prod2 = "zmiana";
	int il2 =99;
	int cena2 = 981;
	
	public void function(String PESEL, koszyk koszykObj) {
		JFrame fLK = new JFrame();

		//global connection to db
		Connection connection = connection = DbConnector.connect();

	    try {
	    	
	        fLK.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("menuV2.jpg")))));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    String column[]={"Produkt","nrSeryjny","Cena"};
	    DefaultTableModel dtm=new DefaultTableModel(column,0);

	    JTable jt=new JTable(dtm);    
	    
	    paragony = new JComboBox();

		try{
			ResultSet resultSet = DbConnector.executeSelectQueryToConnection(connection, "SELECT nrParagonu FROM Transakcja WHERE KlientPESEL="+PESEL+";");
			while(resultSet.next()) {
				String nrParagonu = resultSet.getString("nrParagonu");
				paragony.addItem(nrParagonu);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}

		//TODO - Franciszek & Nastya - przykladowe dane

		try{
			ResultSet resultSet = DbConnector.executeSelectQueryToConnection(connection, "SELECT NrEgzemplarzaWTransakcji.nrSeryjny AS nrSeryjny, NrEgzemplarzaWTransakcji.finalnaCena AS Cena, Produkt.Nazwa AS Produkt FROM NrEgzemplarzaWTransakcji, Egzemplarz, Produkt WHERE Produkt.IDproduktu=Egzemplarz.ProduktIDproduktu AND NrEgzemplarzaWTransakcji.EgzemplarzIDserii=Egzemplarz.nrSeryjny AND NrEgzemplarzaWTransakcji.nrParagonu = "+(String)paragony.getSelectedItem()+";");
			while(resultSet.next()) {
				String Produkt = resultSet.getString("Produkt");
				String nrParagonu = resultSet.getString("nrSeryjny");
				String Cena = resultSet.getString("Cena");
				String[] row = {Produkt, nrParagonu, Cena};
				dtm.addRow(row);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}

	    jt.setBounds(0,0,200,100);      
	    jt.setBackground(Color.blue);
	    JScrollPane sp=new JScrollPane(jt);    
	    
	    JPanel p = new JPanel();
	    p.setBounds(0, 100, 500, 425);
	    p.setBackground(Color.white);
	    p.add(sp);

	    back = new JButton("POWRÓT", bBG);
	    back.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
	    back.setBounds(500, 400, 240, 30);
	    back.setContentAreaFilled(false);
	    back.setBorderPainted(false);
	    back.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	MenuKlient menu = new MenuKlient();
        		menu.function(PESEL, koszykObj);
        		fLK.setVisible(false); //you can't see me!
        		fLK.dispose(); //Destroy the JFrame object
        		return;
	        }
	    });
	    paragony.setBounds(500, 100, 240, 30);
	    
	    paragony.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	dtm.getDataVector().removeAllElements();
				try{
					ResultSet resultSet = DbConnector.executeSelectQuery("SELECT NrEgzemplarzaWTransakcji.nrSeryjny AS nrSeryjny, NrEgzemplarzaWTransakcji.finalnaCena AS Cena, Produkt.Nazwa AS Produkt FROM NrEgzemplarzaWTransakcji, Egzemplarz, Produkt WHERE Produkt.IDproduktu=Egzemplarz.ProduktIDproduktu AND NrEgzemplarzaWTransakcji.EgzemplarzIDserii=Egzemplarz.nrSeryjny AND NrEgzemplarzaWTransakcji.nrParagonu = "+(String)paragony.getSelectedItem()+";");
					while(resultSet.next()) {
						String Produkt = resultSet.getString("Produkt");
						String nrParagonu = resultSet.getString("nrSeryjny");
						String Cena = resultSet.getString("Cena");
						String[] row = {Produkt, nrParagonu, Cena};
						dtm.addRow(row);
					}
				}catch(SQLException e2){
					e2.printStackTrace();
				}
	        }
	    });
	    
	    fLK.add(paragony);
	    fLK.add(back);
	    fLK.add(p);
	    fLK.pack();
	    fLK.setVisible(true);
	}
}
