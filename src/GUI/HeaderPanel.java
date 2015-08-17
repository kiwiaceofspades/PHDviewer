/**
 *
 */
package GUI;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

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

	private String[] currentSelected= null;
	private String[] fullList = null;

	private JPanel Panel;

	public HeaderPanel(Dimension tt, PhDViewer main){
		HOST = main;
		tt.width = 300;
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		this.setPreferredSize(tt);
		JButton button = new JButton("Apply Header Chnages");
		button.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				HOST.changeTableHead(currentSelected);
			}

		});
		this.add(button);
	}

	public void UpdateInfo(String[] full, String[] current) {

		Panel = new JPanel();
		Panel.setLayout(new GridLayout(0,2));
		fullList = full;
		currentSelected =current;

		for( int i =0 ;i<full.length;i++){
			String f= full[i];
			JCheckBox temp = new JCheckBox(f);
			for(String c: current){
				if(f.equals(c)) temp.setSelected(true);
			}
			temp.addActionListener(new clickListener(f,i));
			Panel.add(temp);
		}



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

		private String header;
		private int Index;
		public clickListener(String f,int index) {
			header = f;
			Index = index;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			int i;
			for(i =0; i< fullList.length;i++){
				if(fullList[i].equals(currentSelected[0])){
					break;
				}
			}

			if(Index<i){
				/*
				 * Add it to the beginning of the currentSelection
				 */
				String[] temp = new String[currentSelected.length+1];
				temp[0]=header;
				for(int k =1; k< temp.length ;k++){
					temp[k]=currentSelected[k-1];
				}
			}else if (Index < currentSelected.length-1){
				/*
				 * Adding it to somewhere in the middle of the currentSelection
				 */


			}else {
				/*
				 * Must be at the end
				 */
			}


		}

	}


}
