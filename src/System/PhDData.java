package System;

import java.io.IOException;
import java.util.ArrayList;

public class PhDData {

	private NotFullyAdmitted notFullyAdmitted;
	private CurrentProvisionallyRegisteredStudents currentProvisionallyRegisteredStudents;
	private PhDProposalUnderExamination phDProposalUnderExamination;
	private CurrentFullyRegistered currentFullyRegistered;
	private UnderExamination underExamination;

	private OtherSchoolsAtVUW otherSchoolsAtVUW;
	private OtherUniversities otherUniversities;

	private Preferences preferences;

	private Parser parser;

	public PhDData(String filename){
		parser = new Parser(filename, this);
	}

	public UnderExamination getUnderExamination() {
		return underExamination;
	}
	public void setUnderExamination(UnderExamination underExamination) {
		this.underExamination = underExamination;
	}
	public CurrentFullyRegistered getCurrentFullyRegistered() {
		return currentFullyRegistered;
	}
	public void setCurrentFullyRegistered(CurrentFullyRegistered currentFullyRegistered) {
		this.currentFullyRegistered = currentFullyRegistered;
	}
	public CurrentProvisionallyRegisteredStudents getCurrentProvisionallyRegisteredStudents() {
		return currentProvisionallyRegisteredStudents;
	}
	public void setCurrentProvisionallyRegisteredStudents(
			CurrentProvisionallyRegisteredStudents currentProvisionallyRegisteredStudents) {
		this.currentProvisionallyRegisteredStudents = currentProvisionallyRegisteredStudents;
	}
	public NotFullyAdmitted getNotFullyAdmitted() {
		return notFullyAdmitted;
	}
	public void setNotFullyAdmitted(NotFullyAdmitted notFullyAdmitted) {
		this.notFullyAdmitted = notFullyAdmitted;
	}
	public OtherUniversities getOtherUniversities() {
		return otherUniversities;
	}
	public void setOtherUniversities(OtherUniversities otherUniversities) {
		this.otherUniversities = otherUniversities;
	}
	public OtherSchoolsAtVUW getOtherSchoolsAtVUW() {
		return otherSchoolsAtVUW;
	}
	public void setOtherSchoolsAtVUW(OtherSchoolsAtVUW otherSchoolsAtVUW) {
		this.otherSchoolsAtVUW = otherSchoolsAtVUW;
	}

	public PhDProposalUnderExamination getPhDProposalUnderExamination() {
		return phDProposalUnderExamination;
	}

	public void setPhDProposalUnderExamination(
			PhDProposalUnderExamination phDProposalUnderExamination) {
		this.phDProposalUnderExamination = phDProposalUnderExamination;
	}

	/**
	 * Method called by GUI to make change to the data in the system.
	 * @param type of change to make
	 * @param student to make the change on
	 * @param table that contains the student to make the change on
	 * @return boolean of whether the change was made or not
	 */
	public boolean makeChanges(char type, String[][] student, String table){
		switch(type){
			case 'a':
				// add a new entry
				return addEntry(student, table);
			case 'e':
				// edit a entry that already exists
				return editEntry(student, table);
			default:
				// type of change not accepted
				System.out.println("Change of type: " + type + " not allowed");
				return false;
		}
	}

	// NB: ID should not be hide-able.
	/**
	 * Moves the student from the current table it is in, to the table above (table order is hardcoded)
	 * @param student that is to be moved
	 * @param table that contains the student that is being moved
	 * @return boolean of whether the student was moved successfully
	 */
	public boolean moveStudent(String[][] student, String table){
		int studentID = Integer.parseInt(student[1][1]);
		if(table.equalsIgnoreCase("NotFullyAdmitted")){
			Student studentMoved = notFullyAdmitted.removeStudent(studentID);
			if(studentMoved != null){
				return currentProvisionallyRegisteredStudents.addStudent(studentMoved);
			}
			// throw error?
			System.out.println("Couldn't move Student with ID: " + studentID);
		}
		else if(table.equalsIgnoreCase("CurrentProvisionallyRegisteredStudents")){
			Student studentMoved = currentProvisionallyRegisteredStudents.removeStudent(studentID);
			if(studentMoved != null){
				return phDProposalUnderExamination.addStudent(studentMoved);
			}
			// throw error?
			System.out.println("Couldn't move Student with ID: " + studentID);
		}
		else if(table.equalsIgnoreCase("PhDProposalUnderExamination")){
			Student studentMoved = phDProposalUnderExamination.removeStudent(studentID);
			if(studentMoved != null){
				return currentFullyRegistered.addStudent(studentMoved);
			}
			// throw error?
			System.out.println("Couldn't move Student with ID: " + studentID);
		}
		else if(table.equalsIgnoreCase("CurrentFullyRegistered")){
			Student studentMoved = currentFullyRegistered.removeStudent(studentID);
			if(studentMoved != null){
				return underExamination.addStudent(studentMoved);
			}
			// throw error?
			System.out.println("Couldn't move Student with ID: " + studentID);
		}
		else if(table.equalsIgnoreCase("UnderExamination")){
			Student studentMoved = underExamination.removeStudent(studentID);
			// Possible print something out?
			// throw error?
			System.out.println("Couldn't move Student with ID: " + studentID);
		}
		System.out.println("Can't find the table " + table + " to move the student from");
		return false;
	}

