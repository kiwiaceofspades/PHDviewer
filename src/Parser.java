import java.util.Scanner;


public class Parser {

	private static String testHeader = "| *Name* | *ID* | *Degree* | *EFTS* | *Primary Supervisor* | *Supervision Split* | *Secondary Supervisor* | *Supervision Split* | *Third Supervisor* | *Supervision Split* | *Scholarship* | *Start Date* | *PhD Proposal Submission* | *PhD Proposal Seminar* | *PhD Proposal Confirmation Date* | *Suspension Dates* | *Thesis Submission* + *Examiners Appointed Date* | *FGR Completes Examination* | *Revisions Finalised* | *Deposited in Library* | *Notes* | *Origin* |";

	private static String testStudent = "| Michael Millward | 123456789 | COMP690 | EFTSdata | Person:JamesNoble | 50% | Person:NicholasCameron | 50% | |  | Telstra Clear Postgraduate Scholarship | 20090427 | SUBMITTED | PRESENTED | CONFIRMED | | 20150116 | 20150518 | | | Revisions: 20150901. | D |";

	public Parser() {

	}

	private static void parseTitling() {

	}

	private static void parseHeaders(String line) {
		String[] splitHeaders = line.split("\\|", 0);
			for (String s : splitHeaders) {
				if(s.isEmpty() || s.matches("^\\s*$")) {
					System.out.print("----------");
				}
				s = s.replace("*", "");
				System.out.println(s);
			}
	}

	private static void parseLine(String line) {
		String[] splitLine = line.split("\\|", 0);
		for (String s : splitLine) {
			if(s.isEmpty() || s.matches("^\\s*$")) {
				//System.out.print("----------");
			}
			//System.out.println(s);
		}

		//Student s = new Student(splitLine[1], splitLine[2])
	}

	public static void main(String[] args) {
		parseHeaders(testHeader);
		parseLine(testStudent);
	}

}
