package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import System.Parser;
import System.PhDData;

/**
 *
 */

/**
 * @author schmidmatt
 *
 */
public class PhDViewer extends JFrame {

	private JFrame Me = this;
	private Table Table;
	private PhDData DATA;
	private InfoPanel Info;
	private JButton Add,remove;


	public PhDViewer(String string, PhDData pas) {

		super(string);
		DATA = pas;
		this.setMinimumSize(new Dimension(800,600));
		Table = new Table(DATA,null, this);
		Info = new InfoPanel(this.getSize(),this);

		setLayout(new BorderLayout());
		add(Table, BorderLayout.CENTER);
		add(Info, BorderLayout.EAST);
		addComponentListener(new CompListener());
		WinState Listen = new WinState();
		this.addWindowStateListener(Listen);
		this.addWindowListener(Listen);

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
				UpInfo(temp,true);
			}

		});

		remove = new JButton("Remove");
		remove.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Table.Remove();
			}

		});
		
		


		Buttons.add(Add);
		Buttons.add(remove);
		this.add(Buttons, BorderLayout.NORTH);

		Table.setVisible(true);
		setVisible(true);
	}



	public void ResetSize(){
		Dimension size = getSize();
		//Table
		Dimension tableSize = new Dimension(size);
		tableSize.width = (tableSize.width/3)*2;
		tableSize.height = tableSize.height-50;
		System.out.println(tableSize.toString());
		System.out.println(size.toString());
		Table.setSizeOver(tableSize);
		//Info

	}

	public void UpInfo(String[][] info, boolean b){

		Info.updateInfo(info,b);
		validate();
		repaint();

	}

	public void add(String[][] axis){
		Table.Add(axis);
	}


	public void edit(String[][] axis) {
		Table.Edit(axis);

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PhDViewer Frame = new PhDViewer("PhdViewers",null);

	}


	private class CompListener implements ComponentListener{

		@Override
		public void componentResized(ComponentEvent e) {


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

}