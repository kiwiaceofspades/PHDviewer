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
		return headers;
	}

	public void setHeaders(ArrayList<String> headers) {
		this.headers = headers;
	}

}
