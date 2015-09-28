package System;
import java.util.ArrayList;


public class CurrentFullyRegistered extends PhDTable {

	public CurrentFullyRegistered(ArrayList<Student> students,
			ArrayList<String> headers) {
		super(students, headers);
	}
/*
	@Override
	public int[] getHighlighted() {
		int[] highlighting = new int[students.size()];
		for(int i = 0; i<students.size(); i++){
			// Fetch the time taken
			ECSStudent student = (ECSStudent) students.get(i);
			student.getStartDate();



		}
		return highlighting;
	}
*/

}
