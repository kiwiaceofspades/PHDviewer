import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;


public class Parser {

	private static String testHeader = "| *Name* | *ID* | *Degree* | *EFTS* | *Primary Supervisor* | *Supervision Split* | *Secondary Supervisor* | *Supervision Split* | *Third Supervisor* | *Supervision Split* | *Scholarship* | *Start Date* | *PhD Proposal Submission* | *PhD Proposal Seminar* | *PhD Proposal Confirmation Date* | *Suspension Dates* | *Thesis Submission* + *Examiners Appointed Date* | *FGR Completes Examination* | *Revisions Finalised* | *Deposited in Library* | *Notes* | *Origin* |";
	private static String testStudent = "| Michael Millward | 123456789 | COMP690 | EFTSdata | Person:JamesNoble | 50% | Person:NicholasCameron | 50% | |  | Telstra Clear Postgraduate Scholarship | 20090427 | SUBMITTED | PRESENTED | CONFIRMED | | 20150116 | 20150518 | | | Revisions: 20150901. | D |";
	private Date testDate = new Date(99, 99, 99);
	private static String fname = "SanitizedStudentswNames.txt";

	private enum tableTypes {UE, CFRS, PPUE, CPRS, NFA};
	private tableTypes currentType = tableTypes.UE;

	// Main fields
	private String title = "Blank";
	private String[] titling = new String[4];
	private ArrayList<String> headers = new ArrayList<String>();
	private ArrayList<Student> students = new ArrayList<Student>();

	// Tables
	private UnderExamination underExamination;
	private CurrentFullyRegistered currentFullyRegistered;
	private PhDProposalUnderExamination phdProposalUnderExamination;
	private CurrentProvisionallyRegisteredStudents currentProvisionallyRegisteredStudents;
	private NotFullyAdmitted notFullyAdmitted;

	public Parser() {

	}

	public void runParser(String filename) {
		try {
			//Scanner sc = new Scanner(new File(filename));
			URL path = Parser.class.getResource("SanitizedStudentswNames.txt");
			File f = new File(path.getFile());
			Scanner sc = new Scanner(f);

			parseTitling(sc);
			parseHeaders(sc.nextLine());

			while(sc.hasNextLine()) {
				String line = sc.nextLine();

				if (line.matches("^\\s*$")) {
					break;
				}
				else {
					parseLine(line);
				}
			}
			notFullyAdmitted = new NotFullyAdmitted(students, headers);

			// display sizes to check

		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
	}

	private void parseTitling(Scanner s) {
		titling[0] = s.nextLine();
		if (titling[0].startsWith("-")) {
			title = titling[0].substring(5);
		}

		titling[1] = s.nextLine();
		titling[2] = s.nextLine();
		titling[3] = s.nextLine();
	}

	private void parseHeaders(String line) {
		String[] splitHeaders = line.split("\\|", 0);
			for (String s : splitHeaders) {
				if(s.isEmpty() || s.matches("^\\s*$")) {
					//System.out.print("----------");
				}
				s = s.replace("*", "");
				headers.add(s);
				//System.out.println(s);
			}
	}

	private void parseLine(String line) {
		String[] splitLine = line.split("\\|", 0);

		for (int i = 0; i < splitLine.length; i++) {
			if(splitLine[i].isEmpty() || splitLine[i].matches("^\\s*$")) {
				System.out.print("----------");
			}
			//splitLine[i] = splitLine[i].replace(" ", "");
			System.out.println(splitLine[i]);
		}

		if (!(splitLine.length < 22)) {//ignore table breaks for now
			// Create objects for Student constructor
			splitLine[2] = splitLine[2].replace(" ", "");
			int id = Integer.parseInt(splitLine[2]);

			//make different types of students
			Student s = new ECSStudent(splitLine[1], id, splitLine[3], splitLine[4],
					splitLine[5], splitLine[6], splitLine[7], splitLine[8], splitLine[9], splitLine[10],
					splitLine[11], splitLine[12],
					splitLine[13], splitLine[14], splitLine[15], splitLine[16], splitLine[17],
					splitLine[18], splitLine[19], splitLine[20], splitLine[21], splitLine[22]);

			// check enum and add to right table
			students.add(s);
		}
		else {//parse table break line and set enum
			splitLine[1] = splitLine[1].replace(" ", "");

			switch(splitLine[1]) {
				case "UNDEREXAMINATION":
					currentType = tableTypes.UE;
					break;
				case "CURRENTFULLYREGISTEREDSTUDENTS":
					currentType = tableTypes.CFRS;
					underExamination = new UnderExamination(students, headers);
					students.clear();
					break;
				case "PHDPROPOSALUNDEREXAMINATION":
					currentType = tableTypes.PPUE;
					currentFullyRegistered = new CurrentFullyRegistered(students, headers);
					students.clear();
					break;
				case "CURRENTPROVISIONALLYREGISTEREDSTUDENTS":
					currentType = tableTypes.CPRS;
					phdProposalUnderExamination = new PhDProposalUnderExamination(students, headers);
					students.clear();
					break;
				case "NOTFULLYADMITTED":
					currentType = tableTypes.NFA;
					currentProvisionallyRegisteredStudents = new CurrentProvisionallyRegisteredStudents(students, headers);
					students.clear();
					break;
			}
		}
	}

	private Date parseDate(String date) {
		int startDateYear = Integer.parseInt(date.substring(0, 3));
		int startDateMonth = Integer.parseInt(date.substring(4, 5));
		int startDateDay = Integer.parseInt(date.substring(6, 7));

		Date d = new Date(startDateYear, startDateMonth, startDateDay);
		return d;
	}

	public static void main(String[] args) {
		Parser p = new Parser();
		p.runParser("test");
	}

}
