import java.util.ArrayList;


public class CurrentProvisionallyRegisteredStudents {

	private ArrayList<Student> students;

	public CurrentProvisionallyRegisteredStudents(ArrayList<Student> students){
		this.students = students;
	}

	public ArrayList<Student> getStudents() {
		return students;
	}

	public void setStudents(ArrayList<Student> students) {
		this.students = students;
	}
}
