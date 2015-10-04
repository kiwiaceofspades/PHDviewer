package System;
import java.util.ArrayList;


public class CurrentFullyRegistered extends PhDTable {

	public CurrentFullyRegistered(ArrayList<Student> students,
			ArrayList<String> headers) {
		super(students, headers);
	}

	@Override
	public int[] getHighlighted() {
		int[] highlighting = new int[students.size()];
		for(int i = 0; i<students.size(); i++){
			// Fetch the time taken
			ECSStudent student = (ECSStudent) students.get(i);
			int monthsSinceStart = student.getTimeSinceStartDate();
			if(monthsSinceStart >= 60){
				highlighting[i] = 3;
			}
			else if(monthsSinceStart >= 48){
				highlighting[i] = 2;
			}
			else if(monthsSinceStart >= 36){
				highlighting[i] = 1;
			}
			else{
				highlighting[i] = 0;
			}
		}
		return highlighting;
	}


}