	/**
	 * Adds a student into a table
	 * @param student to be added
	 * @param table that the student is to be added to
	 * @return boolean as to whether the student was added or not
	 */
	public boolean addEntry(String[][] student, String table){
		// Find the student
		if(student.length != 22){
			System.out.println("Entry to add is a different size than expected!");
			return false;
			// throw some sort of error?
		}
		Student toAdd = new ECSStudent(student[0][1], Integer.parseInt(student[1][1]), student[2][1], student[3][1], student[4][1], student[5][1], student[6][1],
				student[7][1], student[8][1], student[9][1], student[10][1], student[11][1], student[12][1], student[13][1], student[14][1], student[15][1],
				student[16][1], student[17][1], student[18][1], student[19][1], student[20][1], student[21][1]);
		// Now add it to the right table
		if(table.equalsIgnoreCase("NotFullyAdmitted")){
			return notFullyAdmitted.addStudent(toAdd);
		}
		else if(table.equalsIgnoreCase("CurrentProvisionallyRegisteredStudents")){
			return currentProvisionallyRegisteredStudents.addStudent(toAdd);
		}
		else if(table.equalsIgnoreCase("PhDProposalUnderExamination")){
			return phDProposalUnderExamination.addStudent(toAdd);
		}
		else if(table.equalsIgnoreCase("CurrentFullyRegistered")){
			return currentFullyRegistered.addStudent(toAdd);
		}
		else if(table.equalsIgnoreCase("UnderExamination")){
			System.out.println("To add : " + toAdd.toFoswiki());
			System.out.println("Under exam : "+underExamination);
			return underExamination.addStudent(toAdd);
		}
		System.out.println("Couldn't find " + table + " to add entry to");
		return false;
	}

	/**
	 * Edits a existing student in a table
	 * @param student to be edited
	 * @param table that contains the student that is to be edited.
	 * @return boolean as to whether or not the student was edited successfully
	 */
	public boolean editEntry(String[][] student, String table){
		if(student.length != 22){
			System.out.println("Entry to change is a different size than expected!");
			return false;
			// throw some sort of error?
		}
		int studentID = Integer.parseInt(student[1][1]);
		int index = -1;
		Student toAdd = new ECSStudent(student[0][1], studentID, student[2][1], student[3][1], student[4][1], student[5][1], student[6][1],
				student[7][1], student[8][1], student[9][1], student[10][1], student[11][1], student[12][1], student[13][1], student[14][1], student[15][1],
				student[16][1], student[17][1], student[18][1], student[19][1], student[20][1], student[21][1]);
		// Now send the edit command to the correct table
		if(table.equalsIgnoreCase("NotFullyAdmitted")){
			return notFullyAdmitted.editStudent(toAdd, studentID);
		}
		else if(table.equalsIgnoreCase("CurrentProvisionallyRegisteredStudents")){
			return currentProvisionallyRegisteredStudents.editStudent(toAdd, studentID);
		}
		else if(table.equalsIgnoreCase("PhDProposalUnderExamination")){
			return phDProposalUnderExamination.editStudent(toAdd, studentID);
		}
		else if(table.equalsIgnoreCase("CurrentFullyRegistered")){
			return currentFullyRegistered.editStudent(toAdd, studentID);
		}
		else if(table.equalsIgnoreCase("UnderExamination")){
			return underExamination.editStudent(toAdd, studentID);
		}
		System.out.println("Couldn't find " + table + " to add entry to");
		return false;

	}

