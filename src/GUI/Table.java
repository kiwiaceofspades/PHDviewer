package GUI;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneLayout;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.JTableHeader;

import com.sun.org.omg.CORBA.ValueDefPackage.FullValueDescription;

import System.CurrentFullyRegistered;
import System.ECSStudent;
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

	private PhDViewer HOST;
	private PhDData DATA;
	private String[] fullHead = {"Name", "ID", "Degree","EFTS","Primary Supervisor", "Supervision Split 1",
			"Secondary Supervisor","Supervision Split 2","Third Supervisor",
			"Supervision Split 3","Scholarship", "Start Date",
							"PhD Proposal Submission", "PhD Proposal Seminar",
							"PhD Proposal Confirmation Date","Suspension Dates",
							"Thesis Submission And Examiners Appointed Date", "FGR Completes Examination",
							"Revisions Finalised", "Deposited in Library", "Origin", "Notes"};
	private String[] LabelHead = {"Name", "ID", "Degree","EFTS","Primary Supervisor", "Supervision Split 1",
			"Secondary Supervisor","Supervision Split 2","Third Supervisor",
			"Supervision Split 3","Scholarship", "Start Date",
							"PhD Proposal Submission", "PhD Proposal Seminar",
							"PhD Proposal Confirmation Date","Suspension Dates",
							"Thesis Submission And Examiners Appointed Date", "FGR Completes Examination",
							"Revisions Finalised", "Deposited in Library", "Origin", "Notes"};

	private String[][] Data_String= {{"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22"},
			{"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22"}};

	private String[] CurrentHead;

	private JTable table = new JTable();;
	private JScrollPane Scroll;

	/**
	 *
	 * @param dATA2 is a ArrayList of Students
	 */
	public Table (PhDData dATA2, String[] Header, PhDViewer host){

		HOST = host;
		DATA =dATA2;


		if( Header == null) {
			CurrentHead = fullHead;
			}
		else {
			CurrentHead = Header;}

		

		setupTable();


	}

	/**
	 *
	 */
	private void setupTable() {
		if(Scroll!=null){
			this.remove(Scroll);
			Scroll=null;
		}
		if( DATA != null)
			Data_String = setupData();

		table = new JTable(Data_String,CurrentHead);
		table.setAutoResizeMode(0);

		for( int i=0 ; i<table.getColumnModel().getColumnCount()-1 ;i++){

			table.getColumnModel().getColumn(i).setMinWidth(100);;
		}
		table.setFillsViewportHeight(true);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent e) {
				//System.out.println("Update");
			getData(table.getSelectedRow());
			}

		});

		Scroll = new JScrollPane(table);


		this.setLayout(new BorderLayout());
		add(Scroll,BorderLayout.CENTER);

		validate();
		repaint();
	}
	/**
	 * Gets the data out of the database and formats it into a string array
	 * so that the jtable can show it.
	 * @return
	 */
	private String[][] setupData(){
		ArrayList<String[]> Data = new ArrayList<String[]>();
		String[] temp;
		CurrentFullyRegistered Full = DATA.getCurrentFullyRegistered();
		for(Student s: Full.getStudents()){
			 temp = s.getValues(CurrentHead);
			 Data.add(temp);
		}
		String [][] tat = new String[1][1];
		return Data.toArray(tat);
	}

	/**
	 * Controls the size of the panel so it all looks correct;
	 * @param size
	 */
	public void setSizeOver(Dimension size) {
		this.setMinimumSize(size);
		Scroll.setMinimumSize(size);
		Scroll.setPreferredSize(size);
		table.setMinimumSize(size);
	}



	/**
	 * gets a signal line out of the table for the info panel to display
	 * @param fist
	 */
	private void getData(int fist){

		CurrentFullyRegistered Full = DATA.getCurrentFullyRegistered();
		Student temp = Full.getStudents().get(fist);

		String[] info = temp.getValues(fullHead);
		String [][] fullInfo = new String [fullHead.length][2];
		for (int i = 0 ; i< fullHead.length;i++){
			fullInfo[i][0]=fullHead[i];
			fullInfo[i][1]=info[i];
		}
		HOST.UpInfo(fullInfo,false);

	}
	public void Add(String[][] axis) {
		DATA.makeChanges('a',axis,"CurrentFullyRegistered");
		System.out.println("Add");
		for(String[] a: axis){
			System.out.println(a[0] +"= "+a[1]);
		}
		setupTable();

	}
	public void Edit(String[][] axis) {
		DATA.makeChanges('e',axis,"CurrentFullyRegistered");
		System.out.println("Edit");
		for(String[] a: axis){
			System.out.println(a[0] +"= "+a[1]);
		}

		setupTable();

	}

	public void Remove(){

		if(table.getSelectedRow()==-1){
			JOptionPane.showMessageDialog(HOST, "You have not selected anything to remove!!","ERROR!!",JOptionPane.ERROR_MESSAGE);
		}else{

		DATA.makeChanges('r',getRemovedData(Data_String[table.getSelectedRow()]),"CurrentFullyRegistered");
		System.out.println("Cleaning");
		for(String a: Data_String[table.getSelectedRow()]){
			System.out.println(a);
		}
			setupTable();
		}

	}

	private String[][] getRemovedData(String[] strings) {
		String[][] temp = new String[this.CurrentHead.length][2];
		for(int i=0 ; i<this.CurrentHead.length;i++ ){
			temp[i][0] = this.CurrentHead[i];
			temp[i][1] = strings[i];
		}
		return temp;
	}

	public String[] getCurrentHead() {
		return CurrentHead;
	}
	public void setCurrentHead(String[] currentHead) {
		CurrentHead = currentHead;
	}




}
