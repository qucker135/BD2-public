import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;




public class koszyk {

    ImageIcon bBG = new ImageIcon("buttonBG.png");
	
    static JButton usun;
    static JButton kup;
    static JButton back;

    
    static JTextField imie;
    static JTextField nazwisko;
    static JTextField imie2;
    static JTextField kod;
    static JTextField miasto;
    static JTextField ulica;
    static JTextField lokal;
    static JTextField tel;
    static JTextField email;
    
    JTable jt;

    String data[][];
    int amountOfData=20;
    String prod= "test";
    int il = -1;
    int cena = -1;
    
    MenuKlient menu;
    ArrayList <String []>rows = new ArrayList<>();


	public void function(String PESEL, koszyk koszykObj) {
		JFrame fK = new JFrame();

	    try {
	    	
	        fK.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("menuV2.jpg")))));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }    
	    
		fK.setSize(751, 650);
		fK.setResizable(false);
	    
	    String column[]={"idProduktu", "Produkt","Ilość","Cena" };
	    DefaultTableModel dtm=new DefaultTableModel(column,0);

	    JTable jt=new JTable(dtm);

        int iterator = 0;

        while (iterator < rows.size()) {
            dtm.addRow(rows.get(iterator));
            iterator++;
        }


	    //////////////////////////////////////
	    jt.setBounds(0,0,200,100);      
	    jt.setBackground(Color.blue);
	    JScrollPane sp=new JScrollPane(jt);    
	    
	    JPanel p = new JPanel();
	    p.setBounds(210, 50, 600, 425);
	    p.setBackground(Color.white);
	    p.add(sp);
	    
	    
	    
	    
	    
	    usun = new JButton("USUŃ", bBG);
	    kup = new JButton("KUP", bBG);
	    back = new JButton("POWRÓT", bBG);

	    usun.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
	    kup.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
	    back.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
	    
	    usun.setBounds(410, 500, 240, 30);
	    kup.setBounds(5, 500, 240, 30);
	    back.setBounds(5, 50, 240, 30);

	    
	    imie = new JTextField("imię");
	    imie2 = new JTextField("drugie imię");
	    nazwisko = new JTextField("nazwisko");
	    kod = new JTextField("kod pocztowy");
	    miasto = new JTextField("miasto");
	    ulica = new JTextField("ulica");
	    lokal = new JTextField("numer lokalu");
	    tel = new JTextField("telefon");
	    email = new JTextField("email");

	    
	    imie.setBounds(10, 280, 200, 20);
	    imie2.setBounds(10, 300, 200, 20);
	    nazwisko.setBounds(10, 320, 200, 20);
	    kod.setBounds(10, 340, 200, 20);
	    miasto.setBounds(10, 360, 200, 20);
	    ulica.setBounds(10, 380, 200, 20);
	    lokal.setBounds(10, 400, 200, 20);
	    tel.setBounds(10, 420, 200, 20);
	    email.setBounds(10, 440, 200, 20);

	    usun.setContentAreaFilled(false);
	    usun.setBorderPainted(false);
	    kup.setContentAreaFilled(false);
	    kup.setBorderPainted(false);
	    back.setContentAreaFilled(false);
	    back.setBorderPainted(false);
	    
	    usun.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	((DefaultTableModel)jt.getModel()).removeRow(jt.getSelectedRow());
	        }
	    });
	    kup.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	
	        	imie.getText();
	        	imie2.getText();
	     	    nazwisko.getText();
	     	    kod.getText();
	     	    miasto.getText();
	     	    ulica.getText();
	     	    lokal.getText();
	     	    tel.getText();
	     	    email.getText();
	        	//powy�ej czyta dane z p�l tekstowych, zajmij sie tym, �eby zosta�y one odpowiednio 
	        	//przetworzone przez baz�
	        	//TODO BM + Franciszek
	     	    //nowy wpis do tabeli transakcje, zawiera dane zebrane wy�ej, do tego potrzebny nowy wpis w tabelach 
	     	    //nr egzemplarza w transakcji, kt�ry zawiera po jedyn konkretnym przedmiocie z danego typu
	     	    //w tabeli Produkty zmniejszamy ilo�� dost�pnych o ilo�� kupionych

				//SELECT AdresIDAdresu FROM Klient WHERE Klient.PESEL = /*PESEL*/
				//SELECT EmailIDEmail FROM Klient WHERE Klient.PESEL = /*PESEL*/
				//SELECT nrTelefonu FROM Klient WHERE Klient.PESEL = /*PESEL*/ - jedno zapytanie


				String AdresIDAdresu = "";
				String EmailIDmail = "";
				String nrTelefonuIDnumeru = "";
				int liczba_pracownikow = 1;


				try{
					ResultSet resultSetAuxSelect = DbConnector.executeSelectQuery("SELECT AdresIDadresu, EmailIDmail, nrTelefonuIDnumeru FROM Klient WHERE Klient.PESEL=\""+PESEL+"\";");
					resultSetAuxSelect.next();
					AdresIDAdresu = resultSetAuxSelect.getString("AdresIDadresu");
					EmailIDmail = resultSetAuxSelect.getString("EmailIDmail");
					nrTelefonuIDnumeru = resultSetAuxSelect.getString("nrTelefonuIDnumeru");

					ResultSet rs_liczaPracownikow = DbConnector.executeSelectQuery("SELECT COUNT(*) AS Liczba FROM Pracownik;");
					rs_liczaPracownikow.next();
					liczba_pracownikow = Integer.parseInt(rs_liczaPracownikow.getString("Liczba"));

				}catch(SQLException e2){
					e2.printStackTrace();
				}

				//System.out.println(AdresIDAdresu+";"+EmailIDmail+"&"+nrTelefonuIDnumeru);

				DbConnector.executeQuery("INSERT INTO Transakcja VALUES (DEFAULT, \""+PESEL+"\", 1, ROUND(RAND()*"+liczba_pracownikow+")+1, "+AdresIDAdresu+", "+EmailIDmail+", "+nrTelefonuIDnumeru+", 0, NOW(), 1, 0);");

				//Transakcja - INSERT
				//PESEL - arg
				//STATUS - 1
				//Pracownik - rand()
				//nrParagonu - idTransakcji + 9losowaych bitow -- DO UPDATU
				//datoczasZakupu - NOW()
				//czyOplacono - 1
				//lacznaCena - 0 -- DO UPDATU

				//UPDATE TRanskacji - (ustalenie nrParagonu na podstwie ostatniego IDTransakcji)

				/*

				int IDostatniejTransakcji = 10;

				try{
					ResultSet resultSetAuxSelect = DbConnector.executeSelectQuery("SELECT IDtransakcji FROM Transakcja ORDER BY IDtransakcji DESC LIMIT 1;");
					resultSetAuxSelect.next();
					IDostatniejTransakcji = Integer.parseInt(resultSetAuxSelect.getString("IDtransakcji"));
				}catch(SQLException e2){
					e2.printStackTrace();
				}

				DbConnector.executeQuery("UPDATE Transakcja SET nrParagonu="+IDostatniejTransakcji+"*POWER(2,9) + ROUND(RAND() * POWER(2,9)) WHERE IDtransakcji="+IDostatniejTransakcji+";");

				String nrParagonu = "1000";

				try{//SELECT W CELU ustalenia nrParagonu
					ResultSet resultSetAuxSelect = DbConnector.executeSelectQuery("SELECT nrParagonu FROM Transakcja ORDER BY IDtransakcji DESC LIMIT 1;");
					resultSetAuxSelect.next();
					nrParagonu = resultSetAuxSelect.getString("nrParagonu");
				}catch(SQLException e2){
					e2.printStackTrace();
				}

				//petla{

				int liczba_egzemplarzy = 0;

				double lacznaCena = 0.0;

				for(int i=0; i<jt.getRowCount();i++){
					liczba_egzemplarzy = Integer.parseInt((String)dtm.getValueAt(i, 2));

					int IDproduktu = Integer.parseInt((String)dtm.getValueAt(i, 0));

					double wartoscPromocji = 0.00;

					double CenaBrutto = 100.0;

					try{//SELECT W CELU ustalenia maksymalnej promocji
						ResultSet resultSetAuxSelect = DbConnector.executeSelectQuery("SELECT MAX(Promocja.Wartosc) AS Wartosc FROM Promocja, PromocjeNaProdukty, Produkt WHERE Promocja.IDPromocji=PromocjeNaProdukty.PromocjaIDpromocji AND PromocjeNaProdukty.ProduktIDproduktu = "+IDproduktu+";");

						resultSetAuxSelect.next();
						wartoscPromocji += Double.parseDouble(resultSetAuxSelect.getString("Wartosc"));

						ResultSet resultSetCenaBrutto = DbConnector.executeSelectQuery("SELECT Cena FROM Produkt WHERE IDproduktu="+IDproduktu+";");

						resultSetCenaBrutto.next();
						CenaBrutto = Double.parseDouble(resultSetCenaBrutto.getString("Cena"));

					}catch(SQLException e2){
						e2.printStackTrace();
					}

					double finalnaCenaProduktu = CenaBrutto * (1.0-wartoscPromocji);

					for(int j=0; j<liczba_egzemplarzy;j++){

						String IDserii = "";
						String nrSeryjny = "";

						try{//SELECT W CELU ustalenia IDserii
							ResultSet resultSetAuxSelect = DbConnector.executeSelectQuery("SELECT IDserii, nrSeryjny FROM Egzemplarz WHERE ProduktIDproduktu = "+IDproduktu+" AND czyDostepne=1 LIMIT 1;");
							System.out.println(IDproduktu);
							resultSetAuxSelect.next();
							IDserii = resultSetAuxSelect.getString("IDserii");
							nrSeryjny = resultSetAuxSelect.getString("nrSeryjny");
						}catch(SQLException e2){
							e2.printStackTrace();
						}

						DbConnector.executeQuery("INSERT INTO NrEgzemplarzaWTransakcji VALUES (DEFAULT, "+IDostatniejTransakcji+", "+IDserii+", "+nrParagonu+", "+nrSeryjny+", "+finalnaCenaProduktu+");");

						lacznaCena += finalnaCenaProduktu;

						//ustawienie czydostepne na zero where Egzemplarz.IDserii = idserii
						DbConnector.executeQuery("UPDATE Egzemplarz SET czyDostepne=0 WHERE IDserii="+IDserii+";");


					}

					//UPDATE Produktu - zmniejszenie ilosci

					DbConnector.executeQuery("UPDATE Produkt SET DostepneSztuki=DostepneSztuki-"+liczba_egzemplarzy+" WHERE IDproduktu="+IDproduktu+";");
				}



				//NrEgzemplarza w transakcji - (petla)
				//IDtransakcji - SELECT*FROM get lAST of Transkacja
				//EgzemplarzIDserii - SELECT Egzemplarz.IDSerii FROM egzemplarz WHERE Produkt.IDProduktu = ProduktID - dziwne iterowanie po JTable AND czyDostepne = 1 LIMIT 1
				//nrParagonu - idTransakcji + 12losowaych bitow
				//nrSeryjny - SELECT na podstawie EgzemplarzIDserii
				//finalna cena - SELECT cena from produkt (produktID mamy)
				//i jeszcze select do promocji -- w celu policzenia finalnej ceny


				//UPDATE lacznaCeny w Transkacji
				DbConnector.executeQuery("UPDATE Transakcja SET lacznaCena="+lacznaCena+" WHERE IDtransakcji="+IDostatniejTransakcji+";");

				*/


				//}end-petla
	        }
	    });
	    back.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
        		menu = new MenuKlient();
        		menu.function(PESEL, koszykObj);
        		fK.setVisible(false); //you can't see me!
        		fK.dispose(); //Destroy the JFrame object
        		return;
	        }
	    });
	    
	    fK.add(imie);
	    fK.add(imie2);
	    fK.add(nazwisko);
	    fK.add(kod);
	    fK.add(miasto);
	    fK.add(ulica);
	    fK.add(lokal);
	    fK.add(tel);
	    fK.add(email);

	    fK.add(usun);
	    fK.add(kup);
	    fK.add(back);
	    
	    fK.add(p); 

	    
	    fK.pack();
	    fK.setVisible(true);
	}
	public void addToBasket(String[] row) {
		rows.add(row);
		return;
	}
	
	public ArrayList getKoszyk() {
		return rows;	
	}


}
