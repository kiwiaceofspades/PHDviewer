package System;
import java.util.ArrayList;


public class NotFullyAdmitted extends PhDTable {

	public NotFullyAdmitted(ArrayList<Student> students,
			ArrayList<String> headers) {
		super(students, headers);
		// TODO Auto-generated constructor stub
	}

	public boolean sort(String header){
		return false;
	}
}