	/**
	 * Writes all the data to a foswiki file. Called when the program is closed.
	 */
	public void writeToFoswiki(){
		try {
			parser.writeToFile(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// to be implemented !

	}

	/**
	 * Sorts every table by the header provided
	 * @param header that each table should be sorted by
	 * @return boolean as to whether the table was successfully sorted or not
	 */
	public boolean sort(String header){
		// Will only for ecs student for now
		if(!underExamination.sort(header)){
			System.out.println("Couldn't sort");
			return false;
		}
		if(!notFullyAdmitted.sort(header)){
			System.out.println("Couldn't sort");
			return false;
		}
		if(!currentProvisionallyRegisteredStudents.sort(header)){
			System.out.println("Couldn't sort");
			return false;
		}
		if(!phDProposalUnderExamination.sort(header)){
			System.out.println("Couldn't sort");
			return false;
		}
		if(!currentFullyRegistered.sort(header)){
			System.out.println("Couldn't sort");
			return false;
		}
		return true;
	}

	/**
	 * Returns an array of integers. Array contains which students in a specific table are marked.
	 * The index of the student entry in the returned array matches up with the index of corresponding student
	 * in the table. 1 = student is marked. 0 = student is not marked.
	 * @param table that you want to check
	 * @return int[] that contains what students should and should not be marked.
	 */
	public int[] getMarked(String table){
		int[] marked;
		if(table.equalsIgnoreCase("NotFullyAdmitted")){
			marked = notFullyAdmitted.getMarked();
		}
		else if(table.equalsIgnoreCase("CurrentProvisionallyRegisteredStudents")){
			marked = currentProvisionallyRegisteredStudents.getMarked();
		}
		else if(table.equalsIgnoreCase("PhDProposalUnderExamination")){
			marked = phDProposalUnderExamination.getMarked();
		}
		else if(table.equalsIgnoreCase("CurrentFullyRegistered")){
			marked = currentFullyRegistered.getMarked();
		}
		else if(table.equalsIgnoreCase("UnderExamination")){
			marked = underExamination.getMarked();
		}
		else {
			System.out.println("Couldn't get Marked for " + table);
			return null;
		}
		System.out.println("Current length of marked: " + marked.length);
		return marked;
	}

	/**
	 * Returns an array of integers. Array contains which students in a specific table have fields that are incorrectly formated.
	 * Usually bad formatting is on a date entry.
	 * The index of the student entry in the returned array matches up with the index of corresponding student in the table.
	 * 1 = student has a field that is incorrectly formated. 0 = student is does not have a field that is incorrectly formatted.
	 * @param table that you want to check
	 * @return int[] that contains what students are and are not incorrectly formatted.
	 */
	public int[] getIncorrectlyFormated(String table){
		int[] incorrectlyFormatted;
		if(table.equalsIgnoreCase("NotFullyAdmitted")){
			incorrectlyFormatted = notFullyAdmitted.getIncorrectlyFormatted();
		}
		else if(table.equalsIgnoreCase("CurrentProvisionallyRegisteredStudents")){
			incorrectlyFormatted = currentProvisionallyRegisteredStudents.getIncorrectlyFormatted();;
		}
		else if(table.equalsIgnoreCase("PhDProposalUnderExamination")){
			incorrectlyFormatted = phDProposalUnderExamination.getIncorrectlyFormatted();
		}
		else if(table.equalsIgnoreCase("CurrentFullyRegistered")){
			incorrectlyFormatted = currentFullyRegistered.getIncorrectlyFormatted();
		}
		else if(table.equalsIgnoreCase("UnderExamination")){
			incorrectlyFormatted = underExamination.getIncorrectlyFormatted();
		}
		else {
			System.out.println("Couldn't get Highlighted for " + table);
			return null;
		}
		return incorrectlyFormatted;
	}

	/**
	 * Returns an array of integers. Array contains which students in a specific table are highlighted.
	 * An entry will be highlighted if it needs attention (which is calculated using logic provided within the Student class).
	 * The index of the student entry in the returned array matches up with the index of corresponding student
	 * in the table. 1 = student should be highlighted . 0 = student is not marked.
	 * @param table that you want to check
	 * @return int[] that contains what students should and should not be marked.
	 */
	public int[] getHighlighted(String table){
		int[] highlighted;
		if(table.equalsIgnoreCase("NotFullyAdmitted")){
			highlighted = notFullyAdmitted.getHighlighted();
		}
		else if(table.equalsIgnoreCase("CurrentProvisionallyRegisteredStudents")){
			highlighted = currentProvisionallyRegisteredStudents.getHighlighted();
		}
		else if(table.equalsIgnoreCase("PhDProposalUnderExamination")){
			highlighted = phDProposalUnderExamination.getHighlighted();
		}
		else if(table.equalsIgnoreCase("CurrentFullyRegistered")){
			highlighted = currentFullyRegistered.getHighlighted();
		}
		else if(table.equalsIgnoreCase("UnderExamination")){
			highlighted = underExamination.getHighlighted();
		}
		else {
			System.out.println("Couldn't get Highlighted for " + table);
			return null;
		}
		return highlighted;
	}

	/**
	 * Toggles the marked field on a student in a specific table
	 * @param student that you want to mark (or unmark)
	 * @param table that contains the student
	 */
	public void toggleMark(String[][] student, String table){
		int studentID = Integer.parseInt(student[1][1]);
		if(table.equalsIgnoreCase("NotFullyAdmitted")){
			notFullyAdmitted.toggleMark(studentID);
		}
		else if(table.equalsIgnoreCase("CurrentProvisionallyRegisteredStudents")){
			currentProvisionallyRegisteredStudents.toggleMark(studentID);
		}
		else if(table.equalsIgnoreCase("PhDProposalUnderExamination")){
			phDProposalUnderExamination.toggleMark(studentID);
		}
		else if(table.equalsIgnoreCase("CurrentFullyRegistered")){
			currentFullyRegistered.toggleMark(studentID);
		}
		else if(table.equalsIgnoreCase("UnderExamination")){
			underExamination.toggleMark(studentID);
		}
		else{
			System.out.println("Can't find the table " + table + " to toggle the student's mark");
		}
	}

}
