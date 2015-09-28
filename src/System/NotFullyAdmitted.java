package System;
import java.util.ArrayList;


public class NotFullyAdmitted extends PhDTable {

	public NotFullyAdmitted(ArrayList<Student> students,
			ArrayList<String> headers) {
		super(students, headers);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int[] getHighlighted() {
		int[] highlighting = new int[students.size()];
		for(int i = 0; i<students.size(); i++){
			// Fetch the time taken
			ECSStudent student = (ECSStudent) students.get(i);
			int monthsSinceStart = student.getTimeSinceStartDate();
			if(monthsSinceStart > 9){
				highlighting[i] = 1;
			}
			else if(monthsSinceStart > 12){
				highlighting[i] = 2;
			}
			else if(monthsSinceStart > 15){
				highlighting[i] = 3;
			}
			else{
				highlighting[i] = 0;
			}
		}
		return highlighting;
	}


}
