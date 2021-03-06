/**
 *
 * Copyright (C) 2015  Matthew Schmidt
 *
 * This file is part of PHDViewer.
 *
 * PHDViewer is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PHDViewer is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import System.PhDData;

/**
 *
 */

/**
 * @author Matthew Schmidt
 *
 */
public class PhDViewer extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 1770606247378958128L;
	private JFrame Me;
	private Table Table;
	private PhDData DATA;
	private InfoPanel Info;
	private HeaderPanel Head;
	private JButton Add,remove,Toggle;
	private String currentTable;
	private String ExtraPanel = "Info";
	private String last="";


	/**
	 * Constructor for this JFrame.
	 * @param string The JFrame Title.
	 * @param pas  The Backbone to this JFrame were all of the Data work is done.
	 */
	public PhDViewer(String string, PhDData pas) {

		super(string);
		Me = this;
		DATA = pas;
		this.setMinimumSize(new Dimension(800,600));
		Table = new Table(DATA,
				DATA.getPreferences().getHeadersForMode("Default view")
				,DATA.getHeaders(), this);
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

				ResetSize();

			}

			@Override
			public void componentMoved(ComponentEvent e) {

			}

			@Override
			public void componentShown(ComponentEvent e) {


			}

			@Override
			public void componentHidden(ComponentEvent e) {


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
				UpInfo(temp,currentTable,true);
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

		Toggle = new JButton("Show Panels");
		Toggle.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				hidePanel();

			}

		});

		JButton MoveUp = new JButton("Move Student Up");
		MoveUp.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {

					MoveUp();

			}

		});

		Buttons.add(Add);
		Buttons.add(remove);
		Buttons.add(MoveUp);
		Buttons.add(Headers);
		Buttons.add(Toggle);

		this.add(Buttons, BorderLayout.NORTH);
		this.hidePanel();

		Table.setVisible(true);
		setVisible(true);
	}

	/**
	 * Tells the back PhDData to move a student up the table system.
	 * will return if nothing is selected.
	 * @param s Date that this move has happened
	 */
	protected void MoveUp() {
		if(currentTable==null) return;
		String s = "";
		if(!(currentTable.equalsIgnoreCase("NotFullyAdmitted")
				||currentTable.equalsIgnoreCase("UnderExamination"))){
		 s = (String)JOptionPane.showInputDialog(
				Me,
				"Please give a date as to\n"
						+ "when this move takes place\n",
						"Moving Student",
						JOptionPane.PLAIN_MESSAGE
				);
		if(s ==null||(s!=null&&s.length()<7)){
			JOptionPane.showMessageDialog(Me,
					"You have no inputed a proper date",
					"Error",JOptionPane.ERROR_MESSAGE);
			return;
		}
		}
		String[][] temp = Info.getCurrentData();
		if(temp== null) return;

		DATA.moveStudent(temp,currentTable,s);
		Table.setupTable();


	}

	/**
	 * Called when you are using the InfoPanel
	 * @param info the Information to be displayed in the panel
	 * @param table what table is this table from
	 * @param b whether we are editing of adding to the table.
	 */
	protected void UpInfo(String[][] info, String table, boolean b){

		if(!ExtraPanel.equals("Info")){
			if(ExtraPanel.equals("Header")) remove(Head);
			add(Info, BorderLayout.EAST);
			ExtraPanel = "Info";
			Toggle.setText("Hide Panel");

		}

		currentTable = table;
		Info.updateInfo(info,b,table);
		validate();
		repaint();

	}
	/**
	 * Shows the HeaderPanel and gives it the
	 * most up to date information to use.
	 * @param full Full List of the headers that the program supports.
	 * @param current the current list of headers that are been use in the JTabel
	 */
	protected void showHeaderPanel(String[] full, String[] current) {
		if(!ExtraPanel.equals("Header")){
			if(ExtraPanel.equals("Info"))remove(Info);
			add(Head,BorderLayout.EAST);
			ExtraPanel = "Header";
			Toggle.setText("Hide Panel");
		}
		Head.UpdateInfo(full,current);
		validate();
		repaint();
	}

	/**
	 * Hides the Info Panel on the right hand side of
	 * the screen and changes the words in the button.
	 */
	protected void hidePanel(){


		if(ExtraPanel.equals("Info"))remove(Info);
		if(ExtraPanel.equals("Header")) remove(Head);
		if(ExtraPanel.equals("Hide")){
			Toggle.setText("Hide Panel");

			if(last.equals("Info")){
				add(Info, BorderLayout.EAST);
				ExtraPanel = "Info";
			}
			if(last.equals("Header")){
				add(Head,BorderLayout.EAST);
				ExtraPanel = "Header";
			}
			last = "";
		}else{

			last = ExtraPanel;
			ExtraPanel = "Hide";

			Toggle.setText("Show Panel");
		}



		validate();
		repaint();
	}

	/**
	 * Called when the window changes size.
	 */
	public void ResetSize(){
		hidePanel();
		Dimension size = getSize();
		//Table
		Dimension HeaderSize = new Dimension(size);
		HeaderSize.width = (HeaderSize.width/3)*1;
		HeaderSize.height = HeaderSize.height-50;
		//System.out.println("HEADERSIZE "+HeaderSize.toString());
		//System.out.println("Window Size "+size.toString());
		Head.setSizeOverride(HeaderSize);
		Info.setSizeOverride(HeaderSize);
		//System.out.println("Info Size "+Info.getSize().toString());
		//System.out.println("Head Size "+Head.getSize().toString());
//		if((Info.getSize().width!=0) && !(Info.getSize().width==HeaderSize.width)){
//			//System.out.println("Info check");
//			//Info.setSizeOverride(HeaderSize);
//		}
//		if((Head.getSize().width!=0) && !(Head.getSize().width==HeaderSize.width)){
//			System.out.println("head check");
//			Head.setSizeOverride(HeaderSize);
//		}
		validate();
		hidePanel();

	}

	/**
	 * Called by the InfoPanel to declare that it wants to make
	 * changes to the Table in the Table.class
	 * @param axis is a row contained with in the data base with its changed valuse it contains the {{headers,values},{headers,values}}
	 * so if the system is working under partial headers we still know what data points were changed in the PHDdata
	 * @param table tells the PHDdata which table is being edited in the system.
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
	/**
	 * Just gets The PhDData object tied to this JFrame.
	 * @return Returns the PhDData object being used.
	 */
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

			int answer = JOptionPane.showConfirmDialog(Me, "Would you like to save?","Confirm Saving",JOptionPane.YES_NO_OPTION);
			if(answer == 0){
				DATA.writeToFoswiki();
				DATA.getPreferences().writeToFile();
				System.exit(0);
			} else if(answer == 1){
				System.exit(0);
			}

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

	/**
	 * Changes the Current Headers being shown.
	 * @param currentSelected the Lsit of Headers to be shown.
	 */
	public void changeTableHead(String[] currentSelected) {
		Table.setCurrentHead(currentSelected);

	}
	/**
	 *  Tells teh backbone to mark the Student Red.
	 * @param information Containing the data to make the changes to
	 * @param table the table that we are working on
	 */
	public void makeRed(String[][] information, String table) {
		DATA.toggleMark(information,table);
		Table.setupTable();
	}

}