package GUI;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneLayout;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.JTableHeader;

import System.CurrentFullyRegistered;
import System.PhDData;
import System.Student;

/**
 *
 */

/**
 * @author schmidmatt
 *
 */
public class Table extends JPanel {

	PhDViewer HOST;
	PhDData DATA;
	String[] fullHead = {"Name", "ID", "Degree","EFTS","Primary Supervisor", "Supervision Split 1",
			"Secondary Supervisor","Supervision Split 2","Third Supervisor",
			"Supervision Split 3","Scholarship", "Start Date",
							"PhD Proposal Submission", "PhD Proposal Seminar",
							"PhD Proposal Confirmation Date","Suspension Dates",
							"Thesis Submission And Examiners Appointed Date", "FGR Completes Examination",
							"Revisions Finalised", "Deposited in Library", "Origin", "Notes"};

	String[] CurrentHead;
	JTable table = new JTable();;
	JScrollPane Scroll;

	/**
	 *
	 * @param dATA2 is a ArrayList of Students
	 */
	public Table (PhDData dATA2, String[] Header, PhDViewer host){

		HOST = host;
		DATA =dATA2;
		String[][] Data_String= {{"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22"},
				{"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22"}};
		if( DATA != null)
			Data_String = setupData();

		if( Header == null) {
			CurrentHead = fullHead;
			}
		else {CurrentHead = Header;}


		table = new JTable(Data_String,CurrentHead);
		table.setAutoResizeMode(0);
	//JTableHeader header =
		//table.getTableHeader().(new Dimension(30,50));
		//table.getTableHeader().setPreferredSize(new Dimension(30,50));
		//header.setPreferredSize(new Dimension(30,50));
//		int[] widths = DATA.getCurrentFullyRegistered().getColumnWidth(fullHead);
//		for( int i=0 ; i<table.getColumnModel().getColumnCount()-1 ;i++){
//
//			table.getColumnModel().getColumn(i).setPreferredWidth();
//		}
		table.setFillsViewportHeight(true);
//		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
//
//			@Override
//			public void valueChanged(ListSelectionEvent e) {
//				//e.
//			//	getData(e.getFirstIndex(),e.getLastIndex());
//			}
//			
//		});

		Scroll = new JScrollPane(table);


		this.setLayout(new BorderLayout());
		add(Scroll,BorderLayout.CENTER);


	}

	private String[][] setupData(){
		ArrayList<String[]> Data = new ArrayList<String[]>();
		String[] temp;
		CurrentFullyRegistered Full = DATA.getCurrentFullyRegistered();
		for(Student s: Full.getStudents()){
			 temp = s.getValues(fullHead);
			 Data.add(temp);
		}
		String [][] tat = new String[1][1];
		return Data.toArray(tat);
	}
	public void setSizeOver(Dimension size) {
		this.setMinimumSize(size);
		Scroll.setMinimumSize(size);
		Scroll.setPreferredSize(size);
		table.setMinimumSize(size);
	}
	
	private void getData(int fist,int last){
		System.out.println(fist+"|"+last);
	}


}
