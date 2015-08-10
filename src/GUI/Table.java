package GUI;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneLayout;

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
	ArrayList<Student> DATA;
	String[] fullHead = {"Name", "ID", "Degree","EFTS","Primary Supervisor", "Supervision split 1", "Secondary Supervisor",
							"Supervision split 2","Third Supervisor","Supervision split 3","Scholarship", "Start Date",
							"PhD Proposal Submission", "PhD Proposal Seminar", "Phd Proposal Confirmation Date",
							"Suspension Dates", "Thesis Submission & Examiners Appointed Date", "FGR Completes Examination",
							"Revisions Finalised", "Deposited in Library", "Origin", "Notes"};

	String[] CurrentHead;
	JTable table = new JTable();;
	JScrollPane Scroll;
	/**
	 *
	 * @param Data is a ArrayList of Students
	 */
	public Table (ArrayList<Student> Data, String[] Header, PhDViewer host){

		HOST = host;
		DATA =Data;
		String[][] Data_String= {{"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22"},
				{"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22"}};
		if( DATA != null) Data_String = setupData();

		if( Header == null) {
			CurrentHead = fullHead;
			}
		else {CurrentHead = Header;}

		table = new JTable(Data_String,CurrentHead);
		table.setAutoResizeMode(0);
		 Scroll = new JScrollPane(table);
		 table.setFillsViewportHeight(true);
		 this.add(Scroll,BorderLayout.CENTER);


	}
	private String[][] setupData(){
		ArrayList<String[]> Data = new ArrayList<String[]>();
		String[] temp;
		for(Student s: DATA){
			 temp = s.getValues(CurrentHead);
			 Data.add(temp);
		}
		return null;
	}
	public void setSizeOver(Dimension size) {
		this.setMinimumSize(size);
		Scroll.setMinimumSize(size);
		Scroll.setPreferredSize(size);
		table.setMinimumSize(size);





	}



}
