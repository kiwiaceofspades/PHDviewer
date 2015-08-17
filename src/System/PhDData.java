package System;

import java.util.ArrayList;

public class PhDData {

	private UnderExamination underExamination;
	private PhDProposalUnderExamination phDProposalUnderExamination;
	private CurrentFullyRegistered currentFullyRegistered;
	private CurrentProvisionallyRegisteredStudents currentProvisionallyRegisteredStudents;
	private NotFullyAdmitted notFullyAdmitted;
	private OtherSchoolsAtVUW otherSchoolsAtVUW;
	private OtherUniversities otherUniversities;

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
				return addEntry(student[1], table);
			case 'e':
				// edit a entry that already exists
				return editEntry(student[1], table);
			default:
				// type of change not accepted
				System.out.println("Change of type: " + type + " not allowed");
				return false;
		}
	}

	public boolean addEntry(String[] student, String table){
		// Find the student
		if(student.length < 22){
			System.out.println("Entry to change is smaller than expected!");
			return false;
			// throw some sort of error?
		}
		Student toAdd = new ECSStudent(student[0], Integer.parseInt(student[1]), student[2], student[3], student[4], student[5], student[6],
				student[7], student[8], student[9], student[10], student[11], student[12], student[13], student[14], student[15],
				student[16], student[17], student[18], student[19], student[20], student[21]);

		// Now add it to the right table
		if(table.equals("CurrentFullyRegistered")){
			currentFullyRegistered.addStudent(toAdd);
		}
		else{
			System.out.println("Couldn't find table to add to");
			// throw some error?
			return false;
		}
		return true;
	}

	public boolean editEntry(String[] student, String table){
		if(student.length < 22){
			System.out.println("Entry to change is smaller than expected!");
			return false;
			// throw some sort of error?
		}
		int studentID = Integer.parseInt(student[1]);
		int index = -1;
		Student toAdd = new ECSStudent(student[0], studentID, student[2], student[3], student[4], student[5], student[6],
				student[7], student[8], student[9], student[10], student[11], student[12], student[13], student[14], student[15],
				student[16], student[17], student[18], student[19], student[20], student[21]);

		// Find the right table to make edit to
		ArrayList<Student> students;
		if(table.equals("CurrentFullyRegistered")){
			return currentFullyRegistered.editStudent(toAdd, studentID);
		}
		else{
			System.out.println("Couldn't find table");
			return false;
		}

	}
}
