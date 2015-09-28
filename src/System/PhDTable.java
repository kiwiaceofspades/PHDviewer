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
		return headers;
		// Previously used an arraylist of headers
		/*
		ArrayList<String> headersFormatted = new ArrayList<String>();
		for(int i = 0; i<headers.size(); i++){
			headersFormatted.add(i, formatHeader(headers.get(i)));

		}
		return headersFormatted;*/
	}

	public String formatHeader(String header){
		String formattedString = "<html>"+header+"</html>";
		return formattedString;
	}

	public void setHeaders(ArrayList<String> headers) {
		this.headers = headers;
	}

	/**
	 * Goes through each entry under each header (provided in the headers array)
	 * and checks how big the biggest entry is (in terms of character length).
	 * In the returned array, the index corresponds to the index of the provided headers array.
	 * E.g. if header studentID has index of 2 in the headers array, then at index 2 of the returned array
	 * there will be the column width of the biggest entry for studentID.
	 * @param headers that
	 * @return int[] that contains the biggest width of an entry
	 */
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

	/**
	 * Edits an existing student entry in the table, does this by replacing an old student entry with the update
	 * version of that student.
	 * @param student - updated version which will replace the old version
	 * @param studentID of the student that will be replaced
	 * @return boolean of whether the edit/replacement was successful.
	 */
	public boolean editStudent(Student student, int studentID){
		int index = findStudent(studentID);
		// need to check the highlighted fields
		Student oldStudent = students.get(index);
		// Edge case when the student needing to be updated has just been marked
		if(oldStudent.isMarked() == true){
			student.toggleMark();
		}
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

	/**
	 * Removes the student from the table
	 * @param studentID of the student to be removed
	 * @return the Student that should be removed
	 */
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

	/**
	 * Checks which entries of the table should be marked. The indexes of the returned int array corresponds to the index
	 * of the Student in the Student list. 1 = student entry should be marked. 0 = student entry should not be marked.
	 * @return int[] of which student entries should be marked.
	 */
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

	/**
	 * Checks which entries of the table have fields which are incorrectly formatted.
	 * The indexes of the returned int array corresponds to the index of the Student in the Student list.
	 * 1 = student entry contains fields which are incorrectly formatted.
	 * 0 = student entry does not contains fields which are incorrectly formatted.
	 * @return int[] of which student entries have fields that are incorrectly formatted.
	 */
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

	/**
	 * Checks which entries of the table should be highlighted. The indexes of the returned int array corresponds to the index
	 * of the Student in the Student list.
	 * 1 = student entry should be highlighted.
	 * 0 = student entry should not be highlighted.
	 * @return int[] of which student entries should be highlighted.
	 */
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

	/**
	 * Sorts the table by a header provided
	 * @param header which the table should be sorted by
	 * @return whether the table was sorted successfully
	 */
	public boolean sort(String header){
		System.out.println("Sorting");
		// Need to make sure the table has the header.
		if(!headers.contains(header)){
			System.out.println("Couldn't find header " + header + " to sort by");
			return false;
		}

		String typeBuffer = "String";
		// Special cases
		if(header.equals("ID") || header.equals("Total time taken")){
			typeBuffer = "Integer";
		}
		else if(header.contains("Supervision Split")){
			typeBuffer = "Percentage";
		}
		else if(header.equals("Start Date")){
			typeBuffer = "StDay";
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
				else if(type.equals("Percentage")){
					String o1val = ((ECSStudent)o1).getValues(finalHeader)[0];
					String o2val = ((ECSStudent)o2).getValues(finalHeader)[0];

					//the following if statements are to keep it inline with the general contract
					if(o1val.isEmpty() && o2val.isEmpty()){
						return 0;
					}
					if(o1val.isEmpty()){
						return -1;
					}
					if(o2val.isEmpty()){
						return 1;
					}
					int o1value = Integer.parseInt(o1val.substring(0, o1val.length()-1));
					int o2value = Integer.parseInt(o2val.substring(0, o2val.length()-1));
					return o1value - o2value;

				}
				else if(type.equals("StDay")){
					String o1val = ((ECSStudent)o1).getValues(finalHeader)[0];
					String o2val = ((ECSStudent)o2).getValues(finalHeader)[0];

					//the following if statements are to keep it inline with the general contract
					if(o1val.isEmpty() && o2val.isEmpty()){
						return 0;
					}
					if(o1val.isEmpty()){
						return -1;
					}
					if(o2val.isEmpty()){
						return 1;
					}
					try{
					int o1value = Integer.parseInt(o1val.substring(6, o1val.length()-1));
					int o2value = Integer.parseInt(o2val.substring(6, o2val.length()-1));
					if((o1value -o2value) == 0){
						o1value = Integer.parseInt(o1val.substring(3, 5));
						o2value = Integer.parseInt(o2val.substring(3, 5));
						if((o1value -o2value) == 0){
							o1value = Integer.parseInt(o1val.substring(0, 2));
							o2value = Integer.parseInt(o2val.substring(0, 2));
							return o1value - o2value;
						}
						return o1value - o2value;
					}
					return o1value - o2value;
					}
					catch(StringIndexOutOfBoundsException e){
						System.err.println("Date is in incorrect format" + e);
						return -1;
					}

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
			System.err.println("Type wasn't set correctly in sort");
			return false;
		}

		return true;

	}

	/**
	 * Toggles the marked field of the student in the table with studentID provided
	 * @param studentID of the student that needs marked field to be toggled.
	 */
	public void toggleMark(int studentID){
		int index = findStudent(studentID);
		if(index == -1){
			System.out.println("Couldn't find studentID " + studentID);
		}
		students.get(index).toggleMark();
	}
}