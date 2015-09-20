package System;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


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

	public boolean addStudent(Student student){
		// Check for id
		students.add(student);
		return true;
	}

	public boolean editStudent(Student student, int studentID){
		int index = findStudent(studentID);
		if(index == -1){
			System.out.println("Couldn't find the student with ID: " + studentID);
			// throw some sort of error?
		}
		else{
			students.set(index, student);
			return true;
		}
		return false;
	}

	public int findStudent(int studentID){
		int index = -1;
		for(int i = 0; i<students.size(); i++){
			ECSStudent pupil = (ECSStudent) students.get(i);
			if(pupil.getId() == studentID){
				index = i;
				break;
			}
		}
		return index;
	}

	public Student removeStudent(int studentID){
		int index = findStudent(studentID);
		if(index == -1){
			System.out.println("Couldn't find the student with ID: " + studentID);
			// throw some sort of error?
		}
		else{
			Student student = students.get(index);
			students.remove(index);
			return student;
		}
		return null;
	}

	public int[] getMarked(){
		int[] marked = new int[students.size()];
		for(int i = 0; i < students.size(); i++){
			Student stud = students.get(i);
			if(stud.isMarked() == true){
				marked[i] = 1;
			}
			else{
				marked[i] = 0;
			}
		}
		return marked;
	}

	public int[] getIncorrectlyFormatted(){
		int[] incorrectlyFormatted = new int[students.size()];
		for(int i = 0; i < students.size(); i++){
			Student stud = students.get(i);
			if(stud.isIncorrectlyFormatted() == true){
				incorrectlyFormatted[i] = 1;
			}
			else{
				incorrectlyFormatted[i] = 0;
			}
		}
		return incorrectlyFormatted;
	}

	public int[] getHighlighted(){
		int[] highlighted = new int[students.size()];
		for(int i = 0; i < students.size(); i++){
			Student stud = students.get(i);
			if(stud.isHighlighted() == true){
				highlighted[i] = 1;
			}
			else{
				highlighted[i] = 0;
			}
		}
		return highlighted;
	}

	public boolean sort(String header){
		System.out.println("Sorting");
		// Need to make sure the table has the header.
		if(!headers.contains(header)){
			System.out.println("Couldn't find header " + header + " to sort by");
			return false;
		}

		String typeBuffer = "String";
		// Special cases
		if(header.equals("ID")){
			typeBuffer = "Integer";
		}

		final String[] finalHeader = {header};
		final String type = typeBuffer;
		final int[] error = new int[1];

		Collections.sort(students, new Comparator<Student>(){
			@Override
			public int compare(Student o1, Student o2) {
				if(type.equals("String")){
					String o1value = ((ECSStudent) o1).getValues(finalHeader)[0];
					String o2value = ((ECSStudent) o2).getValues(finalHeader)[0];
					// Just get the first entry
					return o1value.compareTo(o2value);
				}
				else if(type.equals("Integer")){
					int o1value = Integer.parseInt(((ECSStudent) o1).getValues(finalHeader)[0]);
					int o2value = Integer.parseInt(((ECSStudent) o2).getValues(finalHeader)[0]);
					// Just get the first entry
					return o1value - o2value;
				}
				else{
					// Then we have an error
					error[0] = 1;
					return 0;
				}
			}
		});

		if(error[0] == 1){
			// Then we have an error!
			System.out.println("Type wasn't set correctly in sort");
			return false;
		}

		return true;

	}

	public void toggleMark(int studentID){
		int index = findStudent(studentID);
		students.get(index).toggleMark();
	}
}