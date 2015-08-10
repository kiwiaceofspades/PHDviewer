package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.util.ArrayList;

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

	JFrame Me = this;
	Table Table;
	private PhDData DATA;

	public PhDViewer(String string, PhDData pas) {

		super(string);
		DATA = pas;
		this.setMinimumSize(new Dimension(800,600));
		Table = new Table(DATA,null, this);

		setLayout(new BorderLayout());
		add(Table, BorderLayout.CENTER);
		//ResetSize();
		//pack();
		//repaint();
		addComponentListener(new CompListener());
		WinState Listen = new WinState();
		this.addWindowStateListener(Listen);
		this.addWindowListener(Listen);
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