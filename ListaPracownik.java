import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
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


//historia zam�wie� 


public class ListaPracownik {
	
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
	String numerZBD;
	
	public void function(int idKlienta) {
		JFrame fLK = new JFrame();

	    try {
	    	
	        fLK.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("menuV2.jpg")))));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		fLK.setSize(751, 650);
		fLK.setResizable(false);

	    String column[]={"Produkt","Numer Seryjny","Cena"};
	    DefaultTableModel dtm=new DefaultTableModel(column,0);

	    JTable jt=new JTable(dtm);    
	    
	    paragony = new JComboBox();

		try {
			ResultSet resultSet1 = DbConnector.executeSelectQuery("SELECT IDtransakcji, nrParagonu FROM Transakcja ORDER BY IDtransakcji;");
			while(resultSet1.next()){
				String IDtransakcji = resultSet1.getString("IDtransakcji");
				String nrParagonu = resultSet1.getString("nrParagonu");
				paragony.addItem(IDtransakcji+";"+nrParagonu);
			}
		}catch (SQLException e){
			e.printStackTrace();
		}

    	//paragony.getSelectedItem().toString(); //nrParagonu

	    jt.setBounds(0,0,200,100);      
	    jt.setBackground(Color.blue);
	    JScrollPane sp=new JScrollPane(jt);    
	    
	    JPanel p = new JPanel();
	    p.setBounds(0, 100, 500, 425);
	    p.setBackground(Color.white);
	    p.add(sp);
	    

	    nrZam = new JTextField();
	    nrZam.setText(numerZBD);
	    
	    back = new JButton("POWRÓT", bBG);
	    back.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
	    back.setBounds(500, 400, 240, 30);
	    back.setContentAreaFilled(false);
	    back.setBorderPainted(false);
	    back.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	MenuPracownik menu = new MenuPracownik();
        		menu.function(idKlienta);
        		fLK.setVisible(false); //you can't see me!
        		fLK.dispose(); //Destroy the JFrame object
        		return;
	        }
	    });
	    paragony.setBounds(500, 100, 240, 30);
	    
	    paragony.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	paragony.getSelectedItem();//paragon
	        	dtm.getDataVector().removeAllElements();
				try{
					ResultSet resultSet2 = DbConnector.executeSelectQuery("SELECT NrEgzemplarzaWTransakcji.nrSeryjny AS nrSeryjny, NrEgzemplarzaWTransakcji.finalnaCena AS Cena, Produkt.Nazwa AS Produkt FROM NrEgzemplarzaWTransakcji, Egzemplarz, Produkt WHERE Produkt.IDproduktu=Egzemplarz.ProduktIDproduktu AND NrEgzemplarzaWTransakcji.EgzemplarzIDserii=Egzemplarz.nrSeryjny AND NrEgzemplarzaWTransakcji.TransakcjaIDtransakcji = "+((String)paragony.getSelectedItem()).split(";")[0]+";");
					while(resultSet2.next()) {
						String Produkt = resultSet2.getString("Produkt");
						String nrParagonu = resultSet2.getString("nrSeryjny");
						String Cena = resultSet2.getString("Cena");
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
	    fLK.add(nrZam);
	    fLK.add(p);
	    fLK.pack();
	    fLK.setVisible(true);
	}
}
