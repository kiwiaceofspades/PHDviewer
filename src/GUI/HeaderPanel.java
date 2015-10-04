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
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import System.PhDData;

/**
 * @author schmidmatt
 *
 */
public class HeaderPanel extends JPanel {


	/*
	 * A link to the main host of the JPanel
	 * so that we can pass methods around the window
	 */
	private PhDViewer HOST;

	private TreeSet<heads> currentSelected = null;
	private String[] fullList = null;
	private JPanel Panel;
	private PhDData HEADS;
	private JPanel ButtonsPanel;


	private JComboBox<String> ListofPreferences;
	private String HeaderList="";


	/**
	 *
	 * @param Size of the Main Window
	 * @param main The main JFrame that this program is hosted on
	 */
	public HeaderPanel(Dimension tt, PhDViewer main){
		HOST = main;
		tt.width = 300;
		HEADS = main.getDATA();
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		this.setPreferredSize(tt);


		setupComboBox();
		Dimension panDimension= new Dimension(tt.width,100);
		ButtonsPanel.setMinimumSize(panDimension);
		ButtonsPanel.setPreferredSize(panDimension);
		ButtonsPanel.setMaximumSize(panDimension);

	}

	/**
	 * Builds and adds teh Buttons and comboBox on the panel that is
	 * loaded into the first part of the headers panel.
	 */
	protected void setupComboBox() {
		if(ButtonsPanel!=null)remove(ButtonsPanel);

		ButtonsPanel = new JPanel();
		JButton button = new JButton("Apply Header Changes");
		button.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				String sTemp[] = new String[currentSelected.size()];
				TreeSet<heads> local = currentSelected;
				Iterator<heads> iterator = currentSelected.iterator();
				for(int i =0;i<sTemp.length;i++){
					sTemp[i]= iterator.next().header;
				}
				HOST.changeTableHead(sTemp);
			}

		});

		ButtonsPanel.add(button);
		String[] preferencesList= HEADS.getPreferences().getNamesOfModes();
		ListofPreferences = new JComboBox<String>(preferencesList);
		ListofPreferences.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox)e.getSource();
				String list = (String) cb.getSelectedItem();
				HeaderList = list;
				UpdateInfo(fullList,HEADS.getPreferences().getHeadersForMode(list));
			}

		});

		for (int i= 0; i<preferencesList.length-1;i++){
			if(HeaderList.equalsIgnoreCase(preferencesList[i])){
				ListofPreferences.setSelectedIndex(i);
			}
		}

		ButtonsPanel.add(ListofPreferences);

		JButton jj = new JButton("Add");
		jj.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				String sTemp[] = new String[currentSelected.size()];
				TreeSet<heads> local = currentSelected;
				Iterator<heads> iterator = currentSelected.iterator();
				for(int i =0;i<sTemp.length;i++){
					sTemp[i]= iterator.next().header;
				}
				String s = (String)JOptionPane.showInputDialog(
	                    HOST,
	                    "Please give a name to this header view",
	                    "Adding Header View",
	                    JOptionPane.PLAIN_MESSAGE
	                    );
				if(s!=null){
					HEADS.getPreferences().addMode(s, sTemp);
					setupComboBox();
					UpdateInfo(fullList,sTemp);
				}
			}});

		ButtonsPanel.add(jj);

		jj = new JButton("Clear");
		jj.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				String sTemp[] = new String[currentSelected.size()];
				TreeSet<heads> local = currentSelected;
				Iterator<heads> iterator = currentSelected.iterator();
				for(int i =0;i<sTemp.length;i++){
					sTemp[i]= iterator.next().header;
				}
				String list = (String) ListofPreferences.getSelectedItem();
				HeaderList = list;
				HEADS.getPreferences().deleteMode(list);
				setupComboBox();
				UpdateInfo(fullList,sTemp);
			}});

		ButtonsPanel.add(jj);

		jj = new JButton("Edit");
		jj.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				String sTemp[] = new String[currentSelected.size()];
				TreeSet<heads> local = currentSelected;
				Iterator<heads> iterator = currentSelected.iterator();
				for(int i =0;i<sTemp.length;i++){
					sTemp[i]= iterator.next().header;
				}

				String list = (String) ListofPreferences.getSelectedItem();
				HeaderList = list;

				HEADS.getPreferences().editMode(list, sTemp);
				setupComboBox();
				UpdateInfo(fullList,sTemp);

			}});

		ButtonsPanel.add(jj);

		this.add(ButtonsPanel);
	}

	/**
	 * Updates the list of headers in the header selection area full MUST
	 *  always contain a complete list of the headers.
	 *
	 * @param full Cannot be Null or Empty.
	 * @param current Cannot be Null but can be empty.
	 */
	public void UpdateInfo(String[] full, String[] current) {
		if(full==null || current == null)return;
		if(Panel != null)remove(Panel);
		Panel = new JPanel();
		Panel.setLayout(new GridLayout(0,2));

		fullList = full;
		TreeSet<heads> Treetemp = new TreeSet<heads>();
		Treetemp.clear();
		for(int k=0;k<full.length;k++){
		for(int i=0 ; i<current.length;i++){
			if(full[k].equalsIgnoreCase(current[i]))
			Treetemp.add(new heads(current[i],k));
		}
		}
		currentSelected =Treetemp;

		for( int i =0 ;i<full.length;i++){
			String f= full[i];
			JCheckBox temp = new JCheckBox(f);
			for(String c: current){
				if(f.equals(c)){
					temp.setSelected(true);

				}
			}
			temp.addActionListener(new clickListener(f,i));
			Panel.add(temp);
		}
		this.add(Panel);

		HOST.hidePanel();
		HOST.hidePanel();
	}

	/**
	 * The Action Listener for the header selection in the header panel.
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
			boolean temp = currentSelected.add(head);
			if(!temp){
				currentSelected.remove(head);
			}
		}

	}


	/**
	 * Is a subclass to help sort the headers so they always appear in order.
	 * @author schmidmatt
	 *
	 */
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

	/**
	 * Controls the size of the panel in the screen its called once were changed.
	 * @param size
	 */
	public void setSizeOverride(Dimension size) {
		this.setMinimumSize(size);
		this.setPreferredSize(size);
		Dimension panDimension= new Dimension(size.width,100);
		ButtonsPanel.setMinimumSize(panDimension);
		ButtonsPanel.setPreferredSize(panDimension);
		ButtonsPanel.setMaximumSize(panDimension);
		validate();
	}

}
