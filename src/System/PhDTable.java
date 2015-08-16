package System;
import java.util.ArrayList;


public abstract class PhDTable {

	private ArrayList<Student> students;
	private ArrayList<String> headers;

	public PhDTable(ArrayList<Student> students, ArrayList<String> headers){
		this.students = students;
		this.headers = headers;
	}

	public ArrayList<Student> getStudents() {
		return students;
	}

	public void setStudents(ArrayList<Student> students) {
		this.students = students;
	}

	public ArrayList<String> getHeaders() {
		ArrayList<String> headersFormatted = new ArrayList<String>();
		for(int i = 0; i<headers.size(); i++){
			headersFormatted.set(i, formatHeader(headers.get(i)));
		}
		return headersFormatted;
	}

	public String formatHeader(String header){
		String formattedString = "<html>"+header+"</html>";
		return formattedString;
	}

	public void setHeaders(ArrayList<String> headers) {
		this.headers = headers;
	}

	public int[] getColumnWidth(String[] headers){
		int[] biggestWidth = new int[headers.length];
		for(int i = 0; i<headers.length; i++){
			biggestWidth[i] = -1;
		}
		// Go through all the students
		for(int i = 0; i<students.size(); i++){
			String[] current = students.get(i).getValues(headers);
			// Now go through each header of the student and check what the length is
			for(int j = 0; j<headers.length; j++){
				if(current[j].length() > biggestWidth[j]){
					biggestWidth[j] = current[j].length();
				}
			}
		}
		return biggestWidth;
	}

	public int getSizeOfStudents(){
		return students.size();
	}

	public int getSizeOfHeaders(){
		return headers.size();
	}

	public boolean makeChanges(char type, String[][] student){
		switch(type){
			case 'a':
				// add a new entry
				return addEntry(student[1]);
			case 'e':
				// edit a entry that already exists
				return editEntry(student[1]);
			default:
				// type of change not accepted
				System.out.println("Change of type: " + type + " not allowed");
				return false;
		}
	}

	public boolean addEntry(String[] student){
		// Find the student
		if(student.length < 22){
			System.out.println("Entry to change is smaller than expected!");
			return false;
			// throw some sort of error?
		}
		Student toAdd = new ECSStudent(student[0], Integer.parseInt(student[1]), student[2], student[3], student[4], student[5], student[6],
				student[7], student[8], student[9], student[10], student[11], student[12], student[13], student[14], student[15],
				student[16], student[17], student[18], student[19], student[20], student[21]);
		students.add(toAdd);
		return true;
	}

	public boolean editEntry(String[] student){
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
		// Go through all the students and look for ID that matches
		for(int i = 0; i<students.size(); i++){
			ECSStudent pupil = (ECSStudent) students.get(i);
			if(pupil.getId() == studentID){
				index = i;
			}
		}
		if(index == -1){
			System.out.println("Couldn't find the student with ID: " + studentID);
			// throw some sort of error?
		}
		else{
			students.set(index, toAdd);
			return true;
		}
		return false;
	}

}
