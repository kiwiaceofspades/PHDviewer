package GUI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class InfoPanel extends JPanel {

	/**
	 * A link to the main host of the JPanel
	 * so that we can pass methods around the window
	 */
	private PhDViewer HOST;

	/**
	 * info is all the data that is
	 */
	private String[][] info;


	private JScrollPane scroll;

	private ArrayList<JPanel> Panels =null;

	private ArrayList<JTextField> textBox = null;

	private JButton apply;

	private boolean adding;

	public InfoPanel(Dimension tt,PhDViewer Main){
		HOST = Main;
		tt.width = 300;
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		this.setPreferredSize(tt);
		apply = new JButton("Apply Changes");
		apply.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(adding){
					HOST.add(info);
				} else {
					HOST.edit(info);
					}
				}
		});
		this.add(apply);

	}


	public void updateInfo (String[][] INFO, boolean b){
		adding = b;
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

		textBox = new ArrayList<JTextField>();

		JPanel panel = new JPanel();

		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));

		Panels = new ArrayList<JPanel>();

		for(int i =0 ;i<info.length;i++){

			JPanel temp = new JPanel();
			JTextArea Data = new JTextArea(info[i][0]);
			//temp.setPreferredSize(new Dimension(90,90));
			Data.setLineWrap(true);
			Data.setWrapStyleWord(true);
			Data.setEditable(false);

			JTextField Info = new JTextField(info[i][1]);
			Info.getDocument().addDocumentListener(new DocListen(i,Info));
			textBox.add(Info);


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
}
