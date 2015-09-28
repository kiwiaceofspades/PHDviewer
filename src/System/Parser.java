package System;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
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
	private ArrayList<String> otherStudents = new ArrayList<String>();

	// Tables
	private UnderExamination underExamination;
	private CurrentFullyRegistered currentFullyRegistered;
	private PhDProposalUnderExamination phdProposalUnderExamination;
	private CurrentProvisionallyRegisteredStudents currentProvisionallyRegisteredStudents;
	private NotFullyAdmitted notFullyAdmitted;

	/**
	 * Legacy Parser constructor to populate a PhDData object
	 * @param fname Filename of table data
	 * @param data PhDData object to populate with data
	 */
	public Parser(String fname, PhDData data) {
		runParser(fname);

		data.setUnderExamination(underExamination);
		data.setCurrentFullyRegistered(currentFullyRegistered);
		data.setPhDProposalUnderExamination(phdProposalUnderExamination);
		data.setCurrentProvisionallyRegisteredStudents(currentProvisionallyRegisteredStudents);
		data.setNotFullyAdmitted(notFullyAdmitted);
	}

	/**
	 * Current Parser constructor to populate a PhDData object and Preferences object
	 * @param fname Filename of table data
	 * @param data PhDData object to populate with Students
	 * @param pref Preferences object to populate
	 */
	public Parser(String fname, PhDData data, Preferences pref) {
		runParser(fname);

		// Populate empty PhDData object
		data.setUnderExamination(underExamination);
		data.setCurrentFullyRegistered(currentFullyRegistered);
		data.setPhDProposalUnderExamination(phdProposalUnderExamination);
		data.setCurrentProvisionallyRegisteredStudents(currentProvisionallyRegisteredStudents);
		data.setNotFullyAdmitted(notFullyAdmitted);

		// Populate empty Preferences object
		ArrayList<Mode> mode = parsePreferences("../preferences.txt");
		pref.setModes(mode);
	}

	/**
	 * Empty constructor (used for testing)
	 */
	public Parser() {

	}

	/**
	 * Essentially the main method, parses the Foswiki text file specified by filename by calling a
	 * a number of individual methods.
	 * @param filename Foswiki text file name
	 */
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
			// We are right at the end of the main VUW table
			notFullyAdmitted = new NotFullyAdmitted(new ArrayList<Student>(students), headers);
			// Non-VUW students
			parseOtherStudents(sc);

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

	/**
	 * Parses the titling of a Foswiki text file and stores it in a global variable
	 * @param s
	 */
	private void parseTitling(Scanner s) {
		titling[0] = s.nextLine();
		if (titling[0].startsWith("-")) {
			title = titling[0].substring(5);
		}

		titling[1] = s.nextLine();
		titling[2] = s.nextLine();
		titling[3] = s.nextLine();
	}

	/**
	 * Parses the headers of a Foswiki text file and stores it in a global variable
	 * @param line
	 */
	private void parseHeaders(String line) {
		headers.add("Total time taken");
		int count = 1;
		String[] splitHeaders = line.split("\\|", 0);
			for (String s : splitHeaders) {
				if(s.isEmpty() || s.matches("^\\s*$")) {
					//System.out.print("----------");
					continue;
				}
				s = s.replace("* ", "");
				s = s.replace(" *", "");
				if (s.equalsIgnoreCase("Supervision Split")) {
					s = s + " " + count++;
				}
				headers.add(s);
				//System.out.println(s);
			}
	}

	/**
	 * Parses a line which will represent a student
	 * @param line
	 */
	private void parseLine(String line) {
		String[] splitLine = line.split("\\|", 0);

		for (int i = 0; i < splitLine.length; i++) {
			if(splitLine[i].isEmpty() || splitLine[i].matches("^\\s*$")) {
				//System.out.print("----------");
			}
			splitLine[i] = splitLine[i].trim();
			//System.out.println(splitLine[i]);
		}

		if (!(splitLine.length < 22)) {// Ignore table breaks
			// Create objects for Student constructor in here

			// ID special case
			splitLine[2] = splitLine[2].replace(" ", "");
			int id = Integer.parseInt(splitLine[2]);

			// Make different types of students
			Student s = new ECSStudent(splitLine[1], id, splitLine[3], splitLine[4],
					splitLine[5], splitLine[6], splitLine[7], splitLine[8], splitLine[9], splitLine[10],
					splitLine[11], splitLine[12],
					splitLine[13], splitLine[14], splitLine[15], splitLine[16], splitLine[17],
					splitLine[18], splitLine[19], splitLine[20], splitLine[21], splitLine[22]);

			students.add(s);
		}
		else {// Parse table break line and set enum (we've hit a new table)
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

	/**
	 * Parses the remainder tables, namely non-VUW students
	 * @param sc
	 */
	private void parseOtherStudents(Scanner sc) {
		while (sc.hasNextLine()) {
			otherStudents.add(sc.nextLine());
		}
	}

	/**
	 * Parses a string representation of a date and returns a Date object
	 * @param date String representation of a date
	 * @return A Date object
	 */
	private Date parseDate(String date) {
		int startDateYear = Integer.parseInt(date.substring(0, 3));
		int startDateMonth = Integer.parseInt(date.substring(4, 5));
		int startDateDay = Integer.parseInt(date.substring(6, 7));

		Date d = new Date(startDateYear, startDateMonth, startDateDay);
		return d;
	}

	/**
	 * Parses a preferences file and returns an ArrayList of Modes specified in the file
	 * @param filename
	 * @return ArrayList of Modes
	 */
	private ArrayList<Mode> parsePreferences(String filename) {
		ArrayList<Mode> modes = new ArrayList<Mode>();
		try {
			Scanner sc = new Scanner(new File(filename));

			while (sc.hasNextLine()) {
				ArrayList<String> modeHeaderList = new ArrayList<String>();
				String line = sc.nextLine();
				String[] splitHeaders = line.split("\\|", 0);
				for (int i=2; i<splitHeaders.length-1;i++) {
					String s = splitHeaders[i];
					if(s.isEmpty() || s.matches("^\\s*$")) {
						//System.out.print("----------");
						continue;
					}
					modeHeaderList.add(s);
				}
				Mode m = new Mode(splitHeaders[1], modeHeaderList.toArray(new String[0]));
				modes.add(m);
			}
//				System.out.println("Parser prefs: " + s);
//
//				String[] modeHeaderArray = new String[modeHeaderList.size()];
//				modeHeaderList.toArray(modeHeaderArray);
//				modes.add(new Mode(splitHeaders[0], modeHeaderArray));


			sc.close();
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		return modes;
	}

	/**
	 * Uses a Preferences object to write to the preferences file
	 * @param prefs
	 * @throws IOException
	 */
	public void writeToPreferencesFile(Preferences prefs) throws IOException {
		ArrayList<String> formattedPrefs = prefs.toFoswiki();

		PrintWriter pw = new PrintWriter(new FileWriter(new File("preferences.txt")));
		//pw.flush();

		for (String line : formattedPrefs) {
			pw.println(line);
		}

		pw.close();
	}

	/**
	 * Uses a PhDData object to write to the Foswiki text file
	 * @param data PhDData object
	 * @throws IOException
	 */
	public void writeToFile(PhDData data) throws IOException {
		UnderExamination ue = data.getUnderExamination();
		CurrentFullyRegistered cfs = data.getCurrentFullyRegistered();
		PhDProposalUnderExamination ppue = data.getPhDProposalUnderExamination();
		CurrentProvisionallyRegisteredStudents cprs = data.getCurrentProvisionallyRegisteredStudents();
		NotFullyAdmitted nfa = data.getNotFullyAdmitted();

		PrintWriter pw = new PrintWriter(new FileWriter(new File("output.txt")));
		//pw.flush();

		// Write titles
		pw.println(titling[0]);
		pw.println(titling[1]);
		pw.println(titling[2]);
		pw.println(titling[3]);

		// Write headers
		String headerString = "|";
		for (String s : headers) {
			if (!s.isEmpty()) {
				//System.out.println(s);
				headerString += " *" + s + "* |";
			}
		}

		pw.println(headerString);

		// Write tables
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

		pw.println("");
		for (String s : otherStudents) {
			pw.println(s);
		}

		pw.close();
	}

	public static void main(String[] args) {
		Parser p = new Parser();
		p.runParser(fname);
	}

}
