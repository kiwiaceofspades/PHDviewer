package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import System.PhDData;

/**
 *
 */

/**
 * @author schmidmatt
 *
 */
public class PhDViewer extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 1770606247378958128L;
	//private JFrame Me = this;
	private Table Table;
	private PhDData DATA;
	private InfoPanel Info;
	private HeaderPanel Head;
	private JButton Add,remove;
	private String currentTable;
	private String ExtraPanel = "Info";

	public PhDViewer(String string, PhDData pas) {

		super(string);
		DATA = pas;
		this.setMinimumSize(new Dimension(800,600));
		Table = new Table(DATA,null, this);
		Info = new InfoPanel(this.getSize(),this);
		Head = new HeaderPanel(this.getSize(),this);
		setLayout(new BorderLayout());
		add(Table, BorderLayout.CENTER);
		add(Info, BorderLayout.EAST);
		WinState Listen = new WinState();
		this.addWindowStateListener(Listen);
		this.addWindowListener(Listen);
		this.addComponentListener(new ComponentListener(){

			@Override
			public void componentResized(ComponentEvent e) {
				System.out.println("Big");
				ResetSize();

			}

			@Override
			public void componentMoved(ComponentEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void componentShown(ComponentEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void componentHidden(ComponentEvent e) {
				// TODO Auto-generated method stub

			}

		});
		/*
		 * Local little panel for housing all the buttons that will be used
		 */
		JPanel Buttons = new JPanel();

		Add = new JButton("Add");
		Add.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				String[][] temp = new String [Table.getCurrentHead().length][2];
				for(int i =0 ;i<Table.getCurrentHead().length;i++){
					temp[i][0] = Table.getCurrentHead()[i];
					temp[i][1] = "";
				}
				UpInfo(temp,"CurrentProvisionallyRegisteredStudents",true);
			}

		});

		remove = new JButton("Remove");
		remove.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Table.Remove(currentTable);
			}

		});

		JButton Headers = new JButton("Custom Headers");
		Headers.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				String[]  full = Table.fullHead;
				String[] current = Table.getCurrentHead();
				showHeaderPanel(full,current);


			}

		});

		Buttons.add(Add);
		Buttons.add(remove);
		Buttons.add(Headers);
		this.add(Buttons, BorderLayout.NORTH);

		Table.setVisible(true);
		setVisible(true);
	}



	protected void showHeaderPanel(String[] full, String[] current) {
		if(!ExtraPanel.equals("Header")){
			if(ExtraPanel.equals("Info"))remove(Info);
			add(Head,BorderLayout.EAST);
			ExtraPanel = "Header";
		}
		Head.UpdateInfo(full,current);
		validate();
		repaint();



	}

		public void ResetSize(){
			Dimension size = getSize();
			//Table
			Dimension HeaderSize = new Dimension(size);
			HeaderSize.width = (HeaderSize.width/3)*1;
			HeaderSize.height = HeaderSize.height-50;
			System.out.println("HEADERSIZE "+HeaderSize.toString());
			System.out.println("Window Size "+size.toString());
			Head.setSizeOverride(HeaderSize);
			Info.setSizeOverride(HeaderSize);
			System.out.println("Info Size "+Info.getSize().toString());
			System.out.println("Head Size "+Head.getSize().toString());
			if((Info.getSize().width!=0) && !(Info.getSize().width==HeaderSize.width)){
				System.out.println("Info check");
				Info.setSizeOverride(HeaderSize);
			}
			if((Head.getSize().width!=0) && !(Head.getSize().width==HeaderSize.width)){
				System.out.println("head check");
				Head.setSizeOverride(HeaderSize);
			}

			validate();

		}
	/**
	 * Called when you are using the InfoPanel
	 * @param infothe Information to be displayed in the panel
	 * @param table what table is this table from
	 * @param b wether we are editing of addign to the table.
	 */
	protected void UpInfo(String[][] info, String table, boolean b){

		if(!ExtraPanel.equals("Info")){
			if(ExtraPanel.equals("Header")) remove(Head);
			add(Info, BorderLayout.EAST);
			ExtraPanel = "Info";
		}

		currentTable = table;
		Info.updateInfo(info,b,table);
		validate();
		repaint();

	}
	/**
	 * Called by the InfoPanel to declare that it wants to make
	 * changes to the Table in the Table.class
	 * @param axis is a row contained with in the data base with its changed valuse it contains the {{headers,values},{headers,values}}
	 * so if the system is working under partial headers we still know what data points were changed in the PHDdata
	 * @param tabel tells teh PHDdata which table is being edited in the system.
	 */
	public void add(String[][] axis, String table){
		Table.Add(axis,table);
	}

	/**
	 *
	 * @param axis is a row contained with in the data base with its changed valuse it contains the {{headers,values},{headers,values}}
	 * so if the system is working under partial headers we still know what data points were changed in the PHDdata
	 * @param tabel tells teh PHDdata which table is being edited in the system.
	 * */
	public void edit(String[][] axis, String table2) {
		Table.Edit(axis,table2);

	}

	public PhDData getDATA() {
		return DATA;
	}

	private class WinState extends WindowAdapter{

		@Override
		public void windowStateChanged(WindowEvent e) {

		}
		@Override
		public void windowOpened(WindowEvent e) {

		}
		@Override
		public void windowClosing(WindowEvent e) {

			DATA.writeToFoswiki();
			System.out.println("Death To all Monkeys");
			System.exit(0);
		}
		@Override
		public void windowClosed(WindowEvent e) {

		}
		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub

		}
		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub

		}
		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub

		}
		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub

		}

	}

	public void changeTableHead(String[] currentSelected) {
		Table.setCurrentHead(currentSelected);

	}

}