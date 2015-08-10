package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class InfoPanel extends JPanel {

	private String[][] info;
	private ArrayList<JPanel> Panels =null;
	public InfoPanel(Dimension tt){
		tt.width = 300;
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		this.setPreferredSize(tt);


	}


	public void updateInfo (String[][] INFO){
		if(Panels != null){
			for(int k =0; k<Panels.size();k++){
				if(Panels.get(k)!=null){
					this.remove(Panels.get(k));
				}
			}
		}
		info = INFO;
		Panels = new ArrayList<JPanel>();
		for(int i =0 ;i<info.length;i++){
			JPanel temp = new JPanel();
			JLabel Data = new JLabel(info[i][0]);
			JTextField Info = new JTextField(info[i][1]);
			temp.setLayout(new BorderLayout());
			temp.add(Data, BorderLayout.WEST);
			temp.add(Info,BorderLayout.CENTER);
			Panels.add(temp);
			this.add(temp);
			}
		repaint();
	}






}
