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
	public boolean moveStudent(String[][] student, String table){
		int studentID = Integer.parseInt(student[1][1]);
		if(table.equals("NotFullyAdmitted")){
			Student studentMoved = notFullyAdmitted.removeStudent(studentID);
			if(studentMoved != null){
				return currentProvisionallyRegisteredStudents.addStudent(studentMoved);
			}
			// throw error?
			System.out.println("Couldn't move Student with ID: " + studentID);
		}
		else if(table.equals("CurrentProvisionallyRegisteredStudents")){
			Student studentMoved = currentProvisionallyRegisteredStudents.removeStudent(studentID);
			if(studentMoved != null){
				return phDProposalUnderExamination.addStudent(studentMoved);
			}
			// throw error?
			System.out.println("Couldn't move Student with ID: " + studentID);
		}
		else if(table.equals("PhDProposalUnderExamination")){
			Student studentMoved = phDProposalUnderExamination.removeStudent(studentID);
			if(studentMoved != null){
				return currentFullyRegistered.addStudent(studentMoved);
			}
			// throw error?
			System.out.println("Couldn't move Student with ID: " + studentID);
		}
		else if(table.equals("CurrentFullyRegistered")){
			Student studentMoved = currentFullyRegistered.removeStudent(studentID);
			if(studentMoved != null){
				return underExamination.addStudent(studentMoved);
			}
			// throw error?
			System.out.println("Couldn't move Student with ID: " + studentID);
		}
		else if(table.equals("UnderExamination")){
			Student studentMoved = underExamination.removeStudent(studentID);
			// Possible print something out?
			// throw error?
			System.out.println("Couldn't move Student with ID: " + studentID);
		}
		System.out.println("Can't find the table " + table + " to move the student from");
		return false;
	}

	public boolean addEntry(String[][] student, String table){
		// Find the student
		if(student.length < 22){
			System.out.println("Entry to change is smaller than expected!");
			return false;
			// throw some sort of error?
		}
		Student toAdd = new ECSStudent(student[0][1], Integer.parseInt(student[1][1]), student[2][1], student[3][1], student[4][1], student[5][1], student[6][1],
				student[7][1], student[8][1], student[9][1], student[10][1], student[11][1], student[12][1], student[13][1], student[14][1], student[15][1],
				student[16][1], student[17][1], student[18][1], student[19][1], student[20][1], student[21][1]);

		// Now add it to the right table
		if(table.equals("UnderExamination")){
			 return underExamination.addStudent(toAdd);
		}
		else if(table.equals("CurrentFullyRegistered")){
			return currentFullyRegistered.addStudent(toAdd);
		}
		else if(table.equals("CurrentProvisionallyRegisteredStudents")){
			return currentProvisionallyRegisteredStudents.addStudent(toAdd);
		}
		else{
			System.out.println("Couldn't find table to add to");
			// throw some error?
			return false;
		}
	}

	public boolean editEntry(String[][] student, String table){
		if(student.length < 22){
			System.out.println("Entry to change is smaller than expected!");
			return false;
			// throw some sort of error?
		}
		int studentID = Integer.parseInt(student[1][1]);
		int index = -1;
		Student toAdd = new ECSStudent(student[0][1], studentID, student[2][1], student[3][1], student[4][1], student[5][1], student[6][1],
				student[7][1], student[8][1], student[9][1], student[10][1], student[11][1], student[12][1], student[13][1], student[14][1], student[15][1],
				student[16][1], student[17][1], student[18][1], student[19][1], student[20][1], student[21][1]);

		// Now add it to the right table
		if(table.equals("UnderExamination")){
			return underExamination.editStudent(toAdd, studentID);
		}
		else if(table.equals("CurrentFullyRegistered")){
			return currentFullyRegistered.editStudent(toAdd, studentID);
		}
		else if(table.equals("CurrentProvisionallyRegisteredStudents")){
			return currentProvisionallyRegisteredStudents.editStudent(toAdd, studentID);
		}
		else{
			System.out.println("Couldn't find table to add to");
			// throw some error?
			return false;
		}

	}

	public void writeToFoswiki(){
		try {
			parser.writeToFile(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// to be implemented !

	}

}
