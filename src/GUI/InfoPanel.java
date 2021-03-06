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
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


public class InfoPanel extends JPanel {


	private static final long serialVersionUID = -1098644150408872540L;

	/*
	 * A link to the main host of the JPanel
	 * so that we can pass methods around the window
	 */
	private PhDViewer HOST;

	/*
	 * info is all the data that is
	 */
	private String[][] info;


	private JScrollPane scroll;

	private ArrayList<JPanel> Panels =null;


	/*
	 * The apply button still thinking if i need this stored here
	 */
	private JButton apply;
	private JPanel pan;

	/*
	 * Are we adding or removing
	 */
	private boolean adding;

	/*
	 * Which table are we looking at currently
	 */
	private String Table;


	/**
	 *
	 * @param hostSize The Size of the host to configure the size of this panel.
	 * @param Main Who is the host of this Panel.
	 */
	public InfoPanel(Dimension hostSize,PhDViewer Main){
		HOST = Main;
		hostSize.width = 300;
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		this.setPreferredSize(hostSize);
		pan = new JPanel();
		apply = new JButton("Apply Changes");
		apply.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(adding){
					HOST.add(info,Table);
				} else {
					HOST.edit(info,Table);
				}
			}
		});
		pan.add(apply);

		JButton but = new JButton("Highlight");
		but.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				HOST.makeRed(info,Table);
			}

		});
		pan.add(but);
		Dimension panDimension= new Dimension(hostSize.width,40);
		pan.setMinimumSize(panDimension);
		pan.setPreferredSize(panDimension);
		pan.setMaximumSize(panDimension);
		//pan.setBorder(BorderFactory.createLineBorder(Color.RED));
		add(pan);
	}

	/**
	 * Updates the Contents of the info panel every time it is run it will delete everything and refresh all data.
	 * @param INFO The information on the Row and the Headers that this information relates to
	 * @param b	Weather this is a add or not.
	 * @param table Which table are we working on.
	 */
	public void updateInfo (String[][] INFO, boolean b, String table){
		adding = b;
		Table =table;
		/*
		 * Wiping out any information that was
		 * contained with in this area before.
		 */
		if(scroll != null)
			this.remove(scroll);
		if(Panels != null){
			for(int k =0; k<Panels.size();k++){
				if(Panels.get(k)!=null){
					this.remove(Panels.get(k));
				}
			}
		}

		info = INFO;
		scroll = new JScrollPane();



		JPanel panel = new JPanel();

		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));

		Panels = new ArrayList<JPanel>();
		/*
		 * Adding the new information to the Information Panel;
		 */
		for(int i =0 ;i<info.length;i++){

			JPanel temp = new JPanel();
			JTextArea Data = new JTextArea(info[i][0]);
			Data.setLineWrap(true);
			Data.setWrapStyleWord(true);
			Data.setEditable(false);

			JTextField Info = new JTextField(info[i][1]);
			Info.getDocument().addDocumentListener(new DocListen(i,Info));

			temp.setLayout(new BorderLayout());
			temp.add(Data, BorderLayout.WEST);
			temp.add(Info,BorderLayout.CENTER);

			Panels.add(temp);
			panel.add(temp);

		}

		scroll.getViewport().add(panel);
		this.add(scroll);
		repaint();
	}

	/**
	 *
	 * @return
	 */
	public String[][] getCurrentData(){
		return info;
	}


	/**
	 *
	 * @author Matthew Schmidt
	 *
	 */
	private class DocListen implements DocumentListener{

		private int index;
		private JTextField INFO;



		public DocListen(int i, JTextField info) {
			index = i;
			INFO = info;
		}

		@Override
		public void insertUpdate(DocumentEvent e) {
			//System.out.println(INFO.getText());
			info[index][1] = INFO.getText();

		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			//System.out.println(INFO.getText());
			info[index][1] = INFO.getText();

		}

		@Override
		public void changedUpdate(DocumentEvent e) {
			// TODO Auto-generated method stub

		}


	}

	/**
	 * Sets the size of the Panel.
	 * @param size
	 */
	public void setSizeOverride(Dimension size) {
		this.setMinimumSize(size);
		this.setPreferredSize(size);
		Dimension panDimension = new Dimension(size.width,40);
		pan.setMinimumSize(panDimension);
		pan.setPreferredSize(panDimension);
		pan.setMaximumSize(panDimension);
		pan.validate();
		validate();

	}
}
