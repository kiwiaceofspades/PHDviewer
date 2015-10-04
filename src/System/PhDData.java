package System;

import java.io.IOException;
import java.util.ArrayList;

public class PhDData {

	private NotFullyAdmitted notFullyAdmitted;
	private CurrentProvisionallyRegisteredStudents currentProvisionallyRegisteredStudents;
	private PhDProposalUnderExamination phDProposalUnderExamination;
	private CurrentFullyRegistered currentFullyRegistered;
	private UnderExamination underExamination;

	/* These tables have not been implemented */
	private OtherSchoolsAtVUW otherSchoolsAtVUW;
	private OtherUniversities otherUniversities;

	Preferences preferences;
	private Parser parser;

	public PhDData(String filename) {
		preferences = new Preferences();
		parser = new Parser(filename, this, preferences);
		preferences.setParser(parser);
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

	public void setCurrentFullyRegistered(
			CurrentFullyRegistered currentFullyRegistered) {
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
	 *
	 * @param type
	 *            of change to make
	 * @param student
	 *            to make the change on
	 * @param table
	 *            that contains the student to make the change on
	 * @return boolean of whether the change was made or not
	 */
	public boolean makeChanges(char type, String[][] student, String table) {
		switch (type) {
		case 'a':
			// add a new entry
			return addEntry(student, table);
		case 'e':
			// edit a entry that already exists
			return editEntry(student, table);
		case 'r':
			// remove an entry
			return removeEntry(student, table);
		default:
			// type of change not accepted
			System.err.println("Change of type: " + type + " not allowed");
			return false;
		}
	}

	/**
	 * Removes the student from the current table it is in
	 * @param student
	 *            that is to be removed
	 * @param table
	 *            from where the student will be removed from
	 * @return
	 */
	private boolean removeEntry(String[][] student, String table) {
		int studentID = Integer.parseInt(findValueForHeader("ID", student));
		PhDTable phDTable = getTable(table);
		Student studentMoved = phDTable.removeStudent(studentID);
		// If not null, student was found and deleted
		return studentMoved != null;

	}

	/**
	 * Moves the student from the current table it is in, to the table above
	 * (table order is hardcoded)
	 * @param student
	 *            that is to be moved
	 * @param table
	 *            that contains the student that is being moved
	 * @return boolean of whether the student was moved successfully
	 */
	public boolean moveStudent(String[][] student, String table) {
		// Need to check which table the student is in, so we can remove the
		// student from
		// that table and assign it to the new one
		int studentID = Integer.parseInt(findValueForHeader("ID", student));
		PhDTable phDTable = getTable(table);
		Student studentMoved = phDTable.removeStudent(studentID);
		// If null, then student didn't exist, therefore don't move
		if (studentMoved == null) {
			return false;
		}
		// Now add it to the new table
		switch (table) {
		case "NotFullyAdmitted":
			return currentProvisionallyRegisteredStudents
					.addStudent(studentMoved);
		case "CurrentProvisionallyRegisteredStudents":
			return phDProposalUnderExamination.addStudent(studentMoved);
		case "PhDProposalUnderExamination":
			return currentFullyRegistered.addStudent(studentMoved);
		case "CurrentFullyRegistered":
			return underExamination.addStudent(studentMoved);
		case "UnderExamination":
			// UnderExamination is the highest level
			return true;
		default:
			System.err.println("Couldn't find table: " + table
					+ " to move Student from");
		}
		return false;
	}

	/**
	 * Adds a student into a table
	 * @param student
	 *            to be added
	 * @param table
	 *            that the student is to be added to
	 * @return boolean as to whether the student was added or not
	 */
	public boolean addEntry(String[][] student, String table) {
		Student toAdd = new ECSStudent(student);
		// Add it to the right table
		if (table.equalsIgnoreCase("NotFullyAdmitted")) {
			return notFullyAdmitted.addStudent(toAdd);
		} else if (table
				.equalsIgnoreCase("CurrentProvisionallyRegisteredStudents")) {
			return currentProvisionallyRegisteredStudents.addStudent(toAdd);
		} else if (table.equalsIgnoreCase("PhDProposalUnderExamination")) {
			return phDProposalUnderExamination.addStudent(toAdd);
		} else if (table.equalsIgnoreCase("CurrentFullyRegistered")) {
			return currentFullyRegistered.addStudent(toAdd);
		} else if (table.equalsIgnoreCase("UnderExamination")) {
			System.out.println("To add : " + toAdd.toFoswiki());
			System.out.println("Under exam : " + underExamination);
			return underExamination.addStudent(toAdd);
		}
		System.err.println("Couldn't find " + table + " to add entry to");
		return false;
	}

	/**
	 * Edits a existing student in a table
	 * @param student
	 *            to be edited
	 * @param table
	 *            that contains the student that is to be edited.
	 * @return boolean as to whether or not the student was edited successfully
	 */
	public boolean editEntry(String[][] student, String table) {
		Student toAdd = new ECSStudent(student);
		int studentID = Integer.parseInt(findValueForHeader("ID", student));
		PhDTable phDTable = getTable(table);
		return phDTable.editStudent(toAdd, studentID);
	}


	/**
	 * Writes all the data to a foswiki file. Called when the program is closed.
	 */
	public void writeToFoswiki() {
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
	 * @param header
	 *            that each table should be sorted by
	 * @return boolean as to whether the table was successfully sorted or not
	 */
	public boolean sort(String header) {
		// Will only for ecs student for now
		if (!underExamination.sort(header)) {
			System.err.println("Couldn't sort");
			return false;
		}
		if (!notFullyAdmitted.sort(header)) {
			System.err.println("Couldn't sort");
			return false;
		}
		if (!currentProvisionallyRegisteredStudents.sort(header)) {
			System.err.println("Couldn't sort");
			return false;
		}
		if (!phDProposalUnderExamination.sort(header)) {
			System.err.println("Couldn't sort");
			return false;
		}
		if (!currentFullyRegistered.sort(header)) {
			System.err.println("Couldn't sort");
			return false;
		}
		return true;
	}

	/**
	 * Returns an array of integers. Array contains which students in a specific
	 * table are marked. The index of the student entry in the returned array
	 * matches up with the index of corresponding student in the table. 1 =
	 * student is marked. 0 = student is not marked.
	 *
	 * @param table
	 *            that you want to check
	 * @return int[] that contains what students should and should not be
	 *         marked.
	 */
	public int[] getMarked(String table) {
		PhDTable phDTable = getTable(table);
		return phDTable.getMarked();
	}

	/**
	 * Returns an array of integers. Array contains which students in a specific
	 * table have fields that are incorrectly formated. Usually bad formatting
	 * is on a date entry. The index of the student entry in the returned array
	 * matches up with the index of corresponding student in the table. 1 =
	 * student has a field that is incorrectly formated. 0 = student is does not
	 * have a field that is incorrectly formatted.
	 *
	 * @param table
	 *            that you want to check
	 * @return int[] that contains what students are and are not incorrectly
	 *         formatted.
	 */
	public int[] getIncorrectlyFormated(String table) {
		PhDTable phDTable = getTable(table);
		return phDTable.getIncorrectlyFormatted();
	}

	/**
	 * Returns an array of integers. Array contains which students in a specific
	 * table are highlighted. An entry will be highlighted if it needs attention
	 * (which is calculated using logic provided within the Student class). The
	 * index of the student entry in the returned array matches up with the
	 * index of corresponding student in the table. 1 = student should be
	 * highlighted . 0 = student is not marked.
	 *
	 * @param table
	 *            that you want to check
	 * @return int[] that contains what students should and should not be
	 *         marked.
	 */
	public int[] getHighlighted(String table) {
		PhDTable phDTable = getTable(table);
		return phDTable.getHighlighted();
	}

	/**
	 * Toggles the marked field on a student in a specific table
	 *
	 * @param student
	 *            that you want to mark (or unmark)
	 * @param table
	 *            that contains the student
	 */
	public void toggleMark(String[][] student, String table) {
		int studentID = Integer.parseInt(findValueForHeader("ID", student));
		PhDTable phDTable = getTable(table);
		phDTable.toggleMark(studentID);
	}

	public PhDTable getTable(String table) {
		PhDTable phDTable = null;
		if (table.equalsIgnoreCase("NotFullyAdmitted")) {
			phDTable = notFullyAdmitted;
		} else if (table
				.equalsIgnoreCase("CurrentProvisionallyRegisteredStudents")) {
			phDTable = currentProvisionallyRegisteredStudents;
		} else if (table.equalsIgnoreCase("PhDProposalUnderExamination")) {
			phDTable = phDProposalUnderExamination;
		} else if (table.equalsIgnoreCase("CurrentFullyRegistered")) {
			phDTable = currentFullyRegistered;
		} else if (table.equalsIgnoreCase("UnderExamination")) {
			phDTable = underExamination;
		} else {
			System.err.println("Can't find the table " + table
					+ " to toggle the student's mark");
		}
		return phDTable;
	}

	/**
	 * Gets the headers for the tables. Current implementation assumes that all tables have
	 * the same headers as notFullyAdmitted are
	 * @return
	 */
	public String[] getHeaders() {
		String[] headers = new String[notFullyAdmitted.getHeaders().size()];

		for (int i = 0; i < notFullyAdmitted.getHeaders().size(); i++) {
			headers[i] = notFullyAdmitted.getHeaders().get(i);
			System.out.println("Header: " + headers[i]);
		}

		return headers;
	}

	public String findValueForHeader(String header, String[][] student) {
		// Go through the student object looking for header ID
		String value = null;
		for (int i = 0; i < student.length; i++) {
			if (student[i][0].equalsIgnoreCase(header)) {
				value = student[i][1];
			}
		}
		if (value == null) {
			System.err.println("Couldn't find value for header: " + header);
		}
		return value;
	}

	public Preferences getPreferences() {
		return preferences;
	}

}
