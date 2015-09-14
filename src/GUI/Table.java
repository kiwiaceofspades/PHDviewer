package GUI;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.EventObject;

import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import System.CurrentFullyRegistered;
import System.CurrentProvisionallyRegisteredStudents;
import System.NotFullyAdmitted;
import System.PhDData;
import System.PhDProposalUnderExamination;
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
	private String[][] NotFullyAdmittedData;
	private String[][] CurrentProvisionallyRegisteredStudentsData;
	private String[][] PhDPropsalUnderExaminationData;
	private String[][] CurrentFullyRegisteredData;
	private String[][] UnderExaminationData;



	/*
	 * JTables for clobal links to them as they are called in other methods
	 */
	private JTable NotFullyAdmittedTable;
	private JTable CurrentProvisionallyRegisteredStudentsTable;
	private JTable PhDPropsalUnderExaminationTable;
	private JTable UnderExaminationTable;
	private JTable CurrentFullyRegisteredTable;

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
	 * Sets up the Data in the main table is called everytime the table is updated
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
			NotFullyAdmittedData =setupNotFullyAdmittedData();
			CurrentProvisionallyRegisteredStudentsData=setupCurrentProvisionallyRegisteredStudentsData();
			PhDPropsalUnderExaminationData = setupNotPhDPropsalUnderExaminationData();
			UnderExaminationData= setupUnderExaminationData();
			CurrentFullyRegisteredData = setupCurrentFullyRegisteredData();
		}


		NotFullyAdmittedTable = setTable("NotFullyAdmitted");
		PhDPropsalUnderExaminationTable = setTable("PhDPropsalUnderExamination");
		UnderExaminationTable = setTable("UnderExamination");
		CurrentFullyRegisteredTable = setTable("CurrentFullyRegistered");
		CurrentProvisionallyRegisteredStudentsTable= setTable("CurrentProvisionallyRegisteredStudents");


		/*
		 * NotFullyAdmittedTable;
		 * CurrentProvisionallyRegisteredStudentsTable;
		 * PhDPropsalUnderExaminationTable;
		 * CurrentFullyRegisteredTable;
		 * UnderExaminationTable;
		 */


		/*
		 * Creating alocal Panel to hows each of the tables
		 */
		locPanel.setLayout(new BoxLayout(locPanel,BoxLayout.Y_AXIS));

		JLabel temp = new JLabel("Not Fully Admitted Table");
		locPanel.add(temp);
		locPanel.add(NotFullyAdmittedTable.getTableHeader());
		locPanel.add(NotFullyAdmittedTable);

		temp = new JLabel("Current Fully Registered Table");
		locPanel.add(temp);
		locPanel.add(CurrentFullyRegisteredTable.getTableHeader());
		locPanel.add(CurrentFullyRegisteredTable);

		temp = new JLabel("Phd Propsal Under Examination Table");
		locPanel.add(temp);
		locPanel.add(PhDPropsalUnderExaminationTable.getTableHeader());
		locPanel.add(PhDPropsalUnderExaminationTable);

		temp = new JLabel("CurrentProvisionallyRegisteredStudentsTable");
		locPanel.add(temp);
		locPanel.add(CurrentProvisionallyRegisteredStudentsTable.getTableHeader());
		locPanel.add(CurrentProvisionallyRegisteredStudentsTable);

		temp = new JLabel("Under Examination Table");
		locPanel.add(temp);
		locPanel.add(UnderExaminationTable.getTableHeader());
		locPanel.add(UnderExaminationTable);

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

	private JTable setTable(String Table){
		JTable temp = getTableData(Table);

		temp.setDragEnabled(false);
		temp.setAutoResizeMode(0);
		for(int i=0; i<temp.getColumnModel().getColumnCount()-1;i++){
			temp.getColumnModel().getColumn(i).setCellEditor(new DefaultCellEditor(new JTextField()){

				@Override
				public boolean isCellEditable(EventObject anEvent){
					return false;
				}

			});
			temp.getColumnModel().getColumn(i).setMinWidth(100);
		}
		temp.setFillsViewportHeight(true);
		temp.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		temp.getSelectionModel().addListSelectionListener(getSelectionListener(Table));
		return temp;






	}

	/**
	 * @return
	 */
	private ListSelectionListener getSelectionListener(final String string) {
		if(string.equalsIgnoreCase("CurrentFullyRegistered")){
			return new ListSelectionListener(){

				@Override
				public void valueChanged(ListSelectionEvent e) {
					System.out.println("CurrentFullyRegistered");
					NotFullyAdmittedTable.getSelectionModel().clearSelection();
					PhDPropsalUnderExaminationTable.getSelectionModel().clearSelection();
					UnderExaminationTable.getSelectionModel().clearSelection();
					CurrentProvisionallyRegisteredStudentsTable.getSelectionModel().clearSelection();
					validate();
					int temp = CurrentFullyRegisteredTable.getSelectedRow();
					if(temp>=0){
						getData(temp,"CurrentFullyRegistered");
					}
				}
			};
		}else if(string.equalsIgnoreCase("NotFullyAdmitted")){
			return new ListSelectionListener(){

				@Override
				public void valueChanged(ListSelectionEvent e) {
					System.out.println(string);
					PhDPropsalUnderExaminationTable.getSelectionModel().clearSelection();;
					UnderExaminationTable.getSelectionModel().clearSelection();;
					CurrentFullyRegisteredTable.getSelectionModel().clearSelection();
					CurrentProvisionallyRegisteredStudentsTable.getSelectionModel().clearSelection();
					validate();
					int temp = NotFullyAdmittedTable.getSelectedRow();

					if(temp >=0)
						getData(temp,string);
				}

			};
		}else if(string.equalsIgnoreCase("PhDPropsalUnderExamination")){
			return new ListSelectionListener(){

				@Override
				public void valueChanged(ListSelectionEvent e) {
					System.out.println(string);
					//PhDPropsalUnderExaminationTable.getSelectionModel().clearSelection();;
					NotFullyAdmittedTable.getSelectionModel().clearSelection();
					UnderExaminationTable.getSelectionModel().clearSelection();;
					CurrentFullyRegisteredTable.getSelectionModel().clearSelection();
					CurrentProvisionallyRegisteredStudentsTable.getSelectionModel().clearSelection();
					validate();
					int temp = PhDPropsalUnderExaminationTable.getSelectedRow();
					if(temp >=0)
						getData(temp,string);
				}

			};
		}else if(string.equalsIgnoreCase("CurrentProvisionallyRegisteredStudents")){
			return new ListSelectionListener(){

				@Override
				public void valueChanged(ListSelectionEvent e) {
					//System.out.println("Update");

					System.out.println(string);
					NotFullyAdmittedTable.getSelectionModel().clearSelection();
					PhDPropsalUnderExaminationTable.getSelectionModel().clearSelection();
					CurrentFullyRegisteredTable.getSelectionModel().clearSelection();
					UnderExaminationTable.getSelectionModel().clearSelection();
					validate();
					int temp = CurrentProvisionallyRegisteredStudentsTable.getSelectedRow();
					if(temp>=0)
						getData(temp,string);
				}

			};
		} else if(string.equalsIgnoreCase("UnderExamination")){
			return new ListSelectionListener(){

				@Override
				public void valueChanged(ListSelectionEvent e) {
					System.out.println(string);
					NotFullyAdmittedTable.getSelectionModel().clearSelection();
					PhDPropsalUnderExaminationTable.getSelectionModel().clearSelection();
					CurrentFullyRegisteredTable.getSelectionModel().clearSelection();
					CurrentProvisionallyRegisteredStudentsTable.getSelectionModel().clearSelection();
					validate();
					int temp = UnderExaminationTable.getSelectedRow();
					if(temp >=0)
						getData(temp,string);
				}

			};
		}
		return null;

	}

	private JTable getTableData(String string) {
		JTable temp = null;
		if(string.equalsIgnoreCase("CurrentFullyRegistered")){
			temp = new JTable(CurrentFullyRegisteredData,CurrentHead);
		}else if(string.equalsIgnoreCase("NotFullyAdmitted")){
			temp = new JTable(NotFullyAdmittedData,CurrentHead);
		}else if(string.equalsIgnoreCase("PhDPropsalUnderExamination")){
			temp = new JTable(PhDPropsalUnderExaminationData,CurrentHead);
		}else if(string.equalsIgnoreCase("CurrentProvisionallyRegisteredStudents")){
			temp = new JTable(CurrentProvisionallyRegisteredStudentsData,CurrentHead);
		} else if(string.equalsIgnoreCase("UnderExamination")){
			temp = new JTable(UnderExaminationData,CurrentHead);
		}
		return temp;
	}


	private String[][] setupNotPhDPropsalUnderExaminationData() {
		ArrayList<String[]> Data = new ArrayList<String[]>();
		String[] temp;
		PhDProposalUnderExamination Full = DATA.getPhDProposalUnderExamination();
		for(Student s: Full.getStudents()){
			temp = s.getValues(CurrentHead);
			Data.add(temp);
		}
		String [][] tat = new String[1][1];
		return Data.toArray(tat);
	}

	private String[][] setupNotFullyAdmittedData() {
		ArrayList<String[]> Data = new ArrayList<String[]>();
		String[] temp;
		NotFullyAdmitted Full = DATA.getNotFullyAdmitted();
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
		//CurrentFullyRegisteredTable.setMinimumSize(size);
		validate();
	}



	/**
	 * gets a signal line out of the table for the info panel to display
	 * @param fist
	 * @param string
	 */
	private void getData(int fist, String string){
		Student temp = null;

		if(string.equalsIgnoreCase("CurrentFullyRegistered")){
			CurrentFullyRegistered Full = DATA.getCurrentFullyRegistered();
			temp = Full.getStudents().get(fist);
		}else if(string.equalsIgnoreCase("NotFullyAdmitted")){
			NotFullyAdmitted Full = DATA.getNotFullyAdmitted();
			temp = Full.getStudents().get(fist);
		}else if(string.equalsIgnoreCase("PhDPropsalUnderExamination")){
			PhDProposalUnderExamination Full = DATA.getPhDProposalUnderExamination();
			temp = Full.getStudents().get(fist);
		}else if(string.equalsIgnoreCase("CurrentProvisionallyRegisteredStudents")){
			CurrentProvisionallyRegisteredStudents Full = DATA.getCurrentProvisionallyRegisteredStudents();
			temp = Full.getStudents().get(fist);
		} else if(string.equalsIgnoreCase("UnderExamination")){
			UnderExamination Full = DATA.getUnderExamination();
			temp = Full.getStudents().get(fist);

		}

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
		//		for(String[] a: axis){
		//			System.out.println(a[0] +"= "+a[1]);
		//		}
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
		//		for(String[] a: axis){
		//			System.out.println(a[0] +"= "+a[1]);
		//		}

		setupTable();

	}

	/**
	 * This removes a row from the table it work os a principle of last table
	 * selected knows what you wanted you have selected since each table is a
	 * seperate table.
	 * @param currentTable Name Of the Table that has the information on what line is to be removed.
	 */
	public void Remove(String currentTable){

		if(CurrentFullyRegisteredTable.getSelectedRow()==-1){
			JOptionPane.showMessageDialog(HOST, "You have not selected anything to remove!!","ERROR!!",JOptionPane.ERROR_MESSAGE);
		}else{
			String[][] tempData=null;
			if(currentTable.equals("CurrentProvisionallyRegisteredStudents")) tempData = this.CurrentProvisionallyRegisteredStudentsData;
			if(currentTable.equals("CurrentFullyRegistered"))tempData=this.CurrentFullyRegisteredData;
			if(currentTable.equals("UnderExamination"))tempData=this.UnderExaminationData;



			JTable temp=null;
			if(currentTable.equals("CurrentProvisionallyRegisteredStudents")) temp = this.CurrentProvisionallyRegisteredStudentsTable;
			if(currentTable.equals("CurrentFullyRegistered"))temp=this.CurrentFullyRegisteredTable;
			if(currentTable.equals("UnderExamination"))temp=this.UnderExaminationTable;

			DATA.makeChanges('r',getRemovedData(tempData[temp.getSelectedRow()]),currentTable);
			System.out.println("Cleaning");
			for(String a: CurrentFullyRegisteredData[CurrentFullyRegisteredTable.getSelectedRow()]){
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
		this.setupTable();
	}




}
