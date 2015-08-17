package GUI;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import System.CurrentFullyRegistered;
import System.CurrentProvisionallyRegisteredStudents;
import System.PhDData;
import System.Student;
import System.UnderExamination;

/**
 *
 */

/**
 * @author schmidmatt
 *
 */
public class Table extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 6200720797604161514L;
	private PhDViewer HOST;
	private PhDData DATA;

	/*
	 * Headers that will be use in showing the data in the
	 * PHData the fullHEad is a back up in case nothing was passed
	 */
	public final String[] fullHead = {"Name", "ID", "Degree","EFTS","Primary Supervisor", "Supervision Split 1",
			"Secondary Supervisor","Supervision Split 2","Third Supervisor",
			"Supervision Split 3","Scholarship", "Start Date",
			"PhD Proposal Submission", "PhD Proposal Seminar",
			"PhD Proposal Confirmation Date","Suspension Dates",
			"Thesis Submission And Examiners Appointed Date", "FGR Completes Examination",
			"Revisions Finalised", "Deposited in Library", "Origin", "Notes"};
	private String[] CurrentHead;

	/*
	 * Data Values keeps a hard back up of the table so
	 * i don't have to dig it up each time i want to make a change
	 */
	private String[][] currentFullyRegisteredData= {{"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22"},
			{"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22"}};
	private String[][] UnderExaminationData;
	private String[][] CurrentProvisionallyRegisteredStudentsData;


	/*
	 * JTables for clobal links to them as they are called in other methods
	 */
	private JTable currentFullyRegisteredTable;
	private JTable UnderExaminationTable;
	private JTable CurrentProvisionallyRegisteredStudentsTable;


	private JScrollPane Scroll;




	/**
	 *
	 * @param dATA2 is a ArrayList of Students
	 * @param Header is the header data that the table will look for in the PHDData
	 * @param host Is a link to the HOST frame of this panel used for
	 *  call back and passing information arround the program
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
	 * Sets up the Data in the main tabel is called everytime the table is updated
	 * i got lazy and i feel there wont be used on a big table or having massive
	 * changes happend in mass.
	 *
	 */
	private void setupTable() {

		if(Scroll!=null){
			this.remove(Scroll);
			Scroll=null;
		}
		JPanel locPanel= new JPanel();

		if( DATA != null){
			UnderExaminationData= setupUnderExaminationData();
			currentFullyRegisteredData = setupCurrentFullyRegisteredData();
			CurrentProvisionallyRegisteredStudentsData=setupCurrentProvisionallyRegisteredStudentsData();


		}
		/*
		 * Setting up the Under EXamination Table
		 */
		UnderExaminationTable = new JTable(UnderExaminationData,CurrentHead);
		UnderExaminationTable.setAutoResizeMode(0);

		for( int i=0 ; i<UnderExaminationTable.getColumnModel().getColumnCount()-1 ;i++){
			UnderExaminationTable.getColumnModel().getColumn(i).setMinWidth(100);
		}
		UnderExaminationTable.setFillsViewportHeight(true);
		UnderExaminationTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent e) {

				getData(UnderExaminationTable.getSelectedRow(),"UnderExaminationTable");
			}

		});

		/*
		 * Setting up the current fully Registered table
		 */
		currentFullyRegisteredTable = new JTable(currentFullyRegisteredData,CurrentHead);
		currentFullyRegisteredTable.setAutoResizeMode(0);
		for(int i=0 ; i<currentFullyRegisteredTable.getColumnModel().getColumnCount()-1 ;i++){
			currentFullyRegisteredTable.getColumnModel().getColumn(i).setMinWidth(100);
		}
		currentFullyRegisteredTable.setFillsViewportHeight(true);
		currentFullyRegisteredTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent e) {
				//System.out.println("Update");
				getData(currentFullyRegisteredTable.getSelectedRow(),"currentFullyRegistered");
			}

		});

		/*
		 * Setting up the current Provisionally Registered Students Tabel
		 */
		CurrentProvisionallyRegisteredStudentsTable = new JTable(CurrentProvisionallyRegisteredStudentsData,CurrentHead);
		CurrentProvisionallyRegisteredStudentsTable.setAutoResizeMode(0);
		for(int i=0; i<CurrentProvisionallyRegisteredStudentsTable.getColumnModel().getColumnCount()-1;i++){
			CurrentProvisionallyRegisteredStudentsTable.getColumnModel().getColumn(i).setMinWidth(100);
		}
		CurrentProvisionallyRegisteredStudentsTable.setFillsViewportHeight(true);
		CurrentProvisionallyRegisteredStudentsTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent e) {
				//System.out.println("Update");
				getData(CurrentProvisionallyRegisteredStudentsTable.getSelectedRow(),"CurrentProvisionallyRegisteredStudents");
			}

		});

		/*
		 * Creating alocal Panel to hows each of the tables
		 */
		locPanel.setLayout(new BoxLayout(locPanel,BoxLayout.Y_AXIS));

		locPanel.add(UnderExaminationTable.getTableHeader());
		locPanel.add(UnderExaminationTable);

		locPanel.add(currentFullyRegisteredTable.getTableHeader());
		locPanel.add(currentFullyRegisteredTable);

		locPanel.add(CurrentProvisionallyRegisteredStudentsTable.getTableHeader());
		locPanel.add(CurrentProvisionallyRegisteredStudentsTable);
		/*
		 * Putting the local panel into the scrollPane as we can
		 * be sure the table goes beyond the scope of the screen
		 */
		Scroll = new JScrollPane(locPanel);


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
	private String[][] setupCurrentFullyRegisteredData(){
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
	 * Gets the data out of the database and formats it into a string array
	 * so that the jtable can show it.
	 * @return
	 */
	private String[][] setupUnderExaminationData(){
		ArrayList<String[]> Data = new ArrayList<String[]>();
		String[] temp;
		UnderExamination Full = DATA.getUnderExamination();
		for(Student s: Full.getStudents()){
			temp = s.getValues(CurrentHead);
			Data.add(temp);
		}
		String [][] tat = new String[1][1];
		return Data.toArray(tat);
	}

	/**
	 * Gets the data out of the database and formats it into a string array
	 * so that the jtable can show it.
	 * @return
	 */
	private String[][] setupCurrentProvisionallyRegisteredStudentsData(){
		ArrayList<String[]> Data = new ArrayList<String[]>();
		String[] temp;
		CurrentProvisionallyRegisteredStudents Full = DATA.getCurrentProvisionallyRegisteredStudents();
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
		currentFullyRegisteredTable.setMinimumSize(size);
	}



	/**
	 * gets a signal line out of the table for the info panel to display
	 * @param fist
	 * @param string
	 */
	private void getData(int fist, String string){

		CurrentFullyRegistered Full = DATA.getCurrentFullyRegistered();
		Student temp = Full.getStudents().get(fist);

		String[] info = temp.getValues(fullHead);
		String [][] fullInfo = new String [fullHead.length][2];
		for (int i = 0 ; i< fullHead.length;i++){
			fullInfo[i][0]=fullHead[i];
			fullInfo[i][1]=info[i];
		}
		HOST.UpInfo(fullInfo,string,false);

	}

	/**
	 * Adds a new entrie in the table
	 *
	 * @param axis is a row contained with in the data base with its changed valuse it contains the {{headers,values},{headers,values}}
	 * so if the system is working under partial headers we still know what data points were changed in the PHDdata
	 * @param tabel tells teh PHDdata which table is being edited in the system.
	 */
	public void Add(String[][] axis, String table2) {

		DATA.makeChanges('a',axis,table2);
		System.out.println("Add");
		for(String[] a: axis){
			System.out.println(a[0] +"= "+a[1]);
		}
		setupTable();

	}

	/**
	 * Edits a entrie in the table
	 *
	 * @param axis is a row contained with in the data base with its changed valuse it contains the {{headers,values},{headers,values}}
	 * so if the system is working under partial headers we still know what data points were changed in the PHDdata
	 * @param tabel tells teh PHDdata which table is being edited in the system.
	 */
	public void Edit(String[][] axis, String table) {
		DATA.makeChanges('e',axis,table);
		System.out.println("Edit");
		for(String[] a: axis){
			System.out.println(a[0] +"= "+a[1]);
		}

		setupTable();

	}

	/**
	 * This removes a row from the table it work os a principle of last table
	 * selected knows what you wanted you have selected since each table is a
	 * seperate table.
	 * @param currentTable Name Of the Table that has the information on what line is to be removed.
	 */
	public void Remove(String currentTable){

		if(currentFullyRegisteredTable.getSelectedRow()==-1){
			JOptionPane.showMessageDialog(HOST, "You have not selected anything to remove!!","ERROR!!",JOptionPane.ERROR_MESSAGE);
		}else{
			String[][] tempData=null;
			if(currentTable.equals("CurrentProvisionallyRegisteredStudents")) tempData = this.CurrentProvisionallyRegisteredStudentsData;
			if(currentTable.equals("currentFullyRegistered"))tempData=this.currentFullyRegisteredData;
			if(currentTable.equals("UnderExamination"))tempData=this.UnderExaminationData;



			JTable temp=null;
			if(currentTable.equals("CurrentProvisionallyRegisteredStudents")) temp = this.CurrentProvisionallyRegisteredStudentsTable;
			if(currentTable.equals("currentFullyRegistered"))temp=this.currentFullyRegisteredTable;
			if(currentTable.equals("UnderExamination"))temp=this.UnderExaminationTable;

			DATA.makeChanges('r',getRemovedData(tempData[temp.getSelectedRow()]),currentTable);
			System.out.println("Cleaning");
			for(String a: currentFullyRegisteredData[currentFullyRegisteredTable.getSelectedRow()]){
				System.out.println(a);
			}
			setupTable();
		}

	}

	/**
	 * So this meathod just adds the headers to the data that we are playing
	 * with just to keep the calls and submitions to the PHDData is consistant
	 * @param strings this the list of data that we are removing
	 * @return returns a standized data for the PHDdata remove function
	 */
	private String[][] getRemovedData(String[] strings) {
		String[][] temp = new String[this.CurrentHead.length][2];
		for(int i=0 ; i<this.CurrentHead.length;i++ ){
			temp[i][0] = this.CurrentHead[i];
			temp[i][1] = strings[i];
		}
		return temp;
	}


	/**
	 * Just a getter that will change what data is being shown in the table
	 * @return the currentHead it is a clone and so no changes will effect  the table.
	 */
	public String[] getCurrentHead() {
		return CurrentHead.clone();
	}

	/**
	 * Sets the CurrentHeader information for the table
	 * @param currentHead All changes currently have to be
	 * consistant with what the PHDData is looking if they
	 * are not PHDDAta will throw a Error. FullHead value
	 * contains a list of all the currently available strings
	 */
	public void setCurrentHead(String[] currentHead) {
		CurrentHead = currentHead;
	}




}
