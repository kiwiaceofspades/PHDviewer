package System;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

// Author: Shreyas (ramasushre)
public class Parser {

	// Fields used for testing
	private static String testHeader = "| *Name* | *ID* | *Degree* | *EFTS* | *Primary Supervisor* | *Supervision Split* | *Secondary Supervisor* | *Supervision Split* | *Third Supervisor* | *Supervision Split* | *Scholarship* | *Start Date* | *PhD Proposal Submission* | *PhD Proposal Seminar* | *PhD Proposal Confirmation Date* | *Suspension Dates* | *Thesis Submission* + *Examiners Appointed Date* | *FGR Completes Examination* | *Revisions Finalised* | *Deposited in Library* | *Notes* | *Origin* |";
	private static String testStudent = "| Michael Millward | 123456789 | COMP690 | EFTSdata | Person:JamesNoble | 50% | Person:NicholasCameron | 50% | |  | Telstra Clear Postgraduate Scholarship | 20090427 | SUBMITTED | PRESENTED | CONFIRMED | | 20150116 | 20150518 | | | Revisions: 20150901. | D |";
	private Date testDate = new Date(99, 99, 99);
	private static String fname = "SanitizedStudentswNames.txt";

	// Enum used for determining what table type we are working with
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

	// Constructor takes a filename string and runs the parser. Also takes an empty PhDData object
	// and populates it.
	public Parser(String fname, PhDData data) {
		runParser(fname);

		data.setUnderExamination(underExamination);
		data.setCurrentFullyRegistered(currentFullyRegistered);
		data.setPhDProposalUnderExamination(phdProposalUnderExamination);
		data.setCurrentProvisionallyRegisteredStudents(currentProvisionallyRegisteredStudents);
		data.setNotFullyAdmitted(notFullyAdmitted);
	}

	public Parser() {

	}

	// Essentially the main method, parses the Foswiki text file specified by filename.
	public void runParser(String filename) {
		try {
			Scanner sc = new Scanner(new File(filename));

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
			notFullyAdmitted = new NotFullyAdmitted(new ArrayList<Student>(students), headers);

			// Display sizes to check
			//System.out.println("Under examination: " + underExamination.getSizeOfStudents());
			//System.out.println("Current fully registered: " + currentFullyRegistered.getSizeOfStudents());
			//System.out.println("PhD proposal under examination: " + phdProposalUnderExamination.getSizeOfStudents());
			//System.out.println("Current provisionally registered students: " + currentProvisionallyRegisteredStudents.getSizeOfStudents());
			//System.out.println("Not fully admitted " + notFullyAdmitted.getSizeOfStudents());
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
				//System.out.print("----------");
			}
			//System.out.println(splitLine[i]);
		}

		if (!(splitLine.length < 22)) {//ignore table breaks
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
					//System.out.println("UE");
					currentType = tableTypes.UE;
					break;
				case "CURRENTFULLYREGISTEREDSTUDENTS":
					//System.out.println("CFRS");
					currentType = tableTypes.CFRS;
					underExamination = new UnderExamination(new ArrayList<Student>(students), headers);
					//System.out.println("UE size of students: " + students.size());
					students.clear();
					break;
				case "PHDPROPOSALUNDEREXAMINATION":
					//System.out.println("PPUE");
					currentType = tableTypes.PPUE;
					currentFullyRegistered = new CurrentFullyRegistered(new ArrayList<Student>(students), headers);
					//System.out.println("CFRS size of students: " + students.size());
					students.clear();
					break;
				case "CURRENTPROVISIONALLYREGISTEREDSTUDENTS":
					//System.out.println("CPRS");
					currentType = tableTypes.CPRS;
					phdProposalUnderExamination = new PhDProposalUnderExamination(new ArrayList<Student>(students), headers);
					//System.out.println("PPUE size of students: " + students.size());
					students.clear();
					break;
				case "NOTFULLYADMITTED":
					//System.out.println("NFA");
					currentType = tableTypes.NFA;
					currentProvisionallyRegisteredStudents = new CurrentProvisionallyRegisteredStudents(new ArrayList<Student>(students), headers);
					//System.out.println("CPRS size of students: " + students.size());
					//System.out.println("Case NFA CPRS size: " + currentProvisionallyRegisteredStudents.getSizeOfStudents());
					//System.out.println("Case NFA PPUE size: " + phdProposalUnderExamination.getSizeOfStudents());
					//System.out.println("Case NFA CFRS size: " + currentFullyRegistered.getSizeOfStudents());
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

	public void writeToFile(PhDData data) throws IOException {
		UnderExamination ue = data.getUnderExamination();
		CurrentFullyRegistered cfs = data.getCurrentFullyRegistered();
		PhDProposalUnderExamination ppue = data.getPhDProposalUnderExamination();
		CurrentProvisionallyRegisteredStudents cprs = data.getCurrentProvisionallyRegisteredStudents();
		NotFullyAdmitted nfa = data.getNotFullyAdmitted();

		PrintWriter pw = new PrintWriter(new FileWriter("output.txt"));

		pw.println(titling[0]);
		pw.println(titling[1]);
		pw.println(titling[2]);
		pw.println(titling[3]);

		String headerString = "| *";
		for (String s : headers) {
			headerString += s + "* |";
		}

		pw.println(headerString);

		pw.println("| UNDER EXAMINATION ||||||||||||||||||||||");
		for (Student s : ue.getStudents()) {
			pw.println(s.toFoswiki());
		}
		pw.println("| CURRENT FULLY REGISTERED STUDENTS |||||||||||||||||||||||");
		for (Student s : cfs.getStudents()) {
			pw.println(s.toFoswiki());
		}
		pw.println("| PHD PROPOSAL UNDER EXAMINATION ||||||||||||||||||||||");
		for (Student s : ppue.getStudents()) {
			pw.println(s.toFoswiki());
		}
		pw.println("| CURRENT PROVISIONALLY REGISTERED STUDENTS |||||||||||||||||||||||");
		for (Student s : cprs.getStudents()) {
			pw.println(s.toFoswiki());
		}
		pw.println("| NOT FULLY ADMITTED ||||||||||||||||||||||");
		for (Student s : nfa.getStudents()) {
			pw.println(s.toFoswiki());
		}
	}

	public static void main(String[] args) {
		Parser p = new Parser();
		p.runParser(fname);
	}

}
