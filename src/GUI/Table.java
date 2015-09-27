package GUI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import javax.swing.table.DefaultTableCellRenderer;

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
	public String[] fullHead = {"Name", "ID", "Degree","EFTS","Primary Supervisor", "Supervision Split 1",
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
	private String[][] PhDProposalUnderExaminationData;
	private String[][] CurrentFullyRegisteredData;
	private String[][] UnderExaminationData;



	/*
	 * JTables for clobal links to them as they are called in other methods
	 */
	private JTable NotFullyAdmittedTable;
	private JTable CurrentProvisionallyRegisteredStudentsTable;
	private JTable PhDProposalUnderExaminationTable;
	private JTable UnderExaminationTable;
	private JTable CurrentFullyRegisteredTable;

	private JScrollPane Scroll;

	private String sortedBy = "none";
	private int SortedColumn = -1;


	private boolean started;

	private int ScrollX,ScrollY;
	/**
	 *
	 * @param dATA2 is a ArrayList of Students
	 * @param Header is the header data that the table will look for in the PHDData
	 * @param host Is a link to the HOST frame of this panel used for
	 *  call back and passing information arround the program
	 */
	public Table (PhDData dATA2, String[] Header, String[] FULLHead, PhDViewer host){
		started = false;
		HOST = host;
		DATA =dATA2;
		if(FULLHead!= null)fullHead = FULLHead;

		if( Header == null) {
			CurrentHead = fullHead;
		}
		else {
			CurrentHead = Header;}

		setupTable();
		started = true;

	}

	/**
	 * Sets up the Data in the main table is called everytime the table is updated
	 * i got lazy and i feel there wont be used on a big table or having massive
	 * changes happend in mass.
	 *
	 */
	public void setupTable() {
		if(started){
			ScrollX = Scroll.getHorizontalScrollBar().getValue();
			ScrollY = Scroll.getVerticalScrollBar().getValue();
		}
		for(int i=0; i<CurrentHead.length;i++){
			if(CurrentHead[i].equalsIgnoreCase(sortedBy)){
				SortedColumn= i;
			}
		}


		if(Scroll!=null){
			this.remove(Scroll);
			Scroll=null;
		}
		JPanel locPanel= new JPanel();

		if( DATA != null){
			NotFullyAdmittedData =setupNotFullyAdmittedData();
			CurrentProvisionallyRegisteredStudentsData=setupCurrentProvisionallyRegisteredStudentsData();
			PhDProposalUnderExaminationData = setupNotPhDProposalUnderExaminationData();
			UnderExaminationData= setupUnderExaminationData();
			CurrentFullyRegisteredData = setupCurrentFullyRegisteredData();
		}


		NotFullyAdmittedTable = setTable("NotFullyAdmitted");
		PhDProposalUnderExaminationTable = setTable("PhDProposalUnderExamination");
		UnderExaminationTable = setTable("UnderExamination");
		CurrentFullyRegisteredTable = setTable("CurrentFullyRegistered");
		CurrentProvisionallyRegisteredStudentsTable= setTable("CurrentProvisionallyRegisteredStudents");


		/*
		 * NotFullyAdmittedTable;
		 * CurrentProvisionallyRegisteredStudentsTable;
		 * PhDProposalUnderExaminationTable;
		 * CurrentFullyRegisteredTable;
		 * UnderExaminationTable;
		 */


		/*
		 * Creating alocal Panel to hows each of the tables
		 */
		locPanel.setLayout(new BoxLayout(locPanel,BoxLayout.Y_AXIS));
		JLabel temp= null;

		temp = new JLabel("Under Examination Table");
		locPanel.add(temp);
		locPanel.add(UnderExaminationTable.getTableHeader());
		locPanel.add(UnderExaminationTable);

		temp = new JLabel("Current Fully Registered Table");
		locPanel.add(temp);
		locPanel.add(CurrentFullyRegisteredTable.getTableHeader());
		locPanel.add(CurrentFullyRegisteredTable);

		temp = new JLabel("Phd Propsal Under Examination Table");
		locPanel.add(temp);
		locPanel.add(PhDProposalUnderExaminationTable.getTableHeader());
		locPanel.add(PhDProposalUnderExaminationTable);

		temp = new JLabel("CurrentProvisionallyRegisteredStudentsTable");
		locPanel.add(temp);
		locPanel.add(CurrentProvisionallyRegisteredStudentsTable.getTableHeader());
		locPanel.add(CurrentProvisionallyRegisteredStudentsTable);


		temp = new JLabel("Not Fully Admitted Table");
		locPanel.add(temp);
		locPanel.add(NotFullyAdmittedTable.getTableHeader());
		locPanel.add(NotFullyAdmittedTable);

		/*
		 * Putting the local panel into the scrollPane as we can
		 * be sure the table goes beyond the scope of the screen
		 */
		Scroll = new JScrollPane(locPanel);


		this.setLayout(new BorderLayout());
		add(Scroll,BorderLayout.CENTER);

		if(started){
			Scroll.getHorizontalScrollBar().setValue(ScrollX);
			Scroll.getVerticalScrollBar().setValue(ScrollY);
		}


		validate();
		repaint();

	}

	private JTable setTable(String Table){
		JTable temp = getTableData(Table);

		int[] red,orange,purple;
		red = DATA.getMarked(Table);
		orange = DATA.getHighlighted(Table);
		purple = DATA.getIncorrectlyFormated(Table);

		temp.setDragEnabled(false);
		temp.setAutoResizeMode(0);
		for(int i=0; i<temp.getColumnModel().getColumnCount();i++){


			temp.getColumnModel().getColumn(i).setCellRenderer(new Render(orange,red,purple));
			temp.getColumnModel().getColumn(i).setCellEditor(new DefaultCellEditor(new JTextField()){

				@Override
				public boolean isCellEditable(EventObject anEvent){
					return false;
				}

			});
			temp.getColumnModel().getColumn(i).setMinWidth(100);

		}
		temp.getTableHeader().addMouseListener(getMouse(Table));
		temp.setFillsViewportHeight(true);
		temp.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		temp.getSelectionModel().addListSelectionListener(getSelectionListener(Table));
		return temp;






	}

	private MouseListener getMouse(String string) {
		if(string.equalsIgnoreCase("CurrentFullyRegistered")){
			return new MouseListener(){

				@Override
				public void mouseClicked(MouseEvent e) {
					//System.out.println("Click CurrentFullyRegistered");
					int col = CurrentFullyRegisteredTable.columnAtPoint(e.getPoint());
					String head = CurrentFullyRegisteredTable.getColumnName(col);
					sortedBy = head;
					DATA.sort(head);
					setupTable();
				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub

				}

			};
		}else if(string.equalsIgnoreCase("NotFullyAdmitted")){
			return new MouseListener(){

				@Override
				public void mouseClicked(MouseEvent e) {
					//System.out.println("Click Not Fully Admitted");
					int col = NotFullyAdmittedTable.columnAtPoint(e.getPoint());
					String head = NotFullyAdmittedTable.getColumnName(col);
					sortedBy = head;
					DATA.sort(head);
					setupTable();
				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub

				}

			};
		}else if(string.equalsIgnoreCase("PhDProposalUnderExamination")){
			return new MouseListener(){

				@Override
				public void mouseClicked(MouseEvent e) {
					System.out.println("Click PhD Proposal Under Examination");
					int col = PhDProposalUnderExaminationTable.columnAtPoint(e.getPoint());
					String head = PhDProposalUnderExaminationTable.getColumnName(col);
					sortedBy = head;
					DATA.sort(head);
					setupTable();
				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub

				}

			};
		}else if(string.equalsIgnoreCase("CurrentProvisionallyRegisteredStudents")){
			return new MouseListener(){

				@Override
				public void mouseClicked(MouseEvent e) {
					System.out.println("Click Current Provisionally Registered Students");
					int col = CurrentProvisionallyRegisteredStudentsTable.columnAtPoint(e.getPoint());
					String head = CurrentProvisionallyRegisteredStudentsTable.getColumnName(col);
					sortedBy = head;
					DATA.sort(head);
					setupTable();

				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub

				}

			};
		} else if(string.equalsIgnoreCase("UnderExamination")){
			return new MouseListener(){

				@Override
				public void mouseClicked(MouseEvent e) {
					System.out.println("Click Under Examination");
					int col = UnderExaminationTable.columnAtPoint(e.getPoint());
					String head = UnderExaminationTable.getColumnName(col);
					sortedBy = head;
					DATA.sort(head);
					setupTable();
				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub

				}

			};
		}
		return null;
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
					PhDProposalUnderExaminationTable.getSelectionModel().clearSelection();
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
					PhDProposalUnderExaminationTable.getSelectionModel().clearSelection();;
					UnderExaminationTable.getSelectionModel().clearSelection();;
					CurrentFullyRegisteredTable.getSelectionModel().clearSelection();
					CurrentProvisionallyRegisteredStudentsTable.getSelectionModel().clearSelection();
					validate();
					int temp = NotFullyAdmittedTable.getSelectedRow();

					if(temp >=0)
						getData(temp,string);
				}

			};
		}else if(string.equalsIgnoreCase("PhDProposalUnderExamination")){
			return new ListSelectionListener(){

				@Override
				public void valueChanged(ListSelectionEvent e) {
					System.out.println(string);
					//PhDProposalUnderExaminationTable.getSelectionModel().clearSelection();;
					NotFullyAdmittedTable.getSelectionModel().clearSelection();
					UnderExaminationTable.getSelectionModel().clearSelection();;
					CurrentFullyRegisteredTable.getSelectionModel().clearSelection();
					CurrentProvisionallyRegisteredStudentsTable.getSelectionModel().clearSelection();
					validate();
					int temp = PhDProposalUnderExaminationTable.getSelectedRow();
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
					PhDProposalUnderExaminationTable.getSelectionModel().clearSelection();
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
					PhDProposalUnderExaminationTable.getSelectionModel().clearSelection();
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
		}else if(string.equalsIgnoreCase("PhDProposalUnderExamination")){
			temp = new JTable(PhDProposalUnderExaminationData,CurrentHead);
		}else if(string.equalsIgnoreCase("CurrentProvisionallyRegisteredStudents")){
			temp = new JTable(CurrentProvisionallyRegisteredStudentsData,CurrentHead);
		} else if(string.equalsIgnoreCase("UnderExamination")){
			temp = new JTable(UnderExaminationData,CurrentHead);
		}
		return temp;
	}


	private String[][] setupNotPhDProposalUnderExaminationData() {
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
		}else if(string.equalsIgnoreCase("PhDProposalUnderExamination")){
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



private class Render extends DefaultTableCellRenderer{

	private int[] YELLOW,RED,PURPLE;

/**
 *
 * @param yellow
 * @param red
 * @param purple
 */
	public Render(int[] yellow,int[]red, int[] purple){
		YELLOW=yellow;
		RED=red;
		PURPLE = purple;
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		  Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);


		  if(YELLOW[row]==1){
			  cell.setBackground(Color.YELLOW);
			  cell.setForeground(Color.BLACK);

		  }else if(RED[row]==1){
			  cell.setBackground(Color.RED);
			  cell.setForeground(Color.white);
		  }else {
			  cell.setBackground(Color.WHITE);
			  cell.setForeground(Color.BLACK);
			  if(column== SortedColumn){
				cell.setBackground(Color.LIGHT_GRAY);
			  }
		  }

		  if(PURPLE[row] ==1){
			  cell.setBackground(new Color(102,51,153));
			  cell.setForeground(Color.white);
		  } else if(RED[row]==1 ||YELLOW[row]==1){

		  }else{
		  	cell.setBackground(Color.WHITE);
		  	cell.setForeground(Color.BLACK);
		  	if(column== SortedColumn){
				cell.setBackground(Color.LIGHT_GRAY);
			  }
			}



	    return cell;
	}

}



}
