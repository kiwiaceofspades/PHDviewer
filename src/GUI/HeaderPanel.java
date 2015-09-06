/**
 *
 */
package GUI;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.TreeSet;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

import System.PhDData;

/**
 * @author schmidmatt
 *
 */
public class HeaderPanel extends JPanel {


	/**
	 * A link to the main host of the JPanel
	 * so that we can pass methods around the window
	 */
	private PhDViewer HOST;

	private TreeSet<heads> currentSelected = null;
	private String[] fullList = null;
	private JPanel Panel;
	private PhDData HEADS;


	public HeaderPanel(Dimension tt, PhDViewer main){
		HOST = main;
		tt.width = 300;
		HEADS = main.getDATA();
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		this.setPreferredSize(tt);
		JButton button = new JButton("Apply Header Changes");
		button.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				heads temp[] = new heads[1];
				String sTemp[] = new String[currentSelected.size()];
				TreeSet<heads> local = currentSelected;
				Iterator<heads> iterator = currentSelected.iterator();
				for(int i =0;i<sTemp.length;i++){
					sTemp[i]= iterator.next().header;

				}
				HOST.changeTableHead(sTemp);
			}

		});

		this.add(button);
	}

	public void UpdateInfo(String[] full, String[] current) {

		Panel = new JPanel();
		Panel.setLayout(new GridLayout(0,2));
		fullList = full;
		TreeSet<heads> Treetemp = new TreeSet<heads>();
		for(int i=0 ; i<current.length;i++){
			Treetemp.add(new heads(current[i],i));
		}

		currentSelected =Treetemp;

		for( int i =0 ;i<full.length;i++){
			String f= full[i];
			JCheckBox temp = new JCheckBox(f);
			for(String c: current){
				if(f.equals(c)) temp.setSelected(true);
			}
			temp.addActionListener(new clickListener(f,i));
			Panel.add(temp);
		}
		this.add(Panel);


	}
	/**
	 * This class is still under development problems have arisen as to how i go about
	 * working out were we are in the full data and making sure it remains constaint
	 * against the full data. if this is not kept then we could have columns wondering
	 * around the table which we do not want also has to be done fst and effient as it
	 * could happen lots of times in a very short amount of time and we don't want a
	 * high level of lag will it ticks off.
	 *
	 *
	 * @author schmidmatt
	 *
	 */
	private class clickListener implements ActionListener{

		private heads head;

		public clickListener(String f,int index) {
			head = new heads(f,index);

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(!currentSelected.add(head)){
				currentSelected.remove(head);
			}
		}

	}

	private class heads implements Comparable<heads>{

		private String header;
		private int Index;


		public heads(String header, int index) {
			super();
			this.header = header;
			Index = index;
		}



		/**
		 * @return the header
		 */
		public String getHeader() {
			return header;
		}



		/**
		 * @return the index
		 */
		public Integer getIndex() {
			return Index;
		}

		@Override
		public int compareTo(heads o) {
			return getIndex().compareTo(o.getIndex());
		}



		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "heads [header=" + header + ", Index=" + Index + "]";
		}



	}

}
