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

/**
 *
 */

/**
 * @author schmidmatt
 *
 */
public class PhDViewer extends JFrame {

	JFrame Me = this;
	static Table Table;
	public PhDViewer(String string) {
		super(string);
		this.setMinimumSize(new Dimension(800,600));
		Table = new Table(null,null, this);

		setLayout(new BorderLayout());
		add(Table, BorderLayout.WEST);
		ResetSize();
		this.addComponentListener(new CompListener());
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

		System.out.println("Repaint");
		pack();
		repaint();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PhDViewer Frame = new PhDViewer("PhdViewers");

	}


	private class CompListener implements ComponentListener{

		@Override
		public void componentResized(ComponentEvent e) {
			System.out.println(e.toString());
			if(e.getID()==e.COMPONENT_RESIZED){
			System.out.println(e.paramString());
			ResetSize();
			}
			if(e.getID()==ComponentEvent.WINDOW_EVENT_MASK){
				System.out.println("Windows Event");
			}
			if(e.getID()==ComponentEvent.WINDOW_STATE_EVENT_MASK){
				System.out.println("Windows State Event");
			}
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
