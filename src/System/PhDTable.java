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
				if(headers[j].length() > biggestWidth[j]){
					biggestWidth[j] = headers[j].length();
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

}
